package com.codenation.group3.centralDeErros.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.repository.ErrorLogRepository;

@Service
public class ErrorLogService {
	
	private final ErrorLogRepository repository;
	
	@Autowired
	public ErrorLogService(ErrorLogRepository repository) {
		this.repository = repository;
	}
	
	public Page<ErrorLog> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public ErrorLog findById(Long id) {
		return repository.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public ErrorLog save(ErrorLog log) {
		return repository.save(log);
	}
	
	public ErrorLog update(Long id, ErrorLog newErrorLog) {
		Optional<ErrorLog> log = repository.findById(id);
		
		if (log.isPresent()) {
			log.get().setLevel(newErrorLog.getLevel());
			log.get().setDescription(newErrorLog.getDescription());
			log.get().setLog(newErrorLog.getLog());
			log.get().setOrigin(newErrorLog.getOrigin());
			
			repository.save(log.get());
			
			return log.get();
		}
		
		return null;
	}
	
	public void delete(Long id) {
		Optional<ErrorLog> log = repository.findById(id);

        log.ifPresent(repository::delete);
	}
	
	// Métodos de filtragem
	public Page<ErrorLog> findByLevel(String level, Pageable pageable) {
		return repository.findByLevelIgnoreCase(level, pageable);
	}
	
	public Page<ErrorLog> findByDescription(String description, Pageable pageable) {
		return repository.findByDescriptionContainingIgnoreCase(description, pageable);
	}
	
	public Page<ErrorLog> findByLog(String log, Pageable pageable) {
		return repository.findByLogContainingIgnoreCase(log, pageable);
	}
	
	public Page<ErrorLog> findByOrigin(String origin, Pageable pageable) {
		return repository.findByOriginContainingIgnoreCase(origin, pageable);
	}
	
	public Page<ErrorLog> findByDate(String from, String to, Pageable pageable) {
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDateTime startDate = LocalDate.parse(from, parser).atStartOfDay();
    	LocalDateTime endDate = LocalDate.parse(to, parser).atTime(23, 59);
		
		return repository.findByCreatedAtBetween(startDate, endDate, pageable);
	}
	
	public Integer countByLevel(String level) {
		return repository.countByLevelIgnoreCase(level);
	}

}
