package com.codenation.group3.centralDeErros.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.codenation.group3.centralDeErros.entity.ErrorLog;
import com.codenation.group3.centralDeErros.exceptions.LogIncompleteBodyException;
import com.codenation.group3.centralDeErros.exceptions.LogNotFoundException;
import com.codenation.group3.centralDeErros.exceptions.LogWrongDateFormatException;
import com.codenation.group3.centralDeErros.repository.ErrorLogRepository;

class ErrorLogServiceTest {
	
	
	ErrorLogRepository repository = mock(ErrorLogRepository.class);
	ErrorLogService errorLogService = new ErrorLogService(repository);
	
	Long errorLogA_id = 1L;
	Long errorLogB_id = 2L;
	ErrorLog errorLogA = mock(ErrorLog.class, "errorLogA");
	ErrorLog errorLogB = mock(ErrorLog.class, "errorLogB");
	List<ErrorLog> errorLogList = Arrays.asList(errorLogA, errorLogB);
	Page<ErrorLog> errorLogPage = new PageImpl<>(errorLogList);
	Pageable pageable = PageRequest.of(0, 3);
	

	@Test
	void findAll_shouldReturnPageOfErrorLog() {
		when(repository.findAll(pageable)).thenReturn(errorLogPage);
		
		Page<ErrorLog> result = errorLogService.findAll(pageable);
		
		verify(repository, times(1)).findAll(pageable);
		
		assertEquals(2, result.getContent().size());
		assertEquals(PageImpl.class, result.getClass());
	}

	@Test
	void findById_shouldReturnCorrectLogWhenFindById() {
		when(repository.findById(errorLogA_id)).thenReturn(Optional.of(errorLogA));
		
		ErrorLog foundLog = errorLogService.findById(errorLogA_id);
		
		verify(repository).findById(errorLogA_id);
		assertEquals(errorLogA, foundLog);
	}
	
	@Test
	void findById_shouldReturnExceptionWhenIdDoesntExist() {
		LogNotFoundException thrown = assertThrows(
				LogNotFoundException.class,
				() -> errorLogService.findById(3L)
		);
	}
	
