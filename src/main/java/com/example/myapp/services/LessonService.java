package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.repositories.ModuleRepository;
import com.example.myapp.models.Module;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	
	@Autowired
	LessonRepository lessonRepository;
	@Autowired 
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable ("courseId") int cId, 
			@PathVariable("moduleId") int mId, @RequestBody Lesson lesson) {
			Optional<Module> optModule = moduleRepository.findById(mId);
			if (optModule.isPresent()) {
				Module actModule = optModule.get();
				lesson.setModule(actModule);
				return lessonRepository.save(lesson);
			}
			else {
				return null;
			}
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable ("lessonId") int id) {
		lessonRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		List<Lesson> lessonList = (List<Lesson>) lessonRepository.findAll();
		return lessonList;
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Optional<Lesson> findLessonById(@PathVariable ("lessonId") int id) {
		return lessonRepository.findById(id); 
	}
	
	@GetMapping("/api/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int id) {	
		Optional<Module> optionalModule = moduleRepository.findById(id);
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
		Lesson lesson = lessonRepository.findById(id).get();
		if (lesson != null) {
			lesson.setTitle(newLesson.getTitle());
			//newLesson.setId(id);
			return lessonRepository.save(lesson);
		}
		return null;
	}
}
