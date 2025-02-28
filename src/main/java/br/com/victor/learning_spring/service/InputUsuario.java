package br.com.victor.learning_spring.service;

import java.util.Scanner;

public class InputUsuario {
    private final Scanner sc = new Scanner(System.in);

    public String obterNomeSerie() {
        System.out.println("Digite o nome da série que deseja fazer uma consulta:");
        return sc.nextLine().replace(" ", "+");
    }

    public int obterEpisodio(int temporada) {
        int episodio = 0;
        if (temporada != 0) {
            do {
                System.out.println("Qual episódio gostaria de obter detalhes?");
                episodio = sc.nextInt();
            } while (episodio <= 0);
        }
        return episodio;
    }

    public int obterTemporada() {
        String resposta;
        int temporada = 0;
        do {
            System.out.println("Gostaria de consultar uma temporada específica? [s/n]");
            resposta = sc.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) {
                System.out.println("Qual temporada gostaria de verificar?");
                temporada = sc.nextInt();
                break;
            } else if (!(resposta.equals("n") || resposta.equals("não"))) {
                System.out.println("Opção inválida! Tente novamente");
            }
        } while (!(resposta.equals("não") || resposta.equals("n")));
        return temporada;
    }
}
