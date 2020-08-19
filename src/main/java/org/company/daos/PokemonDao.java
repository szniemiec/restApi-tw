package org.company.daos;

import org.company.models.Pokemon;

public interface PokemonDao {

    Pokemon getById(int id);


    boolean create(Pokemon pokemon);

}
