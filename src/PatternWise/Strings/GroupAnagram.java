package PatternWise.Strings;

import java.util.*;

public class GroupAnagram {

    // approach 1 using sorting with time complexity n*klogk
    List<List<String>> groupAnagram(String[] words) {
        Map<String, List<String>> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            char[] charArr = words[i].toCharArray();
            Arrays.sort(charArr);
            String key = new String(charArr);
            wordMap.computeIfAbsent(key, k -> new ArrayList<>()).add(words[i]);
        }
        return new ArrayList<>(wordMap.values());

    }

    // approach 2 using character frequency with time complexity 0( n*k )
    List<List<String>> groupAnagramOptimised(String[] words) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String word : words) { // o(n)
            int[] char_count = new int[128];
            for (char ch : word.toCharArray()) { // 0(k)
                char_count[ch - 'a']++;
            }
            StringBuilder key = new StringBuilder();
            for (int c : char_count) {
                key.append("#").append(c);
            }
            anagramMap.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(anagramMap.values());
    }

    public static void main(String[] args) {
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        GroupAnagram obj = new GroupAnagram();

        System.out.println(obj.groupAnagramOptimised(strs));
    }

}
/*
 * 
 * pattern
 * 
 * HashMap Basics
 * ↓
 * Two Sum
 * ↓
 * Contains Duplicate
 * ↓
 * Valid Anagram
 * ↓
 * Group Anagrams ← You are here
 * ↓
 * Top K Frequent Elements
 * ↓
 * Longest Consecutive Sequence
 * ↓
 * Sliding Window + Frequency
 * ↓
 * Find All Anagrams in a String
 * ↓
 * Permutation in String
 */