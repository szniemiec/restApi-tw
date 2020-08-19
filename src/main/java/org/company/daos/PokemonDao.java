package org.company.daos;

import org.company.models.Pokemon;

public interface PokemonDao {

    Pokemon getById(int id);

    Pokemon getAll(String[] args);

    boolean create(Pokemon pokemon);

}
