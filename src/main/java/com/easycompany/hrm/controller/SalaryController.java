package com.easycompany.hrm.controller;

import com.easycompany.hrm.dto.SalaryCreateDto;
import com.easycompany.hrm.dto.SalaryInfoDto;
import com.easycompany.hrm.dto.SalaryShortInfoDto;
import com.easycompany.hrm.model.JobTitle;
import com.easycompany.hrm.service.SalaryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hrm/v1/salary")
@ControllerAdvice
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

//    @PostMapping("/createNewSalary")
//    public ResponseEntity<SalaryInfoDto> createNewSalary(@RequestBody @Valid @JsonFormat SalaryCreateDto salaryCreateDto) {
//
//        return ResponseEntity.ok(salaryService.createNewSalary(salaryCreateDto));
//    }

    @PostMapping("/createNewSalary")
    public ResponseEntity<SalaryInfoDto> createNewSalary(
            @RequestParam Integer personnelId, Integer totalWorkedHours,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate receivedDate) {

        return ResponseEntity.ok(salaryService.createNewSalary(personnelId, totalWorkedHours, receivedDate));
    }

    @GetMapping("/findMaxSalary")
    public ResponseEntity<List<SalaryInfoDto>> findMaxSalary() {
        return ResponseEntity.ok(salaryService.findMaxSalary());
    }

    @GetMapping("/findMinSalary")
    public ResponseEntity<List<SalaryInfoDto>> findMinSalary() {
        return ResponseEntity.ok(salaryService.findMinSalary());
    }

    @GetMapping("/findSalaryByDate")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findSalaryByDate(date));
    }

    @GetMapping("/findMaxSalaryByDate")
    public ResponseEntity<List<SalaryInfoDto>> findMaxSalaryByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findMaxSalaryByDate(date));
    }

    @GetMapping("/findMinSalaryByDate")
    public ResponseEntity<List<SalaryInfoDto>> findMinSalaryByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findMinSalaryByDate(date));
    }

    @GetMapping("/findSalaryByPersonnelId")
    public ResponseEntity<List<SalaryShortInfoDto>> findSalaryByPersonnelId(@RequestParam Integer personnelId) {

        return ResponseEntity.ok(salaryService.findSalaryByPersonnelId(personnelId));
    }

    @GetMapping("/findSalaryByMaxWorkedHours")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMaxWorkedHours() {

        return ResponseEntity.ok(salaryService.findSalaryByMaxWorkedHours());
    }

    @GetMapping("/findSalaryByMinWorkedHours")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMinWorkedHours() {

        return ResponseEntity.ok(salaryService.findSalaryByMinWorkedHours());
    }

    @GetMapping("/findSalaryByMaxWorkedHoursByDate")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMaxWorkedHoursByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findSalaryByMaxWorkedHoursByDate(date));
    }

    @GetMapping("/findSalaryByMinWorkedHoursByDate")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMinWorkedHoursByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findSalaryByMinWorkedHoursByDate(date));
    }

    @PutMapping("/updateSalary")
    public ResponseEntity<String> updateSalary(@RequestParam JobTitle jobTitle, Double amount) {
        salaryService.updateSalary(jobTitle, amount);

        return ResponseEntity.ok("Salary rate per hour has been increased for job title " +
                jobTitle + " with " + amount + " amount.");
    }

}
