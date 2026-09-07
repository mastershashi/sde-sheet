package CompanyWise.NetApp;

import java.util.PriorityQueue;

public class KthLargestInBST {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private void inOrderTraversal(TreeNode root, int k, PriorityQueue<Integer> pq) {
        if (root == null)
            return;

        inOrderTraversal(root.left, k, pq);
        pq.add(root.val);
        if (pq.size() > k) {
            pq.poll();
        }

        inOrderTraversal(root.right, k, pq);
    }

    public int kthLargestElement(TreeNode root, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();// minHeap

        inOrderTraversal(root, k, pq);
        return pq.peek();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left0 = new TreeNode(6);
        TreeNode right0 = new TreeNode(12);
        root.left = left0;
        root.right = right0;
        // left subtrre
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(7);
        left0.left = left1;
        left0.right = right1;
        TreeNode left2 = new TreeNode(1);
        TreeNode right2 = new TreeNode(4);
        left1.left = left2;
        left1.right = right2;

        // right subtree
        TreeNode left11 = new TreeNode(11);
        TreeNode right11 = new TreeNode(14);
        right0.left = left11;
        right0.right = right11;

        TreeNode left22 = new TreeNode(13);
        TreeNode right22 = new TreeNode(15);

        right11.left = left22;
        right11.right = right22;
        int k = 4;
        KthLargestInBST obj = new KthLargestInBST();

        System.out.println(obj.kthLargestElement(root, k));

    }
}
