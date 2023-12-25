package com.example.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Course;
import com.example.model.CourseRegistration;
import com.example.model.Student;
import com.example.repository.CourseRepository;
import com.example.repository.RegStudentRepository;
import com.example.repository.StudentRepository;

@Service
public class ServiceRegistration {

	@Autowired
	StudentRepository repo;
	
	@Autowired
	CourseRepository cseRepo;
	
	@Autowired
	RegStudentRepository stuRepo;
	
	public Collection<CourseRegistration> getRegisteredCourses(){
		return repo.findAll();
	}
	
	public CourseRegistration saveRecord(CourseRegistration courseReg) {
		CourseRegistration reg = repo.save(courseReg);
		
		return reg;
	}
	
	public CourseRegistration updateRecord(UUID regid, CourseRegistration reg) {
		Optional<CourseRegistration> opt = repo.findById(regid);
		
		CourseRegistration courseReg = null;
		
		if(opt.isPresent()) {
			courseReg = opt.get();
			courseReg.setCourse(reg.getCourse());
			courseReg.setDateRegistered(reg.getDateRegistered());
			courseReg.setSession(reg.getSession());
			courseReg.setStudent(reg.getStudent());
			courseReg.setRegYear(reg.getRegYear());
			
			courseReg = repo.save(courseReg);
		}
		
		return courseReg;
	}
	
	public String removeRecord(UUID id) {
		repo.deleteById(id);
		
		return "record removed";
	}
	
	public Course saveCourse(Course course) {
		
		Course cse = cseRepo.save(course);
		
		return cse;
	}
	
	public Collection<Course> findAllCourses(){
		
		return cseRepo.findAll();
	}
	
	public Student saveStudent(Student student) {
		
		Student stu = stuRepo.save(student);
		
		return stu;
	}
}
