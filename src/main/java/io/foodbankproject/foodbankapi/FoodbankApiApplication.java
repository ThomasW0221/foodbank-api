package io.foodbankproject.foodbankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodbankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodbankApiApplication.class, args);
	}

}
