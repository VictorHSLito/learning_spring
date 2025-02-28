package br.com.victor.learning_spring.service;

import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;

public interface IServicoAPI {
    public DadosSeries obterDadosSeries(String url);

    public DadosTemporada obterDadosTemp(String nome, int temporada);
}
