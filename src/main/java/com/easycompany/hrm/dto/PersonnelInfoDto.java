package com.easycompany.hrm.dto;

import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Status;

public class PersonnelInfoDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private Long cnp;
    private String email;
    private String address;
    private Status status;
    private JobTitle jobTitle;
    private ContractShortInfoDto contractShortInfoDto;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ContractShortInfoDto getContractShortInfoDto() {
        return contractShortInfoDto;
    }

    public void setContractShortInfoDto(ContractShortInfoDto contractShortInfoDto) {
        this.contractShortInfoDto = contractShortInfoDto;
    }
}
