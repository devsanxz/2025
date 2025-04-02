package Matrix;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 7; // Tamanho da matriz
    private static final char[][] matrix = new char[SIZE][SIZE]; // Matriz 7x7
    private static final Random random = new Random(); // Gerador de números aleatórios25
    private static boolean testMode = false; // Variável de estado para controlar a verbosidade
    private static int printInterval = 500; // Intervalo padrão para impressão (em ms)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o intervalo de tempo ao usuário
        System.out.println("Digite o intervalo de tempo para impressão da matriz (em milissegundos):");
        try {
            printInterval = Integer.parseInt(scanner.nextLine());
            if (printInterval <= 0) {
                System.out.println("Intervalo inválido. Usando o valor padrão de 500ms.");
                printInterval = 500;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Usando o valor padrão de 500ms.");
            printInterval = 500;
        }

        if (testMode) {
            System.out.println("Iniciando o programa em modo de teste (verboso)...");
        } else {
            System.out.println("Iniciando o programa...");
        }
        System.out.println("Criando uma matriz 7x7 e inicializando com '0'.");

        // Inicializa a matriz com '0'
        initializeMatrix();

        // Thread 1: Atualiza uma célula aleatória a cada 100ms
        Thread thread1 = new Thread(() -> {
            while (true) {
                updateRandomCell();
                try {
                    Thread.sleep(100); // Pausa de 100ms
                } catch (InterruptedException e) {
                    if (testMode) {
                        System.out.println("Thread 1 foi interrompida.");
                    }
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        // Thread 2: Imprime a matriz no intervalo definido pelo usuário
        Thread thread2 = new Thread(() -> {
            while (true) {
                printMatrix();
                try {
                    Thread.sleep(printInterval); // Pausa com base no intervalo definido pelo usuário
                } catch (InterruptedException e) {
                    if (testMode) {
                        System.out.println("Thread 2 foi interrompida.");
                    }
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        if (testMode) {
            System.out.println("Iniciando as threads em modo de teste...");
        } else {
            System.out.println("Iniciando as threads...");
        }
        thread1.start();
        thread2.start();
    }

    // Inicializa a matriz com '0'
    private static void initializeMatrix() {
        synchronized (matrix) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    matrix[i][j] = '0';
                }
            }
        }
        if (testMode) {
            System.out.println("Matriz inicializada com '0'.");
        }
    }

    // Atualiza uma célula aleatória com um caractere imprimível (com ou sem Shift/Alt)
    private static void updateRandomCell() {
        int row = random.nextInt(SIZE); // Linha aleatória
        int col = random.nextInt(SIZE); // Coluna aleatória

        // Gera um caractere aleatório imprimível
        char randomChar = generatePrintableChar();

        synchronized (matrix) { // Sincroniza o acesso à matriz
            matrix[row][col] = randomChar;
            if (testMode) {
                System.out.printf("Thread 1: Atualizou a célula [%d][%d] com o caractere '%c'.%n", row, col, randomChar);
            }
        }
    }

    // Gera um caractere imprimível (com ou sem Shift/Alt)
    private static char generatePrintableChar() {
        // Combina os intervalos de caracteres imprimíveis
        int[] ranges = {0x20, 0x7E, 0xA0, 0xFF}; // ASCII imprimíveis e caracteres estendidos
        int rangeIndex = random.nextInt(ranges.length / 2) * 2; // Escolhe um intervalo aleatório
        int start = ranges[rangeIndex];
        int end = ranges[rangeIndex + 1];

        return (char) (random.nextInt(end - start + 1) + start); // Gera um caractere dentro do intervalo
    }

    // Imprime a matriz no console
    private static void printMatrix() {
        synchronized (matrix) { // Sincroniza o acesso à matriz
            if (testMode) {
                System.out.println("Thread 2: Imprimindo a matriz:");
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            // System.out.println("---------------------");
        }
    }
}
