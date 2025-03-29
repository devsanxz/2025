package Velha.Alpha;

import java.util.Scanner;

public class Monolith {
    public static Scanner scanner;
    private static String validInput;
    private static int[][] board = new int[3][3]; // Tabuleiro 3x3

    public static void say(String message) {
        System.out.println(message);
    }
    public static void start() {
        say("Monolith iniciado!");
        clearBoard();
        startScanner();
        queryMove();
        processMove();
    }

    private static void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0; // Limpa o tabuleiro
            }
        }
    }

    private static String getInput() {
        say("Digite a jogada (x,y) no formato NN : N={1,2,3}");
        return scanner.nextLine();
    }

    private static void play() {
        
    }
    private static void processMove() {
        say("Processando jogada...");
        boolean squareOccupied = isTheSquareEmpty(validInput.charAt(0).getNumericValue(), validInput.charAt(1).getNumericValue());
        if (squareOccupied == true) {
            say("Quadrado já ocupado! Tente novamente.");
            queryMove(); // Chama novamente para pedir uma nova jogada
        } else {
            play();
            say("Jogada processada com sucesso!");
        }
    }
    private static boolean isTheSquareEmpty(int x, int y) {
        if (board[x][y] != 0) {
            return false; // Quadrado vazio
        } else {
            return true; // Quadrado ocupado
        }
    }
    private static void queryMove() {
        boolean entradaValida = false;

        while (!entradaValida) { // Continua pedindo entrada até ser válida
            if (validateInput(getInput())) {
                entradaValida = true; // Sai do loop se a entrada for válida
                say("Entrada válida!");
                say("Você digitou: " + validInput);
            } else {
                say("Entrada inválida! Tente novamente.");
            }
        }
    }

    private static void startScanner() {
        say("Scanner iniciado!");
        scanner = new Scanner(System.in);
    }

    private static boolean validateInput(String input) {
        if (input.length() != 2) {
            return false;
        }
        char x = input.charAt(0);
        char y = input.charAt(1);
        if (!Character.isDigit(x) || !Character.isDigit(y) || x < '1' || x > '3' || y < '1' || y > '3') {
            return false;
        }
        validInput = input; // Armazena a entrada válida
        return true;
    }
}