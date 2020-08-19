package org.company.database;

import java.sql.Connection;

public interface Connectable {
    Connection connect();
    void disconnect();
}
