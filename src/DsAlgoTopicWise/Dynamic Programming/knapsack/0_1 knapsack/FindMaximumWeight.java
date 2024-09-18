public class FindMaximumWeight{
    public static int knapsack(int []item, int []wt , int w, int n, int [][]dp){
        if( n == 0 || w == 0){
            return 0;
        }
        //memoization step
        if(dp[n][w]!= -1){
            return dp[n][w];
        }
        if(wt[n-1] <= w){
            dp[n][w] = Math.max(
                item[n-1] + knapsack(item, wt, w - wt[n-1], n-1,dp), 
                knapsack(item, wt, w, n-1,dp));
                return dp[n][w];
        }else{
             dp[n][w] = knapsack(item, wt, w, n-1,dp);
             return dp[n][w];
        }
    }
    public static int knapsackUsingTopDown(int []item, int []wt , int w, int n){
        int [][]dp = new int[n+1][w+1];
        for(int i= 0;i <=n;i++){
            for(int j=0 ;j<=w;j++){
                if(i==0||j==0){
                    dp[i][j] = 0;
                }
            }
        }
       

        for (int i = 1; i <= n; i++) {  // Iterate over items
            for (int j = 1; j <= w; j++) {  // Iterate over capacities
                if (wt[i - 1] <= j) {  // Check if the current item can be included
                    dp[i][j] = Math.max(
                        item[i - 1] + dp[i - 1][j - wt[i - 1]],  // Include the item
                        dp[i - 1][j]  // Exclude the item
                    );
                } else {
                    dp[i][j] = dp[i - 1][j];  // Item cannot be included
                }
            }
        }
        return dp[n][w];
    }
    public static void main(String[] args) {
        int[] item = {1, 4, 5, 7}; 
        int[] wt = {1, 3, 4, 5};   
        int W = 7;
        int n = item.length;
        int [][]dp = new int[n+1][W+1];
        // initializing dp array with -1
        for(int i = 0; i < n+1; i++){
            for(int j = 0; j < W+1; j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(knapsack(item, wt, W, n,dp));
        System.out.println(knapsackUsingTopDown(item, wt, W, n));
    
    }
}