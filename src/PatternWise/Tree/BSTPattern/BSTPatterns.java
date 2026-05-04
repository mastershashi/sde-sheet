package PatternWise.Tree.BSTPattern;

import java.util.*;

public class BSTPatterns {

    /*
     * ============================================================
     * 🔥 CORE INSIGHT
     * ============================================================
     * 
     * BST Property:
     * left < root < right
     * 
     * 👉 Inorder traversal gives SORTED order
     * 
     * ============================================================
     * 🔥 CORE TEMPLATE: INORDER (SORTED TRAVERSAL)
     * ============================================================
     * 
     * void inorder(TreeNode node) {
     * if (node == null) return;
     * 
     * inorder(node.left);
     * 
     * // 👇 PROCESS (sorted order)
     * 
     * inorder(node.right);
     * }
     * 
     * ============================================================
     * 🧠 WHAT CHANGES PER PROBLEM:
     * ============================================================
     * 
     * Kth smallest → stop at k
     * Validate BST → check ordering
     * Range queries → prune using BST property
     * Successor → next greater
     * LCA (BST) → use ordering
     * Search → go left/right
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
     * 1. KTH SMALLEST (MOST IMPORTANT)
     * Variation: inorder + counter
     * - LC 230. Kth Smallest Element in a BST
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg
     * - Time: O(h + k), worst O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    int count = 0;
    int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inorderKth(root, k);
        return result;
    }

    private void inorderKth(TreeNode node, int k) {
        if (node == null)
            return;

        inorderKth(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inorderKth(node.right, k);
    }

    /*
     * ------------------------------------------------------------
     * 2. VALIDATE BST
     * Variation: enforce min/max constraints
     * - LC 98. Validate Binary Search Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, LinkedIn, Uber
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null)
            return true;

        if (node.val <= min || node.val >= max)
            return false;

        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }

    /*
     * ------------------------------------------------------------
     * 3. SEARCH IN BST
     * Variation: use ordering to prune
     * - LC 700. Search in a Binary Search Tree
     * - Companies: Adobe, Google, IBM
     * - Time: O(h), worst O(n)
     * - Space: O(h) recursive, O(1) if iterative
     * ------------------------------------------------------------
     */
    public TreeNode search(TreeNode root, int target) {
        if (root == null || root.val == target)
            return root;

        if (target < root.val)
            return search(root.left, target);
        else
            return search(root.right, target);
    }

    /*
     * ------------------------------------------------------------
     * 4. INSERT INTO BST
     * Variation: place node at correct position
     * - LC 701. Insert into a Binary Search Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, LinkedIn
     * - Time: O(h), worst O(n)
     * - Space: O(h) recursive, O(1) if iterative
     * ------------------------------------------------------------
     */
    public TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        return root;
    }

    /*
     * ------------------------------------------------------------
     * 5. RANGE SUM (IMPORTANT)
     * Variation: prune using BST property
     * - LC 938. Range Sum of BST
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple, Oracle
     * - Time: O(n), often less with pruning
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;

        if (root.val < low)
            return rangeSumBST(root.right, low, high);

        if (root.val > high)
            return rangeSumBST(root.left, low, high);

        return root.val +
                rangeSumBST(root.left, low, high) +
                rangeSumBST(root.right, low, high);
    }

    /*
     * ------------------------------------------------------------
     * 6. INORDER SUCCESSOR
     * Variation: next greater element
     * - LC 285. Inorder Successor in BST
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Palantir
     * - Time: O(h), worst O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (p.val < root.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return successor;
    }

    /*
     * ------------------------------------------------------------
     * 7. LCA IN BST
     * Variation: use ordering to find split point
     * - LC 235. Lowest Common Ancestor of a Binary Search Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Bloomberg,
     *   LinkedIn, Oracle, Uber
     * - Time: O(h), worst O(n)
     * - Space: O(1)
     * ------------------------------------------------------------
     */
    public TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val)
                root = root.left;
            else if (p.val > root.val && q.val > root.val)
                root = root.right;
            else
                return root;
        }
        return null;
    }

    /*
     * ------------------------------------------------------------
     * 8. CONVERT BST TO SORTED LIST
     * Variation: inorder traversal
     * - Closest: LC 897. Increasing Order Search Tree
     * - Companies: Amazon, Google, Facebook/Meta
     * - Time: O(n)
     * - Space: O(h) recursion + O(n) output
     * ------------------------------------------------------------
     */
    public List<Integer> toSortedList(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderList(root, res);
        return res;
    }

    private void inorderList(TreeNode node, List<Integer> res) {
        if (node == null)
            return;

        inorderList(node.left, res);
        res.add(node.val);
        inorderList(node.right, res);
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        BSTPatterns sol = new BSTPatterns();

        /*
         * 5
         * / \
         * 3 7
         * / \ / \
         * 2 4 6 8
         */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        System.out.println("Kth Smallest (k=3): " + sol.kthSmallest(root, 3));
        System.out.println("Is Valid BST: " + sol.isValidBST(root));
        System.out.println("Search 4: " + (sol.search(root, 4) != null));
        sol.insert(root, 9);
        System.out.println("After Insert 9, Search 9: " + (sol.search(root, 9) != null));
        System.out.println("Range Sum [3,7]: " + sol.rangeSumBST(root, 3, 7));

        TreeNode successor = sol.inorderSuccessor(root, root.left); // successor of 3
        System.out.println("Successor of 3: " + (successor != null ? successor.val : "null"));

        System.out.println("LCA BST (2,4): " + sol.lcaBST(root, root.left.left, root.left.right).val);
        System.out.println("Sorted List: " + sol.toSortedList(root));
    }
}
