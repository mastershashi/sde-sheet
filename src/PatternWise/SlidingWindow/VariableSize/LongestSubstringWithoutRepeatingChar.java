import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChar {
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
    }

}
