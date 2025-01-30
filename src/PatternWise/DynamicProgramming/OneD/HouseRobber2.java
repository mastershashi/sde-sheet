package PatternWise.DynamicProgramming.OneD;

import java.util.Arrays;

public class HouseRobber2 {
    static int findMaxRobAmount(int houseArr[]){
        if(houseArr == null || houseArr.length ==0){
            return 0;
        }
        int n = houseArr.length;
        int[] dp = new int[n];
        dp[0] = houseArr[0];
        dp[1] = Math.max(houseArr[0],houseArr[1]);

        // for ith house we have two choice either we rob it or skip it 
        // skip i : if we are skipping ith house then i-1 th hiuse we rob
        // rob i : if we are robbing ith house then we cannot rob i-1th house. we have to rob i-2nd house and total amount would be amount rob at ith house + amount rob at i-2nd house 

        for( int i =2 ;i<houseArr.length ;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+houseArr[i]);
        }
        return dp[n-1];
    }
    static int findMaxRobAmountInACircularArrangement(int houseArr[]){
        // edge cases
          // Edge cases
          if (houseArr == null || houseArr.length == 0) {
            return 0;  // No houses to rob
        }
        if (houseArr.length == 1) {
            return houseArr[0];  // Only one house, rob it
        }
        if (houseArr.length == 2) {
            return Math.max(houseArr[0], houseArr[1]);  // Choose the best of the two houses
        }
        // in house robber 2 problem houses are arranged in a circle so we cannot rob frst and last house as both are adjacent
        // so we have to take two case 
        // case 1 : rob on adjacent house from 0th to n-2
        // case 2 : rob on adjacent house from 1st to n-1 
        // and then find maximum
        
       int case1 =  HouseRobber2.findMaxRobAmount(Arrays.copyOfRange(houseArr, 0, houseArr.length - 1));
       int case2 = HouseRobber2.findMaxRobAmount(Arrays.copyOfRange(houseArr, 1, houseArr.length));
       return Math.max(case1, case2);
    }

    public static void main(String[] args) {
        System.out.println(HouseRobber2.findMaxRobAmountInACircularArrangement(new int[]{2, 7, 9, 3, 1, 2}));
    }

    
}
