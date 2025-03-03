package br.com.victor.learning_spring.service;

import br.com.victor.learning_spring.models.DadosEpisodio;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.models.Episodio;

import java.util.*;
import java.util.stream.Collectors;

public class EstatisticaAPI {

    private List<DadosTemporada> temporadasEst = new ArrayList<>();
    private List<Episodio> episodiosEst = new ArrayList<>();

    public void listaTodosEpisodios() {
        episodiosEst.forEach(System.out::println);
    }

    public void mediaPorTemporada() {
        System.out.println("Média por temporada: ");
        Map<Integer, Double> avaliacaoPorTemporada = episodiosEst.stream()
                .filter(e -> e.getRating() != null && e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporadaEpisodio,
                        Collectors.averagingDouble(Episodio::getRating)));

        System.out.println(avaliacaoPorTemporada);
    }

    public void listaMelhoresEpisodios() {
        System.out.println("Top 5 Episódios:");

        List<DadosEpisodio> dadosEpisodios = temporadasEst.stream()
                        .flatMap(t -> t.episodiosArray().stream())
                                .collect(Collectors.toList());
        dadosEpisodios.stream()
                .sorted(Comparator.comparing(DadosEpisodio::rating).reversed())
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .limit(5)
                .forEach(System.out::println); // A partir desse novo array de episódios, filtra os 5 melhores
    }

    public void exibeEstatiscas() {
        DoubleSummaryStatistics estatiscas = episodiosEst.stream()
                .filter(e ->e.getRating() != null && e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getRating));

        System.out.println("Média de nota da série: " + estatiscas.getAverage());
        System.out.println("Maior nota de um episódio da série: " + estatiscas.getMax());
        System.out.println("Menor nota de um episódio da série: " + estatiscas.getMin());
        System.out.println("Total de episódios da série: " + estatiscas.getCount());
    }

    public void defineArrayTemporadas(List<DadosTemporada> temporadas) {
        this.temporadasEst = temporadas;
        defineArrayEpisodios();
    }

    public void defineArrayEpisodios() {
        this.episodiosEst = temporadasEst.stream()
                .flatMap(t -> t.episodiosArray().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());
    }
}
