package PatternWise.interval;

import java.util.Arrays;

public class NonOverlappingInterval {
    public int removeOverlappingInterval(int[][] interval) {
        if (interval.length <= 1) {
            return 0;
        }
        // sort based on end time
        Arrays.sort(interval, (a, b) -> Integer.compare(a[1], b[1]));
        int removalCount = 0;
        int prevEnd = interval[0][1];
        for (int i = 1; i < interval.length; i++) {
            if (interval[i][0] < prevEnd) {
                // overlapping
                removalCount++;
                prevEnd = Math.min(prevEnd, interval[i][1]);
            } else {
                prevEnd = interval[i][1];
            }
        }
        return removalCount;
    }

    public static void main(String[] args) {
        int[][] intervals = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
        NonOverlappingInterval obj = new NonOverlappingInterval();
        int result = obj.removeOverlappingInterval(intervals);
        System.out.println(result);
    }

}
