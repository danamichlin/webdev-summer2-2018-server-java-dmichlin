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
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		User u = userRepository.save(user);
		return u;
	}
	
	// TODO
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserByID(@PathVariable ("userId") Integer id) {
		return userRepository.findById(id); 
	}
	
	//TODO 
	@PutMapping("/api/user/{userId}")
	public void updateUser(@PathVariable ("userId") Integer id, 
			@RequestBody User user) {
//		Optional<User> optionalUser = userRepository.findById(id);
//		if (optionalUser.isPresent()) {
//			User actualUser = optionalUser.get();
//			actualUser.set
		}
		
		
		
	
	
	//TODO 
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable ("userId") Integer userId) {
		
	}
}
