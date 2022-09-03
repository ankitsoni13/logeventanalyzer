package com.code.loganalyzer.configs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigsTest {

    @Test
    void testGetObjectMapper(){
        AppConfigs configs = new AppConfigs();
        Assertions.assertNotNull(configs.getObjectMapper());
    }
}