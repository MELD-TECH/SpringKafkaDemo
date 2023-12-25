package com.example.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
public class CourseRegistration {
	
	public CourseRegistration() {
		
	}
	
	public CourseRegistration(UUID id, Session session, Student student, Course course, CourseYear regYear, Date registered) {
		this.id = id;
		this.session = session;
		this.student = student;
		this.course = course;
		this.regYear = regYear;
		this.dateRegistered = registered;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	private Session session;
	
	@JoinColumn(referencedColumnName = "id", name = "student_id")
	@ManyToOne
	private Student student;
	
	@JoinColumn(referencedColumnName = "id", name = "course_id")
	@ManyToOne
	private Course course;
	
	@Enumerated(EnumType.STRING)
	private CourseYear regYear;
		
	private Date dateRegistered;
}
