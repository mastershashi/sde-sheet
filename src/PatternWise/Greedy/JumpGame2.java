package PatternWise.Greedy;

public class JumpGame2 {
    public static int maxJump(int[] nums){

        int farthest = 0;
        int currentEnd = 0;
        int jump = 0;
        for(int i = 0 ;i < nums.length-1 ; i++){
            farthest = Math.max(farthest, i+nums[i]);
            if(i == currentEnd){
                jump++;
                currentEnd = farthest;
            }
        }
        return jump;
    }

    public static void main(String[] args) {
        System.out.println(JumpGame2.maxJump(new int[]{2, 3, 1, 1, 4}));
    }
}
