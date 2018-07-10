package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;

import java.util.*;

@RestController
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")	
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	// TODO
	@PostMapping("/api/user")
	public User createUser() {
		return null;
	}
	
	// TODO
	@GetMapping("/api/user/{userId}")
	public User findUserByID(Integer id) {
		return null; 
	}
	
	//TODO 
	@PutMapping("/api/user/{userId}")
	public void updateUser(Integer userId) {
		
	}
	
	//TODO 
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(Integer userId) {
		
	}
}
