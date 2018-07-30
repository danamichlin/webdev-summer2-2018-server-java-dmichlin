package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.myapp.repositories.WidgetRepository;
import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Module;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.models.Widget;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		List<Widget> widgetList = (List<Widget>) widgetRepository.findAll();
		return widgetList;
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Optional<Widget> findWidgetById(@PathVariable ("widgetId") int id) {
		return widgetRepository.findById(id); 
	}
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int id) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(id);
		if (optionalLesson.isPresent()) {
			Lesson actualLesson = optionalLesson.get();
			return actualLesson.getWidgets();
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(@PathVariable ("lessonId") int id, 
			@RequestBody Widget widget) {
		Optional<Lesson> optLesson = lessonRepository.findById(id);
		if (optLesson.isPresent()) {
			Lesson actLesson = optLesson.get();
			widget.setLesson(actLesson);
			return widgetRepository.save(widget);
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/api/widget")
	public List<Widget> saveWidgets(@RequestBody List<Widget> widgets) {
		List<Widget> savedWidgets = new ArrayList<Widget>();
		widgetRepository.deleteAll();
		for (Widget widget: widgets) {
			savedWidgets.add(widgetRepository.save(widget));
		}
		return savedWidgets;
	}
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(
			@PathVariable ("widgetId") int id, 
			@RequestBody Widget newWidget) {
		Widget optionalWidget = widgetRepository.findById(id).get();
		if (optionalWidget!=null) {
			optionalWidget.setTitle(newWidget.getTitle());
			return widgetRepository.save(optionalWidget);
		}
		return null;
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable ("widgetId") int id) {
		widgetRepository.deleteById(id);
	}
	
}
