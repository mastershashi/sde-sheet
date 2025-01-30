package PatternWise.DynamicProgramming;

public class LongestCommonSubsequence {
    static int lcs(String s, String p){
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m+1][n+1];
        int maxLength = 0;

        for(int i = 1; i <=m ;i++){
            for(int j = 1 ;j<=n ;j++){
                if(s.charAt(i-1) == p.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        System.out.println( LongestCommonSubsequence.lcs("abcde", "ace"));
    }
    
}
