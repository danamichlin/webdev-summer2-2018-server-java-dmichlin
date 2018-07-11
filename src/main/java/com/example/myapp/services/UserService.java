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
		
		List<User> list = (List<User>) userRepository.findAll();
		return list;
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserByID(@PathVariable ("userId") int id) {
		return userRepository.findById(id); 
	}
	
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(
			@PathVariable ("userId") int id, 
			@RequestBody User newUser) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			newUser.setId(id);
			return userRepository.save(newUser);
		}
		return null;
	}

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable ("userId") int id) {
		userRepository.deleteById(id);
	}
	
	// registration 
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	//login
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		return userRepository.findUserByCredentials(username, password);
	}
}
