package PatternWise.Tree.SerializationPattern;

import java.util.*;

public class SerializationPatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * Tree ↔ String
     * 
     * You must preserve:
     * ✅ values
     * ✅ structure
     * 
     * 👉 Use NULL markers to avoid ambiguity
     * 
     * ============================================================
     * 🔥 PREORDER TEMPLATE (MOST COMMON)
     * ============================================================
     * 
     * Serialize:
     * node → left → right
     * 
     * Deserialize:
     * read → build → recurse
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

    // delimiter + null marker
    private static final String SEP = ",";
    private static final String NULL = "X";

    /*
     * ------------------------------------------------------------
     * 1. SERIALIZE (PREORDER)
     * Variation: DFS preorder with null markers
     * - LC 297. Serialize and Deserialize Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, LinkedIn
     * - Time: O(n)
     * - Space: O(h) recursion + O(n) encoded string
     * ------------------------------------------------------------
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDFS(root, sb);
        return sb.toString();
    }

    private void serializeDFS(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(node.val).append(SEP);

        serializeDFS(node.left, sb);
        serializeDFS(node.right, sb);
    }

    /*
     * ------------------------------------------------------------
     * 2. DESERIALIZE (PREORDER)
     * Variation: consume tokens in preorder and rebuild recursively
     * - LC 297. Serialize and Deserialize Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, LinkedIn
     * - Time: O(n)
     * - Space: O(h) recursion + O(n) tokens
     * ------------------------------------------------------------
     */
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();

        if (val.equals(NULL))
            return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));

        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }

    /*
     * ------------------------------------------------------------
     * 3. SERIALIZE USING LEVEL ORDER (BFS)
     * Variation: useful for LeetCode-style array representation
     * - LC 297. Serialize and Deserialize Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, LinkedIn
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public String serializeBFS(TreeNode root) {
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == null) {
                sb.append(NULL).append(SEP);
                continue;
            }

            sb.append(node.val).append(SEP);

            q.offer(node.left);
            q.offer(node.right);
        }

        return sb.toString();
    }

    /*
     * ------------------------------------------------------------
     * 4. DESERIALIZE BFS
     * Variation: rebuild children level by level from tokens
     * - LC 297. Serialize and Deserialize Binary Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, LinkedIn
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    public TreeNode deserializeBFS(String data) {
        if (data.isEmpty())
            return null;

        String[] arr = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (!arr[i].equals(NULL)) {
                node.left = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(node.left);
            }
            i++;

            if (!arr[i].equals(NULL)) {
                node.right = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(node.right);
            }
            i++;
        }

        return root;
    }

    /*
     * ------------------------------------------------------------
     * 5. VALIDATE ROUND TRIP (INTERVIEW TRICK)
     * Variation: compare original tree with deserialized tree
     * - LC 100. Same Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple,
     *   Bloomberg
     * - Time: O(n)
     * - Space: O(h), worst O(n)
     * ------------------------------------------------------------
     */
    public boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;

        return a.val == b.val &&
                isSameTree(a.left, b.left) &&
                isSameTree(a.right, b.right);
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        SerializationPatterns sol = new SerializationPatterns();

        /*
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // PREORDER SERIALIZATION
        String serialized = sol.serialize(root);
        System.out.println("Serialized (DFS): " + serialized);

        TreeNode deserialized = sol.deserialize(serialized);
        System.out.println("Same Tree (DFS): " + sol.isSameTree(root, deserialized));

        // BFS SERIALIZATION
        String bfs = sol.serializeBFS(root);
        System.out.println("Serialized (BFS): " + bfs);

        TreeNode bfsTree = sol.deserializeBFS(bfs);
        System.out.println("Same Tree (BFS): " + sol.isSameTree(root, bfsTree));
    }
}
