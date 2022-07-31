class NumberOfIslandDFS{
    private void dfs(int [][]matrix, int row, int col){
        if(row < 0 ||  col < 0 || row > matrix.length-1 ||  col > matrix[0].length -1) return;
        if(matrix[row][col] == 0) return;

        matrix[row][col] = 0;
        dfs(matrix, row-1,col);
        dfs(matrix, row+1,col);
        dfs(matrix, row,col-1);
        dfs(matrix, row,col+1);

    }
    public static void main(String args[]){
        // create a matrix 
        int [][]matrix =  {{ 1 , 1 , 1 , 1 , 0 },{ 1 , 1 , 0 , 1 , 0 },{ 1 , 1 , 0 , 0 , 0 },{ 0 , 0 , 0 , 0 , 0 }};
        int numberOfIsland = 0;
        NumberOfIslandDFS obj = new NumberOfIslandDFS();
       for(int row =0 ;row < matrix.length ;row++){
           for(int col = 0; col < matrix[0].length ;col++){
               if(matrix[row][col] == 1){
                    obj.dfs(matrix, row,col);
                    numberOfIsland++; 
               }
           }
       }
    System.out.println("Number of Island ="+numberOfIsland );

    }
}