package com.neagen.devtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.neagen.devtest"})
public class DevtestApplication {
	Logger logger = LoggerFactory.getLogger(DevtestApplication.class);

	public DevtestApplication(){
	}

	public static void main(String[] args) {
		SpringApplication.run(DevtestApplication.class, args);
	}

}
