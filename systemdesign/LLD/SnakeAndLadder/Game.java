package systemdesign.LLD.SnakeAndLadder;

import java.util.LinkedList;
import java.util.Queue;

public class Game {

    private Board board;
    private Queue<Player> players;

    public Game(Board board) {
        this.board = board;
        this.players =  new LinkedList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        while (!players.isEmpty()) {
                Player currentPlayer = players.poll();
                int diceValue = currentPlayer.rollDice();
                int newPosition = currentPlayer.getPosition() + diceValue;
                if(newPosition > 100) {
                    newPosition = currentPlayer.getPosition(); // If the roll goes beyond 100, the player doesn't move
                }
                newPosition = board.movePlayer(newPosition); // Move the player based on snake/ladder
                currentPlayer.setPosition(newPosition); // Update the player's position

                System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " and moved to position " + newPosition);

                if (newPosition == 100) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    break; // Game ends when a player reaches position 100
                }
    
                // Add the player back for the next turn
                players.add(currentPlayer);
            }
        }
}
