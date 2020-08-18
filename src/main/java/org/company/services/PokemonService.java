package org.company.services;

import org.company.daos.PokemonDao;
import org.company.daos.PokemonDaoDb;
import org.company.models.Pokemon;

public class PokemonService {
    private PokemonDao pokemonDao;

    public PokemonService(PokemonDaoDb pokemonDaoDb) {
        this.pokemonDao = pokemonDaoDb;
    }

    public Pokemon getPokemonById(int id) {
        return pokemonDao.getById(id);
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonDao.create(pokemon); // create is a boolean! TODO: validator of creating!
    }
}
