package PatternWise.Heap;

import java.util.PriorityQueue;

public class KthLargestElement {
    public int getKthLargestElement(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5,6,7};
        int k = 3;
        KthLargestElement obj = new KthLargestElement();
        System.out.println(obj.getKthLargestElement(nums, k));

    }

}
