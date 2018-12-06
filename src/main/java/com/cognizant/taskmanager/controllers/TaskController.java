package com.cognizant.taskmanager.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.taskmanager.pojo.Task;
import com.cognizant.taskmanager.service.TaskService;

@RestController
@CrossOrigin
public class TaskController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	ResponseEntity<?> resEntity;
	List<Task> tasks;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/tasks")
	public ResponseEntity<?> getAllTasks() {
		log.info("Inside TaskController --> getAllTasks Method");
		tasks = taskService.getAllTasks();
		resEntity = new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
		return resEntity;
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/tasks/")
	public ResponseEntity<?>  createTask(@RequestBody Task task) {
		log.info("Inside TaskController --> createTask Method");
		taskService.createTask(task);	
		resEntity = new ResponseEntity<Task>(task, HttpStatus.CREATED);
		return resEntity;
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/tasks/")
	public ResponseEntity<?> updateTask(@RequestBody Task task) {
		log.info("Inside TaskController --> updateTask Method");
		taskService.updateTask(task);
		resEntity = new ResponseEntity<Task>(task, HttpStatus.OK);
		return resEntity;
	}
}
