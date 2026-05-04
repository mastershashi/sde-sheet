package PatternWise.Tree.LCAPattern;

import java.util.*;

public class AdvancedLCAPatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * LCA = first node where left and right both return non-null
     * 
     * Extensions:
     * - Distance = dist(LCA→p) + dist(LCA→q)
     * - Kth ancestor = path tracking
     * - Parent pointer = climb up like linked list
     * - DAG = multiple parents → graph problem
     * 
     * ============================================================
     */

    static class TreeNode {
        int val;
        TreeNode left, right, parent; // parent used in variation

        TreeNode(int val) {
            this.val = val;
        }
    }

    /*
     * ------------------------------------------------------------
     * 1. STANDARD LCA (BASE)
     * Variation: no ordering, search both left and right subtrees
     * - LC 236. Lowest Common Ancestor of a Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg, LinkedIn, Uber
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }

    /*
     * ------------------------------------------------------------
     * 2. DISTANCE BETWEEN TWO NODES
     * Variation: distance = dist(LCA -> p) + dist(LCA -> q)
     * - LC 1740. Find Distance in a Binary Tree
     * - Companies: Amazon
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     *
     * Related:
     * - LC 863. All Nodes Distance K in Binary Tree
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     *   Oracle, Uber
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public int distanceBetweenNodes(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lca(root, p, q);
        return depth(lca, p, 0) + depth(lca, q, 0);
    }

    private int depth(TreeNode node, TreeNode target, int d) {
        if (node == null)
            return -1;
        if (node == target)
            return d;

        int left = depth(node.left, target, d + 1);
        if (left != -1)
            return left;

        return depth(node.right, target, d + 1);
    }

    /*
     * ------------------------------------------------------------
     * 3. KTH ANCESTOR (PATH BASED)
     * Variation: find root-to-target path, then index from target side
     * - LC 1483. Kth Ancestor of a Tree Node
     * - Companies: Google
     * - Current path approach:
     *   Time: O(n), Space: O(h)
     * - Optimal binary lifting:
     *   Preprocess: O(n log n), Query: O(log n), Space: O(n log n)
     * ------------------------------------------------------------
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
     * ------------------------------------------------------------
     * 4. LCA WITH PARENT POINTERS (OPTIMAL)
     * Variation: climb ancestors directly using parent pointers
     * - LC 1650. Lowest Common Ancestor of a Binary Tree III
     * - Companies: Facebook/Meta, LinkedIn, Microsoft
     * - Current HashSet approach:
     *   Time: O(h), Space: O(h)
     * - Two-pointer linked-list style approach:
     *   Time: O(h), Space: O(1)
     * ------------------------------------------------------------
     */
    public TreeNode lcaWithParent(TreeNode p, TreeNode q) {
        Set<TreeNode> visited = new HashSet<>();

        while (p != null) {
            visited.add(p);
            p = p.parent;
        }

        while (q != null) {
            if (visited.contains(q))
                return q;
            q = q.parent;
        }

        return null;
    }

    /*
     * ------------------------------------------------------------
     * 5. LCA IN DAG (MULTIPLE PARENTS)
     * Variation: node can have multiple parents, so LCA becomes graph search
     * - No direct standard LeetCode LCA problem for a general DAG
     * - Closest practice:
     *   LC 1650. Lowest Common Ancestor of a Binary Tree III
     *   LC 2359. Find Closest Node to Given Two Nodes
     * - Companies: graph/common-ancestor variants are commonly asked in
     *   Google, Facebook/Meta, LinkedIn style interviews
     * - Current single-parent simulation:
     *   Time: O(h), Space: O(h)
     * - True DAG with adjacency/list of parents:
     *   Time: O(V + E), Space: O(V)
     * ------------------------------------------------------------
     *
     * 🔥 Important:
     * Tree → 1 parent
     * DAG → multiple parents → becomes GRAPH problem
     * 
     * Approach:
     * - Find all ancestors of p
     * - Find all ancestors of q
     * - Return first common ancestor
     * 
     * (Not strictly "lowest" unless we track levels)
     */
    public TreeNode lcaInDAG(TreeNode p, TreeNode q) {

        Set<TreeNode> ancestorsP = getAncestors(p);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (ancestorsP.contains(curr))
                return curr;

            if (curr.parent != null && !visited.contains(curr.parent)) {
                visited.add(curr.parent);
                queue.offer(curr.parent);
            }
        }

        return null;
    }

    private Set<TreeNode> getAncestors(TreeNode node) {
        Set<TreeNode> set = new HashSet<>();

        while (node != null) {
            set.add(node);
            node = node.parent;
        }
        return set;
    }

    /*
     * ------------------------------------------------------------
     * 6. LCA IN DISTRIBUTED TREE (DESIGN DISCUSSION)
     * Variation: tree data is partitioned across machines/services
     * - No direct LeetCode coding problem
     * - Closest practice:
     *   LC 1650. Lowest Common Ancestor of a Binary Tree III
     *   LC 1483. Kth Ancestor of a Tree Node
     * - Companies: system-design flavored tree/graph questions can appear
     *   in Google, Meta, Amazon, Microsoft, LinkedIn interviews
     * - Basic parent climb:
     *   Time: O(h) remote hops, Space: O(1) to O(h)
     * - With binary lifting / ancestor cache:
     *   Preprocess: O(n log n), Query: O(log n), Space: O(n log n)
     * - Distributed cost focus:
     *   optimize network calls, latency, caching, and consistency
     * ------------------------------------------------------------
     *
     * 🚨 THIS IS NOT A PURE CODING PROBLEM
     * 
     * Interview expectation:
     * 
     * 👉 Tree is distributed across machines
     * 
     * You discuss:
     * 
     * 1. Each node stores:
     * - parent pointer OR
     * - path to root OR
     * - subtree ID
     * 
     * 2. Approaches:
     * - Bring both nodes to same depth (like linked list)
     * - Use hashing / IDs
     * - Use ancestor caching
     * 
     * 3. Optimizations:
     * - Precompute jump pointers (binary lifting)
     * - Use distributed queries
     * 
     * 4. Tradeoffs:
     * - latency vs storage
     * - consistency
     * 
     * 👉 You DO NOT write full code here
     */

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        AdvancedLCAPatterns sol = new AdvancedLCAPatterns();

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

        // set parent pointers
        n5.parent = root;
        n1.parent = root;
        n6.parent = n5;
        n2.parent = n5;
        n0.parent = n1;
        n8.parent = n1;
        n7.parent = n2;
        n4.parent = n2;

        System.out.println("LCA(5,1): " + sol.lca(root, n5, n1).val);
        System.out.println("Distance(5,4): " + sol.distanceBetweenNodes(root, n5, n4));
        System.out.println("Kth Ancestor of 4 (k=2): " + sol.kthAncestor(root, n4, 2));
        System.out.println("LCA with parent (7,4): " + sol.lcaWithParent(n7, n4).val);
    }
}
