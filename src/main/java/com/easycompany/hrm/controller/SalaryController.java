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
@RequestMapping("/hrm/v1/salaries")
@ControllerAdvice
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<SalaryInfoDto> createNewSalary(@RequestBody @Valid @JsonFormat SalaryCreateDto salaryCreateDto) {
//
//        return ResponseEntity.ok(salaryService.createNewSalary(salaryCreateDto));
//    }

    @PostMapping("/create")
    public ResponseEntity<SalaryInfoDto> createNewSalary(
            @RequestParam Integer personnelId, Integer totalWorkedHours,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate receivedDate) {

        return ResponseEntity.ok(salaryService.createNewSalary(personnelId, totalWorkedHours, receivedDate));
    }

    @GetMapping("/max")
    public ResponseEntity<List<SalaryInfoDto>> findMaxSalary() {
        return ResponseEntity.ok(salaryService.findMaxSalary());
    }

    @GetMapping("/min")
    public ResponseEntity<List<SalaryInfoDto>> findMinSalary() {
        return ResponseEntity.ok(salaryService.findMinSalary());
    }

    @GetMapping("/date-search")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findSalaryByDate(date));
    }

    @GetMapping("/max-from-date")
    public ResponseEntity<List<SalaryInfoDto>> findMaxSalaryByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findMaxSalaryByDate(date));
    }

    @GetMapping("/min-from-date")
    public ResponseEntity<List<SalaryInfoDto>> findMinSalaryByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findMinSalaryByDate(date));
    }

    @GetMapping("/personnel-id-search")
    public ResponseEntity<List<SalaryShortInfoDto>> findSalaryByPersonnelId(@RequestParam Integer personnelId) {

        return ResponseEntity.ok(salaryService.findSalaryByPersonnelId(personnelId));
    }

    @GetMapping("/max-hours-search")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMaxWorkedHours() {

        return ResponseEntity.ok(salaryService.findSalaryByMaxWorkedHours());
    }

    @GetMapping("/min-hours-search")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMinWorkedHours() {

        return ResponseEntity.ok(salaryService.findSalaryByMinWorkedHours());
    }

    @GetMapping("/max-hours-date-search")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMaxWorkedHoursByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findSalaryByMaxWorkedHoursByDate(date));
    }

    @GetMapping("/min-hours-date-search")
    public ResponseEntity<List<SalaryInfoDto>> findSalaryByMinWorkedHoursByDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(salaryService.findSalaryByMinWorkedHoursByDate(date));
    }

    @PutMapping("/increase")
    public ResponseEntity<String> updateSalary(@RequestParam JobTitle jobTitle, Double amount) {
        salaryService.updateSalary(jobTitle, amount);

        return ResponseEntity.ok("Salary rate per hour has been increased for job title " +
                jobTitle + " with " + amount + " amount.");
    }

}
