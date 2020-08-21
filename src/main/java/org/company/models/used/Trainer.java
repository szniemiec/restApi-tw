package org.company.models.used;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity (name = "trainer")
@Getter
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
            cascade = CascadeType.ALL
    )
    private List<Pokemon> pokemons;

    public Trainer() {
    }

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

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", pokemons=" + pokemons +
                '}';
    }

    public int getId() {
        return id;
    }

    public Trainer setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Trainer setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Trainer setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public Trainer setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public Trainer setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Trainer setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public Trainer setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        return this;
    }

}
