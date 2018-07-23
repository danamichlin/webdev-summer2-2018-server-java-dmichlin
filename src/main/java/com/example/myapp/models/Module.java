package com.example.myapp.models;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Module {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
  @ManyToOne
  @JsonIgnore
  private Course course;
  @OneToMany(mappedBy="module")
  private List<Lesson> lessons;
  
  // setters + getters
  public void setId(int id) {
	  this.id = id;
  }
  
  public int getId() {
	  return this.id;
  }
  
  public void setTitle(String title) {
	  this.title = title;
  }
  
  public String getTitle() {
	  return this.title;
  }
  
  public void setCourse(Course course) {
	  this.course = course;
  }
  
  public Course getCourse() {
	  return this.course;
  }
  
  public void setLessons(List<Lesson> lessons) {
	  this.lessons = lessons;
  }
  
  public List<Lesson> getLessons() {
	  return this.lessons;
  }
  
  public Module() {
	  
  }
  
  public Module(String title, Course course, List<Lesson> lessons) {
	  this.title = title;
	  this.course = course;
	  this.lessons = lessons;
  }
  
}