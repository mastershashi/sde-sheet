package PatternWise.Graph;

import java.util.*;

public class UndirectedCycleDFS {

    public static boolean hasCycle(int V, List<List<Integer>> graph) {

        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, graph, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(int node,
            int parent,
            List<List<Integer>> graph,
            boolean[] visited) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {

            // not visited → go deeper
            if (!visited[neighbor]) {

                if (dfs(neighbor, node, graph, visited)) {
                    return true;
                }

            }
            // visited but not parent → cycle
            else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int V = 4;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(1).add(0);

        graph.get(1).add(2);
        graph.get(2).add(1);

        graph.get(2).add(3);
        graph.get(3).add(2);

        graph.get(3).add(1); // cycle

        System.out.println(hasCycle(V, graph)); // true
    }
}