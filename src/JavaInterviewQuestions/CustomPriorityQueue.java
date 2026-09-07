import java.util.ArrayList;
import java.util.List;

public class CustomPriorityQueue {

    /**
     * We wiill implement the Prority Queue using Heap data structure and by using
     * the property of complete binary tree
     * 1. Heap Property( Min Heap)
     * Value at root node at every level will be smaller than it's left adn right
     * child
     * 2. Complete binary tree
     * leftIndex = 2 * parentIndex + 1
     * rightIndex = 2 * parentIndex + 2
     * 
     * parentIndex = (leftIndex - 1) / 2
     * parentIndex = (rightInex - 2 ) / 2
     * A complete binary tree is a binary tree where every level, except possibly
     * the last, is completely filled, and all nodes
     * in the last level are as far left as possible. This structure ensures the
     * tree is balanced, minimizing height for efficient operations
     * 
     * height(n) = log(n)
     * note this implementation only support min heap, check file
     */

    List<Integer> data;

    public CustomPriorityQueue() {
        data = new ArrayList<>();
    }

    public void add(Integer value) {
        // add at last position
        // upheapify
        data.add(value);
        upheapify(data.size() - 1);
    }

    private void upheapify(int idx) {
        if (idx == 0)
            return;
        int parentIndex = (idx - 1) / 2;
        if (data.get(idx) < data.get(parentIndex)) {
            swap(idx, parentIndex);
            upheapify(parentIndex);
        }
    }

    private void swap(int idx, int parentIndex) {
        int temp = data.get(idx);
        data.set(idx, data.get(parentIndex));
        data.set(parentIndex, temp);
    }

    public int remove() {
        // swap root with last
        // remove last
        // downheapify from root
        if (data.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }
        swap(0, data.size() - 1);
        int val = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            downheapify(0);
        }
        return val;
    }

    private void downheapify(int idx) {
        int minIndex = idx;
        int leftIndex = (2 * minIndex) + 1;
        int rightIndex = (2 * idx) + 2;
        if (leftIndex < data.size() && data.get(leftIndex) < data.get(minIndex)) {
            minIndex = leftIndex;
        }

        if (rightIndex < data.size() && data.get(rightIndex) < data.get(minIndex)) {
            minIndex = rightIndex;
        }
        if (minIndex != idx) {
            swap(minIndex, idx);
            downheapify(minIndex);
        }
    }

    public Integer peek() {
        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public static void main(String[] args) {
        CustomPriorityQueue obj = new CustomPriorityQueue();
        obj.add(5);
        obj.add(3);
        obj.add(10);
        obj.add(40);
        obj.add(2);
        obj.add(20);
        obj.add(13);

        System.out.println(obj.peek());
        System.out.println(obj.remove());
        System.out.println(obj.remove());

        System.out.println(obj.peek());

    }

}
