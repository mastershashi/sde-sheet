package PatternWise.Tree.TreeAsGraphPattern;

import java.util.*;

public class TreeGraphPatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * Convert Tree → Graph:
     * 
     * Each node connects to:
     * - left
     * - right
     * - parent
     * 
     * Then run BFS like a graph.
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

    Map<TreeNode, TreeNode> parentMap = new HashMap<>();

    /*
     * ------------------------------------------------------------
     * BUILD PARENT MAP
     * Helper: connect each node to its parent before graph-style BFS
     * - Used by LC 863 and LC 2385 style problems
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    private void buildParent(TreeNode root, TreeNode parent) {
        if (root == null)
            return;

        parentMap.put(root, parent);

        buildParent(root.left, root);
        buildParent(root.right, root);
    }

    /*
     * ------------------------------------------------------------
     * 1. BURNING TREE / INFECTION SPREAD
     * Variation: tree becomes undirected graph via parent pointers
     * - LC 2385. Amount of Time for Binary Tree to Be Infected
     * - Related: LC 863. All Nodes Distance K in Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public int timeToBurnTree(TreeNode root, TreeNode target) {

        parentMap.clear();
        buildParent(root, null);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int time = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                for (TreeNode nei : Arrays.asList(
                        node.left,
                        node.right,
                        parentMap.get(node))) {
                    if (nei != null && !visited.contains(nei)) {
                        visited.add(nei);
                        q.offer(nei);
                        burned = true;
                    }
                }
            }

            if (burned)
                time++;
        }

        return time;
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        TreeGraphPatterns sol = new TreeGraphPatterns();

        /*
         * 1
         * / \
         * 5 3
         * \ / \
         * 4 10 6
         * / \
         * 9 2
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(2);

        System.out.println("Time to burn from 3: " + sol.timeToBurnTree(root, root.right));
    }
}
