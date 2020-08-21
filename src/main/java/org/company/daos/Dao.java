package org.company.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    boolean create(T t);

    T readById(int id) throws SQLException;

    boolean update(T t);

    boolean delete(int id);

    List<T> getAllElements();
}
