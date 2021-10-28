package com.codenation.group3.centralDeErros.controller;

import com.codenation.group3.centralDeErros.dtos.ErrorLogDTO;
import com.codenation.group3.centralDeErros.dtos.fullErrorLogDTO;
import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.service.ErrorLogService;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/error-log")
@CrossOrigin
public class ErrorLogController {
	
    private ErrorLogService errorLogService;
    
    @Autowired
    public ErrorLogController(ErrorLogService errorLogService) {
    	this.errorLogService = errorLogService;
    }
    
    @GetMapping
    public ResponseEntity<List<ErrorLogDTO>> findAll(Pageable pageable) {
    	Page<ErrorLog> page = errorLogService.findAll(pageable);
    	
    	List<ErrorLogDTO> errorLogList = page.getContent().stream()
    			.map(this::toErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(errorLogList, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<fullErrorLogDTO> findById(@PathVariable("id") Long id) {
        fullErrorLogDTO errorLog = 
        		this.toFullErrorLogDTO(errorLogService.findById(id));
        
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
        
        return new ResponseEntity<String>(
        		"Log " + id + " successfully deleted.", 
        		HttpStatus.ACCEPTED);
    }
    
    // Métodos de filtragem
    @GetMapping(params = "level")
    public ResponseEntity<List<fullErrorLogDTO>> findByLevel(@PathParam("level") String level, Pageable pageable) {
    	Page<ErrorLog> page = errorLogService.findByLevel(level, pageable); 
    	
    	List<fullErrorLogDTO> errorLogList = page.getContent().stream()
    			.map(this::toFullErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(errorLogList, HttpStatus.OK);
    }
    
    @GetMapping(params = "description")
    public ResponseEntity<List<fullErrorLogDTO>> findByDescription(@PathParam("description") String description, Pageable pageable) {
    	Page<ErrorLog> page = errorLogService.findByDescription(description, pageable);
    	
    	List<fullErrorLogDTO> errorLogList = page.getContent().stream()
    			.map(this::toFullErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(errorLogList, HttpStatus.OK);
    }
    
    @GetMapping(params = "log")
    public ResponseEntity<List<fullErrorLogDTO>> findByLog(@PathParam("log") String log, Pageable pageable) {
    	Page<ErrorLog> page = errorLogService.findByLog(log, pageable);
    	
    	List<fullErrorLogDTO> errorLogList = page.getContent().stream()
    			.map(this::toFullErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(errorLogList, HttpStatus.OK);
    }
    
    @GetMapping(params = "origin")
    public ResponseEntity<List<fullErrorLogDTO>> findByOrigin(@PathParam("origin") String origin, Pageable pageable) {
    	Page<ErrorLog> page = errorLogService.findByOrigin(origin, pageable);
    	
    	List<fullErrorLogDTO> errorLogList = page.getContent().stream()
    			.map(this::toFullErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(errorLogList, HttpStatus.OK);
    }
    
    @GetMapping(params = {"from", "to"})
    public ResponseEntity<List<fullErrorLogDTO>> findByDate(
    		@PathParam("from") String from,
    		@PathParam("to") String to,
    		Pageable pageable
    ) throws ParseException {
    	Page<ErrorLog> page = errorLogService.findByDate(from, to, pageable);
    	
    	List<fullErrorLogDTO> errorLogList = page.getContent().stream()
    			.map(this::toFullErrorLogDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(errorLogList, HttpStatus.OK);
    }
    
    @GetMapping(params = "countBy")
    public ResponseEntity<Integer> countByLevel(@PathParam("countBy") String countBy) {
    	Integer errorsByLevel = errorLogService.countByLevel(countBy);
    	
    	return new ResponseEntity<>(errorsByLevel, HttpStatus.OK);
    }
    
    private ErrorLogDTO toErrorLogDTO(ErrorLog log) {
    	ErrorLogDTO dto = new ErrorLogDTO();
    	dto.setId(log.getId());
    	dto.setLevel(log.getLevel());
    	dto.setDescription(log.getDescription());
    	dto.setOrigin(log.getOrigin());
    	
    	return dto;
    }
    
    private fullErrorLogDTO toFullErrorLogDTO(ErrorLog log) {
    	fullErrorLogDTO dto = new fullErrorLogDTO();
    	dto.setId(log.getId());
    	dto.setLevel(log.getLevel());
    	dto.setDescription(log.getDescription());
    	dto.setLog(log.getLog());
    	dto.setOrigin(log.getOrigin());
    	dto.setCreatedAt(log.getCreatedAt());
    	
    	return dto;
    }

}