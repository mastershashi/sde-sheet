
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityQueue {

    private List<Integer> data;
    private Comparator<Integer> comparator;

    public PriorityQueue(Comparator<Integer> comparator) {
        this.data = new ArrayList<>();
        this.comparator = comparator;
    }

    public void add(Integer value) {
        data.add(value);
        upheapify(data.size() - 1);
    }

    private void upheapify(int idx) {

        if (idx == 0) {
            return;
        }

        int parentIndex = (idx - 1) / 2;

        if (comparator.compare(
                data.get(idx),
                data.get(parentIndex)) < 0) {

            swap(idx, parentIndex);
            upheapify(parentIndex);
        }
    }

    private void swap(int i, int j) {

        Integer temp = data.get(i);

        data.set(i, data.get(j));
        data.set(j, temp);
    }

    public int remove() {

        if (data.isEmpty()) {
            throw new RuntimeException("Priority Queue is empty");
        }

        swap(0, data.size() - 1);

        int value = data.remove(data.size() - 1);

        if (!data.isEmpty()) {
            downheapify(0);
        }

        return value;
    }

    private void downheapify(int idx) {

        int bestIndex = idx;

        int leftIndex = 2 * idx + 1;
        int rightIndex = 2 * idx + 2;

        if (leftIndex < data.size()
                && comparator.compare(
                        data.get(leftIndex),
                        data.get(bestIndex)) < 0) {

            bestIndex = leftIndex;
        }

        if (rightIndex < data.size()
                && comparator.compare(
                        data.get(rightIndex),
                        data.get(bestIndex)) < 0) {

            bestIndex = rightIndex;
        }

        if (bestIndex != idx) {

            swap(idx, bestIndex);

            downheapify(bestIndex);
        }
    }

    public int peek() {

        if (data.isEmpty()) {
            throw new RuntimeException("Priority Queue is empty");
        }

        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public static void main(String[] args) {
        PriorityQueue minHeap = new PriorityQueue(Integer::compare);
        minHeap.add(5);
        minHeap.add(3);
        minHeap.add(10);
        minHeap.add(40);
        minHeap.add(2);
        minHeap.add(20);
        minHeap.add(13);

        System.out.println("Min Heap:");
        System.out.println("Peek: " + minHeap.peek());

        while (minHeap.size() > 0) {
            System.out.println(minHeap.remove());
        }

        // Max Heap
        PriorityQueue maxHeap = new PriorityQueue(
                (a, b) -> Integer.compare(b, a));

        maxHeap.add(5);
        maxHeap.add(3);
        maxHeap.add(10);
        maxHeap.add(40);
        maxHeap.add(2);
        maxHeap.add(20);
        maxHeap.add(13);

        System.out.println("\nMax Heap:");
        System.out.println("Peek: " + maxHeap.peek());

        while (maxHeap.size() > 0) {
            System.out.println(maxHeap.remove());
        }

    }
}
