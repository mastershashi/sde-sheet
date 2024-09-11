
/*check Inclusions

Problem: Given two strings, s1 and s2, check if s1 is a permutation of s2’s substring.
Example: s1 = "ab", s2 = "eidbaooo" */

import java.util.Map;

public class CheckPermutation {
        public static boolean isPermutation(String s1, String s2){
            int end = 0;
            if(s1.length() != s2.length()){
                return false;
            }
            Map<Character, Integer> charFrequency = new java.util.HashMap<>();
            for(char c: s1.toCharArray()){
                charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
            }
            while(end < s2.length()){
                char rightChar = s2.charAt(end);
                if(charFrequency.containsKey(rightChar)){
                    charFrequency.put(rightChar, charFrequency.get(rightChar) - 1);
                    if(charFrequency.get(rightChar) == 0){
                        charFrequency.remove(rightChar);
                    }
                }
                if(charFrequency.size() == 0){
                    return true;
                }
                end++;
            }
            return false;
        }
        public static void main(String[] args) {
            String[][] testCases = {
                {"ab", "eidbaooo", "false"},
                {"abc", "bac", "true"},
                {"abc", "ab", "false"},
                {"abcd", "abcd", "true"},
                {"abc", "xyz", "false"},
                {"aabb", "bbaaaab", "true"},
                {"abc", "cbadef", "true"},
                {"abc", "abcfedcba", "true"},
                {"", "nonempty", "true"},
                {"nonempty", "", "false"},
                {"", "", "true"},
                {"aaa", "aaaaaa", "true"},
                {"aaa", "aaaa", "true"},
                {"abc", "abcfghijk", "true"},
                {"abc", "abcdef", "false"},
                {"abc", "abcdef", "false"},
                {"aaa", "aaaa", "true"},
                {"a", "a", "true"},
                {"a", "b", "false"},
                {"aa", "aaa", "true"},
                {"a", "aaaaa", "true"},
                {"@#%", "%#@!", "true"},
                {"%#@", "#%@!&", "true"},
                {"AbC", "cAB", "true"},
                {"aBc", "cAB", "false"},
                {"abc", "ABC", "false"},
                {"ab", "cabc", "true"},
                {"ab", "abababab", "true"},
                {"b", "abbb", "true"},
                {"abc", "abcabcabc", "true"},
                {"c", "ab", "false"},
                {"abxaxaab", "aab", "true"},
                {"abcd", "abc", "false"},
                {"123", "321456", "true"},
                {"123", "321!@#", "false"},
                {"abc", "aabbcc", "true"}
            };
            int i = 0;
            for(String[] testCase: testCases){
                
            System.out.println("i"+ i + ":"+isPermutation(testCase[0], testCase[1])); 
                i++;
        }
    }
}
