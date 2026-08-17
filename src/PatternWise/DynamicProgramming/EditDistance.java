package PatternWise.DynamicProgramming;

// given two string and allowed operation remove , insert and replace , what is the minimum amount of operation is required to convert the string s1 to s2
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;// if first string is empty then add all the characters from second string
                } else if (j == 0) {
                    dp[i][j] = i; // if second string is empty then remove all the characters from first string
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no chanage in characters , no operation required
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], // remove
                            Math.min(dp[i][j - 1], // Insert
                                    dp[i - 1][j - 1]));// replace
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        EditDistance solution = new EditDistance();
        int min = solution.minDistance(word1, word2);
        System.out.println(min);

    }

}
