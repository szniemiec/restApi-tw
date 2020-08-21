package org.company.database;


import org.company.services.JSONService;

import java.sql.*;

public class ConnectionFactory implements Connectable {

    private Connection c;

    private final String jdbcDriver;
    private final String userLogin;
    private final String userPassword;
    private final String databaseUrl;

    public ConnectionFactory() {
        DbCredentials credentials = new JSONService().readEnvironment();
        this.databaseUrl = credentials.getDatabaseUrl();
        this.jdbcDriver = credentials.getJdbcDriver();
        this.userLogin = credentials.getLogin();
        this.userPassword = credentials.getPassword();

        System.out.println("credentials = " + credentials.toString());
    }

    public ConnectionFactory(JSONService jsonService) {
        System.out.println("Bartosz connector");

        DbCredentials credentials = jsonService.readEnvironment();
        this.databaseUrl = credentials.getDatabaseUrl();
        this.jdbcDriver = credentials.getJdbcDriver();
        this.userLogin = credentials.getLogin();
        this.userPassword = credentials.getPassword();

        System.out.println("credentials = " + credentials.toString());
    }

    public Connection connect() {
        try {
            Class.forName(this.jdbcDriver);
            this.c = DriverManager.getConnection(this.databaseUrl, this.userLogin, this.userPassword);
//            this.c = DriverManager.getConnection(this.databaseUrl, this.userLogin, this.userPassword);
        } catch (SQLException e) {
            System.out.println("Error! Cannot connect with the database.");
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Error! Cannot find JDBC Driver!");
        }
        System.out.println("Opened database successfully");
        return this.c;
    }

    public void disconnect() {
        try {
            this.c.close();
        } catch (SQLException e) {
            System.out.println("Error! Can't close connection with the database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.c;
    }



    // ---------------------- executeQuery method
    private Statement stmt = null;

    private ResultSet rs = null;

    public ResultSet executeQuery(String sql) throws SQLException {
        this.c = this.getConnection();
        try {
            this.stmt = this.c.createStatement();
            this.rs = this.stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error! Cannot execute query!");
            e.printStackTrace();
        }
        return this.rs;
    }

}