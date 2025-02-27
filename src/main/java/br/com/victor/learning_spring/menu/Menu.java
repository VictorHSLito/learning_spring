package br.com.victor.learning_spring.menu;


import br.com.victor.learning_spring.service.LeitorAPI;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static String nome;
    private int episodio;
    private int temporada;

    private final Scanner sc = new Scanner(System.in);

    private final LeitorAPI LEITOR = new LeitorAPI();
    private String url = "http://www.omdbapi.com/?apikey=" + LEITOR.getApikey();

    public Menu() throws IOException {
        setNome();
        setTemporada();
        setEpisodio();
        setUrl();
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
        System.out.println("Qual episódio gostaria de obter detalhes?");
        this.episodio = sc.nextInt();
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
            } else if (resposta.equals("n") || resposta.equals("não")){
                this.temporada = 1;
            } else {
                System.out.println("Opção inválida! Tente novamente");
            }
        } while (!(resposta.equals("Não") || resposta.equals("n")));
    }

    public void setUrl() {
        this.url += "&t=" + this.getNome()
                + "&season=" + this.getTemporada()
                + "&episode=" + this.getEpisodio();
    }

    public String getUrl() {
        return this.url;
    }

    public void interaSobreTemporadas() {

    }

}
