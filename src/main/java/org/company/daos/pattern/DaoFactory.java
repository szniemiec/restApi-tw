package org.company.daos.pattern;

import org.company.daos.PokemonDao;
import org.company.daos.pattern.HerokuDaoFactory;

// Abstract class DAO Factory
public abstract class DaoFactory {

    // List of DAO types supported by the factory
    public static final int HEROKU = 1;
    public static final int LOCAL = 2;
    public static final int ORACLE = 3;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract PokemonDao getPokemonDao();

    public static DaoFactory getDaoFactory(int whichFactory) {
        switch (whichFactory) {
            case HEROKU:
                return new HerokuDaoFactory();
            case LOCAL: // TODO
//                return new LocalDaoFactory();
            case ORACLE: // TODO
//                return new OracleDaoFactory();
            default:
                return null;
        }
    }
}