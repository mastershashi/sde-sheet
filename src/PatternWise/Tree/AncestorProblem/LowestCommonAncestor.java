package PatternWise.Tree.AncestorProblem;

public class LowestCommonAncestor {
static class  TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        while( root != null){
            if(p.val < root.val && q.val < root.val){
                root = root.left;
            }else if(p.val > root.val && q.val > root.val){
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
     }
    public static void main(String[] args) {
        LowestCommonAncestor solution = new LowestCommonAncestor();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left; // Node 2
        TreeNode q = root.right; // Node 8
        
        // Finding the LCA of nodes 2 and 8
        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val);
        
        // Finding the LCA of nodes 2 and 4
        p = root.left; // Node 2
        q = root.left.right; // Node 4
        lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val);
        
    }
    
}
