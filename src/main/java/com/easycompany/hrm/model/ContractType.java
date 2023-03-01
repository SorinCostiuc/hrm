package com.easycompany.hrm.model;

public enum ContractType {
    FULLTIME("Full-time"),
    PARTTIME("Part-time");

    private final String type;

    ContractType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
