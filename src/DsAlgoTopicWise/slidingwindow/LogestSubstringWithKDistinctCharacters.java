import java.util.HashMap;
import java.util.Map;

public class LogestSubstringWithKDistinctCharacters {
    public static int longestSubstringWithKDistinct(String s, int k) {
        if (k == 0 || s == null || s.length() == 0) {
            return -1;
        }

        int start = 0, maxLength = -1;
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char rightChar = s.charAt(end);
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);

            while (charFrequency.size() > k) {
                char leftChar = s.charAt(start);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }
                start++;
            }

            if (charFrequency.size() == k) {
                maxLength = Math.max(maxLength, end - start + 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "aabacbebebe";
        int k1 = 3;
        System.out.println(longestSubstringWithKDistinct(s1, k1));  // Output: 7

        String s2 = "aaaa";
        int k2 = 2;
        System.out.println(longestSubstringWithKDistinct(s2, k2));  // Output: -1
    }
}
