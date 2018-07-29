package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.myapp.repositories.WidgetRepository;
import com.example.myapp.models.Course;
import com.example.myapp.models.Widget;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		List<Widget> widgetList = (List<Widget>) widgetRepository.findAll();
		return widgetList;
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
	
}
