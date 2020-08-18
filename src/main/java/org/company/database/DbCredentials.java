package org.company.database;

public class DbCredentials {

    private final String host;
    private final String port;
    private final String database;
    private final String login;
    private final String password;

    public DbCredentials(String host, String port, String database, String login, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.login = login;
        this.password = password;
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
}
