package org.company.exampleApi.services;

import org.company.exampleApi.daos.StatsDao;
import org.company.exampleApi.models.Stats;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsService {
    private StatsDao statsDao;

    public StatsService(StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    public StatsService() {
    }

    public Stats getStatsById(int id) throws SQLException {
        return this.statsDao.readById(id);
    }

    public void addStats(Stats stats) {
        this.statsDao.create(stats);
    }

    public void deleteStatsById(int id) {
        this.statsDao.delete(id);
    }
    public void updateTrainer(int id) {
//        TODO:
    }

    public List<Stats> getAllStats() {
        return this.statsDao.getAllElements();
    }

    public String getStatsAsJson(Map<String, String> uri) {
        String objectAsJson = "";




        return objectAsJson;
    }

}
