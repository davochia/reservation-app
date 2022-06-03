package com.neagen.devtest;

import com.neagen.devtest.rest.Appointment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages={"com.neagen.devtest"})
public class DevtestApplication {

	public DevtestApplication(){
	}

	public static void main(String[] args) {
		SpringApplication.run(DevtestApplication.class, args);
	}

}
