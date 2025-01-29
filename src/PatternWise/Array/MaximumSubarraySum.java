package PatternWise.Array;

public class MaximumSubarraySum {
    static int maximumSubarraySum(int arr[]){
        int currentSum = arr[0];
        int maxSum = arr[0];
        for( int i = 1 ;i < arr.length ;i++){
            // Kadane's Algorithm 
            currentSum = Math.max(arr[i], currentSum+arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    public static void main(String[] args) {
        System.out.println(MaximumSubarraySum.maximumSubarraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
    
}
