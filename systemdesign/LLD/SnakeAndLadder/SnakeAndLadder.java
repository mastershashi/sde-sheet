package systemdesign.LLD.SnakeAndLadder;

import java.util.HashMap;
import java.util.Map;

public class SnakeAndLadder {
    public static void main(String[] args) {
        Map<Integer, Integer> snakesAndLadders = new HashMap<>();
        snakesAndLadders.put(3, 22); // Ladder from 3 to 22
        snakesAndLadders.put(5, 8);  // Ladder from 5 to 8
        snakesAndLadders.put(20, 29); // Ladder from 20 to 29
        snakesAndLadders.put(17, 4);  // Snake from 17 to 4
        snakesAndLadders.put(19, 7);  // Snake from 19 to 7

        // Create a Board object with the snakes and ladders map
        Board board = new Board(snakesAndLadders);

        // Create a Game object
        Game game = new Game(board);
         // Create and add players to the game
         Player player1 = new Player("Player 1");
         Player player2 = new Player("Player 2");
         Player player3 = new Player("Player 3");
         game.addPlayer(player1);
         game.addPlayer(player2);
         game.addPlayer(player3);
         // Start the game
         game.startGame();

    }
    
}
