package com.arbitrage.scannerbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScannerbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScannerbatchApplication.class, args);
	}

}
