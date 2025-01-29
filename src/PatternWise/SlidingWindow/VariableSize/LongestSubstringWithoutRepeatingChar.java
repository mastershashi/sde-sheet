import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChar {
    static int longestSubstringWithoutRepeatingCharOptimised(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int left =0;
        int maxLength = 0;
        int[] index = new int[128];
        for(int right = 0 ; right < s.length() ;right++){
            int character = s.charAt(right);
            left = Math.max(left, index[character]);
            maxLength = Math.max(maxLength, right-left +1);
            index[character] = right + 1;
        }
        return maxLength;
    }
    static int longestSubstringWithoutRepeatingChar(String s){
        Map<Character, Integer> hash = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLength = 0;
        while(right < s.length()){
            Character c = s.charAt(right);
            if(hash.containsKey(c) && hash.get(c)>= left){
                left = hash.get(c)+1;
            }
            hash.put(c, right);
            maxLength = Math.max(maxLength, right-left+1);
            right++;
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println(LongestSubstringWithoutRepeatingChar.longestSubstringWithoutRepeatingChar("cabdzabctr"));
        System.out.println(LongestSubstringWithoutRepeatingChar.longestSubstringWithoutRepeatingCharOptimised("cabdzabctr"));
    }

}
