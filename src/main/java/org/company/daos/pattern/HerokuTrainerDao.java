package org.company.daos.pattern;


import org.company.models.used.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HerokuTrainerDao extends SessionManager implements TrainerDao {
    private HerokuDaoFactory herokuDaoFactory;
    private Session currentSession;
    private Transaction currentTransaction;

    public HerokuTrainerDao(HerokuDaoFactory herokuDaoFactory) {
        this.herokuDaoFactory = herokuDaoFactory;
    }

    @Override
    public boolean create(Trainer trainer) {
        return false;
    }

    @Override
    public Trainer readById(int id) {
        return null;
    }

    @Override
    public boolean update(Trainer trainer) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Trainer> getAllElements() {
        return null;
    }

    @Override
    public void persist(Trainer trainer) {
        getCurrentSession().save(trainer);
    }

    @Override
    public Session openCurrentSession() {
        this.currentSession = getSessionFactory().openSession();
        return this.currentSession;
    }

    @Override
    public Session openSessionWithTransaction() {
        this.currentSession = getSessionFactory().openSession();
        this.currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    @Override
    public Session getCurrentSession() {
        return this.currentSession;
    }

    @Override
    public void closeCurrentSession() {
        this.currentSession.close();
    }

    @Override
    public void closeSessionWithTransaction() {
        this.currentTransaction.commit();
        this.currentSession.close();
    }
}
