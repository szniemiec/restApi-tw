package org.company.exampleApi.models;

import lombok.Getter;
import org.company.exampleApi.enums.TypeEnum;

import javax.persistence.*;


@Entity(name = "pokemon")
@Getter
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private int pokedexNumber;

    private String name;

    @Enumerated
    private TypeEnum typeEnum;

    @OneToOne(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private Stats stats;

    public Pokemon() {
    }

    public Pokemon(int id, int pokedexNumber, String name, TypeEnum typeEnum, Stats stats) {
        this.id = id;
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.typeEnum = typeEnum;
        this.stats = stats;
    }

    public Pokemon(int pokedexNumber, String name, TypeEnum typeEnum, Stats stats) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.typeEnum = typeEnum;
        this.stats = stats;
    }

    public int getId() {
        return id;
    }

    public Pokemon setId(int id) {
        this.id = id;
        return this;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public Pokemon setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pokemon setName(String name) {
        this.name = name;
        return this;
    }

    public TypeEnum getTypeEnum() {
        return typeEnum;
    }

    public Pokemon setTypeEnum(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
        return this;
    }

    public Stats getStats() {
        return stats;
    }

    public Pokemon setStats(Stats stats) {
        this.stats = stats;
        return this;
    }

    private TypeEnum decideTypeByInt(int typeInt) {
        try {
            switch (typeInt) {
                case 0:
                    return TypeEnum.DRAGON;
                case 1:
                    return TypeEnum.WATER;
                case 2:
                    return TypeEnum.GRASS;
                case 3:
                    return TypeEnum.ELECTRIC;
                case 4:
                    return TypeEnum.POISON;
                default:
                    return TypeEnum.NONE;
            }
        } catch (Exception e) {
            System.out.println("Invalid Pokemon Type! Type will be set to NONE with typeId 6");
            e.printStackTrace();
            return TypeEnum.NONE;
        }
    }

    public TypeEnum decideTypeString(String type) {
        type = type.toUpperCase();
        try {
            switch (type) {
                case "DRAGON":
                    return TypeEnum.DRAGON;
                case "WATER":
                    return TypeEnum.WATER;
                case "GRASS":
                    return TypeEnum.GRASS;
                case "ELECTRIC":
                    return TypeEnum.ELECTRIC;
                case "POISON":
                    return TypeEnum.POISON;
                default:
                    return TypeEnum.NONE;
            }
        } catch (Exception e) {
            System.out.println("Invalid Pokemon Type! Type will be set to NONE with typeId 6");
            e.printStackTrace();
            return TypeEnum.NONE;
        }
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", pokedexNumber=" + pokedexNumber +
                ", name='" + name + '\'' +
                ", typeEnum=" + typeEnum +
                ", stats=" + stats +
                '}';
    }
}
