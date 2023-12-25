package com.example.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.model.CourseRegistration;

@Component
public class MessageProducer {

	@Autowired
	private KafkaTemplate<String, CourseRegistration> kafkaTemplate;
	
	public void sendCourseMessage(String topic, CourseRegistration course) {
		kafkaTemplate.send(topic, course);
	}
	
	
}
