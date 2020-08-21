package org.company.exampleApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.company.exampleApi.daos.PokemonDao;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Stats;

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

}
