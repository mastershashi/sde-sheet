public class TowerOfHanoiDP {
    public static int towerOfHanoi(int n, int source, int destination, int auxiliary) {
        int[][] dp = new int[n + 1][3];
        return solve(dp, n, source, destination, auxiliary);
    }

    private static int solve(int[][] dp, int n, int source, int destination, int auxiliary) {
        if (n == 1) {
            return 1;
        }

        if (dp[n][source] != 0) {
            return dp[n][source];
        }

        int moves = 0;
        moves += solve(dp, n - 1, source, auxiliary, destination);
        moves++; // Move the largest disk
        moves += solve(dp, n - 1, auxiliary, destination, source);

        dp[n][source] = moves;
        return moves;
    }

    public static void main(String[] args) {
        int n = 3; // Number of disks
        int moves = towerOfHanoi(n, 0, 2, 1);
        System.out.println("Number of moves: " + moves);
    }
}