public class TwoSum2Sorted {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) return new int[0];
        
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            
            if (sum == target) {
                return new int[]{left+1, right+1}; // Return 1-based indices
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[0]; 
    }
    public static void main(String args[]){
        TwoSum2Sorted obj = new TwoSum2Sorted();
        int[] numbers = {-1,0};
        int target = -1;
        int[] result = obj.twoSum(numbers, target);
        System.out.println(result[0] + " " + result[1]);
    }
}
