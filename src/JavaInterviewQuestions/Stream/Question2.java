import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class Question2 {

    public static void main(String[] args) {
        /* Given a stream of integer, find the Kth largest element in it using streams */
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2); 
        numbers.add(6);
        numbers.add(3);
        numbers.add(4);
        numbers.add(1);
        numbers.add(8);
        numbers.add(34);
        numbers.add(7);

        int k = 3;

       Optional<Integer> KthHighestNum =  numbers.stream().sorted(Comparator.reverseOrder()).skip(k-1).findFirst();
        if( KthHighestNum.isPresent()){
            //System.out.println(KthHighestNum.get());
        }


        PriorityQueue<Integer> minHeap= new PriorityQueue<>();
        int n = numbers.size();
        for(int i = 0;i < n ;i++){
            minHeap.add(numbers.get(i));
        }

        for(int i = 1;i <=(n-k) ;i++){
                minHeap.poll();
        }
      
        System.out.println("Minheap Kth Highest number"+minHeap.peek());

    }
    
}
