package com.codenation.group3.centralDeErros.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.exceptions.UserAlreadyExistsException;
import com.codenation.group3.centralDeErros.exceptions.UserIncompleteBodyException;
import com.codenation.group3.centralDeErros.exceptions.UserNotFoundException;
import com.codenation.group3.centralDeErros.repository.UserRepository;

class UserServiceTest {
	
	UserRepository repository = mock(UserRepository.class);
	UserService userService = new UserService(repository);
	
	User userA = new User(1L, "user A", "userA@mail.com", "123456");
	User userB = new User(2L, "user B", "userB@mail.com", "12345");
	User userC = new User(3L, "user C", "userC@mail.com", "123123");
	List<User> usersList = Arrays.asList(userA, userB, userC);
	Page<User> usersPage = new PageImpl<>(usersList);
	Pageable pageable = PageRequest.of(0, 3);

	@Test
	void testFindAll_shouldReturnPageOfUsers() {
		when(repository.findAll(pageable)).thenReturn(usersPage);
		
		Page<User> result = userService.findAll(pageable);
		
		verify(repository, times(1)).findAll(pageable);
		
		assertEquals(3, result.getContent().size());
		assertEquals(PageImpl.class, result.getClass());
	}

	@Test
	void testFindById_shouldReturnCorrectUserWhenIdExists() {
		when(repository.findById(userA.getId())).thenReturn(Optional.of(userA));
		
		User foundUser = userService.findById(userA.getId());
		
		verify(repository).findById(userA.getId());
		assertEquals(userA, foundUser);
	}
	
	@Test
	void testFindById_ShouldReturnExceptionWhenIdNotExists() {
		assertThrows(
				UserNotFoundException.class,
				() -> userService.findById(14L)
		);
	}

	@Test
	void testSave_shouldThrowExceptionWhenIncompleteBody() {
		User user_noName = new User(4L, null, "incomp@mail.com", "123");
		User user_noEmail = new User(4L, "incomplete user", null, "123");
		User user_noPassword = 
				new User(4L, "incomplete user", "incomp@mail.com", null);
		
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.save(user_noName) 
		);
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.save(user_noEmail) 
		);
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.save(user_noPassword) 
		);
	}
	
	@Test
	void testSave_shouldThrowExceptionIfUserAlreadyExists() {
		when(repository.findByEmail(userA.getEmail())).thenReturn(userA);
		
		assertThrows(
				UserAlreadyExistsException.class,
				() -> userService.save(userA) 
		);
	}
	
	@Test
	void testSave_shouldCallRepositoryWhenCompleteBody() {
		when(repository.save(userA)).thenReturn(userA);
		
		userService.save(userA);
		
		verify(repository).save(userA);
	}
	
	@Test
	void testUpdate_shouldThrowExceptionIfIncompleteBodyOrNoId() {
		User user_noName = new User(4L, null, "incomp@mail.com", "123");
		User user_noEmail = new User(4L, "incomplete user", null, "123");
		User user_noPassword = 
				new User(4L, "incomplete user", "incomp@mail.com", null);
		
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.update(1L, user_noName) 
		);
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.update(1L, user_noEmail) 
		);
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.update(1L, user_noPassword) 
		);
		
		// no Id, complete Body
		assertThrows(
				UserIncompleteBodyException.class,
				() -> userService.update(null, userA) 
		);
	}

	@Test
	void testUpdate_shouldFindExistingUserById() {
		User updateUser =
				new User(null, "update username", "update@mail.com", "newpassword");
		
		when(repository.findById(1L)).thenReturn(Optional.of(userA));
		
		User foundUser = userService.update(1L, updateUser);
		
		assertEquals(1L, foundUser.getId());
	}
	
	@Test
	void testUpdate_shouldThrowExceptionIfIdNotFound() {
		User updateUser =
				new User(null, "update username", "update@mail.com", "newpassword");
		
		assertThrows(
				UserNotFoundException.class,
				() -> userService.update(3L, updateUser)
		);
	}
	
	@Test
	void testUpdate_shouldReturnUpdateUserWhenFoundAndComplete() {
		User updateUser =
				new User(1L, "update username", "update@mail.com", "newpassword");
		
		when(repository.findById(userA.getId())).thenReturn(Optional.of(userA));
		when(repository.save(updateUser)).thenReturn(updateUser);
		
		User updated = userService.update(userA.getId(), updateUser);
		
		assertEquals(updateUser, updated);
	}
	
	@Test
	void testDelete_shouldThrowExceptionIfIdNotExists() {
		assertThrows(
				UserNotFoundException.class,
				() -> userService.delete(3L)
		);
	}

	@Test
	void testDelete_shouldCallDeleteMethodIfIdExists() {
		when(repository.findById(1L)).thenReturn(Optional.of(userA));
		
		userService.delete(1L);
		
		verify(repository).delete(userA);
	}

	@Test
	void testLoadUserByUsername_shouldCallFindByEmailMethod() {
		String anyEmail = "anyemail@mail.com";
		userService.loadUserByUsername(anyEmail);
		
		verify(repository).findByEmail(anyEmail);
	}

}
