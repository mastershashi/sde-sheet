package CompanyWise.Oracle.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
    List<List<String>> groupAnagram(List<String> str){
        Map<String , List<String>> anagramMap = new HashMap<>();
            for(String st: str){
                int st_count[] = new int[26];
                for( char c: st.toCharArray()){
                    st_count[c - 'a']++;
                }
                StringBuilder key = new StringBuilder();
                for(int i : st_count){
                    key.append(i).append("#");
                }
                anagramMap.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(st);
            }
            return new ArrayList<>(anagramMap.values());
    }
    public static void main(String[] args) {
        GroupAnagram obj = new GroupAnagram();
        List<List<String>> result = obj.groupAnagram(Arrays.asList("eat","aet","tea","doll","lold","dllo","nahi","anhi"));
        System.out.println(result);
    }
    
}
