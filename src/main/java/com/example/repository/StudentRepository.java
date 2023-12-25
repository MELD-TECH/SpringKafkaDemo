package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CourseRegistration;

@Repository
public interface StudentRepository extends JpaRepository<CourseRegistration, UUID> {

}
