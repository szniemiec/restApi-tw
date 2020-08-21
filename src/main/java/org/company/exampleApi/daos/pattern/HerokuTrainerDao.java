package org.company.exampleApi.daos.pattern;


import org.company.exampleApi.daos.PokemonDao;
import org.company.exampleApi.daos.StatsDao;
import org.company.exampleApi.daos.TrainerDao;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Trainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HerokuTrainerDao implements TrainerDao {
    private final HerokuDaoFactory herokuDaoFactory;

    public HerokuTrainerDao(HerokuDaoFactory herokuDaoFactory) {
        this.herokuDaoFactory = herokuDaoFactory;
    }

    @Override
    public boolean create(Trainer trainer) {
        return false;
    }


    @Override
    public boolean update(Trainer trainer) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection con = herokuDaoFactory.connect();

        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM trainer WHERE id = ?;");
            // TODO: should also delete from other tables???
            ps.setInt(1, 2);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error! Deleting from db failed!");
            return false;
        } finally {
            herokuDaoFactory.disconnect();
        }
    }

    @Override
    public List<Trainer> getAllElements() throws SQLException {
        Connection con = herokuDaoFactory.connect();
        Trainer trainer = new Trainer();
        List<Trainer> trainers = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM trainer;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int trainerId = rs.getInt("id");
                String email = rs.getString("email");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int experience = rs.getInt("experience");
                int level = rs.getInt("level");

                List<Pokemon> pokemonsList = getTrainersPokemonByTrainerId(trainerId);
                trainer
                        .setId(trainerId)
                        .setFirstname(firstname)
                        .setLastname(lastname)
                        .setEmail(email)
                        .setExperience(experience)
                        .setLevel(level)
                        .setPokemons(pokemonsList);
                trainers.add(trainer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            con.close();
        }
        return trainers;
    }


    @Override
    public Trainer readById(int id) throws SQLException {
        Connection con = herokuDaoFactory.connect();
        Trainer trainer = new Trainer();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM trainer WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int trainerId = rs.getInt("id");
                String email = rs.getString("email");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int experience = rs.getInt("experience");
                int level = rs.getInt("level");

                List<Pokemon> pokemonsList = getTrainersPokemonByTrainerId(trainerId);
                trainer
                        .setId(trainerId)
                        .setFirstname(firstname)
                        .setLastname(lastname)
                        .setEmail(email)
                        .setExperience(experience)
                        .setLevel(level)
                        .setPokemons(pokemonsList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            con.close();
        }
        return trainer;
    }

    @Override
    public List<Pokemon> getTrainersPokemonByTrainerId(int id) throws SQLException {
        Connection con = herokuDaoFactory.connect();
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM trainer_pokemon WHERE trainer_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            PokemonDao pokemonDao = herokuDaoFactory.getPokemonDao();

            while (rs.next()) {
                int pokemonId = rs.getInt("pokemons_id");
                Pokemon pokemon = pokemonDao.readById(pokemonId);

                System.out.println("found pokemon name = " + pokemon.getName());

                pokemonList.add(pokemon);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            con.close();
        }
        return pokemonList;
    }
}
