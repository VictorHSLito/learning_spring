package br.com.victor.learning_spring.menu;

import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.service.InputUsuario;
import br.com.victor.learning_spring.service.ServicoAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String nome;
    private int episodio = 0;
    private int temporada = 0;
    private String url;

    private final InputUsuario inputService = new InputUsuario();
    private final ServicoAPI apiService = new ServicoAPI();

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
        } else {
            DadosSeries serie = apiService.obterDadosSeries(url);
            System.out.println(serie);
        }
    }
}
