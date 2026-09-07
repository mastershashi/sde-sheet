package CompanyWise.NetSkope;

public class TrappingRainWater {
    public int waterTrap(int []height){
        int left = 0; 
        int right = height.length -1;
        int leftMax =0;
        int rightMax = 0;
        int waterTrap = 0;
        while (left < right) {
            if(height[left] < height[right]){
                leftMax = Math.max(leftMax, height[left]);
                waterTrap +=  leftMax- height[left];
                left++;
            }else{
                rightMax =Math.max(rightMax, height[right]);
                  waterTrap +=  rightMax- height[right];
                  right--;
            }
        }
        return waterTrap;
    }
    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int result = solution.waterTrap(height);
        System.out.println("Trapped water: " + result); // Output: 6
    }

}
