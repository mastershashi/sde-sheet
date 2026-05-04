package PatternWise.Tree.TreeDPPattern;

public class TreeDPPatterns {

    /*
     * ============================================================
     * 🔥 CORE TEMPLATE: TREE DP
     * ============================================================
     * 
     * int global = ...;
     * 
     * int dfs(TreeNode node) {
     * if (node == null) return base;
     * 
     * int left = dfs(node.left);
     * int right = dfs(node.right);
     * 
     * // 👇 combine children (decision logic)
     * 
     * global = Math.max(global, something_using(left, right));
     * 
     * // return best contribution upward
     * return something_for_parent;
     * }
     * 
     * ============================================================
     * 🧠 CORE IDEA:
     * ============================================================
     * 
     * 👉 Each node computes TWO things:
     * 
     * 1. RETURN VALUE (to parent)
     * → best single path / usable contribution
     * 
     * 2. GLOBAL ANSWER
     * → best full solution (may use both children)
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
     * 1. MAX PATH SUM (CLASSIC TREE DP)
     * Variation: global answer may use both children; parent gets one side
     * - LC 124. Binary Tree Maximum Path Sum
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfsMaxPath(root);
        return maxSum;
    }

    private int dfsMaxPath(TreeNode node) {
        if (node == null)
            return 0;

        int left = Math.max(dfsMaxPath(node.left), 0);
        int right = Math.max(dfsMaxPath(node.right), 0);

        // global answer (split path)
        maxSum = Math.max(maxSum, node.val + left + right);

        // return best single path upward
        return node.val + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 2. HOUSE ROBBER III (VERY IMPORTANT)
     * Variation: 2 states -> rob / not rob
     * - LC 337. House Robber III
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Uber
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int rob(TreeNode root) {
        int[] res = dfsRob(root);
        return Math.max(res[0], res[1]);
    }

    // res[0] = not rob, res[1] = rob
    private int[] dfsRob(TreeNode node) {
        if (node == null)
            return new int[] { 0, 0 };

        int[] left = dfsRob(node.left);
        int[] right = dfsRob(node.right);

        int rob = node.val + left[0] + right[0];
        int notRob = Math.max(left[0], left[1]) +
                Math.max(right[0], right[1]);

        return new int[] { notRob, rob };
    }

    /*
     * ------------------------------------------------------------
     * 3. DIAMETER (TREE DP VIEW)
     * Variation: same as diameter pattern, framed as child-state DP
     * - LC 543. Diameter of Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int diameter = 0;

    public int diameter(TreeNode root) {
        diameter = 0;
        dfsDiameter(root);
        return diameter;
    }

    private int dfsDiameter(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsDiameter(node.left);
        int right = dfsDiameter(node.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 4. LONGEST UNIVALUE PATH
     * Variation: extend child path only when values match
     * - LC 687. Longest Univalue Path
     * - Companies: Amazon, Apple, Google
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int longest = 0;

    public int longestUnivaluePath(TreeNode root) {
        longest = 0;
        dfsUnivalue(root);
        return longest;
    }

    private int dfsUnivalue(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfsUnivalue(node.left);
        int right = dfsUnivalue(node.right);

        int leftPath = 0, rightPath = 0;

        if (node.left != null && node.left.val == node.val) {
            leftPath = left + 1;
        }

        if (node.right != null && node.right.val == node.val) {
            rightPath = right + 1;
        }

        longest = Math.max(longest, leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }

    /*
     * ------------------------------------------------------------
     * 5. MAX ROOT -> LEAF PATH (DP STYLE)
     * Variation: return best root-to-leaf contribution
     * - Closest: LC 124. Binary Tree Maximum Path Sum / path-sum variants
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int maxRootToLeaf(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        if (root.left == null && root.right == null)
            return root.val;

        int left = maxRootToLeaf(root.left);
        int right = maxRootToLeaf(root.right);

        return root.val + Math.max(left, right);
    }

    /*
     * ------------------------------------------------------------
     * 6. GENERIC TREE DP TEMPLATE (FOR INTERVIEW)
     * Variation: combine child states and return parent-usable state
     * - Closest: LC 124, LC 337, LC 543, LC 687
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: usually O(n)
     * - Space: usually O(h), worst O(n)
     * ------------------------------------------------------------
     *
     * When stuck, use this mental template:
     * 
     * int dfs(node):
     * left = dfs(left)
     * right = dfs(right)
     * 
     * // compute answer using left + right
     * update global
     * 
     * // return best usable value
     * return best(left, right)
     */

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        TreeDPPatterns sol = new TreeDPPatterns();

        /*
         * 10
         * / \
         * 2 10
         * / \ \
         * 20 1 -25
         * / \
         * 3 4
         */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(-25);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        System.out.println("Max Path Sum: " + sol.maxPathSum(root));
        System.out.println("House Robber: " + sol.rob(root));
        System.out.println("Diameter: " + sol.diameter(root));
        System.out.println("Longest Univalue Path: " + sol.longestUnivaluePath(root));
        System.out.println("Max Root→Leaf: " + sol.maxRootToLeaf(root));
    }
}
