package PatternWise.DutchNationalFlag;

public class MoveZeros {
    /**
     * Idea is to split into two partition
     * 0 ... write - 1 : >0. elements
     * write ... n-1 : 0s element
     * whenever you find nums[i] !=0 , just swap it with write and then write ++
     * 
     */
    public void moveZeroes(int[] nums) {
        int write = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, write);
                write++;
            }
        }
    }

    void swap(int[] nums, int read, int write) {
        int temp = nums[read];
        nums[read] = nums[write];
        nums[write] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 };
        MoveZeros obj = new MoveZeros();
        obj.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
