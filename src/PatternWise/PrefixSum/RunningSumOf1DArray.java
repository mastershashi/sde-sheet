class RunningSumOf1DArray{
    private int[] runningSum(int[] nums){
        int prefixSum[] = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1 ;i < nums.length ;i++){
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        
        return prefixSum;
    } 
    public static void main(String args[]){
           RunningSumOf1DArray obj = new RunningSumOf1DArray();
           int []nums = {1,2,3,4};
           nums = obj.runningSum(nums); 
           System.out.println("[");
           for(int i=0;i<nums.length;i++){
               System.out.print(nums[i]+ " ");
           }
           System.out.println("]");
    }
}