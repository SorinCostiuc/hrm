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
@RequestMapping("/hrm/v1/contract")
@ControllerAdvice
public class ContractController {
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/createNewContract")
    public ResponseEntity<ContractInfoDto> createNewContract(@RequestBody @Valid @JsonFormat ContractCreateDto contractCreateDto) {

        return ResponseEntity.ok(contractService.createNewContract(contractCreateDto));
    }

    @GetMapping("/findContractById")
    public ResponseEntity<ContractInfoDto> getContractById(@RequestParam Integer contractId) {

        return ResponseEntity.ok(contractService.findContractById(contractId));
    }

    @PutMapping("/updateContractEndDate")
    public ResponseEntity<String> updateContractEndDate(
            @RequestParam Integer contractId,
            @RequestParam @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate contractEndDate) {
        contractService.updateContractEndDate(contractId, contractEndDate);

        return ResponseEntity.ok("Personnel with id " + contractId + " has been successfully updated " +
                "with " + contractEndDate + " end date.");
    }

    @PostMapping("/updateContractType")
    public ResponseEntity<String> updateContractType(@RequestParam Integer contractId, ContractType contractType) {
        contractService.updateContractType(contractId, contractType);

        return ResponseEntity.ok("Personnel with id " + contractId + " has been successfully updated " +
                "with " + contractType + " contract.");
    }

    @GetMapping("/findContractByStartDate")
    public ResponseEntity<List<ContractShortInfoDto>> findContractByStartDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate contractStartDate) {

        return ResponseEntity.ok(contractService.findContractByStartDate(contractStartDate));
    }

    @GetMapping("/findContractByEndDate")
    public ResponseEntity<List<ContractShortInfoDto>> findContractByEndDate(
            @RequestParam @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDate contractEndDate) {

        return ResponseEntity.ok(contractService.findContractByEndDate(contractEndDate));
    }

    @GetMapping("/findContractByContractType")
    public ResponseEntity<List<ContractShortInfoDto>> findContractByContractType(@RequestParam ContractType contractType) {

        return ResponseEntity.ok(contractService.findContractByContractType(contractType));
    }

}
