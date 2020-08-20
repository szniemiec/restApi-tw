package org.company.services;

import org.company.daos.pattern.StatsDao;
import org.company.models.used.Stats;

public class StatsService {
    private final StatsDao statsDao;

    public StatsService(StatsDao statsDao) {
        this.statsDao = statsDao;
    }
    public void persist(Stats entity) {
        statsDao.openSessionWithTransaction();
        statsDao.persist(entity);
        statsDao.closeSessionWithTransaction();
    }
}
