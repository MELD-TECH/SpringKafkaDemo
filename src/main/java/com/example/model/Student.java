package com.example.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
public class Student {
	
	public Student() {
		
	}
	
	public Student(UUID id, String lastname, String firstname, int level, Gender gender) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.level = level;
		this.gender = gender;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String lastname; 
	
	private String firstname;
	
	private int level;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	
	
}
