package com.thrtec.desafiobacksouthsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
public class DesafioBackSouthsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioBackSouthsystemApplication.class, args);
    }

}
