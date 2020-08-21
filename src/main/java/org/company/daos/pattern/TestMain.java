//package org.company.daos.pattern;
//
//import org.company.daos.PokemonDao;
//import org.company.daos.PokemonDaoDb;
//import org.company.database.ConnectionFactory;
//import org.company.enums.TypeEnum;
//import org.company.handlers.PokemonHandler;
//import org.company.models.PokemonWithoutStats;
//import org.company.models.used.Pokemon;
//import org.company.services.JSONService;
//import org.company.services.PokemonService;
//
//// Install Lombok plugin:
////https://projectlombok.org/setup/intellij
//
//public class TestMain {
//    public static void main(String[] args) {
//        DaoFactory herokuDatabaseFactory = DaoFactory.getDaoFactory(DatabaseType.HEROKU);
//        PokemonDao pokemonDao = herokuDatabaseFactory.getPokemonDao();
//
//        //        Pokemon pokemon = pokemonDao.readById(5);
////        System.out.println("Factory dao pokemon = " + pokemon.getName());
//
//        PokemonHandler pokemonHandler2 = new PokemonHandler(new PokemonService(herokuDatabaseFactory.getPokemonDao()));
//
//
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
//
//        Pokemon pokemon = new Pokemon();
//
//    }
//}
