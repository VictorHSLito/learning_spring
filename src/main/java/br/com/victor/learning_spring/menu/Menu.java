package br.com.victor.learning_spring.menu;

import br.com.victor.learning_spring.models.DadosEpisodio;
import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.service.InputUsuario;
import br.com.victor.learning_spring.service.ServicoAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.victor.learning_spring.service.InputUsuario.obterResposta;

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

            if (obterResposta().equals("s")) {
                listaMelhoresEpisodios(temporadas);
            }

        } else {
            DadosSeries serie = apiService.obterDadosSeries(url);
            System.out.println(serie);
        }
    }

    public void listaMelhoresEpisodios(List<DadosTemporada> temporadasList) {
        List<DadosEpisodio> dadosEpisodios = temporadasList.stream()
                .flatMap(t -> t.episodiosArray().stream())
                .collect(Collectors.toList()); // Extrai informações das temporadas e cria um novo array

        dadosEpisodios.stream()
                .sorted(Comparator.comparing(DadosEpisodio::rating).reversed())
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .limit(5)
                .forEach(System.out::println); // A partir desse novo array de episódios, filtra os 5 melhores
    }

}
