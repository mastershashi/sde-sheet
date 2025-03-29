package PatternWise.Sorting;

public class MergeSort {
    private void mergeHalves(int[] array, int[] temp, int left,int mid, int right){
        System.arraycopy(array, left, temp, left, right-left+1);

        int i = left;
        int j = mid +1;
        int k = left;

        while(i<=mid && j<=right){
            if(temp[i] <= temp[j]){
                array[k++] = temp[i++];
            }else{
                array[k++] = temp[j++];
            }
        }
        while(i<=mid){
            array[k++] = temp[i++];
        }

        while(j<=right){
            array[k++] = temp[j++];
        }
    } 

    private void mergeSortHelper(int[] array, int[] temp, int leftStart, int rightEnd){
        if(leftStart >= rightEnd){
            return;
        }
        int middle = leftStart + (rightEnd-leftStart) / 2; // prevent over
        mergeSortHelper(array, temp, leftStart, middle);
        mergeSortHelper(array, temp, middle+1, rightEnd);
        mergeHalves(array, temp, leftStart,middle, rightEnd);
    }

    public void mergeSort(int []array ){
        if( array == null ||array.length < 2){
            return;
        }
        int[] temp = new int[array.length];
        mergeSortHelper(array,temp, 0, array.length-1);
    }
    public static void main(String[] args) {
        int arr[] ={12, 11, 13, 5, 6, 7};
        MergeSort ms = new MergeSort();
        System.out.println("Before Sorting");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        ms.mergeSort(arr);
        System.out.println("After Sorting");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        
    }
}
