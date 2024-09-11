import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k){
        if(nums == null || nums.length == 0 || k<=0){
            return new int[0];
        }
        int n = nums.length;
        int []result = new int[n-k+1];
        Deque<Integer> deqeue  = new LinkedList<>();
        for(int i=0; i<n; i++){
            while(!deqeue.isEmpty() && deqeue.peekFirst() < i-k+1){
                deqeue.pollFirst();
            }
            while(!deqeue.isEmpty() && nums[deqeue.peekFirst()] < nums[i]){
                deqeue.pollLast();
            }
            deqeue.offer(i);
            if(i >= k-1){
                result[i-k+1] = nums[deqeue.peekFirst()];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int nums[] = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int result[] = maxSlidingWindow(nums, k);
        for(int i=0; i<result.length; i++){
            System.out.print(result[i] + " ");
        }
        
    }
}
