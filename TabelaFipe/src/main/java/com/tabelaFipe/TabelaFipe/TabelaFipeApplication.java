package com.tabelaFipe.TabelaFipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import principal.Principal;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	private Principal principal = new Principal();


	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		principal.exibir_menu();
		principal.principal();

	}
}
