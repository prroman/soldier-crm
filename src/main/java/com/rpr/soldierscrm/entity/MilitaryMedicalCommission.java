package com.rpr.soldierscrm.entity;

public enum MilitaryMedicalCommission {
    REQUIRE("Потребує"),
    AWAITING("Очікує"),
    INPROGRESS("Проходить"),
    FINISHED("Пройшов");

    private final String displayName;

    MilitaryMedicalCommission(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
