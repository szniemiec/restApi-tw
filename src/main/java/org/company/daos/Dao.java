package org.company.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public interface Dao<T> {

    boolean create(T t);

    T readById(int id);

    boolean update(T t);

    boolean delete(int id);

    List<T> getAllElements();

    void persist(T t);

    Session openCurrentSession();

    Session openSessionWithTransaction();

    static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    Session getCurrentSession();

    void closeCurrentSession();

    void closeSessionWithTransaction();


}
