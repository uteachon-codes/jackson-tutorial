package com.serverless.jackson;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	// convert JSON string to Java Object: deserialization
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
