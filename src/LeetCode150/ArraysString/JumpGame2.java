public class JumpGame2 {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }
    public static void main(String[] args) {
        JumpGame2 obj = new JumpGame2();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println(obj.jump(nums1)); // Output: 2
        System.out.println(obj.jump(nums2)); // Output: 2
    }
}
