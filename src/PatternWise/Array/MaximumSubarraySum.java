package PatternWise.Array;

public class MaximumSubarraySum {
    static int maximumSubarraySum(int arr[]) {
        // Maximum sum of subarray ending at current position
        int currentSum = arr[0];

        // Stores the result (maximum sum found so far)
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // Kadane's Algorithm
            // Either extend the previous subarray or start
            // new from current element
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            // Update result if the new subarray sum is larger
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(MaximumSubarraySum.maximumSubarraySum(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }

}
