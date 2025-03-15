package PatternWise.Matrix;

public class Rotate90Degree {

    private void rotateMatrix(int matrix[][]){
        // transpose a matrix
        for(int i =0 ;i< matrix.length ;i++){
            for(int j=i; j< matrix[i].length ;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    
        // reverse each row 
        for(int i =0 ;i< matrix.length ;i++){
            int left  =0;
            int right = matrix.length-1;
            while(left < right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }

    }
    private void printMatrix(int matrix[][]){
        for(int i =0 ;i< matrix.length ;i++){
            for(int j=0; j< matrix[i].length ;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Rotate90Degree object = new Rotate90Degree();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        object.printMatrix(matrix);

        // Rotate the matrix 90 degrees clockwise
        object.rotateMatrix(matrix);

        System.out.println("\nMatrix after 90-degree clockwise rotation:");
        object.printMatrix(matrix);
    }
    
}
