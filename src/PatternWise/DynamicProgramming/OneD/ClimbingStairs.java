package PatternWise.DynamicProgramming.OneD;

public class ClimbingStairs {
    public static int climbStairs(int n) {
        // Edge cases
        if (n == 0) return 0;  // No stairs to climb
        if (n == 1) return 1;  // Only one way to climb 1 step
        if (n == 2) return 2;  // Two ways to climb 2 steps (1+1 or 2)
        if (n == 3) return 4;  // Four ways to climb 3 steps (1+1+1, 1+2, 2+1, 3)

        // Initialize the dp array to store the number of ways to reach each step
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1;  // 1 way to stay at the ground (do nothing)
        dp[1] = 1;  // 1 way to reach the first step
        dp[2] = 2;  // 2 ways to reach the second step
        dp[3] = 4;  // 4 ways to reach the third step

        // Fill the dp array using the recurrence relation
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // The result is stored in dp[n]
        return dp[n];
    }
    public static void main(String[] args) {
        int n = 5;  // Example input (number of steps)
        int result = climbStairs(n);
        System.out.println("Number of ways to climb to the top: " + result);
        
    }
    
}
