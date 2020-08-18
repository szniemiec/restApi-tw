package org.company.database;


import org.company.services.JSONService;

import java.sql.*;

public class PostgreSQLJDBC {

    private Connection c;
    private final DbCredentials credentials;

    private String jdbcDriver;
    private String userLogin;
    private String userPassword;
    private String databaseUrl;

    public PostgreSQLJDBC() {
        JSONService jsonService = new JSONService();
        credentials = jsonService.readEnvironment();
        System.out.println("credentials = " + credentials.toString());
        this.c = connect(credentials);
    }

    public PostgreSQLJDBC(JSONService jsonService) {
        System.out.println("Bartosz connector");

        credentials = jsonService.readEnvironment();
        this.databaseUrl = credentials.getDatabaseUrl();
        this.jdbcDriver = credentials.getJdbcDriver();
        this.userLogin = credentials.getLogin();
        this.userPassword = credentials.getPassword();

        System.out.println("credentials = " + credentials.toString());
        this.c = connect(credentials);
    }

    private Connection connect(DbCredentials databaseCredentials) {
        String HOST = databaseCredentials.getHost();
        String PORT = databaseCredentials.getPort();
        String DATABASE = databaseCredentials.getDatabase();
        String LOGIN = databaseCredentials.getLogin();
        String PASSWORD = databaseCredentials.getPassword();
        try {
            Class.forName(this.jdbcDriver);
            this.c = DriverManager.getConnection(this.databaseUrl, this.userLogin, this.userPassword);

//            this.c = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE, LOGIN, PASSWORD);
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

    public void disconnect() throws SQLException {
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