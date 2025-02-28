package br.com.victor.learning_spring.service;

import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;

import java.io.IOException;

public class ServicoAPI implements IServicoAPI {
    private final ConectaAPI conexao = new ConectaAPI();
    private final ConverteDados conversor = new ConverteDados();
    private final LeitorAPI LEITOR = new LeitorAPI();
    private final String API_KEY = LEITOR.getApikey();

    public ServicoAPI() throws IOException {
    }

    @Override
    public DadosSeries obterDadosSeries(String url) {
        try {
            String json = conexao.obterDados(url);
            return conversor.obterDados(json, DadosSeries.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocorreu um erro ao obter dados da série: " + e.getMessage());
        }
    }

    @Override
    public DadosTemporada obterDadosTemp(String nome, int temporada) {
        try {
            String url = "http://www.omdbapi.com/?apikey=" + LEITOR.getApikey() + "&t=" + nome + "&season=" + temporada;
            String json = conexao.obterDados(url);
            return conversor.obterDados(json, DadosTemporada.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocorreu um erro ao obter dados da temporada: " + e.getMessage());
        }
    }

    public String getAPI_KEY() {
        return API_KEY;
    }
}
