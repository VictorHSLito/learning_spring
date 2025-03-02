package br.com.victor.learning_spring.service;

import br.com.victor.learning_spring.models.DadosEpisodio;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.models.Episodio;

import java.util.*;
import java.util.stream.Collectors;

public class EstatisticaAPI {
    public void listaTodosEpisodios(List<DadosTemporada> temporadasList) {
        List<Episodio> episodios = temporadasList.stream()
                .flatMap(t -> t.episodiosArray().stream()
                        .map(d -> new Episodio(t.numero(),d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);
    }

    public void mediaPorTemporada(List<DadosTemporada> temporadasList) {
        List<Episodio> episodios = temporadasList.stream()
                .flatMap(t -> t.episodiosArray().stream()
                        .map(d -> new Episodio(t.numero(),d)))
                .collect(Collectors.toList());

        System.out.println("Média por temporada: ");
        Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
                .filter(e -> e.getRating() != null && e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporadaEpisodio,
                        Collectors.averagingDouble(Episodio::getRating)));

        System.out.println(avaliacaoPorTemporada);

        exibeEstatiscas(episodios);
    }

    public void listaMelhoresEpisodios(List<DadosTemporada> temporadasList) {
        System.out.println("Top 5 Episódios:");

        List<DadosEpisodio> dadosEpisodios = temporadasList.stream()
                        .flatMap(t -> t.episodiosArray().stream())
                                .collect(Collectors.toList());
        dadosEpisodios.stream()
                .sorted(Comparator.comparing(DadosEpisodio::rating).reversed())
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .limit(5)
                .forEach(System.out::println); // A partir desse novo array de episódios, filtra os 5 melhores
    }

    public void exibeEstatiscas(List<Episodio> episodios) {
        DoubleSummaryStatistics estatiscas = episodios.stream()
                .filter(e ->e.getRating() != null && e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getRating));

        System.out.println("Média de nota da série: " + estatiscas.getAverage());
        System.out.println("Maior nota de um episódio da série: " + estatiscas.getMax());
        System.out.println("Menor nota de um episódio da série: " + estatiscas.getMin());
        System.out.println("Total de episódios da série: " + estatiscas.getCount());
    }
}
