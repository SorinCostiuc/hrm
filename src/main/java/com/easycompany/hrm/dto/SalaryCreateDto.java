package com.easycompany.hrm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class SalaryCreateDto {
    @NotNull(message = "Salary amount cannot be null")
    private double amount;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate receiveDate;
    private Integer totalWorkedHours;


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(Integer totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }
}
