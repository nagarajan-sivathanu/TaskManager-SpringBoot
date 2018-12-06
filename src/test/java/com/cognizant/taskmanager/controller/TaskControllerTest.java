package com.cognizant.taskmanager.controller;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.taskmanager.controllers.TaskController;
import com.cognizant.taskmanager.pojo.Task;
import com.cognizant.taskmanager.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTest {

	private MockMvc mvc;
	private MvcResult result;
	
	@MockBean
	private TaskService service;
	@InjectMocks
	private TaskController controller;
	
	private Task task;
	static List<Task> taskList;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		taskList = new ArrayList<Task>();
		task = new Task("1","EB Bill Details","Bill Details","",Date.valueOf("2018-02-02"),Date.valueOf("2018-02-28"),10,"A" );
		taskList.add(task);
		task = new Task("2","BSNL Bill Details","Bill Details","",Date.valueOf("2018-03-01"),Date.valueOf("2018-02-31"),30,"A" );
		taskList.add(task);
	}
	
	@Test
	public void testGetAllTasks() {
		String expected = "[{\"taskID\":\"1\",\"task\":\"EB Bill Details\",\"parentTask\":\"Bill Details\",\"parentTaskID\":\"\",\"startDate\":\"2018-02-02\",\"endDate\":\"2018-02-28\",\"priority\":10,\"status\":\"A\"},{\"taskID\":\"2\",\"task\":\"BSNL Bill Details\",\"parentTask\":\"Bill Details\",\"parentTaskID\":\"\",\"startDate\":\"2018-03-01\",\"endDate\":\"2018-03-03\",\"priority\":30,\"status\":\"A\"}]";
		Mockito.when(service.getAllTasks()).thenReturn(taskList);
		try {
				result=mvc.perform(MockMvcRequestBuilders.get("/tasks/").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
				System.out.println(result.getResponse().getContentAsString());
				System.out.println(expected);				
				JSONAssert.assertEquals(expected, result.getResponse()
						.getContentAsString(), false);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Mockito.verify(service, VerificationModeFactory.times(1)).getAllTasks();
		Mockito.verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testCreateTask() throws Exception {
		
		Mockito.doNothing().when(service).createTask(task);
		try {
			mvc.perform(MockMvcRequestBuilders.post("/tasks/").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(task))).andExpect(MockMvcResultMatchers.status().isCreated());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mockito.verify(service, VerificationModeFactory.times(1)).createTask(Mockito.any(Task.class));
		Mockito.verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testUpdateTask() throws Exception {
		
		Mockito.doNothing().when(service).updateTask(task);
		try {
			mvc.perform(MockMvcRequestBuilders.put("/tasks/").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(task))).andExpect(MockMvcResultMatchers.status().isOk());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mockito.verify(service, VerificationModeFactory.times(1)).updateTask(Mockito.any(Task.class));
		Mockito.verifyNoMoreInteractions(service);
	}
	
	private static String jsonToString(Object task) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(task);		
	}
	

}
