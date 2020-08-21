package org.company.exampleApi.database;

public class DbCredentials {

    private final String databaseUrl;
    private final String jdbcDriver;
    private final String host;
    private final String port;
    private final String database;
    private final String login;
    private final String password;

    public DbCredentials(String databaseUrl, String jdbcDriver, String host, String port, String database, String login, String password) {
        this.databaseUrl = databaseUrl;
        this.jdbcDriver = jdbcDriver;
        this.host = host;
        this.port = port;
        this.database = database;
        this.login = login;
        this.password = password;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "DbCredentials{" +
                "databaseUrl='" + databaseUrl + '\'' +
                ", jdbcDriver='" + jdbcDriver + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", database='" + database + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
