public class MinimumSizeSubArraySum {

    static int minimumSizeSubArraySum(int array[], int target){
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while(right < array.length){
            sum = sum+ array[right];
            while(sum >= target){
                minLength = Math.min(minLength, right-left +1);
                sum = sum - array[left];
                left++;
            }
            right++;
        }
        return minLength;
    }

    public static void main(String[] args) {
       System.out.println( MinimumSizeSubArraySum.minimumSizeSubArraySum(new int[]{1,5,3,2,8,3}, 11));
    }
    
}
