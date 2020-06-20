package com.hello.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringInitApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringInitApplication.class,args);
	}
}
