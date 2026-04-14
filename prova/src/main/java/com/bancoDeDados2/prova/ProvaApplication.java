package com.bancoDeDados2.prova;

import com.bancoDeDados2.prova.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProvaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProvaApplication.class, args);
	}

	private final Principal principal;

	public ProvaApplication(Principal principal) {
		this.principal = principal;
	}

	@Override
	public void run(String... args) throws Exception {
		principal.run();
	}
}
