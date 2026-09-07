import java.util.Random;

public class SkipList {

    private static final int MAX_LEVEL = 4;

    private final Node head;
    private int currentLevel;
    private final Random random;

    static class Node {
        int value;
        Node[] forward;

        Node(int value, int level) {
            this.value = value;
            this.forward = new Node[level + 1];
        }
    }

    public SkipList() {
        head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        currentLevel = 0;
        random = new Random();
    }

    // ---------------------------------------
    // SEARCH
    // ---------------------------------------

    public boolean search(int value) {

        Node current = head;

        // Start from highest level
        for (int level = currentLevel; level >= 0; level--) {

            while (current.forward[level] != null
                    && current.forward[level].value < value) {

                current = current.forward[level];
            }
        }

        // Move to the next node at level 0
        current = current.forward[0];

        return current != null && current.value == value;
    }

    // ---------------------------------------
    // INSERT
    // ---------------------------------------

    public void insert(int value) {

        Node[] update = new Node[MAX_LEVEL + 1];

        Node current = head;

        // Find the node before the insertion point
        for (int level = currentLevel; level >= 0; level--) {

            while (current.forward[level] != null
                    && current.forward[level].value < value) {

                current = current.forward[level];
            }

            update[level] = current;
        }

        // Move to possible existing node
        current = current.forward[0];

        // Don't insert duplicates
        if (current != null && current.value == value) {
            return;
        }

        // Decide how many levels this new node will have
        int newLevel = randomLevel();

        // If new node is taller than current list
        if (newLevel > currentLevel) {

            for (int level = currentLevel + 1; level <= newLevel; level++) {

                update[level] = head;
            }

            currentLevel = newLevel;
        }

        // Create new node
        Node newNode = new Node(value, newLevel);

        // Connect the new node
        for (int level = 0; level <= newLevel; level++) {

            newNode.forward[level] = update[level].forward[level];

            update[level].forward[level] = newNode;
        }
    }

    // ---------------------------------------
    // DELETE
    // ---------------------------------------

    public void delete(int value) {

        Node[] update = new Node[MAX_LEVEL + 1];

        Node current = head;

        // Find nodes immediately before the target
        for (int level = currentLevel; level >= 0; level--) {

            while (current.forward[level] != null
                    && current.forward[level].value < value) {

                current = current.forward[level];
            }

            update[level] = current;
        }

        // Candidate node
        current = current.forward[0];

        // Value doesn't exist
        if (current == null || current.value != value) {
            return;
        }

        // Remove node from every level
        for (int level = 0; level <= currentLevel; level++) {

            if (update[level].forward[level] != current) {
                break;
            }

            update[level].forward[level] = current.forward[level];
        }

        // Remove empty levels
        while (currentLevel > 0
                && head.forward[currentLevel] == null) {

            currentLevel--;
        }
    }

    // ---------------------------------------
    // RANDOM LEVEL
    // ---------------------------------------

    private int randomLevel() {

        int level = 0;

        while (random.nextBoolean()
                && level < MAX_LEVEL) {

            level++;
        }

        return level;
    }

    // ---------------------------------------
    // PRINT
    // ---------------------------------------

    public void printList() {

        for (int level = currentLevel; level >= 0; level--) {

            Node current = head.forward[level];

            System.out.print("Level " + level + ": ");

            while (current != null) {

                System.out.print(current.value + " -> ");
                current = current.forward[level];
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        SkipList obj = new SkipList();
        obj.insert(5);
        obj.insert(7);
        obj.insert(3);
        obj.insert(12);
        obj.insert(10);
        obj.insert(35);
        obj.insert(25);
        System.out.println("skipList");
        obj.printList();
        // System.out.println(obj.search(20));
        obj.delete(10);
        // System.out.println(obj.search(10));
        System.out.println("skiplist after delete 10");
        obj.printList();
    }
}
