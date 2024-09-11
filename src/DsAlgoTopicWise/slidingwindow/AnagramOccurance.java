
import java.util.HashMap;
import java.util.Map;

public class AnagramOccurance {
    public static void main(String[] args) {
        String s = "aabaabaa";
        String p = "aaba";
        System.out.println(findAnagrams(s, p));
    }

    public static int findAnagrams(String s, String p) {
        // Create frequency map for all characters
        Map<Character, Integer> map = new HashMap<>();
        
        // Populate the frequency map for the pattern string p
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, count = p.length();
        int res = 0;
        
        // Sliding window over the string s
        while (right < s.length()) {
            char rightChar = s.charAt(right++);
            if (map.containsKey(rightChar)) {
                if (map.put(rightChar, map.get(rightChar) - 1) >= 1) {
                    count--;
                }
            }
            if (count == 0) {
                res++;
            }
            if (right - left + 1 > p.length()) {
                char leftChar = s.charAt(left++);
                if (map.containsKey(leftChar)) {
                    if (map.put(leftChar, map.get(leftChar) + 1) >= 0) {
                        count++;
                    }
                }
            }
        }
        return res;
    }
}
