package com.codenation.group3.centralDeErros.controller;

import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.service.ErrorLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/error-log")
public class ErrorLogController {
    ErrorLogService errorLogService;

    @GetMapping("/{id}")
    public ResponseEntity<ErrorLog> findById(@PathVariable("id") Long id) {
        ErrorLog errorLog = errorLogService.findById(id);
        return new ResponseEntity<>(errorLog, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ErrorLog>> findAll() {
        return new ResponseEntity<>(errorLogService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ErrorLog> createNewErrorLog(@RequestBody ErrorLog errorLog) {
        return new ResponseEntity<>(errorLogService.save(errorLog), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteErrorLog(@PathVariable("id") Long id) {
        errorLogService.delete(id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ErrorLog> updateErrorLog(@PathVariable("id") Long id, @RequestBody ErrorLog errorLog) {
        return new ResponseEntity<>(errorLogService.update(id, errorLog), HttpStatus.ACCEPTED);
    }

}