package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.models.Module;
import com.example.myapp.services.CourseService;
import com.example.myapp.models.Course;

@RestController
public class ModuleService {
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@RequestBody Module module) {
		return moduleRepository.save(module);
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable ("moduleId") int id) {
		moduleRepository.deleteById(id);
	}
	
	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		List<Module> moduleList = (List<Module>) moduleRepository.findAll();
		return moduleList;
	}
	
	@GetMapping("/api/module/{moduleId}")
	public Optional<Module> findCourseById(@PathVariable ("moduleId") int id) {
		return moduleRepository.findById(id); 
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int id) {
		CourseService courseService = new CourseService();
		Optional<Course> optionalCourse = courseService.findCourseById(id);
		if (optionalCourse.isPresent()) {
			Course actualCourse = optionalCourse.get();
			return actualCourse.getModules();
		}
		else {
			// TODO
			// should this be null or throw an exception/message that no course exists?
			return null;
		}
	}
	
	@PutMapping("/api/module/{moduleId}")
	public Module updateModule(
			@PathVariable ("moduleId") int id, 
			@RequestBody Module newModule) {
		Optional<Module> optionalModule = moduleRepository.findById(id);
		if (optionalModule.isPresent()) {
			newModule.setId(id);
			return moduleRepository.save(newModule);
		}
		return null;
	}
}
