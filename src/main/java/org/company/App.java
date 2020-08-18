package org.company;

import org.company.daos.PokemonDaoDb;
import org.company.database.PostgreSQLJDBC;
import org.company.enums.TypeEnum;
import org.company.handlers.PokemonHandler;
import org.company.models.Pokemon;
import org.company.services.JSONService;
import org.company.services.PokemonService;

public class App {
    public static void main(String[] args) {
        PokemonHandler pokemonHandler = new PokemonHandler(
                new PokemonService(
                        new PokemonDaoDb(
                                new PostgreSQLJDBC(
                                        new JSONService()))));


        Pokemon pikachu = new Pokemon("Pikxxjhkgdxxxdddachu", TypeEnum.ELECTRIC);
        pokemonHandler.testAddPokemon(pikachu);

        Pokemon pokemonById = pokemonHandler.testGetByIdPokemon(3);
        System.out.println("pokemonById = " + pokemonById.getName());
    }

}
