package com.easycompany.hrm.service;

import com.easycompany.hrm.dto.ContractCreateDto;
import com.easycompany.hrm.dto.ContractInfoDto;
import com.easycompany.hrm.dto.ContractShortInfoDto;
import com.easycompany.hrm.model.ContractType;

import java.time.LocalDate;
import java.util.List;

public interface ContractService {

    ContractInfoDto createNewContract(ContractCreateDto contractCreateDto);

    ContractInfoDto findContractById(Integer contractId);

    void updateContractEndDate(Integer contractId, LocalDate endDate);
    void updateContractType(Integer contractId, ContractType contractType);

    List<ContractShortInfoDto> findContractByStartDate(LocalDate startDate);

    List<ContractShortInfoDto> findContractByEndDate(LocalDate endDate);

    List<ContractShortInfoDto> findByType(ContractType contractType);
}
