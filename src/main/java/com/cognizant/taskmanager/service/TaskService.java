package com.cognizant.taskmanager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.taskmanager.dao.TaskDao;
import com.cognizant.taskmanager.pojo.Task;
 

@Service
public class TaskService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private CounterService counter;
	
	public List<Task> getAllTasks(){
		log.info("Inside TaskService --> getAllTasks Method");
		List<Task> tasks = new ArrayList<Task>();
		taskDao.findAll().forEach(tasks::add);
		return tasks;	
	}
	public void createTask(Task task) {
		log.info("Inside TaskService --> createTask Method"); 
		task.setTaskID(String.valueOf(counter.getNextSequence("counters")));
		log.info("Unique Task ID : "+task.getTaskID());
		taskDao.insert(task);
	}
	public void updateTask(Task task) {
		log.info("Inside TaskService --> updateTask Method");
		taskDao.save(task);
	}
}
