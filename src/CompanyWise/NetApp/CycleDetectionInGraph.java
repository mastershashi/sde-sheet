package CompanyWise.NetApp;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionInGraph {
    public static boolean hasCycle(int V, List<List<Integer>> graph) {
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, graph, visited, pathVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true;
        for (Integer neighbour : graph.get(node)) {
            if (!visited[neighbour]) {
                if (dfs(neighbour, graph, visited, pathVisited)) {
                    return true;
                }
            } else if (pathVisited[neighbour]) {
                return true;
            }
        }
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
        graph.get(3).remove(Integer.valueOf(1));
        System.out.println(graph);
        System.out.println(hasCycle(V, graph)); // false
    }

}
