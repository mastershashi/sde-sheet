package PatternWise.Graph.PathBased;

import java.util.ArrayList;
import java.util.List;

public class IsPathExists {
    // private boolean []visited = new boolean[];

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        boolean[] visited = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        if (source == destination) {
            return true;
        }
        return dfs(graph, source, destination, visited);

    }

    public boolean dfs( List<List<Integer>> graph, int source, int destination, boolean[] visited) {
        if (source == destination) {
            return true;
        }
        if (visited[source]) {
            return false;
        }
        visited[source] = true;
        for (int edge : graph.get(source)) {
            if (dfs(graph, edge, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 6;

        int[][] edges = {
            {0, 1},
            {0, 2},
            {3, 5},
            {5, 4},
            {4, 3},
            {1, 4}
        };

        int source = 1;
        int destination = 5;
        IsPathExists obj = new IsPathExists();

        boolean result = obj.validPath(n, edges, source, destination);

        System.out.println("Path exists: " + result);
    }

}
