package PatternWise.interval;

import java.util.Arrays;

public class MeetingRoom {

    public boolean canAttendAllMeetings(int[][] intervals) {
        // sort based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {
            // overlapping
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                { 1, 3 },
                { 2, 4 },
                { 5, 7 },
                { 6, 8 }
        };
        MeetingRoom obj = new MeetingRoom();
        System.out.println(obj.canAttendAllMeetings(intervals));
        int[][] intervalsNew = {
                { 1, 2 },
                { 3, 4 },
                { 5, 7 },
                { 9, 12 }
        };
        System.out.println(obj.canAttendAllMeetings(intervalsNew));

    }
}
