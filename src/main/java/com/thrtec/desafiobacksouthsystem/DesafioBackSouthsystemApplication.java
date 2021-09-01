package com.thrtec.desafiobacksouthsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioBackSouthsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackSouthsystemApplication.class, args);
	}

}
