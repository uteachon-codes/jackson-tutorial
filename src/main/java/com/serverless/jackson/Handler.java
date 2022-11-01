package com.serverless.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: {}", input);

		String jsonStr = employeeToJSONString(new Employee("John", "Palo Alto", "HR", "Mgr"));
		LOG.info("jsonStr: {}", jsonStr);

		String employeeJsonString = "{\"name\":\"John\",\"city\":\"Palo Alto\",\"department\":\"HR\",\"designation\":\"Mgr\"}";
		Employee employee = JSONStringToEmployee(employeeJsonString);
		LOG.info("employee: {}", employee.getCity());

		Employee employeeFromFile = readEmployeeJSONFileToEmployee();
		LOG.info("employee city From File: {}", employeeFromFile.getCity());

		List<Employee> employeesList = readEmployeesJSONFileToEmployeeList();
		LOG.info("employeesList size:: {}", employeesList.size());

		Employee[] employeesArray = readEmployeesJSONFileToEmployeeArray();
		LOG.info("employees Array length:: {}", employeesArray.length);	

		JsonNode jsonNode = jSONStringToJsonNode(employeeJsonString);
		LOG.info(jsonNode.toPrettyString());
		String empName = jsonNode.get("name").asText();
		LOG.info("empName: {}", empName);

		Response responseBody = new Response("Go Serverless v1.x! Your function executed successfully!", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}

    // convert Java Object to JSON string: serialization
	public String employeeToJSONString(Employee employee) {
		String jsonStr = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonStr = objectMapper.writeValueAsString(employee); //use .writeValue to new File(emp.json)
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	// convert JSON string to Employee Object: deserialization
	public Employee JSONStringToEmployee(String jsonString) {
		Employee employee = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			employee = objectMapper.readValue(jsonString, Employee.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return employee;
	} 

	//read employee.json: convert to Employee Object: deserialization
	public Employee readEmployeeJSONFileToEmployee() {
		// jackson-tutorial\src\main\resources\Employee.json
		Employee employee = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			employee = objectMapper.readValue(new File("src/main/resources/Employee.json"), Employee.class);
		} catch(IOException i) {
			i.printStackTrace();
		} 
		// catch (JsonProcessingException e) {
		// 	e.printStackTrace();
		// }
		return employee;
	}


	//read EmployeeList.json: convert to Employee List: deserialization
	public List<Employee> readEmployeesJSONFileToEmployeeList() {
		// jackson-tutorial\src\main\resources\Employee.json
		List<Employee> employeeList = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			employeeList = objectMapper.readValue(
				new File("src/main/resources/EmployeeList.json"), 
				new TypeReference<List<Employee>>(){});

		} catch(IOException i) {
			i.printStackTrace();
		} 
		return employeeList;
	}	

	//read employee.json: convert to Employee Object: deserialization
	public Employee[] readEmployeesJSONFileToEmployeeArray() {
		// jackson-tutorial\src\main\resources\Employee.json
		Employee[] employeeArray = new Employee[3];
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			employeeArray = objectMapper.readValue(
				new File("src/main/resources/EmployeeList.json"), 
				Employee[].class);

		} catch(IOException i) {
			i.printStackTrace();
		} 
		return employeeArray;
	}		

	// convert JSON string to JsonNode: deserialization
	public JsonNode jSONStringToJsonNode(String jsonString) {
		JsonNode jsonNode = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonNode = objectMapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonNode;
	} 	


	//JSON String to ArrayList 
	
	//JSON String to Array



}
