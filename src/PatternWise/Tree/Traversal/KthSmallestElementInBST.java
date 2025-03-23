package PatternWise.Tree.Traversal;

public class KthSmallestElementInBST {
    private int result = 0;
    private int count = 0;
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private void inorder(TreeNode root, int k){
        if( root == null){
            return;
        }
        inorder(root.left, k);
        count++;
        if( count == k){
            result = root.val;
            return;
        }
        inorder(root.right, k);
    }
    public int kthSmallestElement(TreeNode root, int k){
        inorder(root, k);
        return result;
    }
    public static void main(String[] args) {
        KthSmallestElementInBST solution = new KthSmallestElementInBST();
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(16);
        root.left.left = new TreeNode(5);
        root.left.left.right = new TreeNode(7);
        root.left.right = new TreeNode(13);
        root.right.right = new TreeNode(18);

        int k = 4;
        int kthSmallest = solution.kthSmallestElement(root, k);
        System.out.println("The " + k + "th smallest element in the BST is: " + kthSmallest);
   
    }
}
