package com.example.kafka.consumer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.model.Course;
import com.example.model.CourseRegistration;
import com.example.model.Student;
import com.example.services.ServiceRegistration;

@Component
public class MessageListener {

	@Autowired
	private ServiceRegistration service;
	
	@KafkaListener(topics = "first-session-course", groupId = "course-group-id", containerFactory = "courseListener")
	public void courseListenerFirstSemester(CourseRegistration reg) {		
		
		  System.out.println("about to save course");
		  
		  Course cr = service.saveCourse(reg.getCourse());
		  
		  Student st = service.saveStudent(reg.getStudent());
		  
		  reg.setCourse(cr);
		  
		  reg.setStudent(st);
		    
		  service.saveRecord(reg);
		 
		
		System.out.println("Course Registration saved in the DB... " + reg.getId() + " " + reg.getSession() + " " + reg.getCourse().getName());
	}
	

}
