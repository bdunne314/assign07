package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassSystemApplication {

	public static void main(String[] args) {
		System.out.println("============ ClassSystemApplication ===========");
		SpringApplication.run(ClassSystemApplication.class, args);
	}

}
