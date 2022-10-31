package com.serverless.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HandlerTest {

    @InjectMocks
    Handler handler;

    void Handler() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequestTest() {

        Map<String, Object> input = new HashMap<>();
        input.put("key1", "value1");
        input.put("key1", "value1");
        input.put("key1", "value1");

        handler = new Handler();
        ApiGatewayResponse apiGatewayResponse = handler.handleRequest(input, null);
        assertEquals(200, apiGatewayResponse.getStatusCode());

        //Mockito.verify(handler).employeeToJSONString(Mockito.mock(Employee.class));
    }
}