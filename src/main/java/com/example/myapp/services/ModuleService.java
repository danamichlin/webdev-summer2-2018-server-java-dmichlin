package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.models.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.models.Course;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable ("courseId") int id, @RequestBody Module module) {
		Optional<Course> optCourse = courseRepository.findById(id);
		if (optCourse.isPresent()) {
			Course actCourse = optCourse.get();
			module.setCourse(actCourse);
			return moduleRepository.save(module);
		}
		else {
			return null;
		}
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
	public Optional<Module> findModuleById(@PathVariable ("moduleId") int id) {
		return moduleRepository.findById(id); 
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
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
		Module optionalModule = moduleRepository.findById(id).get();
		if (optionalModule!=null) {
			optionalModule.setTitle(newModule.getTitle());
			return moduleRepository.save(optionalModule);
		}
		return null;
	}
}
