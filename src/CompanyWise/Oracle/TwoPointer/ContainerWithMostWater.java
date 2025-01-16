package CompanyWise.Oracle.TwoPointer;
public class ContainerWithMostWater {
    int maxArea(int height[]){
        int left = 0;
        int right = height.length -1;
        int max = 0;
        while(left < right){
            int width = Math.min(height[right] , height[left]);
            int breadth = right-left;
            int area = width * breadth;

            max = Math.max(max, area);

            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }

        }
        return max;
    }
    public static void main(String[] args) {
         ContainerWithMostWater obj = new ContainerWithMostWater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(obj.maxArea(height));
    }
    
}
