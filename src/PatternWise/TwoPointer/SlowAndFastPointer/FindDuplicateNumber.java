package PatternWise.TwoPointer.SlowAndFastPointer;

public class FindDuplicateNumber {

    static int findDuplicateNumber(int arr[]){
        // Floyds cycle detection algorithm
        int slow = arr[0];
        int fast = arr[0];
        do{
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(slow != fast);

        slow = arr[0];
        while(slow != fast){
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        // 0=<arr[i] < n 
        System.out.println(FindDuplicateNumber.findDuplicateNumber(new int[]{2,1,0,2,3}));
    }
    
}
