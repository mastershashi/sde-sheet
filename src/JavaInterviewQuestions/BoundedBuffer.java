import java.util.LinkedList;
public class BoundedBuffer<T>{
LinkedList<T> buffer ;
int capacity;
BoundedBuffer(int capacity){
    this.capacity = capacity;
    buffer = new LinkedList<>();
}

public synchronized T get(){
    while(buffer.isEmpty()){
        try{
            wait();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    T item = buffer.removeFirst();
    notifyAll();
    return item;
}
public synchronized void put(T item){
    while(buffer.size() == capacity){
        try{
            wait();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    buffer.add(item);
    notifyAll();
}

    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(10);
        Thread producer = new Thread(() ->{
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Producing: " + i);
                    buffer.put(i);
                    Thread.sleep(100); // Simulate time taken to produce
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() ->{
            try {
                for (int i = 0; i < 10; i++) {
                    Integer item = (Integer) buffer.get();
                    System.out.println("Consuming: " + item);
                    Thread.sleep(150); // Simulate time taken to consume
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        }
    }


