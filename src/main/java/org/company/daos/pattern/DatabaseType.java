package org.company.daos.pattern;

public enum DatabaseType {
    HEROKU(1),
    LOCAL(2),
    ORACLE(3),
    NONE(4); // DEFAULT TYPE

    private final int typeId;

    DatabaseType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
