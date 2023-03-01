package com.easycompany.hrm.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Double amount;
    @Column
    private LocalDate receiveDate;
    @Column
    private Integer totalWorkedHours;
    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;


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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
