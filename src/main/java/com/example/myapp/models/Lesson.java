package com.example.myapp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
  @ManyToOne
  @JsonIgnore
  private Module module;
  @OneToMany(mappedBy="lesson")
  private List<Widget> widgets;
  
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
  
  public void setModule(Module module) {
	  this.module = module;
  }
  
  public Module getModule() {
	  return this.module;
  }
  
  public List<Widget> getWidgets() {
	return widgets;
}

  public void setWidgets(List<Widget> widgets) {
	this.widgets = widgets;
}

public Lesson() {
	  
  }
  
  public Lesson(String title, Module module) {
	  this.title = title;
	  this.module = module;	  
  }
}

