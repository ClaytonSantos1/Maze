package Etapa1;

import java.io.*;

public class Labirinto {
    private static final char PAREDE = 'X';
    private static final char CAMINHO_ABERTO = ' ';
    private static final char SAIDA = 'D';
    private static final char CAMINHO_SOLUCAO = '#';
    private char[][] labirinto;
    private String filename;

    public void criaLabirinto(String filename) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Nome do arquivo não pode ser nulo ou vazio");
        }
    
        setFilename(filename);
    
        FileReader fr = null;
        BufferedReader in = null;
    
        try {
            fr = new FileReader(getFilename());
            in = new BufferedReader(fr);
            String line;
            int rows = 0;
    
            // Contando o número de linhas
            while ((line = in.readLine()) != null) {
                rows++;
            }
            in.close();
    
            // Inicializando array de char[][]
            labirinto = new char[rows][];
            fr = new FileReader(getFilename());
            in = new BufferedReader(fr);
            int row = 0;
            while ((line = in.readLine()) != null) {
                labirinto[row] = line.toCharArray();
                row++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"" + filename + "\" não existe");
            throw new IllegalArgumentException("Arquivo não encontrado", e);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo \"" + filename + "\"");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
    

    // Método para exibir o labirinto inicial
    public void exibirLabirinto() {
        if (labirinto != null) {
            System.out.println("\nLabirinto inicial:");
            for (char[] linha : labirinto) {
                System.out.println(new String(linha));
            }
            setLabirinto(labirinto);
        } else {
            System.out.println("Labirinto não foi criado.");
        }
    }

    // Método para exibir o labirinto final
    public void imprimeLabirinto() {
        if (labirinto != null) {
            System.out.println("Labirinto final:");
            for (char[] linha : labirinto) {
                System.out.println(new String(linha));
            }
        } else {
            System.out.println("Labirinto não foi criado.");
        }
    }

    public boolean percorreLabirinto() {
        return resolverLabirinto(0, 0);
    }

    private boolean resolverLabirinto(int x, int y) {
        // Verifica se a posição atual é a saída
        if (labirinto[x][y] == SAIDA) {
            return true;
        }

        // Verifica se a posição atual é um caminho aberto
        else if (labirinto[x][y] == CAMINHO_ABERTO) {
            labirinto[x][y] = CAMINHO_SOLUCAO;

            // Tenta mover para a direita
            if (resolverLabirinto(x, y + 1)) {
                return true;
            }

            // Tenta mover para baixo
            if (resolverLabirinto(x + 1, y)) {
                return true;
            }

            // Tenta mover para a esquerda
            if (resolverLabirinto(x, y - 1)) {
                return true;
            }

            // Tenta mover para cima
            if (resolverLabirinto(x - 1, y)) {
                return true;
            }

            // Desmarca a posição atual como parte da solução
            labirinto[x][y] = CAMINHO_ABERTO;
            return false;
        }
        return false;
    }

    public static char getParede() {
        return PAREDE;
    }

    public static char getCaminhoAberto() {
        return CAMINHO_ABERTO;
    }

    public static char getSaida() {
        return SAIDA;
    }

    public static char getCaminhoSolucao() {
        return CAMINHO_SOLUCAO;
    }

    public char[][] getLabirinto() {
        return labirinto;
    }

    public void setLabirinto(char[][] labirinto) {
        this.labirinto = labirinto;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
