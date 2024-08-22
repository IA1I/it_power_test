package com.example.test_task_it_power;

import org.springframework.boot.SpringApplication;

public class TestTestTaskItPowerApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestTaskItPowerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
