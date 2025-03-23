package systemdesign.LLD.SnakeAndLadder;

import java.util.Map;

public class Board{
    private Map<Integer, Integer> snakesAndLadders;
    public Board(Map<Integer, Integer> snakesAndLadders) {
        this.snakesAndLadders = snakesAndLadders;
    }
    public int movePlayer(int position) {
        return snakesAndLadders.getOrDefault(position, position);
    }
}