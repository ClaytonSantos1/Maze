package Etapa1;

import java.io.*;

public class PrincipalLabirinto {
    public static void main(String[] args) throws IOException{
        Labirinto lab = new Labirinto();

        // Carrega o labirinto a partir do arquivo de texto
        lab.criaLabirinto("Etapa1/labirinto.txt");

        // Exibe o labirinto inicial
        lab.exibirLabirinto();
        
        // Executa a busca pela saída
        if (lab.percorreLabirinto() == true) {
            System.out.println("Solução encontrada");
        } else {
            System.out.println("Solução não encontrada.");
        }
        
        // Exibe o labirinto final, mostrando o caminho da solução (se encontrado)
        lab.imprimeLabirinto();
    }
}
