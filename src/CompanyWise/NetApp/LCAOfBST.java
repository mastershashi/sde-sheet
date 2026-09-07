package CompanyWise.NetApp;

public class LCAOfBST {
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode left, TreeNode right) {
        while (root != null) {
            if (left.val < root.val && right.val < root.val) {
                root = root.left;
            } else if (left.val > root.val && right.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;

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
        LCAOfBST obj = new LCAOfBST();

        TreeNode LCA = obj.lowestCommonAncestor(root, left0, right0);
        System.out.println("LCA of " + left0.val + " and " + right0.val + " is: " + LCA.val);

        LCA = obj.lowestCommonAncestor(root, left1, right2);
        System.out.println("LCA of " + left1.val + " and " + right2.val + " is: " + LCA.val);

        LCA = obj.lowestCommonAncestor(root, root, right22);
        System.out.println("LCA of " + root.val + " and " + right22.val + " is: " + LCA.val);
    }

}
