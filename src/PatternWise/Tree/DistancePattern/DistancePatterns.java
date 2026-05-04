package PatternWise.Tree.DistancePattern;

import java.util.*;

public class DistancePatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * Tree → Graph conversion → BFS
     * 
     * Used for:
     * - distance K nodes
     * - burning tree
     * - infection spread
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

    Map<TreeNode, TreeNode> parent = new HashMap<>();

    private void build(TreeNode root, TreeNode par) {
        if (root == null)
            return;

        parent.put(root, par);
        build(root.left, root);
        build(root.right, root);
    }

    /*
     * ------------------------------------------------------------
     * 1. NODES AT DISTANCE K FROM TARGET
     * Variation: convert tree to graph using parent map, then BFS
     * - LC 863. All Nodes Distance K in Binary Tree
     * - Companies: Amazon, Bloomberg, Facebook/Meta, Google, Microsoft,
     *   Oracle, Uber
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        parent.clear();
        build(root, null);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int dist = 0;

        while (!q.isEmpty()) {

            if (dist == k)
                break;

            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                for (TreeNode nei : Arrays.asList(
                        node.left,
                        node.right,
                        parent.get(node))) {
                    if (nei != null && !visited.contains(nei)) {
                        visited.add(nei);
                        q.offer(nei);
                    }
                }
            }

            dist++;
        }

        List<Integer> res = new ArrayList<>();
        for (TreeNode n : q)
            res.add(n.val);

        return res;
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        DistancePatterns sol = new DistancePatterns();

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
        root.left = n5;
        root.right = new TreeNode(1);
        n5.left = new TreeNode(6);
        n5.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        n5.right.left = new TreeNode(7);
        n5.right.right = new TreeNode(4);

        System.out.println("Nodes at distance K=2 from 5: " + sol.distanceK(root, n5, 2));
    }
}
