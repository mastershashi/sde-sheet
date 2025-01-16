package CompanyWise.Oracle.String;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindAllAnagram {
    Set<String> findAllAnagram(String s, String p){
        Set<String> anagramString = new HashSet<>();
        if(p.length() > s.length()){
            return anagramString;
        }
        int []s_count = new int[26];
        int []p_count = new int[26];

        for(char i : p.toCharArray()){
            p_count[i - 'a']++;
        }

        int start = 0 ;
        int end = 0;

        while( end < s.length()){
            s_count[s.charAt(end) - 'a']++;
            if( end - start +1 == p.length()){
                if(Arrays.equals(p_count, s_count)){
                    anagramString.add(s.substring(start, end+1));
                }
                s_count[s.charAt(start++) - 'a']--;
            }
            end++;
        }
        return anagramString;
    }
    public static void main(String[] args) {
        FindAllAnagram obj = new FindAllAnagram();

        obj.findAllAnagram("acbbaacbcbaacbbac", "abc").forEach(str -> System.out.println(str));

    }
    
}
