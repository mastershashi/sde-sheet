import java.util.Arrays;

public class PermutationInString {
    static boolean isPermutationStringPresent(String s, String p){
        if (s.length() < p.length()) return false;
        int []p_count = new int[26];
        int []s_count = new int[26];
        for(char c : p.toCharArray()){
            p_count[c - 'a']++;
        }
        int end = 0;
        int start = 0;
        while(end < s.length()){
            s_count[s.charAt(end) - 'a']++;
            if(end -start +1 == p.length()){
                if(Arrays.equals(p_count, s_count)){
                   return true;
                }
                s_count[s.charAt(start) - 'a']--;
                start++;
            }
            end++;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(PermutationInString.isPermutationStringPresent("aehpsdth", "bd"));
    }

}
