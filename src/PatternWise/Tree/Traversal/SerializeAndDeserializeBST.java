package PatternWise.Tree.Traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            serializeHelper(root, sb);  // Call the helper function for serializing
        }
        return sb.toString();
    }
    private void serializeHelper(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append("null").append(",");
            return;
        }  
        // RLR - Pre Order Traversal
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb); 
    }
      // Deserialization: Reconstruct the tree from the pre-order list
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) return null; // If the input is "[]", return null (empty tree)
        String[] nodes = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) return null;
        
        String val = queue.poll();
        if (val.equals(null) || val.equals("null")) return null;
        
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);  // Left subtree first
        node.right = deserializeHelper(queue); // Right subtree second
        
        return node;
    }
    public static void main(String[] args) {
        SerializeAndDeserializeBST solution = new SerializeAndDeserializeBST();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        // Serialize the BST
        String serializedData = solution.serialize(root);
        System.out.println("Serialized BST: " + serializedData);

        // Deserialize the BST
        TreeNode deserializedRoot = solution.deserialize(solution.serialize(root));
        System.out.println("Deserialized BST: " + deserializedRoot.val);

        // Serialize the BST again
        String newSerializedData = solution.serialize(deserializedRoot);
        System.out.println("Serialized BST: " + newSerializedData);
    }
    
}
