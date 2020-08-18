package org.company.enums;

public enum TypeEnum {

    DRAGON(1),
    WATER(2),
    GRASS(3),
    ELECTRIC(4),
    POISON(5);

    private final int typeId;

    TypeEnum(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
