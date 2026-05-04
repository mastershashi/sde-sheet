package PatternWise.Tree.DiameterPattern;

import java.util.*;

public class DiameterPattern {

    /*
     * ============================================================
     * 🔥 CORE TEMPLATE: DFS (HEIGHT + GLOBAL ANSWER)
     * ============================================================
     * 
     * int global = 0;
     * 
     * int dfs(TreeNode node) {
     * if (node == null) return 0;
     * 
     * int left = dfs(node.left);
     * int right = dfs(node.right);
     * 
     * // 👇 PROBLEM-SPECIFIC LOGIC (usually combine left + right)
     * 
     * global = Math.max(global, left + right);
     * 
     * // return height to parent
     * return 1 + Math.max(left, right);
     * }
     * 
     * ============================================================
     * 🧠 KEY IDEA:
     * ============================================================
     * 
     * 👉 Each node acts as a "bridge"
     * 👉 Combine left + right subtree info
     * 👉 Update global answer
     * 👉 Return ONE side upward
     * 
     * ============================================================
     */

    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /*
     * ------------------------------------------------------------
     * 1. DIAMETER OF BINARY TREE
     * Variation: max(left + right), answer in edges
     * - LC 543. Diameter of Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, ByteDance
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfsDiameter(root);
        return diameter;
    }

    private int dfsDiameter(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsDiameter(node.left);
        int right = dfsDiameter(node.right);

        // longest path THROUGH this node
        diameter = Math.max(diameter, left + right);

        // return height
        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 2. DIAMETER (RETURN NUMBER OF NODES IN PATH)
     * Variation: left + right + 1 instead of edge count
     * - Closest: LC 543. Diameter of Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int diameterNodes = 0;

    public int diameterInNodes(TreeNode root) {
        dfsDiameterNodes(root);
        return diameterNodes;
    }

    private int dfsDiameterNodes(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsDiameterNodes(node.left);
        int right = dfsDiameterNodes(node.right);

        diameterNodes = Math.max(diameterNodes, left + right + 1);

        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 3. MAX PATH SUM (RELATED PATTERN)
     * Variation: ignore negative contributions + weighted nodes
     * - LC 124. Binary Tree Maximum Path Sum
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfsMaxPath(root);
        return maxSum;
    }

    private int dfsMaxPath(TreeNode node) {
        if (node == null)
            return 0;

        int left = Math.max(dfsMaxPath(node.left), 0);
        int right = Math.max(dfsMaxPath(node.right), 0);

        int current = node.val + left + right;

        maxSum = Math.max(maxSum, current);

        return node.val + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 4. LONGEST PATH FROM ROOT
     * Variation: standard height / max depth
     * - LC 104. Maximum Depth of Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, LinkedIn, Uber
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int longestPathFromRoot(TreeNode root) {
        return dfsHeight(root);
    }

    private int dfsHeight(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsHeight(node.left);
        int right = dfsHeight(node.right);

        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 5. DIAMETER WITH PATH TRACKING (ADVANCED)
     * Variation: store/reconstruct the actual diameter path
     * - Closest: LC 543. Diameter of Binary Tree follow-up
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(h) recursion + O(d) path, worst O(n)
     * ------------------------------------------------------------
     */
    List<Integer> bestPath = new ArrayList<>();

    public List<Integer> diameterPath(TreeNode root) {
        diameter = 0;
        bestPath = new ArrayList<>();
        dfsPath(root);
        return bestPath;
    }

    private int dfsPath(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsPath(node.left);
        int right = dfsPath(node.right);

        // update best path length (not storing full path here for simplicity)
        if (left + right > diameter) {
            diameter = left + right;
            bestPath = Arrays.asList(node.val); // simplified
        }

        return 1 + Math.max(left, right);
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        DiameterPattern sol = new DiameterPattern();

        /*
         * 1
         * / \
         * 2 3
         * / \ \
         * 4 5 6
         * /
         * 7
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        System.out.println("Diameter (edges): " + sol.diameterOfBinaryTree(root));
        System.out.println("Diameter (nodes): " + sol.diameterInNodes(root));
        System.out.println("Max Path Sum: " + sol.maxPathSum(root));
        System.out.println("Longest Path from Root: " + sol.longestPathFromRoot(root));
        System.out.println("Diameter Path (simplified): " + sol.diameterPath(root));
    }
}
