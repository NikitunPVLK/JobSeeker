package com.example.jobserver;

import com.example.jobserver.api_key.ApiKeyManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobServerApplication.class, args);
		ApiKeyManager.generateApiKey();
	}

}
