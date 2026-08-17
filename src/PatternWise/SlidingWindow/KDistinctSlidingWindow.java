package PatternWise.SlidingWindow;

import java.util.*;

public class KDistinctSlidingWindow {

    /*
     * ============================================================
     * 🧠 CORE IDEA
     * ============================================================
     * Maintain frequency map
     * Shrink when map size > K
     *
     * GENERIC TEMPLATE:
     *
     * Map<T, Integer> freq = new HashMap<>();
     * int left = 0;
     * for (int right = 0; right < n; right++) {
     *     add item[right] to freq;
     *
     *     while (freq.size() > k) {
     *         remove item[left] from freq;
     *         left++;
     *     }
     *
     *     // max-length: ans = max(ans, right - left + 1)
     *     // count: ans += right - left + 1
     * }
     *
     * WHAT DIFFERS PER VARIANT:
     * - LC 340/159/904: maximize length with at most K distinct
     * - LC 992: count exactly K by subtracting atMost results
     * - LC 1248: convert number to parity, then exactly K odds
     *
     * TOP LC VARIANTS:
     * - LC 340. Longest Substring with At Most K Distinct Characters
     *   Companies: Amazon, Bloomberg, Citadel, Facebook/Meta, Google,
     *   Microsoft, Uber
     * - LC 159. Longest Substring with At Most Two Distinct Characters
     *   Companies: Adobe, Amazon, Facebook/Meta, Google, Microsoft
     * - LC 904. Fruit Into Baskets
     *   Companies: Amazon, Google
     * - LC 992. Subarrays with K Different Integers
     *   Companies: Alibaba, Amazon, Goldman Sachs, Google, Uber
     * - LC 1248. Count Number of Nice Subarrays
     *   Companies: Amazon, Google, Microsoft, Facebook/Meta
     * ============================================================
     */

    /*
     * ------------------------------------------------------------
     * 1. LONGEST SUBSTRING WITH K DISTINCT
     * Variation: at most K distinct characters
     * - LC 340. Longest Substring with At Most K Distinct Characters
     * - Related: LC 159. Longest Substring with At Most Two Distinct Characters,
     *   LC 904. Fruit Into Baskets
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft, Uber
     * - Time: O(n)
     * - Space: O(k)
     * ------------------------------------------------------------
     */
    public int longestKDistinct(String s, int k) {
        int left = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char lc = s.charAt(left++);
                map.put(lc, map.get(lc) - 1);
                if (map.get(lc) == 0)
                    map.remove(lc);
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    /*
     * ------------------------------------------------------------
     * 2. COUNT SUBARRAYS WITH AT MOST K DISTINCT
     * Variation: every valid window ending at right contributes window length
     * - Helper for LC 992. Subarrays with K Different Integers
     * - Related: LC 904. Fruit Into Baskets for K = 2 max-length version
     * - Companies: Alibaba, Amazon, Goldman Sachs, Google, Uber
     * - Time: O(n)
     * - Space: O(k)
     * ------------------------------------------------------------
     */
    public int atMostK(int[] nums, int k) {
        int left = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0)
                    map.remove(nums[left]);
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 3. EXACTLY K DISTINCT
     * Variation: exactly(K) = atMost(K) - atMost(K - 1)
     * - LC 992. Subarrays with K Different Integers
     * - Related: LC 1248. Count Number of Nice Subarrays,
     *   LC 930. Binary Subarrays With Sum
     * - Companies: Alibaba, Amazon, Goldman Sachs, Google, Uber
     * - Time: O(n)
     * - Space: O(k)
     * ------------------------------------------------------------
     */
    public int exactlyK(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    /*
     * ------------------------------------------------------------
     * 4. AT MOST TWO DISTINCT CHARACTERS
     * Variation: K is fixed to 2
     * - LC 159. Longest Substring with At Most Two Distinct Characters
     * - Companies: Adobe, Amazon, Facebook/Meta, Google, Microsoft
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        return longestKDistinct(s, 2);
    }

    /*
     * ------------------------------------------------------------
     * 5. FRUIT INTO BASKETS
     * Variation: longest subarray with at most 2 distinct integers
     * - LC 904. Fruit Into Baskets
     * - Companies: Amazon, Google
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int totalFruit(int[] fruits) {
        int left = 0, best = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0)
                    map.remove(fruits[left]);
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {
        KDistinctSlidingWindow sol = new KDistinctSlidingWindow();

        System.out.println(sol.longestKDistinct("araaci", 2));

        int[] nums = { 1, 2, 1, 2, 3 };

        System.out.println(sol.atMostK(nums, 2));
        System.out.println(sol.exactlyK(nums, 2));
        System.out.println("LC 159 At Most Two Distinct: " +
                sol.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
        System.out.println("LC 904 Fruit: " + sol.totalFruit(new int[] { 1, 2, 3, 2, 2 }));
    }
}
