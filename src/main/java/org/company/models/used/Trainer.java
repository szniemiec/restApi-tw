package org.company.models.used;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity (name = "Trainer")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;
    private int level;
    private int experience;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<Pokemon> pokemons;

    public Trainer(String firstname, String lastname, int level, int experience, String email, List<Pokemon> pokemons) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.level = level;
        this.experience = experience;
        this.email = email;
        this.pokemons = pokemons;
    }

    public Trainer(int id, String firstname, String lastname, int level, int experience, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.level = level;
        this.experience = experience;
        this.email = email;
    }

    //    @OneToOne
//    private SpecificInformation specificInformation;

//    @ManyToMany(mappedBy = "pokemon", cascade = CascadeType.PERSIST)
//    private List<Pokemon> pokemons; // list of pokemon Id's

}
