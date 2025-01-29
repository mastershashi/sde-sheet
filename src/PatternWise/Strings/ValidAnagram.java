package PatternWise.Strings;

import java.util.Arrays;

public class ValidAnagram {

    static boolean isValidAnagram(String s, String p){
        int[] s_count = new int[128];
        int[] p_count = new int[128];
        for(char c : s.toCharArray()){
            s_count[c]++;
        }
        for(char c : p.toCharArray()){
            p_count[c]++;
        }
        if(Arrays.equals(s_count, p_count)){
            return true;
        }
        return false;

    }
    public static void main(String[] args) {
        System.out.println(ValidAnagram.isValidAnagram("nishita", "niishta"));
        System.out.println(ValidAnagram.isValidAnagram("car", "CAR"));
    }
    
}
