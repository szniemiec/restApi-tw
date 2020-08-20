package org.company.handlers;

import org.company.models.PokemonWithoutStats;
import org.company.services.PokemonService;

public class PokemonHandler {
    private PokemonService pokemonService;

    public PokemonHandler(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public PokemonWithoutStats testGetByIdPokemon(int id) {
        return null;
//        return this.pokemonService.getPokemonById(id);
    }

    public void testAddPokemon(PokemonWithoutStats pokemon) {
        this.pokemonService.addPokemon(pokemon);
    }
}
