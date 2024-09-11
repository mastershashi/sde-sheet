public class LongestSubstringWithoutRepeating {
    public static int longestSubstringWithoutRepeating(String s) {
    if (s == null || s.length() == 0) {
            return 0;
    }
    // ASCII character set size (128 for standard ASCII)
    int[] index = new int[128];
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        // Update the left pointer to the position after the last occurrence of the current character
        left = Math.max(left, index[currentChar]);
        // Update the maximum length of the substring
        maxLength = Math.max(maxLength, right - left + 1);
        // Update the last occurrence index of the current character
        index[currentChar] = right + 1;
    }
    
    return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(longestSubstringWithoutRepeating(s));  // Output: 3
    }
}