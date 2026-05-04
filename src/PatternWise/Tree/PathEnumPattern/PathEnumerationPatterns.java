package PatternWise.Tree.PathEnumPattern;

import java.util.*;

public class PathEnumerationPatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * Explore ALL root-to-leaf paths
     * 
     * → Backtracking pattern
     * → DFS + path list
     * 
     * ============================================================
     */

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /*
     * ------------------------------------------------------------
     * 1. ALL ROOT -> LEAF PATHS
     * Variation: DFS + path list + backtracking
     * - LC 257. Binary Tree Paths
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple
     * - Time: O(n * h) including path copies, worst O(n^2)
     * - Space: O(h) recursion + output
     * ------------------------------------------------------------
     */
    public List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, new ArrayList<>(), res);
        return res;
    }

    private void dfs(TreeNode node,
            List<Integer> path,
            List<List<Integer>> res) {

        if (node == null)
            return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        }

        dfs(node.left, path, res);
        dfs(node.right, path, res);

        path.remove(path.size() - 1); // backtrack
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        PathEnumerationPatterns sol = new PathEnumerationPatterns();

        /*
         * 1
         * / \
         * 2 3
         * \
         * 5
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println("All Root->Leaf Paths: " + sol.allPaths(root));
    }
}
