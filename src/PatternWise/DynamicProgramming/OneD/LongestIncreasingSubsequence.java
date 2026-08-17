package PatternWise.DynamicProgramming.OneD;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
public static int lengthOfLIS(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each character alone is an LIS of length 1
        
        int maxLIS = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) > s.charAt(j)) { // Ensure increasing order
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        
        return maxLIS;
    }
    public static void main(String[] args) {
        String s = "bdcabcda";
        System.out.println("Length of LIS: " + lengthOfLIS(s));
    }
    
}
