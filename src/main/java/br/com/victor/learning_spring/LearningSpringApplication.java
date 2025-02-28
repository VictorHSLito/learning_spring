package br.com.victor.learning_spring;

import br.com.victor.learning_spring.menu.Menu;
import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.service.ConectaAPI;
import br.com.victor.learning_spring.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Menu menu = new Menu();
	}
}
