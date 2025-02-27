package br.com.victor.learning_spring.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorAPI {
    private final FileReader ARQUIVO = new FileReader("src/apikey"); // Armazena o caminho do arquivo
    private final BufferedReader LEITOR_ARQUIVO = new BufferedReader(ARQUIVO); // Lê o arquivo

    private final String apikey = LEITOR_ARQUIVO.readLine(); // Armazena a chave da API

    public LeitorAPI() throws IOException {
    }

    public String getApikey() {
        return apikey;
    }
}
