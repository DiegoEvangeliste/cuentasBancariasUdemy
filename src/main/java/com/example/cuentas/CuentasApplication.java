package com.example.cuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages={"com.example.something", "com.example.application"})/*(exclude = {DataSourceAutoConfiguration.class })*/
@SpringBootApplication
public class CuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentasApplication.class, args);
	}

}
