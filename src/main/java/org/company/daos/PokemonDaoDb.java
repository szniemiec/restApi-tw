package org.company.daos;

import org.company.database.ConnectionFactory;
import org.company.enums.TypeEnum;
import org.company.exceptions.TypeIdException;
import org.company.models.Pokemon;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDaoDb implements PokemonDao {

    private ConnectionFactory database = null;

//    public PokemonDao(PostgreSQLJDBC database, JSONService jsonService) {
//        this.database = new PostgreSQLJDBC(jsonService);
//    }

    public PokemonDaoDb(ConnectionFactory connectionFactory) {
        this.database = connectionFactory;
    }

    public List<Pokemon> loadAll() {
        final String SELECT_SQL = "SELECT * FROM pokemons;";
        try {
//            database = new PostgreSQLJDBC();
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(SELECT_SQL);
            List<Pokemon> pokemons = new ArrayList<>();
            while (rs.next()) {
                pokemons.add(new Pokemon().setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setType(typeIdToEnum(rs.getInt("type_id"))));
            }
            database.disconnect();
            return pokemons;
        } catch (SQLException | TypeIdException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return new ArrayList<>();
    }

    public Pokemon get(Serializable id) {
        final String SELECT_SQL = "SELECT * FROM pokemons WHERE id = " + id + ";";
        try {
//            database = new PostgreSQLJDBC();
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(SELECT_SQL);
            if (rs.next()) {
                database.disconnect();
                return new Pokemon().setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setType(typeIdToEnum(rs.getInt("type_id")));
            }
        } catch (SQLException | TypeIdException throwables) {
            throwables.printStackTrace();
        } finally {
            database.disconnect();
        }
        return new Pokemon();
    }

    private TypeEnum typeIdToEnum(int typeId) throws TypeIdException {
        return switch (typeId) {
            case 1 -> TypeEnum.DRAGON;
            case 2 -> TypeEnum.WATER;
            case 3 -> TypeEnum.GRASS;
            case 4 -> TypeEnum.ELECTRIC;
            case 5 -> TypeEnum.POISON;
            default -> throw new TypeIdException("Unexpected value: " + typeId);
        };
    }

    public Pokemon getById(int id) {
        Connection con = database.getConnection();
        Pokemon pokemon = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemons WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pokemonId = rs.getInt("id");
                System.out.println(pokemonId);
                String pokemonName = rs.getString("name");
                System.out.println(pokemonName);
                int pokemonType = rs.getInt("type_id");
                System.out.println(pokemonType);
                pokemon = new Pokemon(pokemonId, pokemonName, pokemonType);
            }
        } catch (Exception e) {
            System.err.println("Error! Reading pokemon by id from DB failed!");
        }finally {
            database.disconnect();
        }
        return pokemon;
    }

    public boolean create(Pokemon pokemon) {
        Connection con = database.getConnection();
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
            database.disconnect();
        }
    }

    @Override
    public Pokemon readById(int id) {
            return null;
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
}
