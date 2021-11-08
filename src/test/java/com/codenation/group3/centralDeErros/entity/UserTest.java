package com.codenation.group3.centralDeErros.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class UserTest {
	
	User userA = new User(1L, "user A", "userA@mail.com", "123");	
	
	@Test
	void testHashCode() {
		User sameObject = new User(1L, "user A", "userA@mail.com", "123");
		
		assertEquals(true, userA.hashCode() == sameObject.hashCode());
	}

	@Test
	void testToString() {
		String response = userA.toString();
		
		assertEquals(
				"User [id=" + userA.getId() + 
				", name=" + userA.getName() + 
				", email=" + userA.getEmail() + 
				", password=" + userA.getPassword() + "]", 
				response);
	}

	@Test
	void testGetAuthorities() {
		String expected = Collections.singletonList("ADMIN").toString();
		
		
		assertEquals(expected, userA.getAuthorities().toString());
	}

	@Test
	void testGetPassword() {
		String expected = "123";
		
		assertEquals(expected, userA.getPassword());
	}

	@Test
	void testGetUsername() {
		String expected = "userA@mail.com";
		
		assertEquals(expected, userA.getUsername()); 
	}

	@Test
	void testIsAccountNonExpired() {
		Boolean expected = true;
		
		assertEquals(expected, userA.isAccountNonExpired());
	}

	@Test
	void testIsAccountNonLocked() {
		Boolean expected = true;
		
		assertEquals(expected, userA.isAccountNonLocked());
	}

	@Test
	void testIsCredentialsNonExpired() {
		Boolean expected = true;
		
		assertEquals(expected, userA.isCredentialsNonExpired());
	}

	@Test
	void testIsEnabled() {
		Boolean expected = true;
		
		assertEquals(expected, userA.isEnabled());
	}
	
	@Test
	void testEqualsObject_shouldReturnTrueWhenEqual() {
		Boolean expected = true;
		
		Boolean response = userA.equals(userA);
		
		assertEquals(expected, response);
	}

	@Test
	void testEqualsObject_shouldReturnFalseWhenNullOrNotEqual() {
		User userB = new User(2L, "not user A", "userB@mail.com", "1234");
		ErrorLog log = new ErrorLog();
		Boolean expected = false;
		Boolean expectedDifferentClass = userA.getClass().toString() == log.getClass().toString();
		
		Boolean responseToNull = userA.equals(null);
		Boolean responseToNotEqual = userA.equals(userB);
		
		assertEquals(expected, responseToNull);
		assertEquals(expected, responseToNotEqual);
		assertEquals(expectedDifferentClass, responseToNotEqual);
	}

	@Test
	void testGetId() {
		Long expected = 1L;
		
		assertEquals(expected, userA.getId());
	}

	@Test
	void testGetName() {
		String expected = "user A";
		
		assertEquals(expected, userA.getName());
	}

	@Test
	void testGetEmail() {
		String expected = "userA@mail.com";
		
		assertEquals(expected, userA.getEmail());
	}

	@Test
	void testSetId() {
		userA.setId(3L);
		
		assertEquals(3L, userA.getId());
	}

	@Test
	void testSetName() {
		userA.setName("Different Name");
		
		assertEquals("Different Name", userA.getName());
	}

	@Test
	void testSetEmail() {
		userA.setEmail("differentemail@mail.com");
		
		assertEquals("differentemail@mail.com", userA.getEmail());
	}

	@Test
	void testSetPassword() {
		userA.setPassword("newpassword123");;
		
		assertEquals("newpassword123", userA.getPassword());
	}

}
