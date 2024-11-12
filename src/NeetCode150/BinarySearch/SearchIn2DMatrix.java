/*
 * You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
 */
//logic : convert it to imagenary 1 D array and apply binary search on it
// size of 1 D array = m*n -1
// 1 D array = 0,1,2,3,4,5,6,7,8,9,10,11
// to convert 1 D array index to 2 d array index => row = index/m, col = index%m , why m becuase we have to find the row and col in 2D array 
public class SearchIn2DMatrix{
    public static boolean binarySearch(int [][]matrix, int target,  int m, int n){
        int size = m*n -1;
        int i = 0;
        while(i<=size){
            int mid = i+ (size-i)/2;
            int row= mid/n;
            int col = mid%n;
            int val = matrix[row][col];

            if(target == val){
                return true;
            }
            else if(target < val){
                size = mid-1;
            }
            else{ //  target > val
                i = mid+1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int matrix[][] = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        int m = matrix.length;
        int n = matrix[0].length;
        System.out.println(binarySearch(matrix, target, m,n));
    }
}