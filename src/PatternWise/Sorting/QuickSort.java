package PatternWise.Sorting;

public class QuickSort {
    
     private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private int partition(int[] array , int start, int right){
       int pivot = array[right];
       int i = start-1;

       for(int j = start; j<=right-1; j++){
           if(array[j] <= pivot){
               i++;
               swap(array, i, j);
           }
       }
       // Swap the pivot element with the element at (i + 1) to place it in the correct position
         swap(array, i+1, right);
         return i+1;

    }
    private void quickSortHelper(int[] array, int left, int right){
        if(left >= right){
            return;
        }
        int index = partition(array, left, right);
        quickSortHelper(array, left, index-1);
        quickSortHelper(array, index+1, right);
    }
    public void quickSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        quickSortHelper(array, 0, array.length-1);
    }
    public static void main(String[] args) {

        int arr[] ={12, 11, 13, 5, 6, 7};
        QuickSort qs = new QuickSort();
        System.out.println("Before Sorting");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        qs.quickSort(arr);
        System.out.println("\nAfter Sorting");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        
    }
    
}
