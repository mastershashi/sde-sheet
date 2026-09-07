package CompanyWise.NetSkope;

public class ContainerWithMostWater {
    public int getMaxArea(int []height){
        int sum = 0;
        int left = 0;
        int right = height.length - 1;

        while(left < right){
            int length = right - left;
            int breadth = Math.min(height[left], height[right]);
            int area = length * breadth;
            sum = Math.max(sum , area);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        ContainerWithMostWater obj = new ContainerWithMostWater();
        System.out.println(obj.getMaxArea(height));
    }

}
