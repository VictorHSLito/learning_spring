package br.com.victor.learning_spring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") int numero,
                             @JsonAlias("Episodes") ArrayList<DadosEpisodio> episodiosArray) {
    @Override
    public String toString() {
        return "Temporada número: " + numero +
                ", Episódios" + episodiosArray + "}\n";
    }
}