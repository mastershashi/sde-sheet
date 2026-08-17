package PatternWise.SlidingWindow;

import java.util.*;

public class CountSubarrayPatterns {

    /*
     * ============================================================
     * 🧠 CORE IDEA
     * ============================================================
     * Instead of tracking best window → COUNT valid windows
     *
     * GENERIC TEMPLATE FOR "AT MOST":
     *
     * int left = 0;
     * for (int right = 0; right < n; right++) {
     *     add nums[right] to state;
     *
     *     while (state violates condition) {
     *         remove nums[left] from state;
     *         left++;
     *     }
     *
     *     count += right - left + 1;
     * }
     *
     * GENERIC TEMPLATE FOR "EXACTLY":
     *
     * exactly(K) = atMost(K) - atMost(K - 1)
     *
     * WHAT DIFFERS PER VARIANT:
     * - Product < K: state = product
     * - Binary sum / nice subarrays: state = sum of bits/parity
     * - K distinct: state = frequency map size
     * - Score < K: state = sum, condition = sum * length < K
     * - Bit flips: state = active flip parity using difference array
     *
     * TOP LC VARIANTS:
     * - LC 713. Subarray Product Less Than K
     *   Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - LC 930. Binary Subarrays With Sum
     *   Companies: C3 IoT
     * - LC 992. Subarrays with K Different Integers
     *   Companies: Alibaba, Amazon, Goldman Sachs, Google, Uber
     * - LC 1248. Count Number of Nice Subarrays
     *   Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - LC 995. Minimum Number of K Consecutive Bit Flips
     *   Companies: Akuna Capital, Amazon
     * - LC 2302. Count Subarrays With Score Less Than K
     *   Companies: Amazon, Google, Microsoft
     * ============================================================
     */

    /*
     * ------------------------------------------------------------
     * 1. COUNT AT MOST K DISTINCT
     * Variation: add right - left + 1 valid subarrays at each right
     * - Helper for LC 992. Subarrays with K Different Integers
     * - Related: LC 713. Subarray Product Less Than K,
     *   LC 2302. Count Subarrays With Score Less Than K
     * - Companies: Alibaba, Amazon, Goldman Sachs, Google, Uber
     * - Time: O(n)
     * - Space: O(k)
     * ------------------------------------------------------------
     */
    public int countAtMostK(int[] nums, int k) {
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
     * 2. EXACTLY K DISTINCT
     * Variation: exactly(K) = atMost(K) - atMost(K - 1)
     * - LC 992. Subarrays with K Different Integers
     * - Related: LC 1248. Count Number of Nice Subarrays,
     *   LC 930. Binary Subarrays With Sum,
     *   LC 995. Minimum Number of K Consecutive Bit Flips
     * - Companies: Alibaba, Amazon, Goldman Sachs, Google, Uber
     * - Time: O(n)
     * - Space: O(k)
     * ------------------------------------------------------------
     */
    public int exactlyK(int[] nums, int k) {
        return countAtMostK(nums, k) - countAtMostK(nums, k - 1);
    }

    /*
     * ------------------------------------------------------------
     * 3. BINARY SUBARRAYS WITH SUM
     * Variation: exactly goal ones = atMost(goal) - atMost(goal - 1)
     * - LC 930. Binary Subarrays With Sum
     * - Companies: C3 IoT
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMostBinarySum(nums, goal) - atMostBinarySum(nums, goal - 1);
    }

    private int atMostBinarySum(int[] nums, int goal) {
        if (goal < 0)
            return 0;

        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > goal)
                sum -= nums[left++];

            count += right - left + 1;
        }

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 4. COUNT NUMBER OF NICE SUBARRAYS
     * Variation: convert odd numbers to 1, even numbers to 0
     * - LC 1248. Count Number of Nice Subarrays
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int numberOfSubarrays(int[] nums, int k) {
        return atMostOdd(nums, k) - atMostOdd(nums, k - 1);
    }

    private int atMostOdd(int[] nums, int k) {
        if (k < 0)
            return 0;

        int left = 0, odds = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            odds += nums[right] % 2;

            while (odds > k)
                odds -= nums[left++] % 2;

            count += right - left + 1;
        }

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 5. COUNT SUBARRAYS WITH SCORE LESS THAN K
     * Variation: count windows where sum * length < K
     * - LC 2302. Count Subarrays With Score Less Than K
     * - Companies: Amazon, Google, Microsoft
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public long countSubarraysWithScoreLessThanK(int[] nums, long k) {
        int left = 0;
        long sum = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum * (right - left + 1) >= k)
                sum -= nums[left++];

            count += right - left + 1;
        }

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 6. MINIMUM NUMBER OF K CONSECUTIVE BIT FLIPS
     * Variation: fixed-size flip effect tracked by difference array parity
     * - LC 995. Minimum Number of K Consecutive Bit Flips
     * - Companies: Akuna Capital, Amazon
     * - Time: O(n)
     * - Space: O(n), can be O(1) by marking input in-place
     * ------------------------------------------------------------
     */
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        int activeFlips = 0, answer = 0;

        for (int i = 0; i < n; i++) {
            activeFlips ^= diff[i];

            if ((nums[i] ^ activeFlips) == 0) {
                if (i + k > n)
                    return -1;

                answer++;
                activeFlips ^= 1;
                diff[i + k] ^= 1;
            }
        }

        return answer;
    }

    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {
        CountSubarrayPatterns sol = new CountSubarrayPatterns();

        int[] nums = { 1, 2, 1, 2, 3 };

        System.out.println(sol.countAtMostK(nums, 2));
        System.out.println(sol.exactlyK(nums, 2));
        System.out.println("LC 930 Binary Sum: " +
                sol.numSubarraysWithSum(new int[] { 1, 0, 1, 0, 1 }, 2));
        System.out.println("LC 1248 Nice Subarrays: " +
                sol.numberOfSubarrays(new int[] { 1, 1, 2, 1, 1 }, 3));
        System.out.println("LC 2302 Score < K: " +
                sol.countSubarraysWithScoreLessThanK(new int[] { 2, 1, 4, 3, 5 }, 10));
        System.out.println("LC 995 K Bit Flips: " +
                sol.minKBitFlips(new int[] { 0, 0, 0, 1, 0, 1, 1, 0 }, 3));
    }
}