	@Test
	void testSave_throwsExceptionWhenIncompleteBody() {
		ErrorLog incompleteBody_noLevel = 
				new ErrorLog(1L, null, "log01", "LOG: 01", "service1");
		ErrorLog incompleteBody_noDescription = 
				new ErrorLog(2L, "warning", null, "LOG: 02", "service1");
		ErrorLog incompleteBody_noLog = 
				new ErrorLog(3L, "warning", "log03", null, "service2");
		ErrorLog incompleteBody_noService = 
				new ErrorLog(4L, "error", "log04", "LOG: 04", null);
		
		LogIncompleteBodyException exceptionCase1 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.save(incompleteBody_noLevel) 
		);
		LogIncompleteBodyException exceptionCase2 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.save(incompleteBody_noDescription) 
		);
		LogIncompleteBodyException exceptionCase3 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.save(incompleteBody_noLog) 
		);
		LogIncompleteBodyException exceptionCase4 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.save(incompleteBody_noService) 
		);
	}

	@Test
	void testSave_shouldCallRepositoryWhenCompleteBody() {
		ErrorLog completeBodyLog= 
				new ErrorLog(1L, "warning", "log01", "LOG: 01", "service1");
		when(repository.save(completeBodyLog)).thenReturn(completeBodyLog);
		
		errorLogService.save(completeBodyLog);
		
		verify(repository).save(completeBodyLog);
	}
	
	@Test
	void testUpdate_shouldThrowExceptionIfIncompleteBodyOrNoId() {
		ErrorLog completeBodyLog= 
				new ErrorLog(1L, "warning", "log01", "LOG: 01", "service1");
		ErrorLog incompleteBody_noLevel = 
				new ErrorLog(1L, null, "log01", "LOG: 01", "service1");
		ErrorLog incompleteBody_noDescription = 
				new ErrorLog(2L, "warning", null, "LOG: 02", "service1");
		ErrorLog incompleteBody_noLog = 
				new ErrorLog(3L, "warning", "log03", null, "service2");
		ErrorLog incompleteBody_noService = 
				new ErrorLog(4L, "error", "log04", "LOG: 04", null);
		
		LogIncompleteBodyException exceptionCase_noId = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.update(null, completeBodyLog) 
		);
		LogIncompleteBodyException exceptionCase1 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.update(1L, incompleteBody_noLevel) 
		);
		LogIncompleteBodyException exceptionCase2 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.update(2L, incompleteBody_noDescription) 
		);
		LogIncompleteBodyException exceptionCase3 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.update(3L, incompleteBody_noLog) 
		);
		LogIncompleteBodyException exceptionCase4 = assertThrows(
				LogIncompleteBodyException.class,
				() -> errorLogService.update(3L, incompleteBody_noService) 
		);
	}

	@Test
	void testUpdate_shouldFindExistingLogById() {
		ErrorLog dbLog = 
				new ErrorLog(1L, "warning", "log01", "LOG: 01", "service1");
		ErrorLog updateLog =
				new ErrorLog(null, "warning", "log01Updated", "LOG: 01 with update", "service1");
		
		when(repository.findById(1L)).thenReturn(Optional.of(dbLog));
		
		ErrorLog foundLog = errorLogService.update(1L, updateLog);
		
		assertEquals(1L, foundLog.getId());
	}
	
	@Test
	void testUpdate_shouldThrowExceptionIfLogIsNotFound() {
		ErrorLog dbLog = 
				new ErrorLog(1L, "warning", "log01", "LOG: 01", "service1");
		ErrorLog updateLog =
				new ErrorLog(null, "warning", "log01Updated", "LOG: 01 with update", "service1");
		
		LogNotFoundException thrown = assertThrows(
				LogNotFoundException.class,
				() -> errorLogService.update(3L, updateLog)
		);
	}
	
	@Test
	void testUpdate_shouldReturnUpdateLogWhenFoundAndComplete() {
		ErrorLog dbLog = 
				new ErrorLog(1L, "warning", "log01", "LOG: 01", "service1");
		ErrorLog updateLog =
				new ErrorLog(1L, "warning", "log01Updated", "LOG: 01 with update", "service1");
		
		when(repository.findById(dbLog.getId())).thenReturn(Optional.of(dbLog));
		when(repository.save(updateLog)).thenReturn(updateLog);
		
		ErrorLog updated = errorLogService.update(dbLog.getId(), updateLog);
		
		assertEquals(updateLog, updated);
	}

	@Test
	void testDelete_shouldThrowExceptionIfIdNotExists() {
		LogNotFoundException thrown = assertThrows(
				LogNotFoundException.class,
				() -> errorLogService.delete(3L)
		);
	}
	
	@Test
	void testDelete_shouldCallDeleteMethodIfIdExists() {
		ErrorLog dbLog = 
				new ErrorLog(1L, "warning", "log01", "LOG: 01", "service1");
		when(repository.findById(1L)).thenReturn(Optional.of(dbLog));
		
		errorLogService.delete(1L);
		
		verify(repository).delete(dbLog);
	}

	@Test
	void testFindByLevel_shouldCallFindByLevelMethod() {
		errorLogService.findByLevel("warning", pageable);
		
		verify(repository).findByLevelIgnoreCase("warning", pageable);
	}

	@Test
	void testFindByDescription_ShouldCallFindByDescriptionMethod() {
		errorLogService.findByDescription("description", pageable);
		
		verify(repository).findByDescriptionContainingIgnoreCase("description", pageable);
	}

	@Test
	void testFindByLog_shouldCallFindByLogMethod() {
		errorLogService.findByLog("LOG 01: Some error", pageable);
		
		verify(repository).findByLogContainingIgnoreCase("LOG 01: Some error", pageable);
	}

	@Test
	void testFindByOrigin_shouldCallFindByOriginMethod() {
		errorLogService.findByOrigin("service 01", pageable);
		
		verify(repository).findByOriginContainingIgnoreCase("service 01", pageable);
	}

	@Test
	void testFindByDate_shouldDallFindByDateMethod() {
		LocalDate from = LocalDate.of(2007, 11, 3);
		LocalDate to = LocalDate.of(2014, 10, 13);
		errorLogService.findByDate("2007-11-03", "2014-10-13", pageable);
		
		verify(repository).findByCreatedAtBetween(
				LocalDateTime.of(from, LocalTime.of(0, 0)), 
				LocalDateTime.of(to, LocalTime.of(23, 59)), 
				pageable);
	}
	
	@Test
	void testFindByDate_shouldThrowExceptionIfWrongDateFormat() {
		assertThrows(
				LogWrongDateFormatException.class,
				() -> errorLogService.findByDate("wrong", "format", pageable)
		);
	}

	@Test
	void testCountByLevel() {
		errorLogService.countByLevel("warning");
		
		verify(repository).countByLevelIgnoreCase("warning");
	}

}
