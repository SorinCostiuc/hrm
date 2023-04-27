package com.easycompany.hrm.convertor;

import com.easycompany.hrm.dto.PersonnelCreateDto;
import com.easycompany.hrm.dto.PersonnelInfoDto;
import com.easycompany.hrm.dto.PersonnelShortInfoDto;
import com.easycompany.hrm.model.Personnel;

public class PersonnelConvertor {
    public static Personnel createDtoToEntity(PersonnelCreateDto personnelCreateDto) {
        Personnel personnel = new Personnel();
        personnel.setName(personnelCreateDto.getName());
        personnel.setPhoneNumber(personnelCreateDto.getPhoneNumber());
        personnel.setCnp(personnelCreateDto.getCnp());
        personnel.setEmail(personnelCreateDto.getEmail());
        personnel.setAddress(personnelCreateDto.getAddress());
        personnel.setStatus(personnelCreateDto.getStatus());
        personnel.setJobTitle(personnelCreateDto.getJobTitle());

        return personnel;
    }

    public static PersonnelInfoDto entityToInfoDto(Personnel personnel) {
        PersonnelInfoDto personnelInfoDto = new PersonnelInfoDto();
        personnelInfoDto.setName(personnel.getName());
        personnelInfoDto.setPhoneNumber(personnel.getPhoneNumber());
        personnelInfoDto.setCnp(personnel.getCnp());
        personnelInfoDto.setEmail(personnel.getEmail());
        personnelInfoDto.setAddress(personnel.getAddress());
        personnelInfoDto.setStatus(personnel.getStatus());
        personnelInfoDto.setJobTitle(personnel.getJobTitle());
        personnelInfoDto.setId(personnel.getId());
        personnelInfoDto.setContractShortInfoDto(
                personnel.getContract() != null
                        ? ContractConvertor.entityToShortInfoDto(personnel.getContract()) : null);

        return personnelInfoDto;
    }

    public static PersonnelShortInfoDto entityToShortInfoDto(Personnel personnel) {
        PersonnelShortInfoDto personnelShortInfoDto = new PersonnelShortInfoDto();
        personnelShortInfoDto.setId(personnel.getId());
        personnelShortInfoDto.setName(personnel.getName());
        personnelShortInfoDto.setCnp(personnel.getCnp());
        personnelShortInfoDto.setEmail(personnel.getEmail());
        personnelShortInfoDto.setStatus(personnel.getStatus());
        personnelShortInfoDto.setJobTitle(personnel.getJobTitle());

        return personnelShortInfoDto;
    }
}
