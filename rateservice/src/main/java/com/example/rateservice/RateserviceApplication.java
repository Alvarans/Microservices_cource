package com.example.rateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RateserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateserviceApplication.class, args);
    }

}
