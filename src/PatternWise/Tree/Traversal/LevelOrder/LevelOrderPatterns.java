package PatternWise.Tree.Traversal.LevelOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class LevelOrderPatterns {

    /*
     * ============================================================
     * 🔥 CORE TEMPLATE: LEVEL ORDER (BFS)
     * ============================================================
     * 
     * Queue<TreeNode> q = new LinkedList<>();
     * q.offer(root);
     * 
     * int depth = 0;
     * 
     * while (!q.isEmpty()) {
     * int size = q.size();
     * 
     * for (int i = 0; i < size; i++) {
     * TreeNode node = q.poll();
     * 
     * // 👇 THIS IS WHERE PROBLEM-SPECIFIC LOGIC GOES
     * 
     * if (node.left != null) q.offer(node.left);
     * if (node.right != null) q.offer(node.right);
     * }
     * 
     * depth++; // used when needed
     * }
     * 
     * ============================================================
     * 🧠 WHAT CHANGES PER PROBLEM:
     * ============================================================
     * 
     * LEFT VIEW → if (i == 0)
     * RIGHT VIEW → if (i == size - 1)
     * LEVEL GROUP → store all nodes in list
     * MIN DEPTH → return when first leaf found
     * K DISTANCE → stop when depth == K
     * TOP VIEW → first node at each horizontal distance
     * BOTTOM VIEW → last node at each horizontal distance
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

    // Pair for horizontal distance
    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode n, int h) {
            node = n;
            hd = h;
        }
    }

    /*
     * ------------------------------------------------------------
     * 1. LEVEL ORDER (Return nodes level by level)
     * Variation: collect all nodes per level
     * - LC 102. Binary Tree Level Order Traversal
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     * Bloomberg, LinkedIn, Uber
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }

            res.add(level);
        }
        return res;
    }

    /*
     * ------------------------------------------------------------
     * 2. LEFT VIEW
     * Variation: pick first node of each level, i == 0
     * - Closest: GFG Left View of Binary Tree / LC 199 mirror idea
     * - Companies: Amazon, Microsoft, Flipkart, Adobe
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> leftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (i == 0)
                    res.add(node.val);

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
        }
        return res;
    }

    /*
     * ------------------------------------------------------------
     * 3. RIGHT VIEW
     * Variation: pick last node of each level, i == size - 1
     * - LC 199. Binary Tree Right Side View
     * - Companies: Amazon, Microsoft, Facebook/Meta, Apple, Bloomberg,
     * ByteDance, Uber
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> rightView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (i == size - 1)
                    res.add(node.val);

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
        }
        return res;
    }

    /*
     * ------------------------------------------------------------
     * 4. MINIMUM DEPTH
     * Variation: BFS returns immediately when first leaf is found
     * - LC 111. Minimum Depth of Binary Tree
     * - Companies: Amazon, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            depth++;
        }
        return depth;
    }

    /*
     * ------------------------------------------------------------
     * 5. NODES AT DISTANCE K FROM ROOT
     * Variation: stop BFS at depth K
     * - Closest: LC 863. All Nodes Distance K in Binary Tree
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     * Oracle, Uber
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> nodesAtDistanceK(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            if (depth == k) {
                for (TreeNode node : q) {
                    res.add(node.val);
                }
                return res;
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            depth++;
        }
        return res;
    }

    /**
     * Variant of K distance , if target is given and k is given and we have to list down all the nodes at k distance from target then we need to traversal in three direction.
     * convert the tree into graph 
     * by making a node traveral in three direction 
     *  node → left
     *  node → right
     *  node → parent
     * 
     * bild parent pointer using
     * parent.put(node.left, node);
     * parent.put(node.right, node);
     * 
     * Then start the BFS traversal fropm target 
     */

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();

        if (root == null || target == null)
            return res;

        // 1. Build parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }

        // 2. BFS starting from target
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // All nodes currently in queue are exactly distance k
            if (distance == k) {
                for (TreeNode node : queue) {
                    res.add(node.val);
                }
                return res;
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // left
                if (node.left != null && visited.add(node.left)) {
                    queue.offer(node.left);
                }

                // right
                if (node.right != null && visited.add(node.right)) {
                    queue.offer(node.right);
                }

                // parent
                if (parent.containsKey(node) &&
                        visited.add(parent.get(node))) {

                    queue.offer(parent.get(node));
                }
            }

            distance++;
        }

        return res;
    }

    /*
     * ------------------------------------------------------------
     * 6. TOP VIEW
     * Variation: first node at each horizontal distance
     * - GFG Top View of Binary Tree
     * - Companies: Amazon, Microsoft, Flipkart, Adobe, Samsung
     * - Time: O(n log w) with TreeMap, w = horizontal width
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> topView(TreeNode root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (!map.containsKey(p.hd)) {
                map.put(p.hd, p.node.val);
            }

            if (p.node.left != null)
                q.offer(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null)
                q.offer(new Pair(p.node.right, p.hd + 1));
        }

        return new ArrayList<>(map.values());
    }

    /*
     * ------------------------------------------------------------
     * 7. BOTTOM VIEW
     * Variation: last node at each horizontal distance
     * - GFG Bottom View of Binary Tree
     * - Companies: Amazon, Microsoft, Flipkart, Adobe
     * - Time: O(n log w) with TreeMap, w = horizontal width
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> bottomView(TreeNode root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            map.put(p.hd, p.node.val);

            if (p.node.left != null)
                q.offer(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null)
                q.offer(new Pair(p.node.right, p.hd + 1));
        }

        return new ArrayList<>(map.values());
    }

    // ------------------------------------------------------------
    // MAIN METHOD (TEST EVERYTHING)
    // ------------------------------------------------------------
    public static void main(String[] args) {
        LevelOrderPatterns sol = new LevelOrderPatterns();

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

        System.out.println("Level Order: " + sol.levelOrder(root));
        System.out.println("Left View: " + sol.leftView(root));
        System.out.println("Right View: " + sol.rightView(root));
        System.out.println("Min Depth: " + sol.minDepth(root));
        System.out.println("Nodes at K=2: " + sol.nodesAtDistanceK(root, 2));
        System.out.println("Top View: " + sol.topView(root));
        System.out.println("Bottom View: " + sol.bottomView(root));
    }
}
