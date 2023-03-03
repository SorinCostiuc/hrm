package com.easycompany.hrm.controller;

import com.easycompany.hrm.dto.ContractCreateDto;
import com.easycompany.hrm.dto.ContractInfoDto;
import com.easycompany.hrm.dto.ContractShortInfoDto;
import com.easycompany.hrm.model.ContractType;
import com.easycompany.hrm.service.ContractService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hrm/v1/contracts")
@ControllerAdvice
public class ContractController {
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/create")
    public ResponseEntity<ContractInfoDto> createContract(@RequestBody @Valid @JsonFormat ContractCreateDto contractCreateDto) {

        return ResponseEntity.ok(contractService.createNewContract(contractCreateDto));
    }

    @GetMapping("/id-search")
    public ResponseEntity<ContractInfoDto> findById(@RequestParam Integer contractId) {

        return ResponseEntity.ok(contractService.findContractById(contractId));
    }

    @PutMapping("/end-date")
    public ResponseEntity<String> updateEndDate(
            @RequestParam Integer contractId,
            @RequestParam @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate contractEndDate) {
        contractService.updateContractEndDate(contractId, contractEndDate);

        return ResponseEntity.ok("Personnel with id " + contractId + " has been successfully updated " +
                "with " + contractEndDate + " end date.");
    }

    @PutMapping("/type")
    public ResponseEntity<String> updateType(@RequestParam Integer contractId, ContractType contractType) {
        contractService.updateContractType(contractId, contractType);

        return ResponseEntity.ok("Personnel with id " + contractId + " has been successfully updated " +
                "with " + contractType + " contract.");
    }

    @GetMapping("/start-date-search")
    public ResponseEntity<List<ContractShortInfoDto>> findByStartDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate contractStartDate) {

        return ResponseEntity.ok(contractService.findContractByStartDate(contractStartDate));
    }

    @GetMapping("/end-date-search")
    public ResponseEntity<List<ContractShortInfoDto>> findByEndDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate contractEndDate) {

        return ResponseEntity.ok(contractService.findContractByEndDate(contractEndDate));
    }

    @GetMapping("/type")
    public ResponseEntity<List<ContractShortInfoDto>> findByType(@RequestParam ContractType contractType) {

        return ResponseEntity.ok(contractService.findByType(contractType));
    }

}
