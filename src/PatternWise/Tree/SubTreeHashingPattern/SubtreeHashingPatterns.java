package PatternWise.Tree.SubTreeHashingPattern;

import java.util.*;

public class SubtreeHashingPatterns {

    /*
     * ============================================================
     * 🔥 CORE IDEA
     * ============================================================
     * 
     * Convert each subtree into a UNIQUE SIGNATURE:
     * 
     * signature(node) =
     * node.val + "," +
     * signature(left) + "," +
     * signature(right)
     * 
     * OR better:
     * use hash codes to avoid large strings
     * 
     * ============================================================
     * 🧠 WHY THIS WORKS
     * ============================================================
     * 
     * 👉 Identical structure → identical signature
     * 👉 Different structure → different signature
     * 
     * So we can detect:
     * - duplicate subtrees
     * - repeated patterns
     * - subtree equality
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
     * 1. FIND DUPLICATE SUBTREES (MOST IMPORTANT)
     * Variation: serialize every subtree and count signatures
     * - LC 652. Find Duplicate Subtrees
     * - Companies: Amazon, Google, Facebook/Meta, Microsoft
     * - Time: O(n * h) with strings, worst O(n^2)
     * - Space: O(n * h) signatures, worst O(n^2)
     * ------------------------------------------------------------
     */
    Map<String, Integer> freq = new HashMap<>();
    List<TreeNode> duplicates = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        freq.clear();
        duplicates.clear();
        serialize(root);
        return duplicates;
    }

    private String serialize(TreeNode node) {
        if (node == null)
            return "#";

        String left = serialize(node.left);
        String right = serialize(node.right);

        String signature = node.val + "," + left + "," + right;

        freq.put(signature, freq.getOrDefault(signature, 0) + 1);

        if (freq.get(signature) == 2) {
            duplicates.add(node);
        }

        return signature;
    }

    /*
     * ------------------------------------------------------------
     * 2. SUBTREE IDENTICAL CHECK
     * Variation: compare two trees via hashing/signature
     * - LC 100. Same Tree
     * - Companies: Amazon, Google, Microsoft, Facebook/Meta, Apple
     * - Time: O(n + m)
     * - Space: O(h1 + h2) recursion + signatures
     * ------------------------------------------------------------
     */
    public boolean isSameTree(TreeNode a, TreeNode b) {
        return getHash(a).equals(getHash(b));
    }

    private String getHash(TreeNode node) {
        if (node == null)
            return "#";

        return node.val + "," + getHash(node.left) + "," + getHash(node.right);
    }

    /*
     * ------------------------------------------------------------
     * 3. COUNT UNIQUE SUBTREES
     * Variation: store all unique subtree signatures
     * - Closest: LC 652. Find Duplicate Subtrees
     * - Companies: Amazon, Google, Facebook/Meta, Microsoft
     * - Time: O(n * h) with strings, worst O(n^2)
     * - Space: O(n * h), worst O(n^2)
     * ------------------------------------------------------------
     */
    Set<String> unique = new HashSet<>();

    public int countUniqueSubtrees(TreeNode root) {
        unique.clear();
        collect(root);
        return unique.size();
    }

    private String collect(TreeNode node) {
        if (node == null)
            return "#";

        String left = collect(node.left);
        String right = collect(node.right);

        String sig = node.val + "," + left + "," + right;
        unique.add(sig);

        return sig;
    }

    /*
     * ------------------------------------------------------------
     * 4. SUBTREE WITH MAX FREQUENCY
     * Variation: find most repeated subtree signature
     * - Closest: LC 652. Find Duplicate Subtrees
     * - Companies: Amazon, Google, Facebook/Meta, Microsoft
     * - Time: O(n * h) with strings, worst O(n^2)
     * - Space: O(n * h), worst O(n^2)
     * ------------------------------------------------------------
     */
    Map<String, Integer> map = new HashMap<>();
    String maxSubtree = "";
    int maxFreq = 0;

    public String mostFrequentSubtree(TreeNode root) {
        map.clear();
        maxFreq = 0;
        dfs(root);
        return maxSubtree;
    }

    private String dfs(TreeNode node) {
        if (node == null)
            return "#";

        String left = dfs(node.left);
        String right = dfs(node.right);

        String sig = node.val + "," + left + "," + right;

        int count = map.getOrDefault(sig, 0) + 1;
        map.put(sig, count);

        if (count > maxFreq) {
            maxFreq = count;
            maxSubtree = sig;
        }

        return sig;
    }

    /*
     * ------------------------------------------------------------
     * 5. OPTIMIZED VERSION (USING INTEGER IDS - IMPORTANT FOR FAANG)
     * Variation: intern each subtree signature into an integer id
     * - LC 652. Find Duplicate Subtrees
     * - Companies: Amazon, Google, Facebook/Meta, Microsoft
     * - Time: O(n)
     * - Space: O(n)
     * ------------------------------------------------------------
     */
    Map<String, Integer> idMap = new HashMap<>();
    Map<Integer, Integer> freqId = new HashMap<>();
    int idCounter = 1;
    int result = 0;

    public int findDuplicateSubtreesOptimized(TreeNode root) {
        idMap.clear();
        freqId.clear();
        idCounter = 1;
        result = 0;
        encode(root);
        return result;
    }

    private int encode(TreeNode node) {
        if (node == null)
            return 0;

        int left = encode(node.left);
        int right = encode(node.right);

        String key = node.val + "," + left + "," + right;

        int id = idMap.getOrDefault(key, idCounter++);
        idMap.put(key, id);

        freqId.put(id, freqId.getOrDefault(id, 0) + 1);

        if (freqId.get(id) == 2) {
            result++;
        }

        return id;
    }

    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {

        SubtreeHashingPatterns sol = new SubtreeHashingPatterns();

        /*
         * 1
         * / \
         * 2 3
         * / / \
         * 4 2 4
         * /
         * 4
         */

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);

        root.right.left.left = new TreeNode(4);

        System.out.println("Duplicate Subtrees: " +
                sol.findDuplicateSubtrees(root).size());

        System.out.println("Same Tree (root.left, root.right.left): " +
                sol.isSameTree(root.left, root.right.left));

        System.out.println("Unique Subtrees: " +
                sol.countUniqueSubtrees(root));

        System.out.println("Most Frequent Subtree: " +
                sol.mostFrequentSubtree(root));

        System.out.println("Duplicate Subtrees Optimized: " +
                sol.findDuplicateSubtreesOptimized(root));
    }
}
