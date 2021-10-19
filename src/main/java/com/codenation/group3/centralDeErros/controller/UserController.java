package com.codenation.group3.centralDeErros.controller;

import com.codenation.group3.centralDeErros.dtos.UserDTO;
import com.codenation.group3.centralDeErros.entity.User;
import com.codenation.group3.centralDeErros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
    	this.userService = userService;
    }

//    @GetMapping
//    public ResponseEntity<List<UserDTO>> findAll() {
//    	List<UserDTO> dtoList = userService.findAll()
//    			.stream()
//    			.map(this::toUserDTO)
//    			.collect(Collectors.toList());
//    	
//        return new ResponseEntity<>(dtoList, HttpStatus.OK);
//    }
    
//    @GetMapping
//    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
//    	Page<UserDTO> dtoList = userService.findAll(pageable)
//    			.stream()
//    			.map(this::toUserDTO)
//    			.collect(Collectors.toList());
//    	
//        return new ResponseEntity<>(dtoList, HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(Pageable pageable) {
    	Page<User> page = userService.findAll(pageable);
    	
    	List<UserDTO> response = page.getContent().stream()
    			.map(this::toUserDTO)
    			.collect(Collectors.toList());
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        UserDTO user = this.toUserDTO(userService.findById(id));
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@RequestBody User newUser) {
    	UserDTO user = this.toUserDTO(userService.save(newUser));
    	
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody User newUser) {
    	UserDTO user = this.toUserDTO(userService.update(id, newUser));
    	
    	return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }
    
    private UserDTO toUserDTO(User user) {
    	UserDTO dto = new UserDTO();
    	dto.setId(user.getId());
    	dto.setName(user.getName());
    	dto.setEmail(user.getEmail());
    	dto.setCreatedAt(user.getCreatedAt());
    	
    	return dto;
    }

}













