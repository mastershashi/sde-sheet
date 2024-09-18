
public class RodCutting {
    public static int rodCutting(int[]lengthArr , int []price, int N){
        // edge case
        if(lengthArr.length==0 || price.length==0 || N==0){
            return 0;
        }
        int dp[][] = new int[lengthArr.length+1][N+1];
        for(int i=0;i<=lengthArr.length;i++){
            for(int j=0;j<=N;j++){
                if(i==0 || j==0){
                    dp[i][j] = 0;
                }
            }
        }
        for(int i=1;i <=lengthArr.length;i++){
            for(int j=1;j<=N;j++){
                if(lengthArr[i-1]<=j){
                    dp[i][j] = Math.max(price[i-1]+dp[i][j-lengthArr[i-1]],dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][lengthArr.length];
    }
    public static void main(String args[]){

        int[] lengthArr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] price ={1,5,8,9,10,17,17,20};
        int N = 8;

        
        System.out.println(rodCutting(lengthArr,price,N));
    }

}
