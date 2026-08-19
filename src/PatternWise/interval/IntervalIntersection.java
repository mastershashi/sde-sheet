package PatternWise.interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalIntersection {
    int[][] intervalIntersection(int[][] firstIntervalList, int[][] secondIntervalList) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < firstIntervalList.length && j < secondIntervalList.length) {
            int start = Math.max(firstIntervalList[i][0], secondIntervalList[j][0]);
            int end = Math.min(firstIntervalList[i][1], secondIntervalList[j][1]);

            if (start <= end) {
                // there is an intersection exists
                result.add(new int[] { start, end });
            }

            // move interval that end first
            if (firstIntervalList[i][1] < secondIntervalList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] firstList = { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } };
        int[][] secondList = { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } };
        IntervalIntersection obj = new IntervalIntersection();
        int result[][] = obj.intervalIntersection(firstList, secondList);
        for (int[] resultInterval : result) {
            System.out.println(resultInterval[0] + " " + resultInterval[1]);
        }
    }

}
