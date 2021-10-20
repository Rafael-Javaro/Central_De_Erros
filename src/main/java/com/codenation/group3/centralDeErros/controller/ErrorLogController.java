package com.codenation.group3.centralDeErros.controller;

import com.codenation.group3.centralDeErros.dtos.ErrorLogDTO;
import com.codenation.group3.centralDeErros.dtos.UserDTO;
import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.service.ErrorLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/error-log")
public class ErrorLogController {
	
    private ErrorLogService errorLogService;
    
    @Autowired
    public ErrorLogController(ErrorLogService errorLogService) {
    	this.errorLogService = errorLogService;
    }

//    @GetMapping
//    public ResponseEntity<List<ErrorLogDTO>> findAll() {
//    	List<ErrorLogDTO> dtoList = errorLogService.findAll()
//    			.stream()
//    			.map(this::toErrorLogDTO)
//    			.collect(Collectors.toList());
//    	
//        return new ResponseEntity<>(dtoList, HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<List<ErrorLogDTO>> findAll(Pageable pageable) {
    	Page<ErrorLog> page = errorLogService.findAll(pageable);
    	
    	List<ErrorLogDTO> response = page.getContent().stream()
    			.map(this::toErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ErrorLogDTO> findById(@PathVariable("id") Long id) {
        ErrorLogDTO errorLog = this.toErrorLogDTO(errorLogService.findById(id));
        
        return new ResponseEntity<>(errorLog, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ErrorLogDTO> createNewErrorLog(@RequestBody ErrorLog newErrorLog) {
    	ErrorLogDTO errorLog = this.toErrorLogDTO(errorLogService.save(newErrorLog)); 
    	
        return new ResponseEntity<>(errorLog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ErrorLogDTO> updateErrorLog(@PathVariable("id") Long id, @RequestBody ErrorLog newErrorLog) {
    	ErrorLogDTO errorLog = this.toErrorLogDTO(errorLogService.update(id, newErrorLog));
    	
        return new ResponseEntity<>(errorLog, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteErrorLog(@PathVariable("id") Long id) {
        errorLogService.delete(id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }
    
    private ErrorLogDTO toErrorLogDTO(ErrorLog log) {
    	ErrorLogDTO dto = new ErrorLogDTO();
    	dto.setId(log.getId());
    	dto.setLevel(log.getLevel());
    	dto.setDescription(log.getDescription());
    	dto.setLog(log.getLog());
    	dto.setOrigin(log.getOrigin());
    	
    	return dto;
    }

}