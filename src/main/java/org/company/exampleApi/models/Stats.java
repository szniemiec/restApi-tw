package org.company.exampleApi.models;

import lombok.Getter;

import javax.persistence.*;

@Entity(name = "stats")
@Getter
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int damage;
    private int defense;

    @OneToOne
    private Pokemon pokemon;

    public Stats() {
    }

    public Stats(int id, int damage, int defense) {
        this.id = id;
        this.damage = damage;
        this.defense = defense;
    }

    public Stats(int damage, int defense) {
        this.damage = damage;
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public Stats setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public int getDefense() {
        return defense;
    }

    public Stats setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public int getId() {
        return id;
    }

    public Stats setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", damage=" + damage +
                ", defense=" + defense +
                '}';
    }
}
