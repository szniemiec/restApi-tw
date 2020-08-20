package org.company.models.used;

import org.company.enums.TypeEnum;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

public class TestMain2 {
    public void fillDatabase() {
        Stats pikachuStats = new Stats(11, 22);
        Stats charizardStats = new Stats(220, 100);
        Stats mewtwoStats = new Stats(999, 999);
        Stats magikarpStats = new Stats(1, 1);
        Stats dragoniteStats = new Stats(300, 400);
        statsService.persist(pikachuStats);
        statsService.persist(charizardStats);
        statsService.persist(mewtwoStats);
        statsService.persist(magikarpStats);
        statsService.persist(dragoniteStats);



        PokemonJavaEE pikachu = new PokemonJavaEE(25, "Pikachu", TypeEnum.ELECTRIC, pikachuStats);
        PokemonJavaEE charizard = new PokemonJavaEE(6, "Charizard", TypeEnum.DRAGON, charizardStats);
        PokemonJavaEE mewtwo = new PokemonJavaEE(150, "Mewtwo", TypeEnum.POISON, mewtwoStats);
        PokemonJavaEE magikarp = new PokemonJavaEE(129, "Magikarp", TypeEnum.WATER, magikarpStats);
        PokemonJavaEE dragonite = new PokemonJavaEE(149, "Dragonite", TypeEnum.DRAGON, dragoniteStats);



        List<PokemonJavaEE> ashPokemons = new ArrayList<>();
        ashPokemons.add(pikachu);
        ashPokemons.add(charizard);
        ashPokemons.add(magikarp);
        Trainer ashKetchum = new Trainer("Ash", "Ketchu", 3, 4231, "ash@ketchum.com", ashPokemons);

        List<PokemonJavaEE> garyPokemons = new ArrayList<>();
        garyPokemons.add(mewtwo);
        garyPokemons.add(dragonite);
        Trainer gary = new Trainer("Gary", "Asshole", 10, 84512, "gary@power.com", garyPokemons);





    }
}
