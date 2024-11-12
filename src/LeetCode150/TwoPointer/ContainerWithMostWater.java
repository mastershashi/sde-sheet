package LeetCode150.TwoPointer;
// Question : https://leetcode.com/problems/container-with-most-water/?envType=study-plan-v2&envId=top-interview-150
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if(height == null || height.length == 0) return 0;
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while( left < right){
            int length = right - left;
            int breadth = Math.min(height[left], height[right]);
            int area = length * breadth;
            max = Math.max(max, area);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }
    public static void main(String args[]){
        ContainerWithMostWater obj = new ContainerWithMostWater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(obj.maxArea(height));
    }
}
