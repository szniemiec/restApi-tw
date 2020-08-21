package org.company.exampleApi.database;

import java.sql.Connection;

public interface Connectable {
    Connection connect();
    void disconnect();
}
