package PatternWise.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
    /**
     * LeetCode 269 — Alien Dictionary
     * Question
     * There is a new alien language that uses the English alphabet, but the order
     * of the letters is unknown.
     * 
     * You are given a list of words from the alien language. The words are sorted
     * according to the rules of this alien language.
     * 
     * Return a string containing the characters in the correct alien alphabet
     * order.
     * 
     * If there are multiple valid orders, return any one of them.
     * 
     * If no valid ordering exists, return "".
     * 
     * Example
     * Input:
     * 
     * words = ["wrt", "wrf", "er", "ett", "rftt"]
     * 
     * Output:
     * 
     * "wertf"
     * 
     * 
     */

    static String alienOrder(String[] words) {

        int V = 26;

        // ------------------------------------------------
        // 1. Create adjacency list
        // ------------------------------------------------

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // ------------------------------------------------
        // 2. Track characters that actually exist
        // ------------------------------------------------

        boolean[] present = new boolean[V];

        for (String word : words) {

            for (char ch : word.toCharArray()) {

                present[ch - 'a'] = true;
            }
        }

        // ------------------------------------------------
        // 3. Build graph by comparing adjacent words
        // ------------------------------------------------

        for (int i = 0; i < words.length - 1; i++) {

            String word1 = words[i];
            String word2 = words[i + 1];

            int minLength = Math.min(word1.length(), word2.length());

            boolean foundDifference = false;

            for (int j = 0; j < minLength; j++) {

                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);

                if (ch1 != ch2) {

                    // ch1 comes before ch2
                    graph.get(ch1 - 'a').add(ch2 - 'a');

                    foundDifference = true;

                    // Only the first different character matters
                    break;
                }
            }

            // ------------------------------------------------
            // Invalid case:
            //
            // ["abc", "ab"]
            //
            // The longer word cannot come before its prefix.
            // ------------------------------------------------

            if (!foundDifference && word1.length() > word2.length()) {
                return "";
            }
        }

        // ------------------------------------------------
        // 4. Calculate indegree
        // ------------------------------------------------

        int[] indegree = new int[V];

        for (int i = 0; i < V; i++) {

            for (int neighbor : graph.get(i)) {

                indegree[neighbor]++;
            }
        }

        // ------------------------------------------------
        // 5. Add all characters with indegree 0
        // ------------------------------------------------

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {

            if (present[i] && indegree[i] == 0) {

                queue.add(i);
            }
        }

        // ------------------------------------------------
        // 6. Topological Sort
        // ------------------------------------------------

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {

            int node = queue.remove();

            result.append((char) (node + 'a'));

            for (int neighbor : graph.get(node)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {

                    queue.add(neighbor);
                }
            }
        }

        // ------------------------------------------------
        // 7. Cycle detection
        // ------------------------------------------------

        int numberOfCharacters = 0;

        for (int i = 0; i < V; i++) {

            if (present[i]) {
                numberOfCharacters++;
            }
        }

        // If we could not process all existing characters,
        // there is a cycle.
        if (result.length() != numberOfCharacters) {

            return "";
        }

        return result.toString();
    }

    public static void main(String[] args) {

        /*
         * LeetCode 269
         *
         * Input:
         *
         * ["wrt", "wrf", "er", "ett", "rftt"]
         */

        String[] words = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };

        String result = alienOrder(words);

        System.out.println(result);
    }
}
