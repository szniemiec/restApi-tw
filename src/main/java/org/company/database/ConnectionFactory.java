package org.company.database;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection connect();
    void disconnect();
}
