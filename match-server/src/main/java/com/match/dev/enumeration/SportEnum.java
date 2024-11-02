package com.match.dev.enumeration;

public enum SportEnum {

    FOOTBALL("Football"),
    BASKETBALL("Basketball");

    private final String sport;

    SportEnum(String sport) {
        this.sport = sport;
    }

    public String getSport() {
        return sport;
    }
}
