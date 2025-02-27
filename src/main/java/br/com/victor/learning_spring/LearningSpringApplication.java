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

		ConectaAPI conexao = new ConectaAPI(); // Cria uma nova conexão
		String json = conexao.obterDados(menu.getUrl()); // A partir da conexão gerada, armazena o json

		ConverteDados conversor = new ConverteDados();


		DadosSeries serie = conversor.obterDados(json, DadosSeries.class); // Converte esse json para os moldes de uma classe com o conversor

		System.out.println(serie); // Mostra os dados formatados com o toString()
	}
}
