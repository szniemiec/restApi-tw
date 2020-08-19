package org.company.daos.pattern;

import org.company.daos.PokemonDao;
import org.company.daos.PokemonDaoDb;
import org.company.database.ConnectionFactory;
import org.company.enums.TypeEnum;
import org.company.handlers.PokemonHandler;
import org.company.models.Pokemon;
import org.company.models.PokemonJavaEE;
import org.company.services.JSONService;
import org.company.services.PokemonService;

public class TestMain {
    public static void main(String[] args) {
        DaoFactory herokuDatabaseFactory = DaoFactory.getDaoFactory(DaoFactory.HEROKU);
        PokemonDao pokemonDao = herokuDatabaseFactory.getPokemonDao();
        Pokemon pokemon = pokemonDao.readById(5);
        System.out.println(pokemon.getName());

        PokemonHandler pokemonHandler2 = new PokemonHandler(
                new PokemonService(herokuDatabaseFactory.getPokemonDao()));


        PokemonHandler pokemonHandler = new PokemonHandler(
                new PokemonService(
                        new PokemonDaoDb(
                                new ConnectionFactory(
                                        new JSONService()))));


        Pokemon pikachu = new Pokemon("Pikxxjhkgdxxxdddachu", TypeEnum.ELECTRIC);
        pokemonHandler.testAddPokemon(pikachu);


        PokemonJavaEE pokemonJavaEE = new PokemonJavaEE();

    }
}
