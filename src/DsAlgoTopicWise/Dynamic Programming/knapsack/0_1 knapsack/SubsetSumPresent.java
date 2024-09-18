// Problem Statement: Given an array of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.

public class SubsetSumPresent {
    public static boolean isSubsetSumPresent(int[] arr, int sum){
        boolean[][] dp = new boolean[arr.length+1][sum+1];
        for(int i=0;i<=arr.length ; i++){
            for(int j=0; j<=sum ;j++){
                if(i ==0){
                    dp[i][j] = false;
                }if(j==0){
                    dp[i][j] = true;
                }
            }
        }

        for(int i=1;i<=arr.length ; i++){
            for(int j=1; j<=sum ;j++){
                if(arr[i-1]<= j){
                    dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                }else{
                    dp[i][j]= dp[i-1][j];
                }
            }
        }
        return dp[arr.length][sum];
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 8, 10};
        int sum = 11;
        System.out.println(isSubsetSumPresent(arr, sum));
    }
}
