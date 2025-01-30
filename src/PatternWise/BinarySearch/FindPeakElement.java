package PatternWise.BinarySearch;

public class FindPeakElement {
    static int getPeakElement(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left <right){
            int mid = left + ( right - left) / 2;
            if( arr[mid] > arr[mid+1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;

    }
    public static void main(String[] args) {
        System.out.println(FindPeakElement.getPeakElement(new int[]{1, 2, 3, 1}));
    }
    
}
