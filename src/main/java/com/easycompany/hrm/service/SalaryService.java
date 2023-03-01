package com.easycompany.hrm.service;

import com.easycompany.hrm.dto.SalaryCreateDto;
import com.easycompany.hrm.dto.SalaryInfoDto;
import com.easycompany.hrm.dto.SalaryShortInfoDto;
import com.easycompany.hrm.model.JobTitle;


import java.time.LocalDate;
import java.util.List;


public interface SalaryService {
    //    SalaryInfoDto createNewSalary(SalaryCreateDto salaryCreateDto);
    SalaryInfoDto createNewSalary(Integer personnelId, Integer totalWorkedHours, LocalDate receivedDate);

    void updateSalary(JobTitle jobTitle, Double amount);

    List<SalaryInfoDto> findSalaryByDate(LocalDate date);

    List<SalaryInfoDto> findMaxSalaryByDate(LocalDate date);

    List<SalaryInfoDto> findMinSalaryByDate(LocalDate date);

    List<SalaryShortInfoDto> findSalaryByPersonnelId(Integer personnelId);

    List<SalaryInfoDto> findSalaryByMaxWorkedHours();

    List<SalaryInfoDto> findSalaryByMinWorkedHours();

    List<SalaryInfoDto> findSalaryByMaxWorkedHoursByDate(LocalDate dateMaxHours);

    List<SalaryInfoDto> findSalaryByMinWorkedHoursByDate(LocalDate dateMaxHours);

    List<SalaryInfoDto> findMaxSalary();

    List<SalaryInfoDto> findMinSalary();
}
