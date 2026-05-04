package PatternWise.Tree.Traversal.TreeConstructionPattern;

import java.util.*;

public class TreeConstructionPatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * Rebuild tree from traversal(s)
     * 
     * Most common:
     * - Preorder + Inorder
     * - Postorder + Inorder
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

    Map<Integer, Integer> inorderIndex = new HashMap<>();
    int preIndex = 0;

    /*
     * ------------------------------------------------------------
     * 1. BUILD TREE FROM PREORDER + INORDER
     * Variation: preorder gives root, inorder splits left/right
     * - LC 105. Construct Binary Tree from Preorder and Inorder Traversal
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, Oracle, Twitter
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        inorderIndex.clear();
        preIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right)
            return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int idx = inorderIndex.get(rootVal);

        root.left = build(preorder, left, idx - 1);
        root.right = build(preorder, idx + 1, right);

        return root;
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        TreeConstructionPatterns sol = new TreeConstructionPatterns();

        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };

        TreeNode root = sol.buildTree(preorder, inorder);

        System.out.println("Root: " + root.val);
        System.out.println("Left Child: " + root.left.val);
        System.out.println("Right Child: " + root.right.val);
        System.out.println("Right->Left Child: " + root.right.left.val);
        System.out.println("Right->Right Child: " + root.right.right.val);
    }
}
