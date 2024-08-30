package com.example.basf_backend;

import org.springframework.boot.SpringApplication;

public class TestBasfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(BasfBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
