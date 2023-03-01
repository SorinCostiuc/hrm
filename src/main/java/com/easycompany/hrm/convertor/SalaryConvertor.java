package com.easycompany.hrm.convertor;

import com.easycompany.hrm.dto.SalaryCreateDto;
import com.easycompany.hrm.dto.SalaryInfoDto;
import com.easycompany.hrm.dto.SalaryShortInfoDto;
import com.easycompany.hrm.model.Salary;

public class SalaryConvertor {
    public static Salary createDtoToEntity(SalaryCreateDto salaryCreateDto) {
        Salary salary = new Salary();
        salary.setAmount(salaryCreateDto.getAmount());
        salary.setReceiveDate(salaryCreateDto.getReceiveDate());
        salary.setTotalWorkedHours(salaryCreateDto.getTotalWorkedHours());

        return salary;
    }

    public static SalaryInfoDto entityToInfoDto(Salary salary) {
        SalaryInfoDto salaryInfoDto = new SalaryInfoDto();
        salaryInfoDto.setAmount(salary.getAmount());
        salaryInfoDto.setReceiveDate(salary.getReceiveDate());
        salaryInfoDto.setTotalWorkedHours(salary.getTotalWorkedHours());
        salaryInfoDto.setId(salary.getId());
        salaryInfoDto.setPersonnelShortInfoDto(
                salary.getPersonnel() != null
                        ? PersonnelConvertor.entityToShortInfoDto(salary.getPersonnel()) : null);

        return salaryInfoDto;
    }

    public static SalaryShortInfoDto entityToShortInfoDto(Salary salary) {
        SalaryShortInfoDto salaryShortInfoDto = new SalaryShortInfoDto();
        salaryShortInfoDto.setId(salary.getId());
        salaryShortInfoDto.setAmount(salary.getAmount());
        salaryShortInfoDto.setReceiveDate(salary.getReceiveDate());
        salaryShortInfoDto.setTotalWorkedHours(salary.getTotalWorkedHours());

        return salaryShortInfoDto;
    }
}
