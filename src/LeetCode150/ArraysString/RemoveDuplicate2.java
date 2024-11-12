import java.util.List;
import java.util.ArrayList;
public class RemoveDuplicate2 {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int uniqueIndex = 1; // Pointer for the position of the next unique element

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[uniqueIndex] = nums[i];
                uniqueIndex++;
            }
        }
        return uniqueIndex;
    }
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int newLength = removeDuplicates(nums);
        System.out.println("New length: " + newLength);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
    }
    
}
