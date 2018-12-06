package com.cognizant.taskmanager.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Task")
public class Task {	
	@Id	
	@NotNull
	private String taskID;
	@Indexed(unique=true)
	private String task;
	private String parentTask;
	private String parentTaskID;
	private Date startDate;
	private Date endDate;
	private int priority;
	private String status;
	
	
	public Task() {
		
	}
	public Task(String taskID,String task, String parentTask, String parentTaskID, Date startDate, Date endDate, int priority, String status) {
		super();
	
		this.taskID = taskID;
		this.task = task;
		this.parentTaskID = parentTaskID;
		this.parentTask = parentTask;		
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	public String getParentTaskID() {
		return parentTaskID;
	}
	public void setParentTaskID(String parentTaskID) {
		this.parentTaskID = parentTaskID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
