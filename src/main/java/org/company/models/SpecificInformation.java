package org.company.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class SpecificInformation {
    @Id
    @GeneratedValue
    private int id;

    private int level;
    private int experience;
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(mappedBy = "trainer")
    private Trainer trainer;

}

