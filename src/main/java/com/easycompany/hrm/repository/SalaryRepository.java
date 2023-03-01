package com.easycompany.hrm.repository;

import com.easycompany.hrm.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    List<Salary> findSalariesByReceiveDate(LocalDate receivedDate);

    List<Salary> findSalariesByAmount(Double amount);

    List<Salary> findSalariesByTotalWorkedHours(Integer workedHours);
}
