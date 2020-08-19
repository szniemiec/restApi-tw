package org.company.daos.pattern;

import org.company.daos.PokemonDao;
import org.company.daos.pattern.HerokuDaoFactory;

import static org.company.daos.pattern.DatabaseType.*;

// Abstract class DAO Factory
public abstract class DaoFactory {

    // List of DAO types supported by the factory // TODO: change to ENUMS
    public static final DatabaseType HEROKU = DatabaseType.HEROKU;
    public static final DatabaseType LOCAL = DatabaseType.LOCAL;
    public static final DatabaseType ORACLE = DatabaseType.ORACLE;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract PokemonDao getPokemonDao();

    public static DaoFactory getDaoFactory(DatabaseType databaseType) {
        switch (databaseType) {
            case HEROKU:
                return new HerokuDaoFactory();
            case LOCAL: // TODO
//                return new LocalDaoFactory();
            case ORACLE: // TODO
//                return new OracleDaoFactory();
            case NONE:
                break;
            default:
                throw new NullPointerException();
        }
        return null;
    }
}