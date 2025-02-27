package br.com.victor.learning_spring;

import br.com.victor.learning_spring.menu.Menu;
import br.com.victor.learning_spring.models.DadosEpisodio;
import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.service.ConectaAPI;
import br.com.victor.learning_spring.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;

@SpringBootApplication
public class LearningSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FileReader arquivo = new FileReader("src/apikey"); // Armazena o caminho do arquivo
		BufferedReader leitorArquivo = new BufferedReader(arquivo); // Lê o arquivo

		String apikey = leitorArquivo.readLine(); // Armazena a chave da API

		Menu menu = new Menu();

		String url = "http://www.omdbapi.com/?apikey=" + apikey + "&t=the+walking+dead&season=1";

		ConectaAPI conexao = new ConectaAPI(); // Cria uma nova conexão
		String json = conexao.obterDados(url); // A partir da conexão gerada, armazena o json

		ConverteDados conversor = new ConverteDados();
		DadosSeries serie1 = conversor.obterDados(json, DadosSeries.class); // Converte esse json para os moldes de uma classe com o conversor
		DadosEpisodio episodio1 = conversor.obterDados(json, DadosEpisodio.class);
		DadosTemporada temp1 = conversor.obterDados(json, DadosTemporada.class);
		System.out.println(temp1); // Mostra os dados formatados com o toString()
	}
}
