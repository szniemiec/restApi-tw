package org.company.exampleApi.services;

import org.company.exampleApi.daos.TrainerDao;
import org.company.exampleApi.models.Stats;
import org.company.exampleApi.models.Trainer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainerService {
    private TrainerDao trainerDao;

    public TrainerService(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public TrainerService() {
    }

    public Trainer getTrainerById(int id) throws SQLException {
        return this.trainerDao.readById(id);
    }

    public void addTrainer(Trainer trainer) {
        this.trainerDao.create(trainer);
    }

    public void deleteTrainerById(int id) {
        this.trainerDao.delete(id);
    }
    public void updateTrainer(int id) {
//        TODO:
    }

    public List<Trainer> getAllTrainers() {
        return this.trainerDao.getAllElements();
    }

    public String getTrainerAsJson(Map<String, String> uri) {
        String objectAsJson = "";




        return objectAsJson;

    }

}
