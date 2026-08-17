package PatternWise.Matrix;

public class NumberOfIsland {

    public boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
        if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 1
                && !visited[row][col]) {
            return true;
        }
        return false;
    }

    public void dfs(int[][] grid, int row, int col, boolean[][] visited) {
        int directions[][] = {
                { 0, 1 }, // right
                { 1, 0 }, // down
                { -1, 0 }, // up
                { 0, -1 } // left
        };

        for (int[] direction : directions) {
            int newRow = direction[0] + row;
            int newCol = direction[1] + col;
            if (isValid(grid, newRow, newCol, visited)) {
                visited[newRow][newCol] = true;
                dfs(grid, newRow, newCol, visited);
            }
        }

    }

    public int numberOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int numberOfIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    numberOfIsland++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return numberOfIsland;
    }

    public static void main(String[] args) {
        NumberOfIsland obj = new NumberOfIsland();
        int[][] grid = { { 1, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 } };
        System.out.println(obj.numberOfIsland(grid));

    }

}
