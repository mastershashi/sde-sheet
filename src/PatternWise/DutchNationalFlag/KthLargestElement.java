package PatternWise.DutchNationalFlag;

public class KthLargestElement {
    /**
     * logic :
     * < pivot | pivot | > pivot
     * 
     * @param nums
     * @param k
     * @return
     */

    public int kthLargestElement(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int pivot = nums[left];
            int low = left;
            int mid = left;
            int high = right;

            while (mid <= high) {
                if (nums[mid] < pivot) {
                    swap(nums, low, mid);
                    low++;
                    mid++;
                } else if (nums[mid] > pivot) {
                    swap(nums, mid, high);
                    high--;
                } else {
                    mid++;
                }
            }
            if (target < low) {
                // Target is in < pivot region
                right = low - 1;
            } else if (target > high) {
                // Target is in > pivot region
                left = high + 1;
            } else {
                // Target is inside == pivot region
                return nums[target];
            }
        }
        return -1;
    }

    void swap(int[] nums, int low, int mid) {
        int temp = nums[low];
        nums[low] = nums[mid];
        nums[mid] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 1, 56, 3, 12 };
        int k = 3;
        KthLargestElement obj = new KthLargestElement();
        System.out.println(obj.kthLargestElement(nums, k));
    }

}
