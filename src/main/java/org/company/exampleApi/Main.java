package org.company.exampleApi;

import org.company.exampleApi.daos.PokemonDao;
import org.company.exampleApi.daos.TrainerDao;
import org.company.exampleApi.daos.pattern.DaoFactory;
import org.company.exampleApi.daos.pattern.DatabaseType;
import org.company.exampleApi.enums.TypeEnum;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Stats;
import org.company.exampleApi.models.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);
        em.clear(); //clear hibernate cache - force next statements to read data from db
        // ---------------------- THE POKEMONS START

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        Pokemon foundPokemon = em.find(Pokemon.class, 2);
        System.out.println("\n--Found pokemon #1");
        System.out.println("----name----" + foundPokemon.getName());
//        System.out.println("----name----" + foundPokemon.getName() + foundPokemon.getStats().getDamage());

        DaoFactory daoFactory = DaoFactory.getDaoFactory(DatabaseType.HEROKU);

        PokemonDao pokemonDao = daoFactory.getPokemonDao();
        Pokemon pikachu = pokemonDao.readById(1);
        System.out.println(pikachu.getName() + " qweqweqweqweqweqwew");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");

        System.out.println(pikachu.getStats().toString());
        System.out.println("damage = " + pikachu.getStats().getDamage());

        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");

        TrainerDao trainerDao = daoFactory.getTrainerDao();
        Trainer trainer = trainerDao.readById(1);
        System.out.println("name of trainer = " + trainer.getFirstname());


        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");

        Trainer gary = trainerDao.readById(2);
        System.out.println("trainer2 = " + gary.getFirstname());
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");
        System.out.println("ghfjdskghfjdkghfjdkfhjd");

        loadClass(em);

        em.close();
        emf.close();
        // ---------------------- THE POKEMONS END
    }

    public static void populateDb(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        // ---------------------- THE POKEMONS START

        Stats pikachuStats = new Stats(2523233, 42);
        Stats charizardStats = new Stats(220, 100);
        Stats mewtwoStats = new Stats(999, 999);
        Stats dragoniteStats = new Stats(300, 400);
        Stats magikarpStats = new Stats(1, 1);


        Pokemon pikachu = new Pokemon(124, "Pikachu", TypeEnum.ELECTRIC, pikachuStats);
        Pokemon charizard = new Pokemon(6, "Charizard", TypeEnum.DRAGON, charizardStats);
        Pokemon mewtwo = new Pokemon(150, "Mewtwo", TypeEnum.POISON, mewtwoStats);
        Pokemon dragonite = new Pokemon(149, "Dragonite", TypeEnum.DRAGON, dragoniteStats);
        Pokemon magikarp = new Pokemon(139, "Magikarp", TypeEnum.WATER, magikarpStats);

        transaction.begin();
        em.persist(pikachu);
        em.persist(charizard);
        em.persist(mewtwo);
        em.persist(dragonite);
        em.persist(magikarp);
        transaction.commit();

        System.out.println("pokemons saved");

        List<Pokemon> ashPokemonsList = new ArrayList<>();
        ashPokemonsList.add(pikachu);
        ashPokemonsList.add(magikarp);
        Trainer ash = new Trainer("Ash", "Ketchum", 12, 3232, "ash@gmail.com", ashPokemonsList);


        List<Pokemon> garyPokemonsList = new ArrayList<>();
        garyPokemonsList.add(dragonite);
        garyPokemonsList.add(mewtwo);
        Trainer gary = new Trainer("Gary", "Smith", 99, 9999, "gary@gmail.com", garyPokemonsList);

        transaction.begin();
        em.persist(ash);
        em.persist(gary);
        transaction.commit();
        // ---------------------- THE POKEMONS END
    }

    public static void loadClass(EntityManager em) {
        em.clear();
    }
}


// TODO:
//    - pokemon_id in stats tabel should appear!