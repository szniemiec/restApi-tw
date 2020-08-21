package org.company.exampleApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.company.exampleApi.daos.Dao;
import org.company.exampleApi.daos.PokemonDao;
import org.company.exampleApi.daos.StatsDao;
import org.company.exampleApi.enums.TypeEnum;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Stats;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonService {
    private PokemonDao pokemonDao;


    public PokemonService(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    public PokemonService() {

    }
    public Pokemon getPokemonById(int id) throws SQLException {
        return pokemonDao.readById(id);
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonDao.create(pokemon); // create is a boolean! TODO: validator of creating!
    }

    public void deletePokemonById(int id) {
        this.pokemonDao.delete(id);
    }

    public void updateTrainer(int id) {
//        TODO:
    }

    public List<Pokemon> getAllPokemons() {
        return this.pokemonDao.getAllElements();
    }

    public String getPokemonAsJson(Map<String, String> uriAsMap) throws JsonProcessingException, SQLException {
        JSONService jsonService = new JSONService();

        if (uriAsMap.containsKey("id")) {
            Pokemon pokemon = getPokemonById(Integer.parseInt(uriAsMap.get("id")));
            return jsonService.convertObjectToJson(pokemon);
        }

        List<Pokemon> pokemons = getAllPokemons();
        return jsonService.convertObjectToJson(pokemons);
    }

    public String addPokemonByRequest(HttpServletRequest httpServletRequest) {
        try {
            int pokemonPokedexNumber = Integer.parseInt(httpServletRequest.getParameter("pokedexnumber"));
            String pokemonName = httpServletRequest.getParameter("name");
            TypeEnum pokemonType = decideTypeString(httpServletRequest.getParameter("type"));

            Stats pokemonStats = new Stats()
                    .setDamage(Integer.parseInt(httpServletRequest.getParameter("damage")))
                    .setDefense(Integer.parseInt(httpServletRequest.getParameter("defense")));

            Pokemon newPokemon = new Pokemon(
                    pokemonPokedexNumber
                    , pokemonName
                    , pokemonType
                    , pokemonStats);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(newPokemon);
            transaction.commit();

            return "Pokemon added";

        } catch (NumberFormatException e) {
            System.err.println("Wrong number");
            e.printStackTrace();
        }
        return "Pokemon not added";
    }

    public String deletePokemonByRequest(HttpServletRequest httpServletRequest) throws SQLException {
        int pokemonIdToDelete = Integer.parseInt(httpServletRequest.getParameter("id"));


        if (isPresent(pokemonIdToDelete)) {
            this.pokemonDao.delete(pokemonIdToDelete);
            return "Pokemon deleted";
        }
        return "Pokemon not deleted";
    }

    private boolean isPresent(int pokemonIdToDelete) throws SQLException {
        return (this.pokemonDao.readById(pokemonIdToDelete) != null);
    }

    public TypeEnum decideTypeString(String type) {
        type = type.toUpperCase();
        try {
            switch (type) {
                case "DRAGON":
                    return TypeEnum.DRAGON;
                case "WATER":
                    return TypeEnum.WATER;
                case "GRASS":
                    return TypeEnum.GRASS;
                case "ELECTRIC":
                    return TypeEnum.ELECTRIC;
                case "POISON":
                    return TypeEnum.POISON;
                default:
                    return TypeEnum.NONE;
            }
        } catch (Exception e) {
            System.out.println("Invalid Pokemon Type! Type will be set to NONE with typeId 6");
            e.printStackTrace();
            return TypeEnum.NONE;
        }
    }
}
