package org.company.models;

import javax.persistence.*;
import java.util.List;

public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne
    private SpecificInformation specificInformation;

    @OneToMany(mappedBy = "pokemon")
    private List<Integer> pokemons; // list of pokemon Id's

}
