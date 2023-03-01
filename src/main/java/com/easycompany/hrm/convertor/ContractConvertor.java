package com.easycompany.hrm.convertor;

import com.easycompany.hrm.dto.ContractCreateDto;
import com.easycompany.hrm.dto.ContractInfoDto;
import com.easycompany.hrm.dto.ContractShortInfoDto;
import com.easycompany.hrm.model.Contract;

import java.time.LocalDate;


public class ContractConvertor {
    private static final LocalDate END_OF_TIME = LocalDate.of(2099, 12, 31);

    public static Contract createDtoToEntity(ContractCreateDto contractCreatDto) {
        Contract contract = new Contract();
        contract.setStartDate(contractCreatDto.getStartDate());
        if (contractCreatDto.getEndDate() != null) {
            contract.setEndDate(contractCreatDto.getEndDate());
        } else {
            contract.setEndDate(END_OF_TIME);
        }
        contract.setContractType(contractCreatDto.getContractType());

        return contract;
    }

    public static ContractInfoDto entityToInfoDto(Contract contract) {
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        contractInfoDto.setStartDate(contract.getStartDate());
        if (contract.getEndDate() != null && !contract.getEndDate().equals(END_OF_TIME)) {
            contractInfoDto.setEndDate(contract.getEndDate());
        } else {
            contractInfoDto.setEndDate(null);
        }
        contractInfoDto.setContractType(contract.getContractType());
        contractInfoDto.setId(contract.getId());
        if (contract.getPersonnel() != null) {
            contractInfoDto.setPersonnelShortInfoDto(PersonnelConvertor.entityToShortInfoDto(contract.getPersonnel()));
        }

        return contractInfoDto;
    }

    public static ContractShortInfoDto entityToShortInfoDto(Contract contract) {
        ContractShortInfoDto contractShortInfoDto = new ContractShortInfoDto();
        contractShortInfoDto.setId(contract.getId());
        contractShortInfoDto.setStartDate(contract.getStartDate());
        if (contract.getEndDate() != null && !contract.getEndDate().equals(END_OF_TIME)) {
            contractShortInfoDto.setEndDate(contract.getEndDate());
        }
        contractShortInfoDto.setContractType(contract.getContractType());

        return contractShortInfoDto;
    }

    public static LocalDate getEndOfTime() {
        return END_OF_TIME;
    }
}
