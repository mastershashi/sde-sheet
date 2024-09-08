public class MagicIndex {

    public static int findMagicIndex(int[] arr) {
        return findMagicIndexHelper(arr, 0, arr.length - 1);
    }

    private static int findMagicIndexHelper(int[] arr, int start, int end) {
        if (start > end) {
            return -1; // No magic index found
        }

        int mid = (start + end) / 2;

        if (arr[mid] == mid) {
            return mid;
        }

        // Find left and right indices
        int leftIndex = Math.min(mid - 1, arr[mid]);
      

        // Recursively search appropriate halves
        int leftResult = findMagicIndexHelper(arr, start, leftIndex);
        if (leftResult != -1) {
            return leftResult;
        }
        int rightIndex = Math.max(mid + 1, arr[mid]);
        return findMagicIndexHelper(arr, rightIndex, end);
    }

    public static void main(String[] args) {
        int[] arr = {-10,-5,2,2,2,3,4,7,9,12,13};
        int magicIndex = findMagicIndex(arr);

        if (magicIndex != -1) {
            System.out.println("Magic index found at: " + magicIndex);
        } else {
            System.out.println("Magic index not found");
        }
    }
}