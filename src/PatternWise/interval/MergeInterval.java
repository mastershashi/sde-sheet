import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        if( intervals.length ==0){
            return new int[0][0]; // base case
        }
        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0], b[0])); // sorting based on start of the interval ( nlog(n) complexity)

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for( int i = 1 ;i< intervals.length ;i++){
            int[] currentInterval = intervals[i];
            int[] lastMerged = merged.get(merged.size() - 1);
            if(lastMerged[1] >= currentInterval[0]){
                lastMerged[1] = Math.max(lastMerged[1], currentInterval[1]);
            }else{
                merged.add(currentInterval);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeInterval solution = new MergeInterval();
        int[][] intervals = {
            {1, 3},
            {2, 4},
            {5, 7},
            {6, 8}
        };

        // Calling the merge function
        int[][] mergedIntervals = solution.merge(intervals);
        
        // Output the merged intervals
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
