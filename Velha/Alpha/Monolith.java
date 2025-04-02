package Velha.Alpha;

import java.util.Scanner;

public class Monolith {
    public static Scanner scanner;
    private static String validInput;
    private static char[][] board = new char[3][3]; // Tabuleiro 3x3
    private static char[] playerTable = new char[]{'x', 'o'}; // Jogadores 0 e 1
    private static int currentPlayerIndex = 0; // Índice do jogador atual
    private static char currentPlayer = playerTable[currentPlayerIndex]; // Jogador atual
    private static char emptySquareChar = ' '; // Caractere para quadrado vazio
    private static boolean isInPlayMode = true; // Modo de jogo

    public static void say(String message) {
        System.out.println(message);
    }
    public static void start() {
        say("Monolith iniciado!");
        clearBoard();
        startScanner();
        while (isInPlayMode)
        {
            turnLoop();
        }
    }
    private static void turnLoop() {
        checkPlayability(); // criar
        queryMove();
        processMove();
        endTurn();
        checkWinner(); // criar
    }

    private static void endTurn() {
        say("Turno encerrado!");
    }

    private static void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = emptySquareChar; // Limpa o tabuleiro
            }
        }
    }

    private static String getInput() {
        say("Digite a jogada (x,y) no formato NN : N={1,2,3}");
        return scanner.nextLine();
    }
    private static void clearInput() {
        say("Entrada limpa!");
        validInput = ""; // Limpa a entrada válida
    }
    private static void play(int line, int column) {
        board[line - 1][column - 1] = currentPlayer; // Marca a jogada no tabuleiro
        say("Jogada realizada: " + line + ", " + column);
        clearInput(); // Limpa a entrada
        callNextPlayer();
        showBoard();
    }
    private static void callNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerTable.length; // Alterna o jogador
        currentPlayer = playerTable[currentPlayerIndex]; // Atualiza o jogador atual
        say("Próximo jogador: " + currentPlayer);
    }
    private static void showBoard() {
        say("Tabuleiro atual:");
        for (int i = 0; i < board.length; i++) {
            String lineMessage = "";
            for (int j = 0; j < board[i].length; j++) {
                lineMessage += ("[" + board[i][j] + "]");
            }
            say(lineMessage);
            //System.out.println();
        }
    }
    private static void processMove() {
        say("Processando jogada...");
        int line = Character.getNumericValue(validInput.charAt(0));
        int column = Character.getNumericValue(validInput.charAt(1));
        boolean squareOccupied = isTheSquareEmpty(line, column);
        if (squareOccupied == true) {
            say("Quadrado já ocupado! Tente novamente.");
            queryMove(); // Chama novamente para pedir uma nova jogada
        } else {
            play(line, column);
            say("Jogada processada com sucesso!");
        }
    }
    private static boolean isTheSquareEmpty(int line, int column) {
        if (board[line - 1][column - 1] == emptySquareChar) {
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
            validInput = ""; // Limpa a entrada inválida
            say("Entrada inválida! Os números devem estar entre 1 e 3.");
            return false;
        }
        validInput = input; // Armazena a entrada válida
        return true;
    }
}