package PatternWise.Heap;

import java.util.PriorityQueue;

public class FindMedianOfaStream {
    PriorityQueue<Integer> maxHeap ;
    PriorityQueue<Integer> minHeap;
    
    public FindMedianOfaStream(){
        maxHeap = new PriorityQueue<>((a,b)->b-a); // stores the smallest value at root
        minHeap = new PriorityQueue<>(); // stores the largest value at root 
    }

    private void addNum(Integer number){
        maxHeap.offer(number);

        // Balance the heaps: maxHeap can only have at most one more element than minHeap
        if(maxHeap.size() > minHeap.size()+1){
            minHeap.offer(maxHeap.poll());
        }
         // If the number in maxHeap is greater than the number in minHeap, swap the roots
         if(!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
            minHeap.offer(maxHeap.poll());
            maxHeap.offer(minHeap.poll());
         }

    }

    private double findMedian(){

        if(maxHeap.size() > minHeap.size()){
            return maxHeap.peek();
        }else{
            return (minHeap.peek() + maxHeap.peek())/ 2.0;
        }

    }


    public static void main(String[] args) {
        FindMedianOfaStream medianFinder = new FindMedianOfaStream();
        
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian()); // Output: 1.0
        
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian()); // Output: 1.5
        
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian()); // Output: 2.0

        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian()); // Output: 2.0
    }
    
}
