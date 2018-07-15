package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.myapp.models.Course;
import com.example.myapp.models.User;
import com.example.myapp.repositories.CourseRepository;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable ("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		List<Course> courseList = (List<Course>) courseRepository.findAll();
		return courseList;
	}
	
	@GetMapping("/api/course/{courseId}")
	public Optional<Course> findCourseById(@PathVariable ("courseId") int id) {
		return courseRepository.findById(id); 
	}
	
	@PutMapping("/api/course/{courseId}")
	public Course updateCourse(
			@PathVariable ("courseId") int id, 
			@RequestBody Course newCourse) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if (optionalCourse.isPresent()) {
			newCourse.setId(id);
			return courseRepository.save(newCourse);
		}
		return null;
	}
	
	

}
