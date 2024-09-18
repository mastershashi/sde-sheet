
public class CoinChange2 {
    public static int coinChange(int[]coins, int amount){
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=amount;j++){
                if(i==0){
                    dp[i][j] = Integer.MAX_VALUE-1;
                }
                if(j==0){
                    dp[i][j] = 0;
                }
            }
        }
        for(int i=1;i<=amount;i++){
            if(i%coins[0]==0){
                dp[1][i] = i/coins[0];
            }else{
                dp[1][i] = Integer.MAX_VALUE-1;
            }
        }

        for(int i=2;i<=n;i++){
            for(int j=1;j<=amount ;j++){
                if(coins[i-1]<=j){
                    dp[i][j] = Math.min( 1+dp[i][j-coins[i-1]],
                        dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][amount];
    }
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println(coinChange(coins, amount));
    }
    
}
