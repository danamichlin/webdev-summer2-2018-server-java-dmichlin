package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.models.Lesson;
import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.models.Module;
import com.example.myapp.services.CourseService;
import com.example.myapp.models.Course;

@RestController
public class LessonService {
	
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@RequestBody Lesson lesson) {
		return lessonRepository.save(lesson);
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteLesson(@PathVariable ("lessonId") int id) {
		lessonRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		List<Lesson> lessonList = (List<Lesson>) lessonRepository.findAll();
		return lessonList;
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Optional<Lesson> findCourseById(@PathVariable ("lessonId") int id) {
		return lessonRepository.findById(id); 
	}
	
	//TODO 
	// why 
	@GetMapping("/api/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int id) {
		ModuleService moduleService = new ModuleService();
		Optional<Module> optionalModule = moduleService.findModuleById(id);
		if (optionalModule.isPresent()) {
			Module actualModule = optionalModule.get();
			return actualModule.getLessons();
		}
		else {
			// TODO
			// should this be null or throw an exception/message that no course exists?
			return null;
		}
	}
	
	@PutMapping("/api/lesson/{lessonId}")
	public Lesson updateLesson(
			@PathVariable ("lessonId") int id, 
			@RequestBody Lesson newLesson) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(id);
		if (optionalLesson.isPresent()) {
			newLesson.setId(id);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
}
