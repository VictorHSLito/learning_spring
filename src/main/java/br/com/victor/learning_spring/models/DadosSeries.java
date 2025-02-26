package br.com.victor.learning_spring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSeries(@JsonAlias("Title") String nome,
                          @JsonAlias("Genre") String genero,
                          @JsonAlias("Year") String anoLacamento,
                          @JsonAlias("Writer") String autor,
                          @JsonAlias("Rating") float rating,
                          @JsonAlias("totalSeasons") String quantidadeTemporadas) {

    @Override
    public String toString() {
        return "{Titulo = " + this.nome +
                ", Ano de Lançamento = " + this.anoLacamento +
                ", Genero = " + this.genero +
                ", Autor = " + this.autor +
                ", Avaliação = " + this.rating +
                ", Quantidade de temporadas = " + this.quantidadeTemporadas +
                "}";
    }
}
