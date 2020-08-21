//package org.company;
//
//import org.company.daos.PokemonDaoDb;
//import org.company.database.ConnectionFactory;
//import org.company.enums.TypeEnum;
//import org.company.handlers.PokemonHandler;
//import org.company.models.PokemonWithoutStats;
//import org.company.services.JSONService;
//import org.company.services.PokemonService;
//
//public class App {
//    public static void main(String[] args) {
//        PokemonHandler pokemonHandler = new PokemonHandler(
//                new PokemonService(
//                        new PokemonDaoDb(
//                                new ConnectionFactory(
//                                        new JSONService()))));
//
//
//        PokemonWithoutStats pikachu = new PokemonWithoutStats("Pikxxjhkgdxxxdddachu", TypeEnum.ELECTRIC);
//        pokemonHandler.testAddPokemon(pikachu);
//
//        PokemonWithoutStats pokemonById = pokemonHandler.testGetByIdPokemon(3);
//        System.out.println("pokemonById = " + pokemonById.getName());
//    }
//
//}
//
//
//// WHICH Handler better?
//// Lombok does not work
