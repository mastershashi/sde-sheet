package PatternWise.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TopoSortDFS {

    static void dfs(int node, List<List<Integer>> adj, Stack<Integer> stack, int[] visited) {
        visited[node] = 1;
        for (Integer ad : adj.get(node)) {
            if (visited[ad] == 0) {
                dfs(ad, adj, stack, visited);
            }
        }
        stack.push(node);

    }

    static int[] toposort(int V, List<List<Integer>> adj) {

        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, adj, stack, visited);
            }
        }
        int[] ans = new int[V];
        int i = 0;
        while (!stack.isEmpty()) {
            ans[i++] = stack.peek();
            stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        TopoSortDFS obj = new TopoSortDFS();
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>()); // Node 0
        adjList.add(new ArrayList<>()); // Node 1
        adjList.add(new ArrayList<>(Arrays.asList(3))); // Node 2 points to node 3
        adjList.add(new ArrayList<>(Arrays.asList(1))); // Node 3 points to node 1
        adjList.add(new ArrayList<>(Arrays.asList(0, 1))); // Node 4 points to nodes 0 and 1
        adjList.add(new ArrayList<>(Arrays.asList(0, 2))); // Node 5 points to nodes 0 and 2

        int numNodes = 6; // Total number of nodes in the graph

        // Perform topological sort
        int[] result = toposort(numNodes, adjList);
        for (int i : result) {
            System.out.print(i + "->");
        }
    }

}
