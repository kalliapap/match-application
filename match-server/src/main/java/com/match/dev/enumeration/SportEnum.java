package com.match.dev.enumeration;

import java.util.Objects;

public enum SportEnum {

    Football(1),
    Basketball(2);

    private final Integer dbValue;

    SportEnum(Integer dbValue) {
        this.dbValue = dbValue;
    }

    public Integer getDbValue() {
        return dbValue;
    }

    public static SportEnum fromDbValue(Integer dbValue) {
        for (SportEnum sport : values()) {
            if (Objects.equals(sport.getDbValue(), dbValue)) {
                return sport;
            }
        }
        throw new IllegalArgumentException("Unknown dbValue: " + dbValue);
    }
}