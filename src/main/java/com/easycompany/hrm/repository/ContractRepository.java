package com.easycompany.hrm.repository;

import com.easycompany.hrm.model.Contract;
import com.easycompany.hrm.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findByStartDate(LocalDate date);

    List<Contract> findByEndDate(LocalDate date);

    List<Contract> findByContractType(ContractType contractType);

}
