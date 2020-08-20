package org.company.models.used;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.company.enums.TypeEnum;

import javax.persistence.*;


@Entity(name = "Pokemon")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class PokemonJavaEE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private int pokedexNumber;

    private String name;

    @Enumerated
    private TypeEnum typeEnum;

    @OneToOne
    private Stats stats;

    public PokemonJavaEE(int pokedexNumber, String name, TypeEnum typeEnum, Stats stats) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.typeEnum = typeEnum;
        this.stats = stats;
    }

    public PokemonJavaEE(int pokedexNumber, String name, int typeId, Stats stats) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.typeEnum = decideTypeByInt(typeId);
        this.stats = stats;
    }

    private TypeEnum decideTypeByInt(int typeInt) {
        try {
            switch (typeInt) {
                case 1:
                    return TypeEnum.DRAGON;
                case 2:
                    return TypeEnum.WATER;
                case 3:
                    return TypeEnum.GRASS;
                case 4:
                    return TypeEnum.ELECTRIC;
                case 5:
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

}
