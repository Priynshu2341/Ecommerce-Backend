package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println(">>> JVM timezone: " + java.util.TimeZone.getDefault().getID());
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
