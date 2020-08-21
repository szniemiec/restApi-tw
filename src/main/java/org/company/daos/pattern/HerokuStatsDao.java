package org.company.daos.pattern;

import org.company.database.ConnectionFactory;
import org.company.models.used.Stats;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.OneToOne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HerokuStatsDao  implements StatsDao {
//    private final ConnectionFactory connectionFactory;
    private final HerokuDaoFactory herokuDaoFactory;

    public HerokuStatsDao(HerokuDaoFactory herokuDaoFactory) {
//        this.connectionFactory = new ConnectionFactory();
        this.herokuDaoFactory = herokuDaoFactory;
    }

    @Override
    public boolean create(Stats stats) {
        return false;
    }


    @Override
    public boolean update(Stats stats) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Stats> getAllElements() {
        return null;
    }

    @Override
    public Stats readById(int id) throws SQLException {
        System.out.println("stats by id");
//        Connection con = connectionFactory.connect();
        Connection con = herokuDaoFactory.connect();
        Stats stats = new Stats();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stats WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int statsId = rs.getInt("id");
                int damage = rs.getInt("damage");
                int defense = rs.getInt("defense");
                System.out.format("id = %d, damage = %d, defense = %d",
                        statsId,
                        damage,
                        defense);
                stats.setId(statsId).setDamage(damage).setDefense(defense);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            con.close();
        }
        return stats;
    }
}
