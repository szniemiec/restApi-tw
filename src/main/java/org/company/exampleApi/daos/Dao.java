package org.company.exampleApi.daos;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    boolean create(T t);

    T readById(int id) throws SQLException;

    boolean update(T t);

    boolean delete(int id);

    List<T> getAllElements();
}
