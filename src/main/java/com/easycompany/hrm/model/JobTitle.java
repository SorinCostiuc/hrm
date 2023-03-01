package com.easycompany.hrm.model;

public enum JobTitle {
    DEVELOPER("Developer", 18.0d),
    TESTER("Tester", 15.0d),
    TEAMLEADER("Team leader", 22.0d);

    private final String title;
    private Double salaryPerHour;


    JobTitle(String title, Double salaryPerHour) {
        this.title = title;
        this.salaryPerHour = salaryPerHour;
    }

    public String getTitle() {
        return title;
    }

    public Double getSalaryPerHour() {
        return salaryPerHour;
    }
    public void setSalaryPerHour(Double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
