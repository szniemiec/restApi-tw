package org.company.exampleApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.company.exampleApi.daos.TrainerDao;
import org.company.exampleApi.models.Pokemon;
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

    public String getTrainerAsJson(Map<String, String> mapOfUri) throws SQLException, JsonProcessingException {
        JSONService jsonService = new JSONService();

        if (mapOfUri.containsKey("id")) {
            Trainer trainer = getTrainerById(Integer.parseInt(mapOfUri.get("id")));
            return jsonService.convertObjectToJson(trainer);
        }

        List<Trainer> trainers = getAllTrainers();
        return jsonService.convertObjectToJson(trainers);

    }

}
