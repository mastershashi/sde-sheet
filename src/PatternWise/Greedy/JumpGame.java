package PatternWise.Greedy;

public class JumpGame {
    public static boolean canJump(int[] nums){

        int farthest = 0;
        for(int i = 0 ;i < nums.length ; i++){
            if(i > farthest){
                return false;
            }
            farthest = Math.max(farthest, i+nums[i]);
        }
        return farthest >= nums.length -1;
    }

    public static void main(String[] args) {
        // Test case 1: can reach the last index
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println(JumpGame.canJump(nums1)); // Output: true

        // Test case 2: cannot reach the last index
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(JumpGame.canJump(nums2)); // Output: false
    }
    
}
