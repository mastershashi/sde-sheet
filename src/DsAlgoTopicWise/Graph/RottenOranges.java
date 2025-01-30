import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    static class Pair{
        int row;
        int col;
        int time;
        Pair( int row, int col, int time){
            this.col = col;
            this.row = row;
            this.time = time;
    }
    }

    public static int timeTakenToRottOranges(int grid[][]){

        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair> queue = new LinkedList<>();
        int [][] vis = new int[m][n];
        int countFresh=0;
        int visCount = 0;
        int time = 0;
        for(int i =0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 2){
                    queue.add(new Pair(i, j, 0));
                    vis[i][j] = 2;
                }else{
                    vis[i][j] = 0;
                }
                if(grid[i][j] == 1){
                    countFresh++;
                }
            }
        }

        while(!queue.isEmpty()){
            Pair queueNode = queue.poll();
            int row = queueNode.row;
            int col = queueNode.col;
            int tm = queueNode.time;
            time = Math.max(tm, time);
            int[] dRow = new int[]{-1,0,1,0}; // up -> right -> down -> left 
            int[] dCol = new int[]{0,1,0,-1}; // up -> right -> down -> left 
            
            for(int k =0; k< 4;k++){
                int nRow = row+ dRow[k];
                int nCol = col + dCol[k];
                if(nRow >=0&& nRow < n&& nCol >=0 && nCol < m && grid[nRow][nCol] == 1 && vis[nRow][nCol] == 0){
                    queue.add(new Pair(nRow, nCol, time +1));
                    vis[nRow][nCol] = 2;
                    visCount++;
                }
            } 
        }
        if (countFresh != visCount) return -1;
        return time;
    }

    public static void main(String[] args) {
        int [][]grid = {{2,1,1},{1,1,0},{0,1,1}};
       int time = timeTakenToRottOranges(grid);
       System.out.println("Time Taken to rott all the oranges = "+ time);
    }
    
}
