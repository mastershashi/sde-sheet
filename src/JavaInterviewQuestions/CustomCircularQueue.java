public class CustomCircularQueue<T> {

    private Object[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CustomCircularQueue(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.data = new Object[capacity];

        front = 0;
        rear = 0;
        size = 0;
    }

    // Add element
    public void enqueue(T value) {

        if (isFull()) {
            throw new IllegalStateException(
                    "Queue Overflow: Queue is full");
        }

        data[rear] = value;

        rear = (rear + 1) % capacity;

        size++;
    }

    // Remove element
    @SuppressWarnings("unchecked")
    public T dequeue() {

        if (isEmpty()) {
            throw new IllegalStateException(
                    "Queue Underflow: Queue is empty");
        }

        T value = (T) data[front];

        data[front] = null;

        front = (front + 1) % capacity;

        size--;

        return value;
    }

    // Return first element without removing
    @SuppressWarnings("unchecked")
    public T peek() {

        if (isEmpty()) {
            throw new IllegalStateException(
                    "Queue Underflow: Queue is empty");
        }

        return (T) data[front];
    }

    // Check whether queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check whether queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Number of elements
    public int size() {
        return size;
    }

    // Maximum capacity
    public int capacity() {
        return capacity;
    }

    // Display elements in FIFO order
    @SuppressWarnings("unchecked")
    public void display() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Queue: ");

        for (int i = 0; i < size; i++) {

            int index = (front + i) % capacity;

            System.out.print((T) data[index] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        CustomCircularQueue<Integer> queue = new CustomCircularQueue<>(5);

        System.out.println("Initial:");
        queue.display();

        // Enqueue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        queue.display();

        System.out.println("Front: " + queue.peek());
        System.out.println("Size: " + queue.size());

        // Queue is full
        try {
            queue.enqueue(60);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Dequeue
        System.out.println("Removed: " + queue.dequeue());
        System.out.println("Removed: " + queue.dequeue());

        queue.display();

        // Circular behavior
        queue.enqueue(60);
        queue.enqueue(70);

        queue.display();

        System.out.println("Front: " + queue.peek());

        // Remove everything
        while (!queue.isEmpty()) {
            System.out.println("Removed: " + queue.dequeue());
        }

        // Underflow
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

}
