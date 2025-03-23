package systemdesign.LLD.SnakeAndLadder;

public class Player {

    private int position;
    private String name;

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Player(String name) {
        this.name = name;
        this.position = 1;
    }
    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
    
}
