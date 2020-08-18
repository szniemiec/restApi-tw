package org.company;

import org.company.daos.PokemonDao;
import org.company.services.JSONService;

public class App {
    public static void main(String[] args) {
        PokemonDao pokemonDao = new PokemonDao(new JSONService());
    }
}
