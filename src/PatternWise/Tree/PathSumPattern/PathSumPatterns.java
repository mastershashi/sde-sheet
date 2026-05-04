package PatternWise.Tree.PathSumPattern;

import java.util.*;

public class PathSumPatterns {

    /*
     * ============================================================
     * 🔥 CORE TEMPLATE: DFS WITH RUNNING STATE
     * ============================================================
     * 
     * void dfs(TreeNode node, int sum) {
     * if (node == null) return;
     * 
     * sum += node.val;
     * 
     * // 👇 PROBLEM-SPECIFIC CHECK
     * 
     * dfs(node.left, sum);
     * dfs(node.right, sum);
     * }
     * 
     * ============================================================
     * 🧠 KEY IDEA:
     * ============================================================
     * 
     * 👉 Carry running state (sum / path)
     * 👉 Explore ALL paths downward
     * 👉 Backtracking when needed
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
     * 1. PATH SUM I (Root -> Leaf, boolean)
     * Variation: check only at leaf
     * - LC 112. Path Sum
     * - Companies: Amazon, Microsoft, Facebook/Meta, Apple, Adobe, Oracle
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public boolean hasPathSum(TreeNode root, int target) {
        return dfsHasPath(root, 0, target);
    }

    private boolean dfsHasPath(TreeNode node, int sum, int target) {
        if (node == null)
            return false;

        sum += node.val;

        if (node.left == null && node.right == null) {
            return sum == target;
        }

        return dfsHasPath(node.left, sum, target) ||
                dfsHasPath(node.right, sum, target);
    }

    /*
     * ------------------------------------------------------------
     * 2. PATH SUM II (Return all root -> leaf paths)
     * Variation: track path list + backtracking
     * - LC 113. Path Sum II
     * - Companies: Amazon, Google, Facebook/Meta, Apple, Bloomberg,
     *   LinkedIn
     * - Time: O(n * h) including path copies, worst O(n^2)
     * - Space: O(h) recursion + output
     * ------------------------------------------------------------
     */
    public List<List<Integer>> pathSumII(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfsPaths(root, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfsPaths(TreeNode node, int target, int sum,
            List<Integer> path, List<List<Integer>> res) {

        if (node == null)
            return;

        sum += node.val;
        path.add(node.val);

        if (node.left == null && node.right == null) {
            if (sum == target) {
                res.add(new ArrayList<>(path));
            }
        }

        dfsPaths(node.left, target, sum, path, res);
        dfsPaths(node.right, target, sum, path, res);

        path.remove(path.size() - 1); // backtrack
    }

    /*
     * ------------------------------------------------------------
     * 3. PATH SUM III (ANY DOWNWARD PATH, count)
     * Variation: prefix sum, count paths ending at current node
     * - LC 437. Path Sum III
     * - Companies: Amazon, Microsoft, Facebook/Meta, Bloomberg, Uber
     * - Time: O(n)
     * - Space: O(h) average recursion/prefix stack, worst O(n)
     * ------------------------------------------------------------
     */
    public int pathSumIII(TreeNode root, int target) {
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1); // base case
        return dfsPrefix(root, 0, target, prefix);
    }

    private int dfsPrefix(TreeNode node, int currSum, int target,
            Map<Integer, Integer> prefix) {

        if (node == null)
            return 0;

        currSum += node.val;

        int count = prefix.getOrDefault(currSum - target, 0);

        prefix.put(currSum, prefix.getOrDefault(currSum, 0) + 1);

        count += dfsPrefix(node.left, currSum, target, prefix);
        count += dfsPrefix(node.right, currSum, target, prefix);

        // backtrack
        prefix.put(currSum, prefix.get(currSum) - 1);

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 4. COUNT ROOT -> LEAF PATHS WITH SUM
     * Variation: count instead of boolean
     * - Closest: LC 112. Path Sum / LC 113. Path Sum II
     * - Companies: Amazon, Microsoft, Facebook/Meta, Google
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int countPathSum(TreeNode root, int target) {
        return dfsCount(root, 0, target);
    }

    private int dfsCount(TreeNode node, int sum, int target) {
        if (node == null)
            return 0;

        sum += node.val;

        int count = 0;

        if (node.left == null && node.right == null && sum == target) {
            count = 1;
        }

        count += dfsCount(node.left, sum, target);
        count += dfsCount(node.right, sum, target);

        return count;
    }

    /*
     * ------------------------------------------------------------
     * 5. MAX ROOT -> LEAF PATH SUM
     * Variation: maximize sum instead of matching target
     * - Closest: LC 124. Binary Tree Maximum Path Sum
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int maxRootToLeafSum(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);

        return root.val + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 6. PRINT ALL ROOT -> LEAF PATHS (no sum condition)
     * Variation: just track paths
     * - LC 257. Binary Tree Paths
     * - Companies: Amazon, Google, Facebook/Meta, Microsoft, Apple
     * - Time: O(n * h) including path copies, worst O(n^2)
     * - Space: O(h) recursion + output
     * ------------------------------------------------------------
     */
    public List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfsAllPaths(root, new ArrayList<>(), res);
        return res;
    }

    private void dfsAllPaths(TreeNode node, List<Integer> path,
            List<List<Integer>> res) {

        if (node == null)
            return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        }

        dfsAllPaths(node.left, path, res);
        dfsAllPaths(node.right, path, res);

        path.remove(path.size() - 1);
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        PathSumPatterns sol = new PathSumPatterns();

        /*
         * 10
         * / \
         * 5 -3
         * / \ \
         * 3 2 11
         * / \ \
         * 3 -2 1
         */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println("Has Path Sum (18): " + sol.hasPathSum(root, 18));
        System.out.println("All Paths Sum (18): " + sol.pathSumII(root, 18));
        System.out.println("Path Sum III (8): " + sol.pathSumIII(root, 8));
        System.out.println("Count Root→Leaf (18): " + sol.countPathSum(root, 18));
        System.out.println("Max Root→Leaf Sum: " + sol.maxRootToLeafSum(root));
        System.out.println("All Root→Leaf Paths: " + sol.allPaths(root));
    }
}
