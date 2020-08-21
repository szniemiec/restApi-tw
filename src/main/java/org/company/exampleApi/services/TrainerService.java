package org.company.exampleApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.company.exampleApi.daos.TrainerDao;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Trainer> getAllTrainers() throws SQLException {
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

    public String deleteTrainerByRequest(HttpServletRequest httpServletRequest) throws SQLException {
        int trainerIdToDelete = Integer.parseInt(httpServletRequest.getParameter("id"));


        if (isPresent(trainerIdToDelete)) {
            this.trainerDao.delete(trainerIdToDelete);
            return "Pokemon deleted";
        }
        return "Pokemon not deleted";
    }

    private boolean isPresent(int trainerIdToDelete) throws SQLException {
        return (this.trainerDao.readById(trainerIdToDelete) != null);
    }

    public String addTrainerByRequest(HttpServletRequest httpServletRequest) {

        try {
            Trainer trainer = new Trainer();
            String firstname = httpServletRequest.getParameter("firstname");
            String lastname = httpServletRequest.getParameter("lastname");
            int level = Integer.parseInt(httpServletRequest.getParameter("level"));
            int experience = Integer.parseInt(httpServletRequest.getParameter("experience"));
            String email = httpServletRequest.getParameter("email");
            List<Pokemon> pokemons = new ArrayList<>();

            trainer
                    .setFirstname(firstname)
                    .setLastname(lastname)
                    .setLevel(level)
                    .setExperience(experience)
                    .setEmail(email)
                    .setPokemons(pokemons);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(trainer);
            transaction.commit();

            return "Trainer added";
        } catch (Exception e) {
            System.err.println("Wrong number");
        }
        return "Trainer not added";
    }
}
