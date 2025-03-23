package PatternWise.Tree.Traversal.LevelOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBST {
    static class  TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static class Pair{
        TreeNode node;
        int hd;
        Pair(TreeNode node, int hd){
            this.node = node;
            this.hd = hd;
        }
    }
    public  List<Integer> getTopView(TreeNode root){
        Map<Integer, Integer> map = new TreeMap<>();// TreeMap automatically sorts the keys for the result will be in sorte3d order based on it's horizontal distance
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;
            if(!map.containsKey(hd)){
                map.put(hd, node.val);
            }
            if(node.left != null){
                queue.add(new Pair(node.left, hd - 1));
            }
            if(node.right != null){
                queue.add(new Pair(node.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());
    }
    public List<Integer> getLeftView(TreeNode root){
              // A map to track the first node at each level
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> leftView = new ArrayList<>();
        Queue<Pair> queue = new LinkedList<>();
        
        if (root != null) {
            queue.offer(new Pair(root, 0)); // Start from root at level 0
        }
        
        // Perform level-order traversal
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.hd;
            
            // If this is the first node at the current level, add it to the left view
            if (!map.containsKey(level)) {
                map.put(level, node.val);
                leftView.add(node.val); // Adding the first node of each level
            }

            // Add left child first to the queue (this ensures we process left nodes first)
            if (node.left != null) {
                queue.offer(new Pair(node.left, level + 1));
            }

            // Add right child only if left child is not available at that level
            if (node.right != null && node.left == null) {
                queue.offer(new Pair(node.right, level + 1));
            }
        }
        return leftView;
    }
    public List<Integer> getRightView(TreeNode root){
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> rightView = new ArrayList<>();
        Queue<Pair> queue = new LinkedList<>();
        
        if (root != null) {
            queue.offer(new Pair(root, 0)); // Start from root at level 0
        }
        
        // Perform level-order traversal
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.hd;
            
            // If this is the first node at the current level, add it to the right view
            map.put(level, node.val);
            rightView.add(node.val); // Adding the first node of each level

            // Add right child first to the queue (this ensures we process right nodes first)
            if (node.right != null) {
                queue.offer(new Pair(node.right, level + 1));
            }

            // Add left child only if right child is not available at that level
            if (node.left != null && node.right == null) {
                queue.offer(new Pair(node.left, level + 1));
            }
        }
        return rightView;
    }
    public  List<Integer> getBottomView(TreeNode root){
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new TreeMap<>();// TreeMap automatically sorts the keys for the result will be in sorte3d order based on it's horizontal distance
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;
            
            // In the map, store the node at each horizontal distance.
            // If a node already exists at this HD, replace it (since we want the bottom-most node).
            map.put(hd, node.val);

            // Add left and right children with the corresponding horizontal distance
            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {
        TopViewOfBST solution = new TopViewOfBST();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        List<Integer> topViewList = solution.getTopView(root);
        System.out.println("Top view of the BST is: " + topViewList);

        List<Integer> leftView = solution.getLeftView(root);
        System.out.println("Left view of the BST is: " + leftView);

        List<Integer> rightView = solution.getRightView(root);
        System.out.println("Right view of the BST is: " + rightView);

        List<Integer> bottomView = solution.getBottomView(root);
        System.out.println("Bottom view of the BST is: " + bottomView);
    }
    
}
