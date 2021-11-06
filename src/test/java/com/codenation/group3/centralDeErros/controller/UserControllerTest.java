package com.codenation.group3.centralDeErros.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.codenation.group3.centralDeErros.dtos.UserDTO;
import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.service.UserService;

class UserControllerTest {
	
	UserService service = mock(UserService.class);
	UserController userController = new UserController(service);
	
	User userA = new User(1L, "user A", "userA@mail.com", "123456");
	User userB = new User(2L, "user B", "userB@mail.com", "12345");
	User userC = new User(3L, "user C", "userC@mail.com", "123123");
	List<User> usersList = Arrays.asList(userA, userB, userC);
	Page<User> usersPage = new PageImpl<>(usersList);
	Pageable pageable = PageRequest.of(0, 3);
	
	UserDTO dtoA = new UserDTO(userA.getId(), userA.getName(), userA.getEmail(), userA.getCreatedAt());
	UserDTO dtoB = new UserDTO(userB.getId(), userB.getName(), userB.getEmail(), userB.getCreatedAt());
	UserDTO dtoC = new UserDTO(userC.getId(), userC.getName(), userC.getEmail(), userC.getCreatedAt());
	List<UserDTO> userDTOList = Arrays.asList(dtoA, dtoB, dtoC);
	
	@Test
	void testFindAll_shouldCallUserService() {
		service.findAll(pageable);
		
		verify(service).findAll(pageable);
	}

	@Test
	void testFindAll_shouldReturnListOfUserDTOAndStatusOK() {
		ResponseEntity<List<UserDTO>> expected = 
				new ResponseEntity<>(userDTOList, HttpStatus.OK);
		when(service.findAll(pageable)).thenReturn(usersPage);
		
		ResponseEntity<List<UserDTO>> response = userController.findAll(pageable);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testFindById_shouldCallUserService() {
		when(service.findById(1L)).thenReturn(userA);
		
		userController.findById(1L);
		
		verify(service).findById(1L);
	}

	@Test
	void testFindById_ShouldReturnResponseEntityOKWhenFound() {
		when(service.findById(1L)).thenReturn(userA);
		ResponseEntity<UserDTO> expected = new ResponseEntity<>(dtoA, HttpStatus.OK);
		
		ResponseEntity<UserDTO> response = userController.findById(1L);
		
		assertEquals(expected, response);
	}

	@Test
	void testCreateNewUser_shouldCallUserServiceSaveMethod() {
		when(service.save(userA)).thenReturn(userA);
		
		userController.createNewUser(userA);
		
		verify(service).save(userA);
	}
	
	@Test
	void testCreateNewUser_shouldReturnResponseEntityCREATED() {
		when(service.save(userA)).thenReturn(userA);
		
		ResponseEntity<UserDTO> expected = 
				new ResponseEntity<UserDTO>(dtoA, HttpStatus.CREATED);
		
		ResponseEntity<UserDTO> response = userController.createNewUser(userA);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testUpdateUser_shouldCallUserServiceUpdateMethod() {
		when(service.update(1L, userA)).thenReturn(userA);
		
		userController.updateUser(1L, userA);
		
		verify(service).update(1L, userA);
	}

	@Test
	void testUpdateUser_shouldReturnResponseEntityACCEPTED() {
		when(service.update(1L, userA)).thenReturn(userA);
		
		ResponseEntity<UserDTO> expected = 
				new ResponseEntity<UserDTO>(dtoA, HttpStatus.ACCEPTED);
		
		ResponseEntity<UserDTO> response = userController.updateUser(1L, userA);
		
		assertEquals(expected, response);
	}
	
	@Test	
	void testDeleteUser_shouldCallServiceDeleteMethod() {
		userController.deleteUser(1L);
		
		verify(service).delete(1L);
	}

	@Test
	void testDeleteUser_shouldReturnResponseEntityACCEPTED() {
		Long userId = 1L;
		ResponseEntity<String> expected = new ResponseEntity<String>(
        		"User " + userId + " successfully deleted.", 
        		HttpStatus.ACCEPTED);
		
		ResponseEntity<String> response = userController.deleteUser(userId);
		
		assertEquals(expected, response);
	}

}
