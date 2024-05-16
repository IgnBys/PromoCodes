package com.sii.promoCodes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.sii.promoCodes.*" })
@SpringBootApplication
public class PromoCodesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromoCodesApplication.class, args);
	}

}
