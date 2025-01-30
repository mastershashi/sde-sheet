package PatternWise.DynamicProgramming;

public class LongestCommonSubstring {

    static int lcs(String s, String p){
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m+1][n+1];
        int maxLength = 0;
        int endPos = 0;

        for(int i = 1; i <=m ;i++){
            for(int j = 1 ;j<=n ;j++){
                if(s.charAt(i-1) == p.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    //maxLength = Math.max(maxLength, dp[i][j]);
                    if(maxLength < dp[i][j]){
                        maxLength = dp[i][j];
                        endPos = i - 1;
                    }
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        if(maxLength == 0){
            System.out.println("");
        }
        System.out.println(s.substring(endPos-maxLength+1, endPos+1));
        return maxLength;
    }
    public static void main(String[] args) {
       System.out.println( LongestCommonSubstring.lcs("abcdxyz", "xyzabcd"));
    }
    
}
