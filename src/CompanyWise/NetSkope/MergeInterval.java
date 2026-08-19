package CompanyWise.NetSkope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {
    public int[][] mergeInterval(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // sorting complexity 0(n log n)
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            if (result.isEmpty() || result.get(result.size() - 1)[1] < interval[0]) {
                // no overlapping
                result.add(interval);
            } else {
                // overlapping
                int[] last = result.get(result.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {
                { 1, 3 },
                { 2, 6 },
                { 8, 10 },
                { 9, 12 }
        };
        MergeInterval obj = new MergeInterval();
        int[][] result = obj.mergeInterval(intervals);
        for (int[] interval : result) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }

}
