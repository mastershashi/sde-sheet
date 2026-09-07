import java.util.LinkedList;

public class CustomSet<T> {
    private final int SIZE = 10;
    private LinkedList<T>[] bucket;

    @SuppressWarnings("unchecked")
    public CustomSet() {
        this.bucket = new LinkedList[SIZE];
        for (int i = 0; i < this.SIZE; i++) {
            this.bucket[i] = new LinkedList<>();
        }
    }

    private int getIndex(T value) {
        return Math.abs(value.hashCode()) % SIZE;
    }

    public boolean add(T value) {
        int index = getIndex(value);
        if (bucket[index].contains(value)) {
            return false; // Already exists
        }
        bucket[index].add(value);
        return true;
    }

    public boolean contains(T value) {
        int index = getIndex(value);
        return bucket[index].contains(value);
    }

    public boolean remove(T value) {
        int index = getIndex(value);
        return bucket[index].remove(value);
    }

    public int size() {
        int count = 0;
        for (LinkedList<T> bucket : bucket) {
            count += bucket.size();
        }
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Display elements
    public void display() {
        for (LinkedList<T> bucket : bucket) {
            for (T value : bucket) {
                System.out.print(value + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        CustomSet<Integer> set = new CustomSet<>();

        set.add(10);
        set.add(20);
        set.add(30);
        set.add(10); // Duplicate, won't be added

        System.out.println(set.contains(20)); // true
        System.out.println(set.contains(50)); // false

        System.out.println(set.size()); // 3

        set.remove(20);

        set.display(); // 10 30

    }
}
