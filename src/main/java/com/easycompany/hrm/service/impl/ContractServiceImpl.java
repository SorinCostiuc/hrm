package com.easycompany.hrm.service.impl;

import com.easycompany.hrm.convertor.ContractConvertor;
import com.easycompany.hrm.dto.ContractCreateDto;
import com.easycompany.hrm.dto.ContractInfoDto;
import com.easycompany.hrm.dto.ContractShortInfoDto;
import com.easycompany.hrm.exception.ContractException;
import com.easycompany.hrm.model.Contract;
import com.easycompany.hrm.model.ContractType;
import com.easycompany.hrm.repository.ContractRepository;
import com.easycompany.hrm.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public ContractInfoDto createNewContract(ContractCreateDto contractCreateDto) {

        return ContractConvertor.entityToInfoDto(contractRepository.save(ContractConvertor
                .createDtoToEntity(contractCreateDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public ContractInfoDto findContractById(Integer contractId) {

        return ContractConvertor.entityToInfoDto(contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractException("Could not find contract with id: " + contractId)));
    }

    @Override
    public void updateContractEndDate(Integer contractId, LocalDate endDate) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractException("Could not find contract with id: " + contractId));
        contract.setEndDate(endDate);

        contractRepository.saveAndFlush(contract);
    }

    @Override
    public void updateContractType(Integer contractId, ContractType contractType) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractException("Could not find contract with id: " + contractId));
        contract.setContractType(contractType);

        contractRepository.saveAndFlush(contract);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractShortInfoDto> findContractByStartDate(LocalDate startDate) {

        return contractRepository.findByStartDate(startDate).stream().map(ContractConvertor::entityToShortInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new ContractException("Could not find contracts with start date: " + startDate);
                            }
                            return list;
                        }));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractShortInfoDto> findContractByEndDate(LocalDate endDate) {

        return contractRepository.findByEndDate(endDate).stream().map(ContractConvertor::entityToShortInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new ContractException("Could not find contracts with end date: " + endDate);
                            }
                            return list;
                        }));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractShortInfoDto> findByType(ContractType contractType) {

        return contractRepository.findByContractType(contractType).stream().map(ContractConvertor::entityToShortInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new ContractException("Could not find any " + contractType + " contracts");
                            }
                            return list;
                        }));
    }
}
