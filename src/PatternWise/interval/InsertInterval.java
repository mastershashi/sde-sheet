package PatternWise.interval;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    // given intervals is non overalpping and already sorted
    public int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        // current interval ends before new interval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        // overlapping handling, merge current and new interval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        // add new interval
        result.add(newInterval);
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {

        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        InsertInterval obj = new InsertInterval();
        int[][] result = obj.insertInterval(intervals, new int[] { 4, 8 });
        for (int[] current : result)
            System.out.println(current[0] + " " + current[1]);
    }

}
