package org.company.models.used;

import org.company.daos.PokemonDao;
import org.company.daos.pattern.DaoFactory;
import org.company.daos.pattern.DatabaseType;
import org.company.daos.pattern.StatsDao;
import org.company.daos.pattern.TrainerDao;
import org.company.enums.TypeEnum;
import org.company.services.PokemonService;
import org.company.services.StatsService;
import org.company.services.TrainerService;

import java.util.ArrayList;
import java.util.List;

public class TestMain2 {
    public void fillDatabase() {
        DaoFactory herokuDaoFactory = DaoFactory.getDaoFactory(DatabaseType.HEROKU);

        StatsDao statsDao = herokuDaoFactory.getStatsDao();
        StatsService statsService = new StatsService(statsDao);
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


        PokemonDao pokemonDao = herokuDaoFactory.getPokemonDao();
        PokemonService pokemonService = new PokemonService(pokemonDao);
        Pokemon pikachu = new Pokemon(25, "Pikachu", TypeEnum.ELECTRIC, pikachuStats);
        Pokemon charizard = new Pokemon(6, "Charizard", TypeEnum.DRAGON, charizardStats);
        Pokemon mewtwo = new Pokemon(150, "Mewtwo", TypeEnum.POISON, mewtwoStats);
        Pokemon magikarp = new Pokemon(129, "Magikarp", TypeEnum.WATER, magikarpStats);
        Pokemon dragonite = new Pokemon(149, "Dragonite", TypeEnum.DRAGON, dragoniteStats);
        pokemonService.persist(pikachu);
        pokemonService.persist(charizard);
        pokemonService.persist(mewtwo);
        pokemonService.persist(magikarp);
        pokemonService.persist(dragonite);


        TrainerDao trainerDao = herokuDaoFactory.getTrainerDao();
        TrainerService trainerService = new TrainerService(trainerDao);
        List<Pokemon> ashPokemons = new ArrayList<>();
        ashPokemons.add(pikachu);
        ashPokemons.add(charizard);
        ashPokemons.add(magikarp);
        Trainer ashKetchum = new Trainer("Ash", "Ketchu", 3, 4231, "ash@ketchum.com", ashPokemons);
//        trainerService.persist(ashKetchum);


        List<Pokemon> garyPokemons = new ArrayList<>();
        garyPokemons.add(mewtwo);
        garyPokemons.add(dragonite);
        Trainer gary = new Trainer("Gary", "Asshole", 10, 84512, "gary@power.com", garyPokemons);
//        trainerService.persist(gary);





    }
}
