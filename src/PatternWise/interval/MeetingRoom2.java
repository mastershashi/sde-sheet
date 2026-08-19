package PatternWise.interval;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom2 {
    public int minMeetingRoom(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll();
            }
            // alcoate room
            minHeap.offer(end);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {
                { 0, 30 },
                { 5, 10 },
                { 15, 20 }
        };
        MeetingRoom2 obj = new MeetingRoom2();

        System.out.println(obj.minMeetingRoom(intervals));
    }

}
