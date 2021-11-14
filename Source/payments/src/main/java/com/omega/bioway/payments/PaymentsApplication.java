package com.omega.bioway.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PaymentsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

}
