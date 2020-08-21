package org.company.exampleApi.daos;

import org.company.exampleApi.daos.Dao;
import org.company.exampleApi.models.Pokemon;
import org.company.exampleApi.models.Trainer;

import java.sql.SQLException;
import java.util.List;

public interface TrainerDao extends Dao<Trainer> {

    List<Pokemon> getTrainersPokemonByTrainerId(int id) throws SQLException;

}
