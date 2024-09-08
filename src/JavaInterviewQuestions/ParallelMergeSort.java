import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<Void> {
    private final int[] array;
    private final int low;
    private final int high;

    public ParallelMergeSort(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Void compute() {
        if (high - low <= 1) {
            return null; // Base case: array of size 1 or less is already sorted
        }

        int mid = (low + high) / 2;

        // Fork two subtasks to sort left and right halves
        ParallelMergeSort leftTask = new ParallelMergeSort(array, low, mid);
        ParallelMergeSort rightTask = new ParallelMergeSort(array, mid, high);

        leftTask.fork();
        rightTask.compute();
        leftTask.join();

        // Merge the sorted halves
        merge(array, low, mid, high);

        return null;
    }

    private void merge(int[] array, int low, int mid, int high) {
        int[] temp = new int[high - low];
        int i = low, j = mid, k = 0;

        while (i < mid && j < high) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i < mid) {
            temp[k++] = array[i++];
        }

        while (j < high) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, 0, array, low, high - low);
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ParallelMergeSort(array, 0, array.length));

        // Print the sorted array
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}