package PatternWise.Strings;

public class LongestPalindromicString {

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of the palindrome
    }

    public  static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand around center (odd-length palindrome)
            int len1 = expandAroundCenter(s, i, i);
            // Expand around center (even-length palindrome)
            int len2 = expandAroundCenter(s, i, i + 1);
            // Get the longer of the two
            int len = Math.max(len1, len2);

            // If we found a longer palindrome, update start and end
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }
    
}
