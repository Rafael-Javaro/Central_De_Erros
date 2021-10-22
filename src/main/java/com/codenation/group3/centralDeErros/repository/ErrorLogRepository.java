package com.codenation.group3.centralDeErros.repository;

import com.codenation.group3.centralDeErros.entity.ErrorLog;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
	
	Page<ErrorLog> findByLevelIgnoreCase(String level, Pageable pageable);
	
	Page<ErrorLog> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
	
	Page<ErrorLog> findByLogContainingIgnoreCase(String log, Pageable pageable);
	
	Page<ErrorLog> findByOriginContainingIgnoreCase(String origin, Pageable pageable);
	
	Page<ErrorLog> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	
	Integer countByLevelIgnoreCase(String level);
}
