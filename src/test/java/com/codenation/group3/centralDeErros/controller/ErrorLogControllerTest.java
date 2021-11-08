package com.codenation.group3.centralDeErros.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.codenation.group3.centralDeErros.dtos.ErrorLogDTO;
import com.codenation.group3.centralDeErros.dtos.UserDTO;
import com.codenation.group3.centralDeErros.dtos.fullErrorLogDTO;
import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.service.ErrorLogService;

class ErrorLogControllerTest {
	
	ErrorLogService service = mock(ErrorLogService.class);
	ErrorLogController errorLogController = new ErrorLogController(service);
	
	ErrorLog logA = new ErrorLog(1L, "warning", "a warning for bad code", "LOG25: BAD CODE", "service x");
	ErrorLog logB = new ErrorLog(2L, "error", "error in line 257", "LOG28: NULL POINTER", "service y");
	List<ErrorLog> errorLogList = Arrays.asList(logA, logB);
	Page<ErrorLog> errorLogPage = new PageImpl<>(errorLogList);
	
	ErrorLogDTO dtoA = new ErrorLogDTO(logA.getId(), logA.getLevel(), logA.getDescription(), logA.getOrigin());
	ErrorLogDTO dtoB = new ErrorLogDTO(logB.getId(), logB.getLevel(), logB.getDescription(), logB.getOrigin());
	fullErrorLogDTO fullDtoA = 
			new fullErrorLogDTO(logA.getId(), logA.getLevel(), logA.getDescription(), logA.getLog(), logA.getOrigin(), logA.getCreatedAt());
	fullErrorLogDTO fullDtoB = 
			new fullErrorLogDTO(logB.getId(), logB.getLevel(), logB.getDescription(), logB.getLog(), logB.getOrigin(), logB.getCreatedAt());
	List<ErrorLogDTO> errorLogDTOList = Arrays.asList(dtoA, dtoB);
	List<fullErrorLogDTO> fullErrorLogDTOList = Arrays.asList(fullDtoA, fullDtoB);
	Pageable pageable = PageRequest.of(0, 3);

	@Test
	void testFindAll_shouldCallErrorLogServiceFindAllMethod() {
		service.findAll(pageable);
		
		verify(service).findAll(pageable);
	}
	
