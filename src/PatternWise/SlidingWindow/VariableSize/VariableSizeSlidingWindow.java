package PatternWise.SlidingWindow.VariableSize;

public class VariableSizeSlidingWindow {

    /*
     * ============================================================
     * 🧠 CORE IDEA
     * ============================================================
     * Expand right → shrink left when invalid
     *
     * GENERIC TEMPLATE:
     *
     * int left = 0;
     * for (int right = 0; right < n; right++) {
     * add right element into window state;
     *
     * while (window is invalid) {
     * remove left element from state;
     * left++;
     * }
     *
     * update answer with valid window [left..right];
     * }
     *
     * WHAT DIFFERS PER VARIANT:
     * - Unique chars: invalid when frequency of current char > 1
     * - Sum/product/cost: invalid when state exceeds limit
     * - Flip zeros: invalid when zeros > K
     * - Minimum window: update inside while(valid), then shrink
     *
     * TOP LC VARIANTS:
     * - LC 3. Longest Substring Without Repeating Characters
     * Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     * Bloomberg, Adobe, Uber
     * - LC 209. Minimum Size Subarray Sum
     * Companies: Amazon, Apple, Bloomberg, ByteDance, Facebook/Meta,
     * Google, Microsoft, Oracle
     * - LC 713. Subarray Product Less Than K
     * Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - LC 1004. Max Consecutive Ones III
     * Companies: Amazon, Facebook/Meta, Microsoft, Yandex
     * - LC 1208. Get Equal Substrings Within Budget
     * Companies: Google, Amazon, Microsoft
     * - LC 487. Max Consecutive Ones II
     * Companies: Google
     * ============================================================
     */

    /*
     * ------------------------------------------------------------
     * 1. LONGEST SUBARRAY SUM <= K
     * Variation: maximize valid window while sum/cost <= K
     * - Closest: LC 1208. Get Equal Substrings Within Budget
     * - Related: LC 1004. Max Consecutive Ones III, LC 487. Max Consecutive Ones II
     * - Companies: Google, Amazon, Microsoft, Facebook/Meta
     * - Time: O(n) for non-negative nums/costs
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int longestSumAtMostK(int[] nums, int k) {
        int left = 0, sum = 0, max = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > k)
                sum -= nums[left++];

            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    /*
     * ------------------------------------------------------------
     * 2. SHORTEST SUBARRAY SUM >= K
     * Variation: minimize valid window after reaching target
     * - LC 209. Minimum Size Subarray Sum
     * - Companies: Amazon, Apple, Bloomberg, ByteDance, Facebook/Meta,
     * Google, Microsoft, Oracle
     * - Time: O(n) for positive nums
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int shortestSumAtLeastK(int[] nums, int k) {
        int left = 0, sum = 0, min = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= k) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /*
     * ------------------------------------------------------------
     * 3. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
     * Variation: shrink while current character is duplicated
     * - LC 3. Longest Substring Without Repeating Characters
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     * Bloomberg, Adobe, Uber
     * - Time: O(n)
     * - Space: O(1), ASCII/charset bounded
     * ------------------------------------------------------------
     */
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[128];
        int left = 0, best = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c]++;

            while (freq[c] > 1)
                freq[s.charAt(left++)]--;

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    /*
     * ------------------------------------------------------------
     * 4. SUBARRAY PRODUCT LESS THAN K
     * Variation: count valid windows after shrinking product
     * - LC 713. Subarray Product Less Than K
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1)
            return 0;

        int left = 0, product = 1, count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k)
                product /= nums[left++];

            count += right - left + 1;
        }

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 5. MAX CONSECUTIVE ONES III
     * Variation: longest window with at most K zeros
     * - LC 1004. Max Consecutive Ones III
     * - Companies: Amazon, Facebook/Meta, Microsoft, Yandex
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, zeros = 0, best = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0)
                zeros++;

            while (zeros > k) {
                if (nums[left++] == 0)
                    zeros--;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    /*
     * ------------------------------------------------------------
     * 6. GET EQUAL SUBSTRINGS WITHIN BUDGET
     * Variation: longest window whose replacement cost <= maxCost
     * - LC 1208. Get Equal Substrings Within Budget
     * - Companies: Google, Amazon, Microsoft
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, cost = 0, best = 0;

        for (int right = 0; right < s.length(); right++) {
            cost += Math.abs(s.charAt(right) - t.charAt(right));

            while (cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    /*
     * ------------------------------------------------------------
     * 7. MAX CONSECUTIVE ONES II
     * Variation: longest window with at most one zero
     * - LC 487. Max Consecutive Ones II
     * - Companies: Google
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int findMaxConsecutiveOnesII(int[] nums) {
        return longestOnes(nums, 1);
    }

    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {
        VariableSizeSlidingWindow sol = new VariableSizeSlidingWindow();

        int[] nums = { 1, 2, 1, 0, 1, 1, 0 };

        System.out.println(sol.longestSumAtMostK(nums, 4));
        System.out.println(sol.shortestSumAtLeastK(nums, 4));
        System.out.println("LC 3 No Repeat: " + sol.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("LC 713 Product < K: " +
                sol.numSubarrayProductLessThanK(new int[] { 10, 5, 2, 6 }, 100));
        System.out.println("LC 1004 Max Ones III: " +
                sol.longestOnes(new int[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 }, 2));
        System.out.println("LC 1208 Equal Substring: " + sol.equalSubstring("abcd", "bcdf", 3));
        System.out.println("LC 487 Max Ones II: " +
                sol.findMaxConsecutiveOnesII(new int[] { 1, 0, 1, 1, 0 }));
    }
}
