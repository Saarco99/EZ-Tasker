package com.saar.eztasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.saar.eztasker")
public class EzTaskerApplication {


	public static void main(String[] args) {
		SpringApplication.run(EzTaskerApplication.class, args);
	}


}
