package com.easycompany.hrm.service.impl;

import com.easycompany.hrm.convertor.SalaryConvertor;
import com.easycompany.hrm.dto.SalaryCreateDto;
import com.easycompany.hrm.dto.SalaryInfoDto;
import com.easycompany.hrm.dto.SalaryShortInfoDto;
import com.easycompany.hrm.exception.ContractException;
import com.easycompany.hrm.exception.PersonnelException;
import com.easycompany.hrm.exception.SalaryException;
import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Personnel;
import com.easycompany.hrm.model.Salary;
import com.easycompany.hrm.repository.PersonnelRepository;
import com.easycompany.hrm.repository.SalaryRepository;
import com.easycompany.hrm.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final PersonnelRepository personnelRepository;

    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository, PersonnelRepository personnelRepository) {
        this.salaryRepository = salaryRepository;
        this.personnelRepository = personnelRepository;
    }
//without calculation, manually input of the json payload (depending on frontend)
//    @Override
//    public SalaryInfoDto createNewSalary(SalaryCreateDto salaryCreateDto) {
//
//        return SalaryConvertor.entityToInfoDto(salaryRepository.save(SalaryConvertor
//                .createDtoToEntity(salaryCreateDto)));
//    }

    //with calculation, from the parameters asked in the api (depending on frontend)
    @Override
    public SalaryInfoDto createNewSalary(Integer personnelId, Integer totalWorkedHours, LocalDate receivedDate) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new ContractException("Could not find personnel with id: " + personnelId));
        Salary salary = new Salary();
        salary.setTotalWorkedHours(totalWorkedHours);
        salary.setReceiveDate(receivedDate);
        salary.setPersonnel(personnel);
        salary.setAmount(calculateSalary(totalWorkedHours, personnel.getJobTitle().getSalaryPerHour()));

        return SalaryConvertor.entityToInfoDto(salaryRepository.save(salary));
    }

    public static Double calculateSalary(Integer totalHours, Double ratePerHour) {
        return totalHours * ratePerHour;
    }

    @Override
    public void updateSalary(JobTitle jobTitle, Double amount) {
        jobTitle.setSalaryPerHour(jobTitle.getSalaryPerHour() + amount);
    }

    @Override
    public List<SalaryInfoDto> findSalaryByDate(LocalDate date) {
        return salaryRepository.findAll().stream()
                .filter(salary -> salary.getReceiveDate().equals(date))
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new SalaryException("Could not find any max salaries on " + date);
                            }
                            return list;
                        }));
    }

    @Override
    public List<SalaryInfoDto> findMaxSalaryByDate(LocalDate date) {
        try {
            return salaryRepository.findSalariesByReceiveDate(date).stream()
                    .collect(Collectors.groupingBy(Salary::getAmount))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElseThrow(() -> new SalaryException("Could not find any max salaries"))
                    .stream()
                    .map(SalaryConvertor::entityToInfoDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new SalaryException("Could not find any salaries for the given date: " + date);
        }
    }

    @Override
    public List<SalaryInfoDto> findMinSalaryByDate(LocalDate date) {
        try {
            return salaryRepository.findSalariesByReceiveDate(date).stream()
                    .collect(Collectors.groupingBy(Salary::getAmount))
                    .entrySet().stream()
                    .min(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElseThrow(() -> new SalaryException("Could not find any max salaries"))
                    .stream()
                    .map(SalaryConvertor::entityToInfoDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new SalaryException("Could not find any salaries for the given date: " + date);
        }
    }

    @Override
    public List<SalaryShortInfoDto> findSalaryByPersonnelId(Integer personnelId) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new PersonnelException("Could not find personnel with id: " + personnelId));

        return salaryRepository.findAll().stream()
                .filter(salary -> salary.getPersonnel().equals(personnel))
                .map(SalaryConvertor::entityToShortInfoDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) {
                                throw new SalaryException("Could not find any salaries on personnel with id: " + personnelId);
                            }
                            return list;
                        }));
    }

    @Override
    public List<SalaryInfoDto> findSalaryByMaxWorkedHours() {
        List<Salary> salaries = salaryRepository.findAll();
        if (salaries.isEmpty()) {
            throw new SalaryException("Could not find any salaries");
        }
        return salaries.stream()
                .collect(Collectors.groupingBy(Salary::getTotalWorkedHours))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new SalaryException("Could not find any salaries with max worked hours."))
                .stream()
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryInfoDto> findSalaryByMinWorkedHours() {
        List<Salary> salaries = salaryRepository.findAll();
        if (salaries.isEmpty()) {
            throw new SalaryException("Could not find any salaries");
        }
        return salaries.stream()
                .collect(Collectors.groupingBy(Salary::getTotalWorkedHours))
                .entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new SalaryException("Could not find any salaries with min worked hours."))
                .stream()
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryInfoDto> findSalaryByMaxWorkedHoursByDate(LocalDate dateMaxHours) {
        List<Salary> salaries = salaryRepository.findSalariesByReceiveDate(dateMaxHours);
        if (salaries.isEmpty()) {
            throw new SalaryException("Could not find any salaries on " + dateMaxHours);
        }
        return salaries.stream()
                .collect(Collectors.groupingBy(Salary::getTotalWorkedHours))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new SalaryException("There is no max salary on " + dateMaxHours))
                .stream()
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryInfoDto> findSalaryByMinWorkedHoursByDate(LocalDate dateMinHours) {
        List<Salary> salaries = salaryRepository.findSalariesByReceiveDate(dateMinHours);
        if (salaries.isEmpty()) {
            throw new SalaryException("Could not find any salaries on " + dateMinHours);
        }
        return salaries.stream()
                .collect(Collectors.groupingBy(Salary::getTotalWorkedHours))
                .entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new SalaryException("There is no max salary on " + dateMinHours))
                .stream()
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryInfoDto> findMaxSalary() {
        List<Salary> salaries = salaryRepository.findAll();
        if (salaries.isEmpty()) {
            throw new SalaryException("Could not find any salaries");
        }
        return salaries.stream()
                .collect(Collectors.groupingBy(Salary::getAmount))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new SalaryException("Could not find any max salaries"))
                .stream()
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryInfoDto> findMinSalary() {
        List<Salary> salaries = salaryRepository.findAll();
        if (salaries.isEmpty()) {
            throw new SalaryException("Could not find any salaries");
        }
        return salaries.stream()
                .collect(Collectors.groupingBy(Salary::getAmount))
                .entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new SalaryException("Could not find any min salaries"))
                .stream()
                .map(SalaryConvertor::entityToInfoDto)
                .collect(Collectors.toList());
    }
}
