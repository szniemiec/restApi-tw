package org.company.daos.pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class SessionManager {

    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        this.currentSession = getSessionFactory().openSession();
        return this.currentSession;
    }

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

//    public Session getCurrentSession() {
//        return this.currentSession;
//    }

    public Transaction getCurrentTransaction() {
        return this.currentTransaction;
    }

    public void closeCurrentSession() {
        this.currentSession.close();
    }

    public void closeSessionWithTransaction() {
        this.currentTransaction.commit();
        this.currentSession.close();
    }
}
