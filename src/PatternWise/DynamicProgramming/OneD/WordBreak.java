package PatternWise.DynamicProgramming.OneD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public static boolean wordBreakUsingSlidingWindow(String s, List<String> wordDict){
        Set<String> wordSet = new HashSet<>(wordDict); // Set for fast lookup
        Set<String> presentWord = new HashSet<>();
        int end = 0;
        int start = 0;
        while(end < s.length()){
            end++;
            if(wordSet.contains(s.substring(start, end))){ 
                presentWord.add(s.substring(start, end));
                    start = end;
            }
        }
        if(presentWord.size() == wordSet.size()) return true;
        return false;
    }
public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // Set for fast lookup
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Base case: empty string is always valid
        
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0); // Start with the first index
        
        // Iterate through the string using the queue
        while (!queue.isEmpty()) {
            int start = queue.poll();
            
            for (int end = start + 1; end <= s.length(); end++) {
                // Check if the substring s[start..end-1] is in the dictionary
                if (dp[start] && wordSet.contains(s.substring(start, end))) {
                    if (!dp[end]) {
                        dp[end] = true;
                        queue.add(end); // Add this index to process further
                    }
                }
            }
        }
        
        return dp[s.length()];
    }
    public static void main(String[] args) {

        System.out.println(WordBreak.wordBreak("applepenapple" ,new ArrayList<>(Arrays.asList("apple", "pen"))));
        System.out.println(WordBreak.wordBreakUsingSlidingWindow("applepenapple" ,new ArrayList<>(Arrays.asList("apple", "pen"))));
        
        
    }
    
}
