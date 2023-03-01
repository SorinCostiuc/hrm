package com.easycompany.hrm.service.impl;

import com.easycompany.hrm.convertor.ContractConvertor;
import com.easycompany.hrm.convertor.PersonnelConvertor;
import com.easycompany.hrm.dto.PersonnelCreateDto;
import com.easycompany.hrm.dto.PersonnelInfoDto;
import com.easycompany.hrm.dto.PersonnelShortInfoDto;
import com.easycompany.hrm.exception.ContractException;
import com.easycompany.hrm.exception.PersonnelException;
import com.easycompany.hrm.model.Contract;
import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Personnel;
import com.easycompany.hrm.model.Status;
import com.easycompany.hrm.repository.ContractRepository;
import com.easycompany.hrm.repository.PersonnelRepository;
import com.easycompany.hrm.service.PersonnelService;
import com.easycompany.hrm.utils.StatusChangeScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    private final PersonnelRepository personnelRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public PersonnelServiceImpl(PersonnelRepository personnelRepository, ContractRepository contractRepository) {
        this.personnelRepository = personnelRepository;
        this.contractRepository = contractRepository;
    }


    @Override
    public PersonnelInfoDto createNewPersonnel(PersonnelCreateDto personnelCreateDto) {

        return PersonnelConvertor.entityToInfoDto(personnelRepository.save(PersonnelConvertor
                .createDtoToEntity(personnelCreateDto)));
    }

    @Override
    public PersonnelInfoDto employPersonnel(Integer personnelId, Integer contractId) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new PersonnelException("Could not find personnel with id: " + personnelId));
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractException("Could not find contract with id: " + contractId));

        if (personnel.getContract() == null && contract.getPersonnel() == null) {
            personnel.setContract(contract);
            personnel.setStatus(Status.EMPLOYEE);
            contractRepository.save(contract);
        }

        return PersonnelConvertor.entityToInfoDto(personnelRepository.save(personnel));
    }


    @Override
    public PersonnelInfoDto firePersonnel(Integer personnelId, LocalDate endDate) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new PersonnelException("Could not find personnel with id: " + personnelId));
        if (personnel.getContract() == null) {
            throw new ContractException("The person you selected is not an employee");
        }
        if (personnel.getContract().getEndDate().equals(ContractConvertor.getEndOfTime())) {
            StatusChangeScheduler scheduler = new StatusChangeScheduler(Status.EMPLOYEE, endDate);
            scheduler.startSchedulerChangeStatusToFired();
            personnel.getContract().setEndDate(endDate);

            contractRepository.save(personnel.getContract());

            return PersonnelConvertor.entityToInfoDto(personnelRepository.save(personnel));
        } else {
            throw new ContractException("The person you selected has " + personnel.getContract().getEndDate() +
                    " as end date for the contract");
        }
    }

    @Override
    public void updatePersonnel(Integer personnelId, String personnelName, Long personnelCnp, String personnelAddress
            , String personnelEmail, String personnelPhoneNumber) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new PersonnelException("Could not find personnel with id: " + personnelId));
        personnel.setName(personnelName);
        personnel.setCnp(personnelCnp);
        personnel.setAddress(personnelAddress);
        personnel.setEmail(personnelEmail);
        personnel.setPhoneNumber(personnelPhoneNumber);

        personnelRepository.saveAndFlush(personnel);
    }

    @Override
    public void changeJobTitle(Integer personnelId, JobTitle jobtitle) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new PersonnelException("Could not find personnel with id: " + personnelId));
        personnel.setJobTitle(jobtitle);
        personnelRepository.save(personnel);
    }

    @Override
    public List<PersonnelInfoDto> findByName(String name) {

        return personnelRepository.findAll().stream().map(PersonnelConvertor::entityToInfoDto)
                .filter(personnelShortInfoDto -> personnelShortInfoDto.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new PersonnelException("Could not find personnel with name: " + name);
                            }
                            return list;
                        }));
    }

    @Override
    public List<PersonnelShortInfoDto> findByStatus(Status status) {

        return personnelRepository.findByStatus(status).stream().map(PersonnelConvertor::entityToShortInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new PersonnelException("Could not find personnel with status: " + status);
                            }
                            return list;
                        }));
    }

    @Override
    public List<PersonnelShortInfoDto> findByJobTitle(JobTitle jobTitle) {

        return personnelRepository.findByJobTitle(jobTitle).stream().map(PersonnelConvertor::entityToShortInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new PersonnelException("Could not find personnel with job title: " + jobTitle);
                            }
                            return list;
                        }));
    }

    @Override
    public PersonnelInfoDto findByCnp(Long cnp) {
        try {
            return PersonnelConvertor.entityToInfoDto(personnelRepository.findByCnp(cnp));
        } catch (Exception e) {
            throw new PersonnelException("Could not find personnel with cnp: " + cnp);
        }
    }

    @Override
    public PersonnelInfoDto findById(Integer personnelId) {

        return PersonnelConvertor.entityToInfoDto(personnelRepository.findById(personnelId)
                .orElseThrow(() -> new PersonnelException("Could not find personnel with id: " + personnelId)));

    }
}
