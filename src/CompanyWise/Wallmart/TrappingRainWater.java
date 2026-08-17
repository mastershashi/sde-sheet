package CompanyWise.Wallmart;

public class TrappingRainWater {
    /**
     * Given n non-negative integers representing an elevation map where the width
     * of each bar is 1, compute how much water it can trap after raining.
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * Explanation: The above elevation map (black section) is represented by array
     * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
     * are being trapped.
     * Example 2:
     * 
     * Input: height = [4,2,0,3,2,5]
     * Output: 9
     * 
     * 
     * Constraints:
     * 
     * n == height.length
     * 1 <= n <= 2 * 104
     * 0 <= height[i] <= 105
     */

    int waterTrap(int arr[]) {

        int left = 0;
        int right = arr.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int waterTrap = 0;
        while (left < right) {
            if (arr[left] < arr[right]) {
                leftMax = Math.max(leftMax, arr[left]);
                waterTrap += leftMax - arr[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, arr[right]);
                waterTrap += rightMax - arr[right];
                right--;
            }
        }
        return waterTrap;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = solution.waterTrap(height);
        System.out.println("Trapped water: " + result);  // Output: 6
    }

}
