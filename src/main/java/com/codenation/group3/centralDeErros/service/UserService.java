package com.codenation.group3.centralDeErros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.repository.UserRepository;

@Service
public class UserService {
	
	UserRepository repository;
	
	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public User update(Long id, User newUser) {
		Optional<User> user = repository.findById(id);
		
		if (user.isPresent()) {
			user.get().setName(newUser.getName());
			user.get().setEmail(newUser.getEmail());
			user.get().setPassword(newUser.getPassword());
			
			return user.get();
		}
		
		return null;
	}
	
	public void delete(Long id) {
		Optional<User> user = repository.findById(id);
		
		if (user.isPresent()) {
			repository.delete(user.get());
		}
	}
	
	

}