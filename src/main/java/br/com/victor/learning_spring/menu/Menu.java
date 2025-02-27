package br.com.victor.learning_spring.menu;

import java.util.Scanner;

public class Menu {
    private String nome;
    private int episodio;
    private int temporada;

    private static final Scanner sc = new Scanner(System.in);

    public Menu() {
        setNome();
        setTemporada();
        setEpisodio();
    }

    public String getNome() {
        return nome;
    }

    public void setNome() {
        System.out.println("Digite o nome da série/filme que deseja fazer uma consulta:");
        this.nome = sc.nextLine();
        this.nome = this.nome.replace(" ", "+");
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
            System.out.println("Gostaria de consultar uma temporada específica?");
            resposta = sc.nextLine();
            if (resposta.equals("s") || resposta.equals("Sim")) {
                System.out.println("Qual temporada gostaria de verificar?");
                this.temporada = sc.nextInt();
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente");
            }
        } while (!(resposta.equals("Não") || resposta.equals("N")));
    }
}
