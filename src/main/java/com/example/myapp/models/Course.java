package com.example.myapp.models;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Course {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @Temporal(TemporalType.TIMESTAMP)
  private Date modified;
  //@OneToMany(mappedBy="course")
  //private List<Module> modules;
  
  //getters + setters:
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
  
  public void setCreated(Date created) {
	  this.created = created;
  }
  
  public Date getCreated() {
	  return this.created;
  }
  
  public void setModified(Date modified) {
	  this.modified = modified;
  }
  
  public Date getModified() {
	  return this.modified;
  }

}