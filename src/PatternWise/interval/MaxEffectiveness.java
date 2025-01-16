import java.util.Arrays;

class Interval {
    int pid, startTime, endTime, effectiveness;

    public Interval(int pid, int startTime, int endTime, int effectiveness) {
        this.pid = pid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.effectiveness = effectiveness;
    }
}

public class MaxEffectiveness {

    // Function to calculate the maximum effectiveness of non-overlapping intervals
    public static int maxEffectiveness(Interval[] intervals) {
        // Step 1: Sort intervals by their end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.endTime, b.endTime));

        int n = intervals.length;
        
        // Step 2: DP array to store the maximum effectiveness up to each interval
        int[] dp = new int[n];
        
        // Step 3: Initialize the DP array
        dp[0] = intervals[0].effectiveness;  // The first interval's effectiveness
        
        // Step 4: Calculate dp values for each interval
        for (int i = 1; i < n; i++) {
            // Option 1: Exclude the current interval
            dp[i] = Math.max(dp[i], dp[i-1]);

            // Option 2: Include the current interval
            // Find the last non-overlapping interval
            int lastNonOverlap = binarySearch(intervals, i);
            
            // If there's a valid non-overlapping interval, include the current interval
            if (lastNonOverlap != -1) {
                dp[i] = Math.max(dp[i], dp[lastNonOverlap] + intervals[i].effectiveness);
            } else {
                dp[i] = Math.max(dp[i], intervals[i].effectiveness);
            }
        }

        // Step 5: Return the maximum effectiveness found in dp
        return dp[n - 1];
    }

    // Binary search to find the last interval that ends before intervals[i].startTime
    public static int binarySearch(Interval[] intervals, int currentIndex) {
        int left = 0, right = currentIndex - 1;
        int targetStartTime = intervals[currentIndex].startTime;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid].endTime <= targetStartTime) {
                // This is a valid non-overlapping interval
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Return the index of the last valid non-overlapping interval
        return right;
    }

    public static void main(String[] args) {
        // Example input
        Interval[] intervals = {
            new Interval(1, 1, 2, 50),
            new Interval(2, 3, 5, 50),
            new Interval(3, 6, 8, 100),
            new Interval(4, 2, 100, 200)
        };
        
        // Find and print the result
        int result = maxEffectiveness(intervals);
        System.out.println("Maximum effectiveness of non-overlapping intervals: " + result);
    }
}
