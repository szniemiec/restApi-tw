package org.company.exampleApi.daos.pattern;

import org.company.exampleApi.daos.PokemonDao;
import org.company.exampleApi.daos.StatsDao;
import org.company.exampleApi.enums.TypeEnum;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HerokuPokemonDao implements PokemonDao {
//    private final ConnectionFactory connectionFactory;
    private final HerokuDaoFactory herokuDaoFactory;

    public HerokuPokemonDao(HerokuDaoFactory herokuDaoFactory) {
//        this.connectionFactory = new ConnectionFactory();
        this.herokuDaoFactory = herokuDaoFactory;
    }

    @Override
    public Pokemon readById(int id) throws SQLException {
//        Connection con = connectionFactory.connect();
        Connection con = herokuDaoFactory.connect();
        Pokemon pokemon = new Pokemon();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pokemon WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pokemonId = rs.getInt("id");
                System.out.println("id = " + pokemonId);
                String pokemonName = rs.getString("name");
                System.out.println("name = " + pokemonName);
                int pokedexNumber = rs.getInt("pokedexnumber");
                System.out.println("pokedexNumber = " + pokedexNumber);
                TypeEnum typeEnum = decideTypeByInt(rs.getInt("typeenum"));
                System.out.println("type = " + typeEnum);

//                int statsId = rs.getInt("stats_id");

                StatsDao statsDao = this.herokuDaoFactory.getStatsDao();
//                HerokuStatsDao statsDao = new HerokuStatsDao(this.herokuDaoFactory);
                Stats stats = statsDao.readById(pokemonId);
                System.out.println("stats = " + stats.toString());

                pokemon = new Pokemon(pokemonId, pokedexNumber, pokemonName, typeEnum, stats);
            }
        } catch (Exception e) {
            System.err.println("Error! Reading stats by id from DB failed!");
        } finally {
            con.close();
        }
        return pokemon;
    }

    @Override
    public boolean update(Pokemon pokemon) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        StatsDao statsDao = herokuDaoFactory.getStatsDao();
        statsDao.delete(id);










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
        } finally {
            herokuDaoFactory.disconnect();
        }
    }

    private TypeEnum decideTypeByInt(int typeInt) {
        try {
            switch (typeInt) {
                case 0:
                    return TypeEnum.DRAGON;
                case 1:
                    return TypeEnum.WATER;
                case 2:
                    return TypeEnum.GRASS;
                case 3:
                    return TypeEnum.ELECTRIC;
                case 4:
                    return TypeEnum.POISON;
                default:
                    return TypeEnum.NONE;
            }
        } catch (Exception e) {
            System.out.println("Invalid Pokemon Type! Type will be set to NONE with typeId 6");
            e.printStackTrace();
            return TypeEnum.NONE;
        }
    }

}
