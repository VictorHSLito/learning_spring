package br.com.victor.learning_spring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String nome,
                            @JsonAlias("Released") String dataLancamento,
                            @JsonAlias("Episode") int numero,
                            @JsonAlias("imdbRating") float rating){
    @Override
    public String toString() {
        return "{" +
                "Nome = " + nome +
                ", Data de Lançamento = " + dataLancamento +
                ", Número do episódio = " + numero +
                ", Rating = " + rating +
                '}';
    }
}
