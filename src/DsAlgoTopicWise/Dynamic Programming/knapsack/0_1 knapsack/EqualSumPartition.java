public class EqualSumPartition {
    public static boolean isSubsetSumPresent(int []arr, int sum){
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
    public static boolean isEqualSumPartition(int arr[],int n){
        int sum =0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        if(sum%2!=0){
            return false;
        }
       return isSubsetSumPresent(arr,sum/2);
    }
    public static void main(String[] args) {
        int[] arr = {1, 5, 11, 5};
        System.out.println(isEqualSumPartition(arr,arr.length));
    }
}
