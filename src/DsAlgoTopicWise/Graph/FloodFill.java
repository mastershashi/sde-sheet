class FloodFill {
    public static void dfs(int[][] image,int[][]newImage , int sr, int sc, int color){
        int initialColour = image[sr][sc];
        int []dRows = new int[]{-1,0,1,0};
        int []dCols = new int[]{0,1,0,-1};
        newImage[sr][sc] = color;
        for(int i =0;i<4;i++){
            int n = newImage.length;
            int m = newImage[0].length;
            int row = sr + dRows[i];
            int col = sc + dCols[i];
            if( row >=0 && row<n && col>=0 && col< m && newImage[row][col] == initialColour &&  newImage[row][col] != color){
               dfs(image, newImage, row,col,color); 
            }
        }
    }
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
       int[][]newImage = image;
       dfs(image,newImage, sr, sc, color);
       return newImage;
    }
    public static void main(String[] args) {
        int image[][] = new int[][]{
            {},
            {},
            {}
        };
       image = floodFill(image,1,1,2);
       for(int i=0;i< image.length ;i++){
        for(int j=0 ;j < image[0].length ;j++){
            System.out.println(image[i][j]+" ");
        }
        System.out.println();
       }
    }
}
