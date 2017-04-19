package com.bruno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	//Start the application
	//loglevel is set to debug
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

