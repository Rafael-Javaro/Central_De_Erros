package com.codenation.group3.centralDeErros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.repository.ErrorLogRepository;

@Service
public class ErrorLogService {
	
	private final ErrorLogRepository repository;
	
	@Autowired
	public ErrorLogService(ErrorLogRepository repository) {
		this.repository = repository;
	}
	
	public List<ErrorLog> findAll() {
		return repository.findAll();
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
			
			return log.get();
		}
		
		return null;
	}
	
	public void delete(Long id) {
		Optional<ErrorLog> log = repository.findById(id);

        log.ifPresent(repository::delete);
	}

}
