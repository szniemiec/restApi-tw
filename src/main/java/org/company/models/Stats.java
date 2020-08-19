package org.company.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int damage;

    private int defense;

    @OneToOne(mappedBy = "pokemonJavaEE")
    private PokemonJavaEE pokemonJavaEE;

}
