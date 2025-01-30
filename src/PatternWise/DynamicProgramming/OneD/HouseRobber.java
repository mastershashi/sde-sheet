package PatternWise.DynamicProgramming.OneD;

public class HouseRobber {
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
    public static void main(String[] args) {
        System.out.println(HouseRobber.findMaxRobAmount(new int[]{2, 7, 9, 3, 1}));
    }
    
}
