package org.company.handlers;

import org.company.models.Pokemon;
import org.company.services.PokemonService;

public class PokemonHandler {
    private PokemonService pokemonService;

    public PokemonHandler(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public Pokemon testGetByIdPokemon(int id) {
        return null;
//        return this.pokemonService.getPokemonById(id);
    }

    public void testAddPokemon(Pokemon pokemon) {
        this.pokemonService.addPokemon(pokemon);
    }
}
