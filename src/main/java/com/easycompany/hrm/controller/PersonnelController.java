package com.easycompany.hrm.controller;


import com.easycompany.hrm.dto.PersonnelCreateDto;
import com.easycompany.hrm.dto.PersonnelInfoDto;
import com.easycompany.hrm.dto.PersonnelShortInfoDto;
import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.model.Status;
import com.easycompany.hrm.service.PersonnelService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hrm/v1/personnel")
@ControllerAdvice
public class PersonnelController {
    private final PersonnelService personnelService;

    @Autowired
    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonnelInfoDto> createPersonnel(@RequestBody @Valid PersonnelCreateDto personnelCreateDto) {

        return ResponseEntity.ok(personnelService.createNewPersonnel(personnelCreateDto));
    }

    @GetMapping("/name-search")
    public ResponseEntity<List<PersonnelInfoDto>> findByName(@RequestParam String name) {

        return ResponseEntity.ok(personnelService.findByName(name));
    }

    @GetMapping("/status-search")
    public ResponseEntity<List<PersonnelShortInfoDto>> findByStatus(@RequestParam Status status) {

        return ResponseEntity.ok(personnelService.findByStatus(status));
    }

    @GetMapping("/job-title-search")
    public ResponseEntity<List<PersonnelShortInfoDto>> findByJobTitle(@RequestParam JobTitle jobTitle) {

        return ResponseEntity.ok(personnelService.findByJobTitle(jobTitle));
    }

    @GetMapping("/cnp-search")
    public ResponseEntity<PersonnelInfoDto> findByCnp(@RequestParam Long cnp) {

        return ResponseEntity.ok(personnelService.findByCnp(cnp));
    }

    @PutMapping("/job-title-change")
    public ResponseEntity<String> updateJobTitle(@RequestParam Integer personnelId, JobTitle jobTitle) {
        personnelService.changeJobTitle(personnelId, jobTitle);

        return ResponseEntity.ok("Personnel with id " + personnelId + " has been successfully updated with the " +
                "job title " + jobTitle);
    }

    @PostMapping("/employ")
    public ResponseEntity<PersonnelInfoDto> employPersonnel(@RequestParam Integer personnelId, Integer contractId) {

        return ResponseEntity.ok(personnelService.employPersonnel(personnelId, contractId));
    }

    @PostMapping("/fire")
    public ResponseEntity<PersonnelInfoDto> firePersonnel(
            @RequestParam Integer personnelId,
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate endDate) {

        return ResponseEntity.ok(personnelService.firePersonnel(personnelId, endDate));
    }

    @PutMapping("/info")
    public ResponseEntity<String> updatePersonnel(@RequestParam Integer personnelId, String personnelName, Long personnelCnp,
                                                  String personnelAddress, @Email String personnelEmail, String personnelPhoneNumber) {
        personnelService.updatePersonnel(personnelId, personnelName, personnelCnp, personnelAddress, personnelEmail
                , personnelPhoneNumber);

        return ResponseEntity.ok("Personnel with id " + personnelId + " has been successfully updated");
    }

    @GetMapping("/id-search")
    public ResponseEntity<PersonnelInfoDto> findById(@RequestParam Integer personnelId) {

        return ResponseEntity.ok(personnelService.findById(personnelId));
    }
}
