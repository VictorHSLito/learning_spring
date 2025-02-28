package br.com.victor.learning_spring.menu;

import br.com.victor.learning_spring.models.DadosSeries;
import br.com.victor.learning_spring.models.DadosTemporada;
import br.com.victor.learning_spring.service.ConectaAPI;
import br.com.victor.learning_spring.service.ConverteDados;
import br.com.victor.learning_spring.service.LeitorAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private String nome;
    private int episodio = 0;
    private int temporada = 0;

    private final Scanner sc = new Scanner(System.in);

    private final ConectaAPI conexao = new ConectaAPI(); // Cria uma nova conexão
    private final ConverteDados conversor = new ConverteDados();

    private final LeitorAPI LEITOR = new LeitorAPI();
    private String url = "http://www.omdbapi.com/?apikey=" + LEITOR.getApikey();

    public Menu() throws IOException, InterruptedException {
        obterDados();
        setUrl();
        mostraInformacoesSerie();
    }

    private void obterDados()  {
        setNome();
        setTemporada();
        setEpisodio();
    }
    public String getNome() {
        return nome;
    }

    public void setNome() {
        System.out.println("Digite o nome da série que deseja fazer uma consulta:");
        nome = sc.nextLine();
        nome = nome.replace(" ", "+");
    }

    public int getEpisodio() {
        return episodio;
    }

    public void setEpisodio() {
        if (this.getTemporada() != 0) {
            do {
                System.out.println("Qual episódio gostaria de obter detalhes?");
                this.episodio = sc.nextInt();
            } while (this.getEpisodio() <= 0);
        }
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada() {
        String resposta;
        do {
            System.out.println("Gostaria de consultar uma temporada específica? [s/n]");
            resposta = sc.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                System.out.println("Qual temporada gostaria de verificar?");
                this.temporada = sc.nextInt();
                break;
            } else if (!(resposta.equals("n") || resposta.equals("não"))){
                System.out.println("Opção inválida! Tente novamente");
            }
        } while (!(resposta.equals("não") || resposta.equals("n")));
    }

    public void setUrl() {
        this.url += "&t=" + this.getNome()
                +((this.getTemporada() != 0) ? "&season=" + getTemporada() : "")
                +((this.getEpisodio() != 0) ? "&episode=" + getEpisodio(): "");
    }

    public String getUrl() {
        return this.url;
    }

    public void mostraInformacoesSerie() throws IOException, InterruptedException {
        if (this.getTemporada() == 0) {
            List<DadosTemporada> temporadas = new ArrayList<>();
            String json = conexao.obterDados(getUrl());
            DadosSeries serie = conversor.obterDados(json, DadosSeries.class);
            for (int i = 1; i <= serie.quantidadeTemporadas(); i++) {
                json = conexao.obterDados("https://www.omdbapi.com/?t=" + getNome() + "&season=" + i + "&apikey=6585022c");
                DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(temporada);
            }
            temporadas.forEach(System.out::println);
        } else {
            String json = conexao.obterDados(getUrl());
            DadosSeries serie = conversor.obterDados(json, DadosSeries.class);
            System.out.println(serie);
        }
    }
}
