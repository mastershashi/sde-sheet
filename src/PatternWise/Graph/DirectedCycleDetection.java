package PatternWise.Graph;

import java.util.*;

public class DirectedCycleDetection {

    public static boolean hasCycle(int V, List<List<Integer>> graph) {

        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        // Handle disconnected components
        for (int node = 0; node < V; node++) {
            if (!visited[node]) {
                if (dfs(node, graph, visited, pathVisited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(int node,
            List<List<Integer>> graph,
            boolean[] visited,
            boolean[] pathVisited) {

        // Mark node as visited
        visited[node] = true;

        // Mark node as part of the current DFS path
        pathVisited[node] = true;

        // Explore all neighbors
        for (int neighbor : graph.get(node)) {

            // Case 1: Neighbor not visited
            if (!visited[neighbor]) {

                if (dfs(neighbor, graph, visited, pathVisited)) {
                    return true;
                }
            }

            // Case 2: Neighbor is already in the current DFS path
            else if (pathVisited[neighbor]) {
                return true; // Back edge found → Cycle exists
            }
        }

        // Backtracking:
        // Remove the node from the current DFS path
        pathVisited[node] = false;

        return false;
    }

    public static void main(String[] args) {

        int V = 4;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(3);
        graph.get(3).add(1); // Creates a cycle

        System.out.println(hasCycle(V, graph)); // true
    }
}