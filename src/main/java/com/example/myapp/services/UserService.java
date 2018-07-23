package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;

import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	//User admin services
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
	public User register(@RequestBody User user, HttpSession session, HttpServletResponse response) {
		String username = user.getUsername();
		User existingUser = userRepository.findUserByUsername(username);
		if (existingUser != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		User currentUser = userRepository.save(user);
		session.setAttribute("currentUser", currentUser);
		return currentUser;
	}
	
	//checkLogin
	@GetMapping("/checkLogin")
	public User checkLogin(HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}
	
	//login
	@PostMapping("/login")
	public User login(@RequestBody User user, HttpServletResponse response, HttpSession session) {
		String username = user.getUsername();
		String password = user.getPassword();
		User newUser = userRepository.findUserByCredentials(username, password);
		if (newUser == null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return newUser;
		}
		session.setAttribute("currentUser", newUser);
		return newUser;
	}
	
	//profile
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser, HttpSession session) {
		
		User currentUser = (User) session.getAttribute("currentUser");

		int id = currentUser.getId();
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			newUser.setId(id);
			User actualUser = optionalUser.get();
			actualUser.setUsername(newUser.getUsername());
			actualUser.setPassword(newUser.getPassword());
			actualUser.setEmail(newUser.getEmail());
			actualUser.setFirstName(newUser.getFirstName());
			actualUser.setLastName(newUser.getLastName());
			actualUser.setRole(newUser.getRole());
			actualUser.setPhone(newUser.getPhone());
			actualUser.setDateOfBirth(newUser.getDateOfBirth());

			return userRepository.save(actualUser);
		}
		return null;	
	}
	
	@GetMapping("/api/profile")
	public User getUserProfile(HttpSession session) {
		User cu = (User) session.getAttribute("currentUser");
		return cu;
	}

	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
