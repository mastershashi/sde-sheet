package PatternWise.SlidingWindow;

import java.util.*;

public class MinWindowPatterns {

    /*
     * ============================================================
     * 🧠 CORE IDEA
     * ============================================================
     * Expand until valid → shrink to minimize
     *
     * GENERIC TEMPLATE:
     *
     * int left = 0;
     * for (int right = 0; right < n; right++) {
     *     add right into window;
     *
     *     while (window is valid) {
     *         update minimum answer;
     *         remove left from window;
     *         left++;
     *     }
     * }
     *
     * WHAT DIFFERS PER VARIANT:
     * - Sum window: valid when sum >= target
     * - Cover string: valid when all required frequencies are covered
     * - Subsequence: valid is ordered matching, not frequency matching
     * - Balanced string: valid when outside-window counts are already balanced
     *
     * TOP LC VARIANTS:
     * - LC 209. Minimum Size Subarray Sum
     *   Companies: Amazon, Apple, Bloomberg, ByteDance, Facebook/Meta,
     *   Google, Microsoft, Oracle
     * - LC 76. Minimum Window Substring
     *   Companies: Adobe, Airbnb, Amazon, Apple, Bloomberg, Facebook/Meta,
     *   Google, LinkedIn, Microsoft, Oracle, Uber
     * - LC 727. Minimum Window Subsequence
     *   Companies: Amazon, Bloomberg, eBay, Google, Houzz, Microsoft
     * - LC 1234. Replace the Substring for Balanced String
     *   Companies: Amazon, Google, Microsoft
     * ============================================================
     */

    /*
     * ------------------------------------------------------------
     * 1. MIN SUBARRAY SUM >= K
     * Variation: shrink while window is valid to minimize length
     * - LC 209. Minimum Size Subarray Sum
     * - Related: LC 76. Minimum Window Substring for frequency-cover windows
     * - Companies: Amazon, Apple, Bloomberg, ByteDance, Facebook/Meta,
     *   Google, Microsoft, Oracle
     * - Time: O(n) for positive nums
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int minSubArrayLen(int k, int[] nums) {
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
     * 2. MINIMUM WINDOW SUBSTRING
     * Variation: smallest window covering required frequencies
     * - LC 76. Minimum Window Substring
     * - Companies: Adobe, Airbnb, Amazon, Apple, Bloomberg, Facebook/Meta,
     *   Google, LinkedIn, Microsoft, Oracle, Uber
     * - Time: O(n + m)
     * - Space: O(1), ASCII bounded
     * ------------------------------------------------------------
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length())
            return "";

        int[] need = new int[128];
        for (char c : t.toCharArray())
            need[c]++;

        int left = 0, required = t.length();
        int bestLen = Integer.MAX_VALUE, bestStart = 0;

        for (int right = 0; right < s.length(); right++) {
            if (need[s.charAt(right)]-- > 0)
                required--;

            while (required == 0) {
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestStart = left;
                }

                if (++need[s.charAt(left++)] > 0)
                    required++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    /*
     * ------------------------------------------------------------
     * 3. MINIMUM WINDOW SUBSEQUENCE
     * Variation: cover ordered subsequence, then backtrack to minimize
     * - LC 727. Minimum Window Subsequence
     * - Companies: Amazon, Bloomberg, eBay, Google, Houzz, Microsoft
     * - Time: O(n * m) in worst case
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public String minWindowSubsequence(String s, String t) {
        int bestLen = Integer.MAX_VALUE, bestStart = -1;
        int i = 0;

        while (i < s.length()) {
            int j = 0;
            while (i < s.length()) {
                if (s.charAt(i) == t.charAt(j))
                    j++;
                if (j == t.length())
                    break;
                i++;
            }

            if (i == s.length())
                break;

            int end = i + 1;
            j = t.length() - 1;
            while (i >= 0) {
                if (s.charAt(i) == t.charAt(j))
                    j--;
                if (j < 0)
                    break;
                i--;
            }

            if (end - i < bestLen) {
                bestLen = end - i;
                bestStart = i;
            }

            i++;
        }

        return bestStart == -1 ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    /*
     * ------------------------------------------------------------
     * 4. REPLACE SUBSTRING FOR BALANCED STRING
     * Variation: find smallest removable window so outside counts <= n / 4
     * - LC 1234. Replace the Substring for Balanced String
     * - Companies: Amazon, Google, Microsoft
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int balancedString(String s) {
        int n = s.length(), target = n / 4;
        int[] count = new int[128];
        for (char c : s.toCharArray())
            count[c]++;

        int left = 0, best = n;

        for (int right = 0; right < n; right++) {
            count[s.charAt(right)]--;

            while (left <= right &&
                    count['Q'] <= target && count['W'] <= target &&
                    count['E'] <= target && count['R'] <= target) {
                best = Math.min(best, right - left + 1);
                count[s.charAt(left++)]++;
            }
        }

        return best;
    }

    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {
        MinWindowPatterns sol = new MinWindowPatterns();

        int[] nums = { 2, 3, 1, 2, 4, 3 };

        System.out.println(sol.minSubArrayLen(7, nums));
        System.out.println("LC 76 Min Window: " + sol.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println("LC 727 Min Window Subsequence: " + sol.minWindowSubsequence("abcdebdde", "bde"));
        System.out.println("LC 1234 Balanced String: " + sol.balancedString("WQWRQQQW"));
    }
}
