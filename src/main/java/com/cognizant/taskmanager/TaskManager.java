package com.cognizant.taskmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TaskManager {
	private final static Logger log = LoggerFactory.getLogger(TaskManager.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(TaskManager.class, args);
		log.info("Spring Boot App has been executed Successfully");
	}
}
