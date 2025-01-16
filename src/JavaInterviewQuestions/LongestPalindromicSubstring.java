public class LongestPalindromicSubstring {
     // Function to expand around the center and return the length of the palindrome
     private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome
        return right - left - 1;
    }
    public static int longestPalindromicSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand around center for odd-length palindromes
            int len1 = expandAroundCenter(s, i, i);
            // Expand around center for even-length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Find the longer palindrome between the two
            int len = Math.max(len1, len2);
            
            // Update the start and end indices if a longer palindrome is found
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Return the longest palindromic substring
        System.out.println("longest palindromic substring "+s.substring(start, end + 1));
        return s.substring(start, end + 1).length();
    }
    public static void main(String[] args) {
        String s1 = "ABCDDCHA";
        System.out.println(longestPalindromicSubstring(s1));
    }
}
    
