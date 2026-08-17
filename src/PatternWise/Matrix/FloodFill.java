package PatternWise.Matrix;

public class FloodFill {
    public boolean isValid(int[][] image, int sr, int sc, int originalColor) {
        if (sr >= 0 && sc >= 0 && sr < image.length && sc < image[0].length
                && image[sr][sc] == originalColor) {
            return true;
        }
        return false;
    }

    public int[][] dfs(int[][] image, int sr, int sc, int originalColor, int color) {
        int directions[][] = {
                { 0, 1 }, // right
                { 1, 0 }, //
                { -1, 0 },
                { 0, -1 }
        };
        image[sr][sc] = color;
        for (int[] direction : directions) {
            int newRow = direction[0] + sr;
            int newCol = direction[1] + sc;

            if (isValid(image, newRow, newCol, originalColor)) {
                dfs(image, newRow, newCol, originalColor, color);
            }
        }
        return image;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        image[sr][sc] = color;
        dfs(image, sr, sc, originalColor, color);
        return image;
    }

    public static void main(String[] args) {
        FloodFill obj = new FloodFill();
        int sr = 1;
        int sc = 1;
        int color = 2;
        int[][] image = {
                { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 }
        };
        int[][] result = obj.floodFill(image, sr, sc, color);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

}
