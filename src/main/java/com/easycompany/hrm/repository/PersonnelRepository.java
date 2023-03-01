package com.easycompany.hrm.repository;

import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Personnel;
import com.easycompany.hrm.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    List<Personnel> findByName(String name);

    List<Personnel> findByStatus(Status status);

    List<Personnel> findByJobTitle(JobTitle jobTitle);

    Personnel findByCnp(Long cnp);
}
