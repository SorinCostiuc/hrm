package com.easycompany.hrm.dto;

import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonnelCreateDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String phoneNumber;
    @NotNull(message = "CNP is mandatory")
    @Min(value = 1000000000000L)
    private Long cnp;
    @Email(message = "Invalid email")
    @NotBlank(message = "Contact information required")
    private String email;

    private String address;
    @NotNull(message = "Must choose status")
    private Status status;

    private JobTitle jobTitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
}
