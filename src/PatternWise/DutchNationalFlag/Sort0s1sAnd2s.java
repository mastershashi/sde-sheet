package PatternWise.DutchNationalFlag;

public class Sort0s1sAnd2s {
    /**
     * Dutch national flag is an in-place one pass , partition algorithm , used to
     * divide the array containing three three categories of elements into
     * three corresponding regions.
     * 
     * low : boundary of Os
     * mid : current element
     * high : boundayry of 2s
     * Key Idea: Keep the left side as 0s, the right side as 2s, and process the
     * unknown middle portion
     * Invariant :
     * 
     * 0 ... low-1 : Os
     * low ... mid-1: 1s
     * mid ... high: unknown
     * high+1 ... n-1: 2s
     */
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;

            } else if (nums[mid] == 1) {
                mid++;

            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = { 2, 0, 2, 1, 1, 0 };
        Sort0s1sAnd2s obj = new Sort0s1sAnd2s();
        obj.sortColors(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
