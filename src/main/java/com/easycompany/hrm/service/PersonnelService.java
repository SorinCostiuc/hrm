package com.easycompany.hrm.service;

import com.easycompany.hrm.dto.PersonnelCreateDto;
import com.easycompany.hrm.dto.PersonnelInfoDto;
import com.easycompany.hrm.dto.PersonnelShortInfoDto;
import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Status;


import java.time.LocalDate;
import java.util.List;

public interface PersonnelService {
    PersonnelInfoDto createNewPersonnel(PersonnelCreateDto personnelCreateDto);

    PersonnelInfoDto employPersonnel(Integer personnelId, Integer contractId);

    PersonnelInfoDto firePersonnel(Integer personnelId, LocalDate endDate);

    void updatePersonnel(Integer personnelId, String personnelName, Long personnelCnp, String personnelAddress
            , String personnelEmail, String personnelPhoneNumber);

    void changeJobTitle(Integer personnelId, JobTitle jobTitle);

    List<PersonnelInfoDto> findByName(String name);

    List<PersonnelShortInfoDto> findByStatus(Status status);

    List<PersonnelShortInfoDto> findByJobTitle(JobTitle jobTitle);

    PersonnelInfoDto findByCnp(Long cnp);

    PersonnelInfoDto findById(Integer personnelId);

}
