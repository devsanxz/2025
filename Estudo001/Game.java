package Estudo001;

public class Game {
    Board board;
    Player player0;
    Player player1;
    int currentPlayer;
    
    public Game() {
        board = new Board();
        player0 = new Player();
        player1 = new Player();
        currentPlayer = 0;
    }    
    public void start() {
        System.out.println("Game started!");
    }
}
