package PatternWise.Tree.LCAPattern;

import java.util.*;

public class LCAPatterns {

    /*
     * ============================================================
     * 🔥 CORE TEMPLATE: LCA (Binary Tree)
     * ============================================================
     * 
     * TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
     * if (root == null || root == p || root == q)
     * return root;
     * 
     * TreeNode left = lca(root.left, p, q);
     * TreeNode right = lca(root.right, p, q);
     * 
     * if (left != null && right != null)
     * return root;
     * 
     * return left != null ? left : right;
     * }
     * 
     * ============================================================
     * 🧠 KEY IDEA:
     * ============================================================
     * 
     * 👉 Each subtree returns:
     * - null (no target found)
     * - p or q (found target)
     * - LCA (if already found below)
     * 
     * 👉 First node where left & right both return non-null = LCA
     * 
     */

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /*
     * // ------------------------------------------------------------
     * // 1. LCA IN BINARY TREE (STANDARD)
     * // Variation: no ordering → search both sides
     * - LC 236. Lowest Common Ancestor of a Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     * Bloomberg, LinkedIn, Uber
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * // ------------------------------------------------------------
     */
    public TreeNode lcaBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lcaBinaryTree(root.left, p, q);
        TreeNode right = lcaBinaryTree(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    /*
     * ------------------------------------------------------------
     * // 2. LCA IN BST
     * // Variation: use ordering to go one direction
     * 2. LCA in BST
     * - LC 235. Lowest Common Ancestor of a Binary Search Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg,
     * LinkedIn, Oracle, Uber
     * - Time: O(h)
     * - Space: O(1) for iterative solution
     * ------------------------------------------------------------
     */
    public TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root; // split point
            }
        }
        return null;
    }

    /*
     * // ------------------------------------------------------------
     * // 3. DISTANCE BETWEEN TWO NODES
     * // Variation: distance = dist(LCA→p) + dist(LCA→q)
     * 3. Distance Between Two Nodes
     * - LC 1740. Find Distance in a Binary Tree
     * - Companies: Amazon
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * 
     * * Related distance problem:
     * - LC 863. All Nodes Distance K in Binary Tree
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     * Oracle, Uber
     * - Time: O(n)
     * - Space: O(n)
     * // ------------------------------------------------------------
     */
    public int distanceBetweenNodes(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lcaBinaryTree(root, p, q);
        return distanceFrom(lca, p, 0) + distanceFrom(lca, q, 0);
    }

    private int distanceFrom(TreeNode node, TreeNode target, int dist) {
        if (node == null)
            return -1;
        if (node == target)
            return dist;

        int left = distanceFrom(node.left, target, dist + 1);
        if (left != -1)
            return left;

        return distanceFrom(node.right, target, dist + 1);
    }

    /*
     * // ------------------------------------------------------------
     * // 4. KTH ANCESTOR
     * // Variation: track path using recursion
     * 4. Kth Ancestor
     * - LC 1483. Kth Ancestor of a Tree Node
     * - Companies: Google
     * - Current path approach:
     * Time: O(n), Space: O(h)
     * - Optimal binary lifting:
     * Preprocess: O(n log n), Query: O(log n), Space: O(n log n)
     * // ------------------------------------------------------------
     */
    public int kthAncestor(TreeNode root, TreeNode target, int k) {
        List<TreeNode> path = new ArrayList<>();
        findPath(root, target, path);

        int idx = path.size() - k - 1;
        return idx >= 0 ? path.get(idx).val : -1;
    }

    private boolean findPath(TreeNode node, TreeNode target, List<TreeNode> path) {
        if (node == null)
            return false;

        path.add(node);

        if (node == target)
            return true;

        if (findPath(node.left, target, path) ||
                findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    /*
     * // ------------------------------------------------------------
     * // 5. LCA OF MULTIPLE NODES (SET)
     * // Variation: extend to more than 2 nodes
     * 5. LCA of Multiple Nodes
     * - LC 1676. Lowest Common Ancestor of a Binary Tree IV
     * - Companies: Amazon
     * - Time: O(n)
     * - Space: O(h + k), where k = number of target nodes
     * // ------------------------------------------------------------
     */
    public TreeNode lcaMultiple(TreeNode root, Set<TreeNode> targets) {
        if (root == null || targets.contains(root))
            return root;

        TreeNode left = lcaMultiple(root.left, targets);
        TreeNode right = lcaMultiple(root.right, targets);

        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    /*
     * ------------------------------------------------------------
     * 6. CHECK IF TWO NODES EXIST (INTERVIEW EDGE CASE)
     * Variation: ensure both nodes present
     * - LC 1644. Lowest Common Ancestor of a Binary Tree II
     * - Companies: LinkedIn, Microsoft
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     *
     * n = number of nodes, h = tree height
     * ------------------------------------------------------------
     */
    public TreeNode lcaWithCheck(TreeNode root, TreeNode p, TreeNode q) {
        if (!exists(root, p) || !exists(root, q))
            return null;
        return lcaBinaryTree(root, p, q);
    }

    private boolean exists(TreeNode node, TreeNode target) {
        if (node == null)
            return false;
        if (node == target)
            return true;
        return exists(node.left, target) || exists(node.right, target);
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        LCAPatterns sol = new LCAPatterns();

        /*
         * 3
         * / \
         * 5 1
         * / \ / \
         * 6 2 0 8
         * / \
         * 7 4
         */

        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);

        root.left = n5;
        root.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        System.out.println("LCA(5,1): " + sol.lcaBinaryTree(root, n5, n1).val);
        System.out.println("LCA(5,4): " + sol.lcaBinaryTree(root, n5, n4).val);
        System.out.println("Distance(5,4): " + sol.distanceBetweenNodes(root, n5, n4));
        System.out.println("Kth Ancestor of 4 (k=2): " + sol.kthAncestor(root, n4, 2));

        Set<TreeNode> set = new HashSet<>();
        set.add(n7);
        set.add(n4);
        System.out.println("LCA Multiple (7,4): " + sol.lcaMultiple(root, set).val);
    }
}
