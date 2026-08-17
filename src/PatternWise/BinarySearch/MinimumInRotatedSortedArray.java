package PatternWise.BinarySearch;

public class MinimumInRotatedSortedArray {
    public static int findMin(int []arr){
        int left = 0; 
        int right = arr.length - 1;
        while(left < right){
            int mid = left + (right - left) /2 ;
            if( arr[mid] < arr[right]){
                right = mid ;
            }else{
                 left = mid + 1;
            }
        }
        return right;
    }
    public static void main(String[] args) {
        System.out.println(MinimumInRotatedSortedArray.findMin(new int[]{4, 5 ,6 ,7, 0, 1, 2}));
    }
    
}
