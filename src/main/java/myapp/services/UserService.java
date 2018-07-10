package myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import myapp.models.User;
import myapp.repositories.UserRepository;

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
	public void createUser() {
		
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
