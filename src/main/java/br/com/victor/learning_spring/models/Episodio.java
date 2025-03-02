package br.com.victor.learning_spring.models;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Episodio {
    private String nomeEpisodio;
    private int temporadaEpisodio;
    private LocalDate dataLancamento;
    private int numeroEpisodio;
    private Double rating;

    public Episodio(int numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporadaEpisodio = numeroTemporada;
        this.nomeEpisodio = dadosEpisodio.nome();
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
            this.rating = Double.valueOf(dadosEpisodio.rating());
        } catch (NumberFormatException e ) {
            this.rating = 0.0;
        } catch (DateTimeException e) {
            this.dataLancamento = null;
        }
        this.numeroEpisodio = dadosEpisodio.numero();
    }

    public String getNomeEpisodio() {
        return nomeEpisodio;
    }

    public void setNomeEpisodio(String nomeEpisodio) {
        this.nomeEpisodio = nomeEpisodio;
    }

    public int getTemporadaEpisodio() {
        return temporadaEpisodio;
    }

    public void setTemporadaEpisodio(int temporadaEpisodio) {
        this.temporadaEpisodio = temporadaEpisodio;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = LocalDate.parse(dataLancamento);
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = Double.valueOf(rating);
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "nomeEpisodio='" + nomeEpisodio + '\'' +
                ", temporadaEpisodio=" + temporadaEpisodio +
                ", dataLancamento=" + dataLancamento +
                ", numeroEpisodio=" + numeroEpisodio +
                ", rating=" + rating +
                '}';
    }
}