	@Test
	void testFindAll_shouldReturnListOfErrorLogDTOAndStatusOK() {
		ResponseEntity<List<ErrorLogDTO>> expected = 
				new ResponseEntity<>(errorLogDTOList, HttpStatus.OK);
		when(service.findAll(pageable)).thenReturn(errorLogPage);
		
		ResponseEntity<List<ErrorLogDTO>> response = errorLogController.findAll(pageable);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testFindByIdShouldCallErrorLogService() {
		when(service.findById(1L)).thenReturn(logA);
		
		errorLogController.findById(1L);
		
		verify(service).findById(1L);
	}

	@Test
	void testFindById_shouldReturnResponseEntityOKWhenFound() {
		when(service.findById(1L)).thenReturn(logA);
		ResponseEntity<fullErrorLogDTO> expected = 
				new ResponseEntity<>(fullDtoA, HttpStatus.OK);
		
		ResponseEntity<fullErrorLogDTO> response = errorLogController.findById(1L);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testCreateNewErrorLog_shouldCallErrorLogServiceSaveMethod() {
		when(service.save(logA)).thenReturn(logA);
		
		errorLogController.createNewErrorLog(logA);
		
		verify(service).save(logA);
	}

	@Test
	void testCreateNewErrorLog_shouldReturnResponseEntityCREATED() {
		when(service.save(logA)).thenReturn(logA);
		
		ResponseEntity<ErrorLogDTO> expected = 
				new ResponseEntity<ErrorLogDTO>(dtoA, HttpStatus.CREATED);
		
		ResponseEntity<ErrorLogDTO> response = errorLogController.createNewErrorLog(logA);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testUpdateErrorLog_shouldCallErrorLogServiceUpdateMethod() {
		when(service.update(1L, logA)).thenReturn(logA);
		
		errorLogController.updateErrorLog(1L, logA);
		
		verify(service).update(1L, logA);
	}

	@Test
	void testUpdateErrorLog_shouldReturnResponseEntityACCEPTED() {
		when(service.update(1L, logA)).thenReturn(logA);
		
		ResponseEntity<ErrorLogDTO> expected = 
				new ResponseEntity<ErrorLogDTO>(dtoA, HttpStatus.ACCEPTED);
		
		ResponseEntity<ErrorLogDTO> response = errorLogController.updateErrorLog(1L, logA);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testDeleteErrorLog_shouldCallServiceDeleteMethod() {
		errorLogController.deleteErrorLog(1L);
		
		verify(service).delete(1L);
	}

	@Test
	void testDeleteErrorLog_shouldReturnResponseEntityACCEPTED() {
		Long errorLogId = 1L;
		ResponseEntity<String> expected = new ResponseEntity<String>(
        		"Log " + errorLogId + " successfully deleted.", 
        		HttpStatus.ACCEPTED);
		
		ResponseEntity<String> response = errorLogController.deleteErrorLog(errorLogId);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testFindByLevel_shouldCallErrorLogServiceFindByLevelMethod() {
		when(service.findByLevel("warning", pageable)).thenReturn(errorLogPage);
		
		errorLogController.findByLevel("warning", pageable);
		
		verify(service).findByLevel("warning", pageable);
	}

	@Test
	void testFindByLevel_shouldReturnFullErrorLogDTOListAndStatusOK() {
		when(service.findByLevel("warning", pageable)).thenReturn(errorLogPage);
		
		ResponseEntity<List<fullErrorLogDTO>> expected = 
				new ResponseEntity<>(fullErrorLogDTOList, HttpStatus.OK);
		
		ResponseEntity<List<fullErrorLogDTO>> response = 
				errorLogController.findByLevel("warning", pageable);
		
		assertEquals(expected, response);
	}

	@Test
	void testFindByDescription_shouldCallErrorLogServiceFindByDescriptionMethod() {
		when(service.findByDescription("error description", pageable)).thenReturn(errorLogPage);
		
		errorLogController.findByDescription("error description", pageable);
		
		verify(service).findByDescription("error description", pageable);
	}
	
	@Test
	void testFindByDescription_shouldReturnFullErrorLogDTOListAndStatusOK() {
		when(service.findByDescription("error description", pageable)).thenReturn(errorLogPage);
		
		ResponseEntity<List<fullErrorLogDTO>> expected = 
				new ResponseEntity<>(fullErrorLogDTOList, HttpStatus.OK);
		
		ResponseEntity<List<fullErrorLogDTO>> response = 
				errorLogController.findByDescription("error description", pageable);
		
		assertEquals(expected, response);
	}

	@Test
	void testFindByLog_shouldCallErrorLogServiceFindByLogMethod() {
		when(service.findByLog("LOG 13: MAX LINES PER FUNCTION", pageable)).thenReturn(errorLogPage);
		
		errorLogController.findByLog("LOG 13: MAX LINES PER FUNCTION", pageable);
		
		verify(service).findByLog("LOG 13: MAX LINES PER FUNCTION", pageable);
	}
	
	@Test
	void testFindByLog_shouldReturnFullErrorLogDTOListAndStatusOK() {
		when(service.findByLog("LOG 13: MAX LINES PER FUNCTION", pageable)).thenReturn(errorLogPage);
		
		ResponseEntity<List<fullErrorLogDTO>> expected = 
				new ResponseEntity<>(fullErrorLogDTOList, HttpStatus.OK);
		
		ResponseEntity<List<fullErrorLogDTO>> response = 
				errorLogController.findByLog("LOG 13: MAX LINES PER FUNCTION", pageable);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testFindByOrigin_shouldCallErrorLogServiceFindByOriginMethod() {
		when(service.findByOrigin("service y", pageable)).thenReturn(errorLogPage);
		
		errorLogController.findByOrigin("service y", pageable);
		
		verify(service).findByOrigin("service y", pageable);
	}
	
	@Test
	void testFindByOrigin_shouldReturnFullErrorLogDTOListAndStatusOK() {
		when(service.findByOrigin("service y", pageable)).thenReturn(errorLogPage);
		
		ResponseEntity<List<fullErrorLogDTO>> expected = 
				new ResponseEntity<>(fullErrorLogDTOList, HttpStatus.OK);
		
		ResponseEntity<List<fullErrorLogDTO>> response = 
				errorLogController.findByOrigin("service y", pageable);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testFindByDate_shouldCallErrorLogServiceFindByDateMethod() throws ParseException {
		when(service.findByDate("2007-11-3", "2014-10-13", pageable)).thenReturn(errorLogPage);
		
		errorLogController.findByDate("2007-11-3", "2014-10-13", pageable);
		
		verify(service).findByDate("2007-11-3", "2014-10-13", pageable);
	}

	@Test
	void testFindByDate_shouldReturnListOfFullErrorLogDTOAndStatusOK() throws ParseException {
		when(service.findByDate("2007-11-3", "2014-10-13", pageable)).thenReturn(errorLogPage);

		ResponseEntity<List<fullErrorLogDTO>> expected = 
				new ResponseEntity<>(fullErrorLogDTOList, HttpStatus.OK);
		
		ResponseEntity<List<fullErrorLogDTO>> response = 
				errorLogController.findByDate("2007-11-3", "2014-10-13", pageable);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testCountByLevel_shouldCallErrorLogServiceCountByLevelMethod() {
		Integer logsByLevel = 2;
		when(service.countByLevel("warning")).thenReturn(logsByLevel);
		
		errorLogController.countByLevel("warning");
		
		verify(service).countByLevel("warning");
	}

	@Test
	void testCountByLevel_shouldReturnIntegerAndStatusOK() {
		Integer logsByLevel = 2;
		when(service.countByLevel("warning")).thenReturn(logsByLevel);

		ResponseEntity<Integer> expected = 
				new ResponseEntity<>(logsByLevel, HttpStatus.OK);
		
		ResponseEntity<Integer> response = 
				errorLogController.countByLevel("warning");
		
		assertEquals(expected, response);
	}

}
