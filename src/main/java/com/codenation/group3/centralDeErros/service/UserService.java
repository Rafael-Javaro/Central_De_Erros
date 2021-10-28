package com.codenation.group3.centralDeErros.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.exceptions.UserAlreadyExistsException;
import com.codenation.group3.centralDeErros.exceptions.UserIncompleteBodyException;
import com.codenation.group3.centralDeErros.exceptions.UserNotFoundException;
import com.codenation.group3.centralDeErros.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository repository;
	
	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public User save(User user) {
		if (user.getName() == null 
			|| user.getEmail() == null 
			|| user.getPassword() == null) {
    		throw new UserIncompleteBodyException();
    	}
		
		if (repository.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException(user.getEmail());
		}
		
		return repository.save(user);
	}
	
	public User update(Long id, User newUser) {
		if (id == null
				|| newUser.getName() == null 
				|| newUser.getEmail() == null 
				|| newUser.getPassword() == null) {
	    		throw new UserIncompleteBodyException();
	    	}
		
		Optional<User> user = repository.findById(id);
		
		if (user.isPresent()) {
			user.get().setName(newUser.getName());
			user.get().setEmail(newUser.getEmail());
			user.get().setPassword(newUser.getPassword());
			
			repository.save(user.get());
			
			return user.get();
		}
		
		throw new UserNotFoundException(id);
	}
	
	public void delete(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));

		repository.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repository.findByEmail(email);
	}
}
