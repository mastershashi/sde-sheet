package CompanyWise.Oracle.TwoPointer;

public class TrappingRainWater {

    int trap(int []height){
        int left = 0;
        int right = height.length -1;
        int waterTrapped = 0;
        int leftMax = 0;
        int rightMax = 0;
        while( left <= right){
            if( height[left] <= height[right]){
                if(leftMax <= height[left]){
                    leftMax = height[left];
                }else{
                    // calculate trapped water
                    waterTrapped += leftMax- height[left];
                }
                left++;
            }else{
                if(rightMax <= height[right]){
                    rightMax = height[right];
                }else{
                   // calculate trapped water
                   waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }
        return waterTrapped;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = solution.trap(height);
        System.out.println("Trapped water: " + result);  // Output: 6
    }
    
}
