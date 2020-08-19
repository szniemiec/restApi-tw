package org.company.daos.pattern;

import org.company.daos.PokemonDao;
import org.company.database.DbCredentials;
import org.company.services.JSONService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HerokuDaoFactory extends DaoFactory {
    private final String jdbcDriver;
    private final String userLogin;
    private final String userPassword;
    private final String databaseUrl;
    private Connection connection;

    public HerokuDaoFactory() {
        DbCredentials credentials = new JSONService().readEnvironment();
        this.databaseUrl = credentials.getDatabaseUrl();
        this.jdbcDriver = credentials.getJdbcDriver();
        this.userLogin = credentials.getLogin();
        this.userPassword = credentials.getPassword();

        System.out.println("credentials = " + credentials.toString());
    }

    public PokemonDao getPokemonDao() {
        return new HerokuPokemonDao(this);
        // TODO: should be without parameters, but then it would require to use DataSource library
        // return new HerokuPokemonDao();
    }

    public Connection connect() {
        try {
            Class.forName(this.jdbcDriver);
            this.connection = DriverManager.getConnection(this.databaseUrl, this.userLogin, this.userPassword);
        } catch (SQLException e) {
            System.out.println("Error! Cannot connect with the database.");
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Error! Cannot find JDBC Driver!");
        }
        System.out.println("Opened database successfully");
        return this.connection;
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error! Can't close connection with the database.");
            e.printStackTrace();
        }
    }
}
