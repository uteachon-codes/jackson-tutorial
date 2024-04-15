package com.serverless.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SystemEnvVariablesTest {

    @BeforeAll
    static void setUp() {
        System.setProperty("SYSTEM_VAR1", "VALUE1");
        System.setProperty("SYSTEM_VAR2", "VALUE2");
    }

    @AfterAll
    static void tearDown() {
        System.clearProperty("SYSTEM_VAR1");
        System.clearProperty("SYSTEM_VAR2");
    }

    @Test
    void testSystemEnvVariables() {
        assertEquals("VALUE1", System.getProperty("SYSTEM_VAR1"));
        assertEquals("VALUE2", System.getProperty("SYSTEM_VAR2"));
    }

}