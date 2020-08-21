package org.company.daos.pattern;

import org.company.daos.PokemonDao;
import org.company.enums.TypeEnum;
import org.company.models.used.Pokemon;
import org.company.models.used.Stats;
import org.company.models.used.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JPAExample {
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

        loadClass(em);

        em.close();
        emf.close();
        // ---------------------- THE POKEMONS END
    }

    public static void populateDb(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        // ---------------------- THE POKEMONS START

        Stats stats = new Stats(23, 42);
        Pokemon pokemon = new Pokemon(124, "Pikachu", TypeEnum.ELECTRIC, stats);

        transaction.begin();
        em.persist(pokemon);
        transaction.commit();
        System.out.println("pikachu saved");



        Stats magikarpStats = new Stats(1, 1);
        Pokemon magikarp = new Pokemon(139, "Magikarp", TypeEnum.WATER, magikarpStats);
        List<Pokemon> pokemonsList = new ArrayList<>();
        pokemonsList.add(pokemon);
        pokemonsList.add(magikarp);

        Trainer ash = new Trainer("ash", "ketchum", 12, 3232, "ash@gmail.com", pokemonsList);
        transaction.begin();
        em.persist(ash);
        transaction.commit();
        // ---------------------- THE POKEMONS END
    }
    public static void loadClass(EntityManager em){
        em.clear();
    }
}
