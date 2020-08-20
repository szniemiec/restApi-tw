package org.company.daos.pattern;

import org.company.daos.PokemonDao;
import org.company.models.PokemonWithoutStats;
import org.company.models.used.Pokemon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HerokuPokemonDao extends SessionManager implements PokemonDao   {
    private final HerokuDaoFactory herokuDaoFactory;
    private Session currentSession;
    private Transaction currentTransaction;

    public HerokuPokemonDao(HerokuDaoFactory herokuDaoFactory) {
        this.herokuDaoFactory = herokuDaoFactory;
    }

    @Override
    public Pokemon readById(int id) {
        Connection con = herokuDaoFactory.connect();
        Pokemon pokemon = null;
//        try {
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemons WHERE id = ?");
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int pokemonId = rs.getInt("id");
//                System.out.println("id = " + pokemonId);
//                String pokemonName = rs.getString("name");
//                System.out.println("name = " + pokemonName);
//                int pokemonType = rs.getInt("type_id");
//                System.out.println("type id = " + pokemonType);
//                pokemon = new PokemonWithoutStats(pokemonId, pokemonName, pokemonType);
//            }
//        } catch (Exception e) {
//            System.err.println("Error! Reading pokemon by id from DB failed!");
//        }finally {
//            herokuDaoFactory.disconnect();
//        }
        return pokemon;
    }

    @Override
    public boolean update(Pokemon pokemon) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Pokemon> getAllElements() {
        return null;
    }



    @Override
    public boolean create(Pokemon pokemon) {
        Connection con = herokuDaoFactory.connect();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO pokemons(name, type_id) VALUES (?, ?);");
            ps.setString(1, pokemon.getName());
            ps.setInt(2, pokemon.getTypeEnum().getTypeId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error! Adding to DB failed!");
            return false;
        }finally {
            herokuDaoFactory.disconnect();
        }
    }

    public void persist(Pokemon pokemon) {
        getCurrentSession().save(pokemon);
    }

    @Override
    public Session openCurrentSession() {
        this.currentSession = getSessionFactory().openSession();
        return this.currentSession;
    }

    @Override
    public Session openSessionWithTransaction() {
        this.currentSession = getSessionFactory().openSession();
        this.currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    @Override
    public Session getCurrentSession() {
        return this.currentSession;
    }

    @Override
    public void closeCurrentSession() {
        this.currentSession.close();
    }

    @Override
    public void closeSessionWithTransaction() {
        this.currentTransaction.commit();
        this.currentSession.close();
    }
}
