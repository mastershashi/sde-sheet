package PatternWise.Tree.HeightDepth;

import java.util.*;

public class HeightDepthPatterns {

    /*
     * ============================================================
     * 🔥 CORE TEMPLATE: DFS (BOTTOM-UP)
     * ============================================================
     * 
     * int dfs(TreeNode node) {
     * if (node == null) return 0;
     * 
     * int left = dfs(node.left);
     * int right = dfs(node.right);
     * 
     * // 👇 THIS IS WHERE PROBLEM-SPECIFIC LOGIC GOES
     * 
     * return something_for_parent;
     * }
     * 
     * ============================================================
     * 🧠 WHAT CHANGES PER PROBLEM:
     * ============================================================
     * 
     * HEIGHT → 1 + max(left, right)
     * MIN DEPTH (DFS) → careful with null children
     * DIAMETER → max(left + right)
     * BALANCED TREE → check abs(left - right)
     * MAX PATH SUM → ignore negative values
     * DEEPEST NODE → track max depth
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
     * 1. HEIGHT / MAX DEPTH
     * Variation: return 1 + max(left, right)
     * - LC 104. Maximum Depth of Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, LinkedIn, Uber
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int height(TreeNode root) {
        if (root == null)
            return 0;

        int left = height(root.left);
        int right = height(root.right);

        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 2. MIN DEPTH (DFS version)
     * Variation: cannot blindly take min; handle null child
     * - LC 111. Minimum Depth of Binary Tree
     * - Companies: Amazon, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null)
            return 1 + minDepth(root.right);
        if (root.right == null)
            return 1 + minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /*
     * ------------------------------------------------------------
     * 3. DIAMETER OF TREE
     * Variation: track max(left + right)
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

        // update diameter
        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 4. BALANCED BINARY TREE
     * Variation: check height difference <= 1
     * - LC 110. Balanced Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Adobe,
     *   Bloomberg
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public boolean isBalanced(TreeNode root) {
        return dfsBalanced(root) != -1;
    }

    private int dfsBalanced(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsBalanced(node.left);
        if (left == -1)
            return -1;

        int right = dfsBalanced(node.right);
        if (right == -1)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 5. MAXIMUM PATH SUM
     * Variation: ignore negative contributions
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
     * 6. DEEPEST NODE (RETURN VALUE)
     * Variation: track depth globally
     * - Closest: LC 515. Find Largest Value in Each Tree Row /
     *   LC 1302. Deepest Leaves Sum
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int maxDepth = -1;
    int deepestValue = -1;

    public int deepestNode(TreeNode root) {
        dfsDeepest(root, 0);
        return deepestValue;
    }

    private void dfsDeepest(TreeNode node, int depth) {
        if (node == null)
            return;

        if (depth > maxDepth) {
            maxDepth = depth;
            deepestValue = node.val;
        }

        dfsDeepest(node.left, depth + 1);
        dfsDeepest(node.right, depth + 1);
    }

    /*
     * ------------------------------------------------------------
     * 7. HEIGHT OF EACH NODE (IMPORTANT SDE-3 FOLLOW-UP)
     * Variation: compute and cache height for every node
     * - Closest: LC 2458. Height of Binary Tree After Subtree Removal Queries
     * - Companies: Google, Amazon, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public Map<TreeNode, Integer> heightMap(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        dfsHeightMap(root, map);
        return map;
    }

    private int dfsHeightMap(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null)
            return 0;

        int left = dfsHeightMap(node.left, map);
        int right = dfsHeightMap(node.right, map);

        int h = 1 + Math.max(left, right);
        map.put(node, h);

        return h;
    }

    // ------------------------------------------------------------
    // MAIN METHOD (TEST EVERYTHING)
    // ------------------------------------------------------------
    public static void main(String[] args) {

        HeightDepthPatterns sol = new HeightDepthPatterns();

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

        System.out.println("Height: " + sol.height(root));
        System.out.println("Min Depth: " + sol.minDepth(root));
        System.out.println("Diameter: " + sol.diameterOfBinaryTree(root));
        System.out.println("Is Balanced: " + sol.isBalanced(root));
        System.out.println("Max Path Sum: " + sol.maxPathSum(root));
        System.out.println("Deepest Node: " + sol.deepestNode(root));

        Map<TreeNode, Integer> map = sol.heightMap(root);
        System.out.println("Height Map Size: " + map.size());
    }
}
