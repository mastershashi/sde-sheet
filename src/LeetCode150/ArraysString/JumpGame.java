public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
        }

        return maxReach >= nums.length - 1;
    }
    public static void main(String[] args) {
        JumpGame obj = new JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(obj.canJump(nums1)); // Output: true
        System.out.println(obj.canJump(nums2)); // Output: false
    }
}
