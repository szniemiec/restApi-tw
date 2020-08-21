//package org.company.services;
//
//import org.company.daos.PokemonDao;
//import org.company.daos.PokemonDaoDb;
//import org.company.models.PokemonWithoutStats;
//import org.company.models.used.Pokemon;
//
//public class PokemonService {
//    private PokemonDao pokemonDao;
//
//
//    public PokemonService(PokemonDao pokemonDao) {
//        this.pokemonDao = pokemonDao;
//    }
//
//    public PokemonService(PokemonDaoDb pokemonDaoDb) {
//        this.pokemonDao = pokemonDaoDb;
//    }
//
//    public PokemonService() {
//
//    }
////    public Pokemon getPokemonById(int id) {
////        return pokemonDao.getById(id);
////    }
//
//    public void addPokemon(PokemonWithoutStats pokemon) {
////        pokemonDao.create(pokemon); // create is a boolean! TODO: validator of creating!
//    }
//
//    public void persist(Pokemon entity) {
//        pokemonDao.openSessionWithTransaction();
//        pokemonDao.persist(entity);
//        pokemonDao.closeSessionWithTransaction();
//    }
//}
