package CompanyWise.NetApp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversalBST {
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

    public List<List<Integer>> levelOrderTraversalResult(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
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

        LevelOrderTraversalBST obj = new LevelOrderTraversalBST();
        List<List<Integer>> levelOrderTraversal = obj.levelOrderTraversalResult(root);

        for (List<Integer> level : levelOrderTraversal) {
            for (Integer levelVal : level) {
                System.out.print(levelVal + "->");
            }
        }
    }

}
