package br.com.victor.learning_spring.menu;

import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.service.EstatisticaAPI;
import br.com.victor.learning_spring.service.InputUsuario;
import br.com.victor.learning_spring.service.ServicoAPI;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static br.com.victor.learning_spring.service.InputUsuario.obterResposta;

public class Menu {
    private String nome;
    private int episodio = 0;
    private int temporada = 0;
    private String url;

    private final InputUsuario inputService = new InputUsuario();
    private final ServicoAPI apiService = new ServicoAPI();

    private final EstatisticaAPI estatistica = new EstatisticaAPI();

    public Menu() throws IOException, InterruptedException {
        obterDados();
        setUrl();
        mostraInformacoesSerie();
    }

    private void obterDados() {
        this.nome = inputService.obterNomeSerie();
        this.temporada = inputService.obterTemporada();
        this.episodio = inputService.obterEpisodio(temporada);
    }

    public void setUrl() {
        url = "http://www.omdbapi.com/?apikey=" + apiService.getAPI_KEY() + "&t=" + nome
                + (temporada != 0 ? "&season=" + temporada : "")
                + (episodio != 0 ? "&episode=" + episodio : "");
    }

    public void mostraInformacoesSerie() {
        if (temporada == 0) {
            List<DadosTemporada> temporadas = new ArrayList<>();
            DadosSeries serie = apiService.obterDadosSeries(url);
            for (int i = 1; i <= serie.quantidadeTemporadas(); i++) {
                DadosTemporada temporada = apiService.obterDadosTemp(nome, i);
                temporadas.add(temporada);
            }
            temporadas.forEach(System.out::println);

            System.out.println("Gostaria de saber quais são os 5 melhores episódios dessa série? [s/n]");

            if (obterResposta().equals("s")) {
                estatistica.listaMelhoresEpisodios(temporadas);
            }

            System.out.println("Gostaria de obter dados detalhados de cada episódio da série? [s/n]");

            if (obterResposta().equals("s")) {
                estatistica.listaTodosEpisodios(temporadas);
            }

            System.out.println("Gostaria de saber a média das notas por temporada? [s/n]");

            if (obterResposta().equals("s")) {
                estatistica.mediaPorTemporada(temporadas);
            }

        } else {
            DadosSeries serie = apiService.obterDadosSeries(url);
            System.out.println(serie);
        }
    }
}
