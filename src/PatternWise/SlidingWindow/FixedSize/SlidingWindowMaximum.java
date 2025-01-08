import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
// this is a valid solution but it will give you TLE as time complexity is n*k and if k ->n then n^2 which is bad 
    static int[] findSlidingWindowMax(int nums[], int k){
        if (nums == null || nums.length == 0 || k <= 0) return new int[]{};
          // Result array to store the maximums for each window
        int[] maxArr = new int[nums.length - k + 1];
        
        // Initialize pointers
        int start = 0;
        int end = 0;
        int p = 0;  // Pointer for result array
        while (end < nums.length) {
         if (end - start + 1 < k) {
                end++;
            }
            if (end - start + 1 == k) {
                // Calculate the max of the current window
                int max = Integer.MIN_VALUE;
                for (int i = start; i <= end; i++) {
                    max = Math.max(max, nums[i]);
                }
                // Store the max in result array
                maxArr[p] = max;
                // Move the window by incrementing start and end
                start++;
                end++;
                p++;
            }
        }
        return maxArr;
    }
// optized solution using DeQueue
    public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0) return new int[]{};

        // Result array to store the maximum values
        int[] maxArr = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();  // Deque to store indices
        int p = 0;  // Pointer for result array

        for (int i = 0; i < nums.length; i++) {
            // Remove elements out of the current window (i - k)
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove elements from the back of the deque while they are smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.offerLast(i);

            // When we have processed at least `k` elements, the front of the deque is the max for this window
            if (i >= k - 1) {
                maxArr[p++] = nums[deque.peekFirst()];
            }
        }

        return maxArr;
    }
    public static void main(String[] args) {
            int arr[] = new int[]{1,3,-1,-3,5,3,6,7};
            int k = 3;
            int []maxArr = SlidingWindowMaximum.findSlidingWindowMax(arr,k);
            System.out.println(" maxArr size "+ maxArr.length);
            System.out.print(" Maximumwindow ");
            for( int i = 0 ;i< maxArr.length;i++){
                System.out.print(maxArr[i]);
            }
    }
}
