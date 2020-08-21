//package org.company.services;
//
//import org.company.daos.pattern.TrainerDao;
//import org.company.models.used.Trainer;
//
//public class TrainerService {
//    private final TrainerDao trainerDao;
//
//    public TrainerService(TrainerDao trainerDao) {
//        this.trainerDao = trainerDao;
//    }
//
//    public void persist(Trainer entity) {
//        trainerDao.openSessionWithTransaction();
//        trainerDao.persist(entity);
//        trainerDao.closeSessionWithTransaction();
//    }
//}
