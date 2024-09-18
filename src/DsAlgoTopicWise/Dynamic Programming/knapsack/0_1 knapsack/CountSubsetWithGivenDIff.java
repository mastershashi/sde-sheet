
public class CountSubsetWithGivenDIff {
    // Count the number of subset with given difference
    public static int countSubsetSum(int arr[],int sum){
        int[][] dp = new int[arr.length+1][sum+1];
        for(int i=0;i<=arr.length ; i++){
            for(int j=0; j<=sum ;j++){
                if(i ==0){
                    dp[i][j] = 0;
                }
                if(j==0){
                    dp[i][j] = 1;
                }
            }
        }

        for(int i=1;i<=arr.length ; i++){
            for(int j=1; j<=sum ;j++){
                if(arr[i-1]<= j){
                    dp[i][j] = dp[i-1][j-arr[i-1]] + dp[i-1][j];
                }else{
                    dp[i][j]= dp[i-1][j];
                }
            }
        }
        return dp[arr.length][sum];
    }
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int diff = 1;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int s1 = (diff + sum) / 2;
        System.out.println(countSubsetSum(arr, s1));
    }
    
}
