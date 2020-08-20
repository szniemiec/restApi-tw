package org.company.models;

import org.company.enums.TypeEnum;

public class PokemonWithoutStats {

    private int id;
    private String name;
    private TypeEnum type;

    public PokemonWithoutStats() {
    }

    public PokemonWithoutStats(String name, TypeEnum typeEnum) {
        this.name = name;
        this.type = typeEnum;
    }

    public PokemonWithoutStats(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = decideTypeString(type);
    }

    public PokemonWithoutStats(int id, String name, int typeInt) {
        this.id = id;
        this.name = name;
        this.type = decideTypeInt(typeInt);
        System.out.println("type enum = " + this.type);
    }

    private TypeEnum decideTypeInt(int typeInt) {
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

    public int getId() {
        return id;
    }

    public PokemonWithoutStats setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PokemonWithoutStats setName(String name) {
        this.name = name;
        return this;
    }

    public TypeEnum getType() {
        return type;
    }

    public String getTypeInString()
    {
        return type.toString();
    }

    public PokemonWithoutStats setType(TypeEnum type) {
        this.type = type;
        return this;
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
