package com.easycompany.hrm.model;

public enum Status {
    APPLICANT("Applicant"),
    EMPLOYEE("Employee"),
    FIRED("Fired");
    // TODO: 25.02.2023 if there's time: suspended si metode pentru suspendare si reincepere contract, trebuie modificat si contract cu suspended time
    private final String status;

    public String getStatus() {
        return status;
    }

    Status (String status){
        this.status = status;
    }


}
