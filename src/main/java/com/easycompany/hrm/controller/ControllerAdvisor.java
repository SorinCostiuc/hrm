package com.easycompany.hrm.controller;

import com.easycompany.hrm.exception.ContractException;
import com.easycompany.hrm.exception.PersonnelException;
import com.easycompany.hrm.exception.SalaryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PersonnelException.class)
    public ResponseEntity<Object> handlerPersonnelException(PersonnelException personnelException) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time stamp", LocalDateTime.now());
        body.put("Error Message", personnelException.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContractException.class)
    public ResponseEntity<Object> handlerContractException(ContractException contractException) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time stamp", LocalDateTime.now());
        body.put("Error Message", contractException.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SalaryException.class)
    public ResponseEntity<Object> handlerSalaryException(SalaryException salaryException) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Time stamp", LocalDateTime.now());
        body.put("Error Message", salaryException.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
