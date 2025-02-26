package br.com.victor.learning_spring.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConectaAPI {
    public String obterDados(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse <String> response = null;

        try {
           response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            System.out.println("Houve um erro de execução: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro na entrada/saída de dados: " + e.getMessage());
        }

        return response.body();
    }

}
