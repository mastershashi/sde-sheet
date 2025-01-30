import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {
    
    private int bfs(int[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dRow = new int[]{-1, 0, 1, 0};
        int[] dCol = new int[]{0, 1, 0, -1};
        queue.add(new int[]{i, j});
        int area = 0;

        // Mark the initial cell as visited
        grid[i][j] = 0;

        while (!queue.isEmpty()) {
            int[] queueItem = queue.poll();
            int row = queueItem[0];
            int col = queueItem[1];
            area++;

            // Explore all 4 directions
            for (int d = 0; d < 4; d++) {
                int nRow = row + dRow[d];
                int nCol = col + dCol[d];

                // Boundary and validity check
                if (nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && grid[nRow][nCol] == 1) {
                    grid[nRow][nCol] = 0;  // Mark as visited
                    queue.offer(new int[]{nRow, nCol});
                }
            }
        }

        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        // Traverse all cells in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int area = bfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);  // Update maximum area
                }
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();

        int[][] grid = {
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 1},
            {0, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}
        };

        int maxArea = solution.maxAreaOfIsland(grid);
        System.out.println("Maximum area of island: " + maxArea);  // Expected output: 6
    }
}
