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

    @PostMapping("/createNewPersonnel")
    public ResponseEntity<PersonnelInfoDto> createNewPersonnel(@RequestBody @Valid PersonnelCreateDto personnelCreateDto) {

        return ResponseEntity.ok(personnelService.createNewPersonnel(personnelCreateDto));
    }

    @GetMapping("/findPersonnelByName")
    public ResponseEntity<List<PersonnelInfoDto>> getPersonnelByName(@RequestParam String name) {

        return ResponseEntity.ok(personnelService.findByName(name));
    }

    @GetMapping("/findPersonnelByStatus")
    public ResponseEntity<List<PersonnelShortInfoDto>> getPersonnelByStatus(@RequestParam Status status) {

        return ResponseEntity.ok(personnelService.findByStatus(status));
    }

    @GetMapping("/findPersonnelByJobTitle")
    public ResponseEntity<List<PersonnelShortInfoDto>> getPersonnelByJobTitle(@RequestParam JobTitle jobTitle) {

        return ResponseEntity.ok(personnelService.findByJobTitle(jobTitle));
    }

    @GetMapping("/findPersonnelByCnp")
    public ResponseEntity<PersonnelInfoDto> getPersonnelByCnp(@RequestParam Long cnp) {

        return ResponseEntity.ok(personnelService.findByCnp(cnp));
    }

    @PutMapping("/updatePersonnelJobTitle")
    public ResponseEntity<String> updatePersonnelJobTitle(@RequestParam Integer personnelId, JobTitle jobTitle) {
        personnelService.changeJobTitle(personnelId, jobTitle);

        return ResponseEntity.ok("Personnel with id " + personnelId + " has been successfully updated with the " +
                "job title " + jobTitle);
    }

    @PostMapping("/employPersonnel")
    public ResponseEntity<PersonnelInfoDto> employPersonnel(@RequestParam Integer personnelId, Integer contractId) {

        return ResponseEntity.ok(personnelService.employPersonnel(personnelId, contractId));
    }

    @PostMapping("/firePersonnel")
    public ResponseEntity<PersonnelInfoDto> firePersonnel(
            @RequestParam Integer personnelId,
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate endDate) {

        return ResponseEntity.ok(personnelService.firePersonnel(personnelId, endDate));
    }

    @PutMapping("/updatePersonnel")
    public ResponseEntity<String> updatePersonnel(@RequestParam Integer personnelId, String personnelName, Long personnelCnp,
                                                  String personnelAddress, @Email String personnelEmail, String personnelPhoneNumber) {
        personnelService.updatePersonnel(personnelId, personnelName, personnelCnp, personnelAddress, personnelEmail
                , personnelPhoneNumber);

        return ResponseEntity.ok("Personnel with id " + personnelId + " has been successfully updated");
    }

    @GetMapping("/findPersonnelById")
    public ResponseEntity<PersonnelInfoDto> getPersonnelById(@RequestParam Integer personnelId) {

        return ResponseEntity.ok(personnelService.findById(personnelId));
    }
}
