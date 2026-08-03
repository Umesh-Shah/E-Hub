package com.ehub;

import org.springframework.boot.SpringApplication;

public class TestEhubApplication {

	public static void main(String[] args) {
		SpringApplication.from(EhubApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
