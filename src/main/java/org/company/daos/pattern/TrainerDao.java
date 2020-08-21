package org.company.daos.pattern;

import org.company.daos.Dao;
import org.company.models.used.Pokemon;
import org.company.models.used.Trainer;

import java.sql.SQLException;
import java.util.List;

public interface TrainerDao extends Dao<Trainer> {

    List<Pokemon> getTrainersPokemonByTrainerId(int id) throws SQLException;

}
