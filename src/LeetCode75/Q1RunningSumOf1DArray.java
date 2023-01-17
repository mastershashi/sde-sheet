class Q1RunningSumOf1DArray{
    /**
    Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

    Return the running sum of nums.

    Example 1:

    Input: nums = [1,2,3,4]
    Output: [1,3,6,10]
    Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
    Example 2:

    Input: nums = [1,1,1,1,1]
    Output: [1,2,3,4,5]
    Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
    Example 3:

    Input: nums = [3,1,2,10,1]
    Output: [3,4,6,16,17] 
*/
    private int[] runningSum(int[] nums){
        int prefixSum[] = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1 ;i < nums.length ;i++){
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        
        return prefixSum;
    } 
    public static void main(String args[]){
           Q1RunningSumOf1DArray obj = new Q1RunningSumOf1DArray();
           int []nums = {1,2,3,4};
           nums = obj.runningSum(nums); 
           System.out.print("[");
           for(int i=0;i<nums.length;i++){
               System.out.print(nums[i]+ " ");
           }
           System.out.println("]");
    }
}