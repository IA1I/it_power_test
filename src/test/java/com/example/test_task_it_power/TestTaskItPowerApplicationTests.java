package com.example.test_task_it_power;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestTaskItPowerApplicationTests {

    @Test
    void contextLoads() {
    }

}
