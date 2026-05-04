package PatternWise.Tree.Traversal.LevelOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBST {
    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     *
     * Level-order traversal with either:
     * - level index for left/right view
     * - horizontal distance for top/bottom view
     *
     * ============================================================
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    /*
     * ------------------------------------------------------------
     * 1. TOP VIEW
     * Variation: first node at each horizontal distance
     * - GFG Top View of Binary Tree
     * - Companies: Amazon, Microsoft, Flipkart, Adobe, Samsung
     * - Time: O(n log w) with TreeMap, w = horizontal width
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> getTopView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;
            if (!map.containsKey(hd)) {
                map.put(hd, node.val);
            }
            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());
    }

    /*
     * ------------------------------------------------------------
     * 2. LEFT VIEW
     * Variation: first node at each level
     * - GFG Left View of Binary Tree
     * - Companies: Amazon, Microsoft, Flipkart, Adobe
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> getLeftView(TreeNode root) {
        List<Integer> leftView = new ArrayList<>();
        if (root == null) {
            return leftView;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;

                if (i == 0) {
                    leftView.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(new Pair(node.left, current.hd + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, current.hd + 1));
                }
            }
        }
        return leftView;
    }

    /*
     * ------------------------------------------------------------
     * 3. RIGHT VIEW
     * Variation: last node at each level
     * - LC 199. Binary Tree Right Side View
     * - Companies: Amazon, Microsoft, Facebook/Meta, Apple, Bloomberg,
     *   ByteDance, Uber
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> getRightView(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if (root == null) {
            return rightView;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;

                if (i == size - 1) {
                    rightView.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(new Pair(node.left, current.hd + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, current.hd + 1));
                }
            }
        }
        return rightView;
    }

    /*
     * ------------------------------------------------------------
     * 4. BOTTOM VIEW
     * Variation: last node at each horizontal distance
     * - GFG Bottom View of Binary Tree
     * - Companies: Amazon, Microsoft, Flipkart, Adobe
     * - Time: O(n log w) with TreeMap, w = horizontal width
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> getBottomView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;

            map.put(hd, node.val);

            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {
        TopViewOfBST solution = new TopViewOfBST();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        List<Integer> topViewList = solution.getTopView(root);
        System.out.println("Top view of the BST is: " + topViewList);

        List<Integer> leftView = solution.getLeftView(root);
        System.out.println("Left view of the BST is: " + leftView);

        List<Integer> rightView = solution.getRightView(root);
        System.out.println("Right view of the BST is: " + rightView);

        List<Integer> bottomView = solution.getBottomView(root);
        System.out.println("Bottom view of the BST is: " + bottomView);
    }
}
