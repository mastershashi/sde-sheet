package PatternWise.SlidingWindow.FixedSize;

import java.util.*;

public class FixedSizeSlidingWindow {

    /*
     * ============================================================
     * 🧠 CORE IDEA
     * ============================================================
     * Window size = K (fixed)
     * Maintain sum/state of exactly K elements
     *
     * GENERIC TEMPLATE:
     *
     * int state = 0;
     * for (int right = 0; right < n; right++) {
     *     add nums[right] into state;
     *
     *     if (right >= k) {
     *         remove nums[right - k] from state;
     *     }
     *
     *     if (right >= k - 1) {
     *         update answer using exactly k elements;
     *     }
     * }
     *
     * WHAT DIFFERS PER VARIANT:
     * - Sum/average: state = running sum
     * - Count condition: compare state with threshold
     * - Maximize saved value: window gain + fixed base answer
     * - Frequency match: state = char counts + match count
     * - Word concatenation: window moves in word-size jumps
     *
     * TOP LC VARIANTS:
     * - LC 643. Maximum Average Subarray I
     *   Companies: Google
     * - LC 1343. Number of Sub-arrays of Size K and Average >= Threshold
     *   Companies: LinkedIn
     * - LC 1052. Grumpy Bookstore Owner
     *   Companies: Amazon, Google, Facebook/Meta
     * - LC 1456. Maximum Number of Vowels in a Substring of Given Length
     *   Companies: Amazon, Google, Microsoft
     * - LC 567. Permutation in String
     *   Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     *   Uber, Yahoo
     * - LC 438. Find All Anagrams in a String
     *   Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     *   Oracle, Robinhood, Uber
     * - LC 30. Substring with Concatenation of All Words
     *   Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg
     * ============================================================
     */

    /*
     * ------------------------------------------------------------
     * 1. MAX SUM OF SIZE K
     * Variation: fixed window sum optimization
     * - Closest: LC 643. Maximum Average Subarray I
     * - Related: LC 1052. Grumpy Bookstore Owner
     * - Companies: Google, Amazon, Facebook/Meta
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int maxSum(int[] nums, int k) {
        int sum = 0, max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i >= k)
                sum -= nums[i - k];

            if (i >= k - 1) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /*
     * ------------------------------------------------------------
     * 2. MIN SUM OF SIZE K
     * Variation: fixed window minimization
     * - Closest: LC 643. Maximum Average Subarray I, inverted objective
     * - Companies: Google
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int minSum(int[] nums, int k) {
        int sum = 0, min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i >= k)
                sum -= nums[i - k];

            if (i >= k - 1) {
                min = Math.min(min, sum);
            }
        }
        return min;
    }

    /*
     * ------------------------------------------------------------
     * 3. AVERAGE OF EVERY WINDOW
     * Variation: fixed window sum / average
     * - LC 643. Maximum Average Subarray I
     * - Related: LC 1343. Number of Sub-arrays of Size K and Average >= Threshold
     * - Companies: Google, LinkedIn
     * - Time: O(n)
     * - Space: O(n - k + 1) output
     * ------------------------------------------------------------
     */
    public double[] average(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int sum = 0, idx = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i >= k)
                sum -= nums[i - k];

