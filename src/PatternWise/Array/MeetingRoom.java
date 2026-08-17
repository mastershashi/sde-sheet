package PatternWise.Array;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MeetingRoom {

    // this solution is 

    public static int minmumRoomRequired(int[][] interval) {
        if (interval.length == 0) {
            return 0;
        }
        if (interval.length == 1) {
            return 1;
        }
        int count = 2;
        boolean foundFreeRoom = false;
        Map<Integer, Integer> roomCountMap = new HashMap<>();
        int[] secondRow = interval[1];
        roomCountMap.put(1, secondRow[1]);
        int[] firstRow = interval[0];
        roomCountMap.put(2, firstRow[1]);
        for (int i = 2; i < interval.length; i++) {
            int[] newRow = interval[i];
            for (Map.Entry<Integer, Integer> entry : roomCountMap.entrySet()) {
                 if (newRow[0] >= entry.getValue()) {
                    foundFreeRoom = true;
                    entry.setValue(newRow[1]); // room now occupied till new end
                    break;
                }
            }

            if (!foundFreeRoom) {
                count++;
                roomCountMap.put(count, newRow[1]);
            }

        }
        return count;
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        int[][] interval = {
                { 1, 5 },
                { 2, 6 },
                { 3, 7 },
                { 4, 8 },
                { 5, 9 }
        };
        System.out.println(MeetingRoom.minmumRoomRequired(interval));
        // {{1,5},{2,6},{3,7},{4,8},{5,9}}
        // startEnd -> key , value : 0
        int[][] interval1 =  {{0,30},{5,10},{15,20},{25,35},{30,40},{35,45}};
            System.out.println(MeetingRoom.minmumRoomRequired(interval1));
    }

}

// [[0,30],[5,10],[15,20],[25,35],[30,40],[35,45]]

// Example 1:

// Input: intervals = [(0,40),(5,10),(15,20)]

// Output: 2

// Explanation:
// room1: (0,40)
// room2: (5,10),(15,20)

// Example 2:

// Input: intervals = [(4,9)]

// Output: 1

// Example 3
// Input: [[1,5],[2,6],[3,7],[4,8],[5,9]]
// Output: : 4

// Example 4
// Input:[[0,30],[5,10],[15,20],[25,35],[30,40],[35,45]]
// Output : 2

// Example 5

// Input: [[1,2],[3,4],[5,6],[7,8]]
// Output: 1
