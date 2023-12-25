package com.example.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.producer.MessageProducer;
import com.example.model.Course;
import com.example.model.CourseRegistration;
import com.example.model.Student;
import com.example.services.ServiceRegistration;

@RestController
@RequestMapping("/api")
public class CourseRegistrationController {

	@Autowired
	ServiceRegistration service;
	
	@Autowired
	private MessageProducer producer;
	
	@GetMapping("/findall")
	public ResponseEntity<Object> getAllRegisteredCourses(){
		Collection<CourseRegistration> listCourses = service.getRegisteredCourses();
				
		return new ResponseEntity<>(listCourses, HttpStatus.OK);
	}
	
	@PostMapping("/save-course")
	public ResponseEntity<Object> saveRegisteredCourses(@RequestBody CourseRegistration reg){
	
	try {
		Student student = reg.getStudent();
		student.setId(UUID.randomUUID());
		
		Course crs = reg.getCourse();
		crs.setId(UUID.randomUUID());
		
		reg.setId(UUID.randomUUID());
		reg.setStudent(student);
		reg.setCourse(crs);	
		
		producer.sendCourseMessage("first-session-course", reg);
		
		return new ResponseEntity<>(reg, HttpStatus.OK);
	}catch(Exception ex) {
		ex.getMessage();
		return new ResponseEntity<Object>("Error occurred here... ", HttpStatus.BAD_REQUEST);
	}
		
	}
	
	@PutMapping("/update-course/{id}")
	public ResponseEntity<Object> updateCourseRecord(@PathVariable String id, @RequestBody CourseRegistration reg){
		UUID regid = UUID.fromString(id);
		
		CourseRegistration courseReg = service.updateRecord(regid, reg);
		
		return new ResponseEntity<>(courseReg, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-course/{id}")
	public ResponseEntity<Object> deleteRecord(@PathVariable("id")  String id){
		UUID regid = UUID.fromString(id);
		service.removeRecord(regid);
		
		return new ResponseEntity<Object>("record successfully removed", HttpStatus.OK);
		
	}
	
	@PostMapping("/publish-course")
	public String saveCourse(@RequestBody Course course) {
		
		course.setId(UUID.randomUUID());
		
		course = service.saveCourse(course);		

		
		return "course successfully put in kafka";
	}
	
	@GetMapping("/findallcourses")
	public ResponseEntity<Object> findCourses(){
		
		Collection<Course> course = service.findAllCourses();
		
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
}