            if (i >= k - 1) {
                res[idx++] = (double) sum / k;
            }
        }
        return res;
    }

    /*
     * ------------------------------------------------------------
     * 4. MAXIMUM AVERAGE SUBARRAY I
     * Variation: maximize fixed-size sum, return average
     * - LC 643. Maximum Average Subarray I
     * - Companies: Google
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public double findMaxAverage(int[] nums, int k) {
        return (double) maxSum(nums, k) / k;
    }

    /*
     * ------------------------------------------------------------
     * 5. COUNT WINDOWS WITH AVERAGE >= THRESHOLD
     * Variation: fixed window + condition check
     * - LC 1343. Number of Sub-arrays of Size K and Average >= Threshold
     * - Companies: LinkedIn
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0, count = 0;
        int target = k * threshold;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            if (right >= k)
                sum -= arr[right - k];
            if (right >= k - 1 && sum >= target)
                count++;
        }

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 6. GRUMPY BOOKSTORE OWNER
     * Variation: base satisfied customers + max extra gain window
     * - LC 1052. Grumpy Bookstore Owner
     * - Companies: Amazon, Google, Facebook/Meta
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int base = 0, gain = 0, bestGain = 0;

        for (int right = 0; right < customers.length; right++) {
            if (grumpy[right] == 0)
                base += customers[right];
            else
                gain += customers[right];

            if (right >= minutes && grumpy[right - minutes] == 1)
                gain -= customers[right - minutes];

            bestGain = Math.max(bestGain, gain);
        }

        return base + bestGain;
    }

    /*
     * ------------------------------------------------------------
     * 7. MAX VOWELS IN SUBSTRING OF LENGTH K
     * Variation: fixed window count over characters
     * - LC 1456. Maximum Number of Vowels in a Substring of Given Length
     * - Companies: Amazon, Google, Microsoft
     * - Time: O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public int maxVowels(String s, int k) {
        int count = 0, best = 0;

        for (int right = 0; right < s.length(); right++) {
            if (isVowel(s.charAt(right)))
                count++;
            if (right >= k && isVowel(s.charAt(right - k)))
                count--;
            if (right >= k - 1)
                best = Math.max(best, count);
        }

        return best;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /*
     * ------------------------------------------------------------
     * 8. PERMUTATION IN STRING
     * Variation: fixed-size frequency window equals target frequency
     * - LC 567. Permutation in String
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     *   Uber, Yahoo
     * - Time: O(n)
     * - Space: O(1), alphabet size 26
     * ------------------------------------------------------------
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] need = new int[26];
        int[] window = new int[26];

        for (int i = 0; i < s1.length(); i++)
            need[s1.charAt(i) - 'a']++;

        for (int right = 0; right < s2.length(); right++) {
            window[s2.charAt(right) - 'a']++;
            if (right >= s1.length())
                window[s2.charAt(right - s1.length()) - 'a']--;
            if (right >= s1.length() - 1 && Arrays.equals(need, window))
                return true;
        }

        return false;
    }

    /*
     * ------------------------------------------------------------
     * 9. FIND ALL ANAGRAMS
     * Variation: collect every fixed-size frequency match index
     * - LC 438. Find All Anagrams in a String
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     *   Oracle, Robinhood, Uber
     * - Time: O(n)
     * - Space: O(1), alphabet size 26
     * ------------------------------------------------------------
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length())
            return res;

        int[] need = new int[26];
        int[] window = new int[26];

        for (int i = 0; i < p.length(); i++)
            need[p.charAt(i) - 'a']++;

        for (int right = 0; right < s.length(); right++) {
            window[s.charAt(right) - 'a']++;
            if (right >= p.length())
                window[s.charAt(right - p.length()) - 'a']--;
            if (right >= p.length() - 1 && Arrays.equals(need, window))
                res.add(right - p.length() + 1);
        }

        return res;
    }

    /*
     * ------------------------------------------------------------
     * 10. SUBSTRING WITH CONCATENATION OF ALL WORDS
     * Variation: fixed word-count window, slide by word length offsets
     * - LC 30. Substring with Concatenation of All Words
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg
     * - Time: O(n * wordLen) offsets simplify to O(n) word visits
     * - Space: O(m), m = number of words
     * ------------------------------------------------------------
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0 || s.isEmpty())
            return res;

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        if (s.length() < totalLen)
            return res;

        Map<String, Integer> need = new HashMap<>();
        for (String word : words)
            need.put(word, need.getOrDefault(word, 0) + 1);

        for (int offset = 0; offset < wordLen; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int left = offset, count = 0;

            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (!need.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                while (window.get(word) > need.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);
                    window.put(leftWord, window.get(leftWord) - 1);
                    count--;
                    left += wordLen;
                }

                if (count == words.length)
                    res.add(left);
            }
        }

        return res;
    }

    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {
        FixedSizeSlidingWindow sol = new FixedSizeSlidingWindow();

        int[] nums = { 2, 1, 5, 1, 3, 2 };

        System.out.println("Max Sum: " + sol.maxSum(nums, 3));
        System.out.println("Min Sum: " + sol.minSum(nums, 3));
        System.out.println("Avg: " + Arrays.toString(sol.average(nums, 3)));
        System.out.println("LC 643 Max Average: " + sol.findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 4));
        System.out.println("LC 1343 Avg >= Threshold: " +
                sol.numOfSubarrays(new int[] { 2, 2, 2, 2, 5, 5, 5, 8 }, 3, 4));
        System.out.println("LC 1052 Grumpy: " +
                sol.maxSatisfied(new int[] { 1, 0, 1, 2, 1, 1, 7, 5 },
                        new int[] { 0, 1, 0, 1, 0, 1, 0, 1 }, 3));
        System.out.println("LC 1456 Max Vowels: " + sol.maxVowels("abciiidef", 3));
        System.out.println("LC 567 Permutation: " + sol.checkInclusion("ab", "eidbaooo"));
        System.out.println("LC 438 Anagrams: " + sol.findAnagrams("cbaebabacd", "abc"));
        System.out.println("LC 30 Word Concatenation: " +
                sol.findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
    }
}
