package com.easycompany.hrm.dto;


import java.time.LocalDate;

public class SalaryInfoDto {
    private Integer id;
    private Double amount;
    private LocalDate receiveDate;
    private Integer totalWorkedHours;
    private PersonnelShortInfoDto personnelShortInfoDto;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public PersonnelShortInfoDto getPersonnelShortInfoDto() {
        return personnelShortInfoDto;
    }

    public void setPersonnelShortInfoDto(PersonnelShortInfoDto personnelShortInfoDto) {
        this.personnelShortInfoDto = personnelShortInfoDto;
    }
}
