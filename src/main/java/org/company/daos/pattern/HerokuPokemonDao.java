package org.company.daos.pattern;

import org.company.daos.Dao;
import org.company.daos.PokemonDao;
import org.company.models.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HerokuPokemonDao implements PokemonDao  {
    private final HerokuDaoFactory herokuDaoFactory;

    public HerokuPokemonDao(HerokuDaoFactory herokuDaoFactory) {
        this.herokuDaoFactory = herokuDaoFactory;
    }

    @Override
    public Pokemon readById(int id) {
        Connection con = herokuDaoFactory.connect();
        Pokemon pokemon = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemons WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pokemonId = rs.getInt("id");
                System.out.println("id = " + pokemonId);
                String pokemonName = rs.getString("name");
                System.out.println("name = " + pokemonName);
                int pokemonType = rs.getInt("type_id");
                System.out.println("type id = " + pokemonType);
                pokemon = new Pokemon(pokemonId, pokemonName, pokemonType);
            }
        } catch (Exception e) {
            System.err.println("Error! Reading pokemon by id from DB failed!");
        }finally {
            herokuDaoFactory.disconnect();
        }
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
            ps.setInt(2, pokemon.getType().getTypeId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error! Adding to DB failed!");
            return false;
        }finally {
            herokuDaoFactory.disconnect();
        }
    }
}
