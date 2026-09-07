package PatternWise.Tree.Traversal.VerticalOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrderTreeTravrsal {
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

    static class Pair {
        int column;
        TreeNode node;

        public Pair(TreeNode node, int column) {
            this.column = column;
            this.node = node;
        }
    }

    private List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair queueItem = queue.poll();
            TreeNode node = queueItem.node;
            Integer column = queueItem.column;
            map.putIfAbsent(column, new ArrayList<>());
            map.get(column).add(node.val);
            if (node.left != null) {
                queue.offer(new Pair(node.left, column - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, column + 1));
            }
        }
        for (List<Integer> nodes : map.values()) {
            result.add(nodes);
        }
        return result;
    }

    private List<Integer> bottomView(TreeNode root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            map.put(current.column, current.node.val);
            if (current.node.left != null) {
                queue.offer(new Pair(current.node.left, current.column - 1));
            }
            if (current.node.right != null) {
                queue.offer(new Pair(current.node.right, current.column + 1));
            }
        }
        return new ArrayList<>(map.values());
    }

    private List<Integer> topView(TreeNode root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            map.putIfAbsent(current.column, current.node.val);
            if (current.node.left != null) {
                queue.offer(new Pair(current.node.left, current.column - 1));
            }
            if (current.node.right != null) {
                queue.offer(new Pair(current.node.right, current.column + 1));
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        VerticalOrderTreeTravrsal obj = new VerticalOrderTreeTravrsal();
        System.out.println(obj.verticalOrderTraversal(root));
        System.out.println(obj.bottomView(root));
          System.out.println(obj.topView(root));

    }

}
