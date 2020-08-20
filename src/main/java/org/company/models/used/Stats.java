package org.company.models.used;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Stats")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int damage;
    private int defense;

    public Stats(int id, int damage, int defense) {
        this.id = id;
        this.damage = damage;
        this.defense = defense;
    }

    public Stats(int damage, int defense) {
        this.damage = damage;
        this.defense = defense;
    }
}
