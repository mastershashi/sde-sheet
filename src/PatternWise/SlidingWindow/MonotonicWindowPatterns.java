package PatternWise.SlidingWindow;

import java.util.*;

public class MonotonicWindowPatterns {

    /*
     * ============================================================
     * 🧠 CORE IDEA
     * ============================================================
     * Use deque to maintain max/min in window
     *
     * GENERIC TEMPLATE:
     *
     * Deque<Integer> dq = new ArrayDeque<>();
     * for (int i = 0; i < n; i++) {
     *     while (!dq.isEmpty() && new value makes dq tail useless) {
     *         dq.pollLast();
     *     }
     *     dq.offerLast(i);
     *
     *     while (!dq.isEmpty() && dq.peekFirst() is outside window) {
     *         dq.pollFirst();
     *     }
     *
     *     use dq.peekFirst() as best max/min candidate;
     * }
     *
     * WHAT DIFFERS PER VARIANT:
     * - LC 239: one decreasing deque for max of each fixed window
     * - LC 1438: one decreasing max deque + one increasing min deque
     * - LC 1425: decreasing deque over DP values within distance K
     * - LC 862: increasing deque over prefix sums
     *
     * TOP LC VARIANTS:
     * - LC 239. Sliding Window Maximum
     *   Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, ByteDance, Uber
     * - LC 1438. Longest Continuous Subarray With Absolute Diff <= Limit
     *   Companies: Google
     * - LC 1425. Constrained Subsequence Sum
     *   Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - LC 862. Shortest Subarray with Sum at Least K
     *   Companies: Google, Amazon, Microsoft
     * ============================================================
     */

    /*
     * ------------------------------------------------------------
     * 1. MAX IN WINDOW
     * Variation: decreasing deque stores candidate max indices
     * - LC 239. Sliding Window Maximum
     * - Related: LC 1438 uses two deques for max and min,
     *   LC 1425 uses deque over DP values
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, ByteDance, Uber
     * - Time: O(n)
     * - Space: O(k)
     * ------------------------------------------------------------
     */
    public int[] maxWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int idx = 0;

        for (int i = 0; i < nums.length; i++) {

            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();

            dq.offerLast(i);

            if (dq.peekFirst() == i - k)
                dq.pollFirst();

            if (i >= k - 1)
                res[idx++] = nums[dq.peekFirst()];
        }

        return res;
    }

    /*
     * ------------------------------------------------------------
     * 2. LONGEST CONTINUOUS SUBARRAY WITH ABS DIFF <= LIMIT
     * Variation: maintain max and min deques for variable window
     * - LC 1438. Longest Continuous Subarray With Absolute Diff <= Limit
     * - Companies: Google
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDq = new LinkedList<>();
        Deque<Integer> minDq = new LinkedList<>();
        int left = 0, best = 0;

        for (int right = 0; right < nums.length; right++) {
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[right])
                maxDq.pollLast();
            while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[right])
                minDq.pollLast();

            maxDq.offerLast(right);
            minDq.offerLast(right);

            while (nums[maxDq.peekFirst()] - nums[minDq.peekFirst()] > limit) {
                if (maxDq.peekFirst() == left)
                    maxDq.pollFirst();
                if (minDq.peekFirst() == left)
                    minDq.pollFirst();
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    /*
     * ------------------------------------------------------------
     * 3. CONSTRAINED SUBSEQUENCE SUM
     * Variation: DP where best previous dp is kept in monotonic deque
     * - LC 1425. Constrained Subsequence Sum
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Deque<Integer> dq = new LinkedList<>();
        int best = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && dq.peekFirst() < i - k)
                dq.pollFirst();

            dp[i] = nums[i] + (dq.isEmpty() ? 0 : Math.max(0, dp[dq.peekFirst()]));
            best = Math.max(best, dp[i]);

            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i])
                dq.pollLast();
            dq.offerLast(i);
        }

        return best;
    }

    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {
        MonotonicWindowPatterns sol = new MonotonicWindowPatterns();

        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };

        System.out.println(Arrays.toString(sol.maxWindow(nums, 3)));
        System.out.println("LC 1438 Longest Abs Diff: " +
                sol.longestSubarray(new int[] { 10, 1, 2, 4, 7, 2 }, 5));
        System.out.println("LC 1425 Constrained Sum: " +
                sol.constrainedSubsetSum(new int[] { 10, 2, -10, 5, 20 }, 2));
    }
}
