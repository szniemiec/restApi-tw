package org.company.models;

import org.company.enums.TypeEnum;

public class Pokemon {

    private int id;
    private String name;
    private TypeEnum type;

    public Pokemon() {
    }

    public Pokemon(String name, TypeEnum type) {
        this.name = name;
        this.type = type;
    }

    public Pokemon(int id, String name, TypeEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Pokemon setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pokemon setName(String name) {
        this.name = name;
        return this;
    }

    public TypeEnum getType() {
        return type;
    }

    public Pokemon setType(TypeEnum type) {
        this.type = type;
        return this;
    }
}
