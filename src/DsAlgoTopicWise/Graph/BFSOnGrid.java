import java.util.LinkedList;
import java.util.Queue;

public class BFSOnGrid {
    private static final int[] DIRECTIONS ={-1,0,1,0,0,-1,0,1}; // up/down/left/right
   // Function to perform BFS on the grid
   public static int[] bfs(int[][] grid, int startX, int startY) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int [] bfsGrid = new int[grid.length * grid[0].length];
    int k = 0;
    queue.add(new int[]{startX, startY});
    visited[startX][startY] = true;
        while(!queue.isEmpty()){
            int[] cell = queue.poll();
            int x= cell[0];
            int y= cell[1];
            bfsGrid[k++] = grid[x][y];
            for(int i=0; i< 4; i++){
                int newX = x + DIRECTIONS[i * 2];
                int newY = y + DIRECTIONS[i * 2 + 1];
                if(isValid(grid, newX, newY) && !visited[newX][newY]){
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }

        }

        return bfsGrid;
   }

   private static boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
   }

    public static void main(String args[]){
        // Example grid: 0 represents open space, 1 represents obstacles
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}, 
            {13, 14, 15, 16}
        };

        // Start BFS from the top-left corner (0, 0)
        int bfsgrid[] = bfs(grid, 0, 0);
        for(int i=0; i<bfsgrid.length; i++){
            System.out.print(bfsgrid[i] + " ");
        }
        System.out.println();
        System.out.println(" 1 2 5 3 6 9 4 7 10 13 8 11 14 12 15 16");
     }
}
