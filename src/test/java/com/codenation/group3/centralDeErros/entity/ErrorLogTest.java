package com.codenation.group3.centralDeErros.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ErrorLogTest {
	
	ErrorLog logA = new ErrorLog(1L, "error", "an error occured", "LOG 67: ERROR", "service x");

	@Test
	void testHashCode() {
		ErrorLog sameObject = new ErrorLog(1L, "error", "an error occured", "LOG 67: ERROR", "service x");
		
		assertEquals(true, logA.hashCode() == sameObject.hashCode());
	}

	@Test
	void testEqualsObject_shouldReturnTrueWhenEqual() {
		assertEquals(
				true, 
				new ErrorLog().toString().equals(new ErrorLog().toString()));
	}
	
	@Test
	void testEqualsObject_shouldReturnFalseWhenNullOrNotEqual() {
		ErrorLog logB = new ErrorLog(2L, "error", "error log B", "LOG B", "service y");
		ErrorLog log = new ErrorLog();
		Boolean expected = false;
		Boolean expectedDifferentClass = logA.getClass().toString() == log.getClass().toString();
		
		Boolean responseToNull = logA.equals(null);
		Boolean responseToNotEqual = logA.equals(logB);
		
		assertEquals(expected, responseToNull);
		assertEquals(expected, responseToNotEqual);
		assertEquals(expectedDifferentClass, responseToNotEqual);
	}

	@Test
	void testToString() {
		String response = logA.toString();
		
		assertEquals(
				"ErrorLog [id=" + logA.getId() + 
				", level=" + logA.getLevel() + 
				", description=" + logA.getDescription() + 
				", log=" + logA.getLog() + 
				", origin="	+ logA.getOrigin() + "]", 
				response);
	}

	@Test	
	void testGetId() {
		Long expected = 1L;
		
		assertEquals(expected, logA.getId());
	}

	@Test
	void testGetLevel() {
		String expected = "error";
		
		assertEquals(expected, logA.getLevel());
	}

	@Test
	void testGetDescription() {
		String expected = "an error occured";
		
		assertEquals(expected, logA.getDescription());
	}

	@Test
	void testGetLog() {
		String expected = "LOG 67: ERROR";
		
		assertEquals(expected, logA.getLog());
	}

	@Test
	void testGetOrigin() {
		String expected = "service x";
		
		assertEquals(expected, logA.getOrigin());
	}

	@Test
	void testSetId() {
		logA.setId(3L);
		
		assertEquals(3L, logA.getId());
	}

	@Test
	void testSetLevel() {
		logA.setLevel("warning");;
		
		assertEquals("warning", logA.getLevel());
	}

	@Test
	void testSetDescription() {
		logA.setDescription("a new description");
		
		assertEquals("a new description", logA.getDescription());
	}

	@Test
	void testSetLog() {
		logA.setLog("NEW LOG");
		
		assertEquals("NEW LOG", logA.getLog());
	}

	@Test
	void testSetOrigin() {
		logA.setOrigin("service y");
		
		assertEquals("service y", logA.getOrigin());
	}

}
