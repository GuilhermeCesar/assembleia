package br.medeiros.guilherme.testesouth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TesteSouthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteSouthApplication.class, args);
	}

}
