package PatternWise.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EventualSafeState {

    static List<Integer> eventualSafeNodes(int[][] graph) {
        /**
         * LeetCode 802 — Find Eventual Safe States
         * Question
         * You are given a directed graph where graph[i] contains the nodes that node i
         * points to.
         * 
         * A node is a safe node if every possible path starting from that node
         * eventually reaches a terminal node.
         * 
         * Return all safe nodes in ascending order.
         * 
         * Test Case
         * int[][] graph = {
         * {1, 2}, // 0 -> 1, 2
         * {2, 3}, // 1 -> 2, 3
         * {5}, // 2 -> 5
         * {0}, // 3 -> 0
         * {5}, // 4 -> 5
         * {}, // 5 -> terminal
         * {} // 6 -> terminal
         * };
         * 
         * Expected output:
         * 
         * [2, 4, 5, 6]
         */
        int V = graph.length;

        // Reverse adjacency list
        List<List<Integer>> reverseGraph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        // Build reverse graph AND indegree together
        int[] indegree = new int[V];

        for (int node = 0; node < V; node++) {

            for (int neighbor : graph[node]) {

                // Original edge:
                // node -> neighbor
                //
                // Reverse edge:
                // neighbor -> node

                reverseGraph.get(neighbor).add(node);

                // Since we added:
                // neighbor -> node
                //
                // node has one incoming edge
                indegree[node]++;
            }
        }

        // Add all nodes with indegree 0
        // These are terminal nodes in the original graph.
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {

            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Kahn's Algorithm
        List<Integer> safeNodes = new ArrayList<>();

        while (!queue.isEmpty()) {

            int node = queue.remove();

            safeNodes.add(node);

            // Traverse reverse graph
            for (int neighbor : reverseGraph.get(node)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Required by LeetCode: ascending order
        safeNodes.sort(Integer::compareTo);

        return safeNodes;
    }

    public static void main(String[] args) {

        /*
         * LeetCode 802
         *
         * Input:
         *
         * 0 -> 1, 2
         * 1 -> 2, 3
         * 2 -> 5
         * 3 -> 0
         * 4 -> 5
         * 5 -> terminal
         * 6 -> terminal
         */

        int[][] graph = {
                { 1, 2 },
                { 2, 3 },
                { 5 },
                { 0 },
                { 5 },
                {},
                {}
        };

        List<Integer> result = eventualSafeNodes(graph);

        System.out.println(result);

        int[][] graph1 = { { 1, 2, 3, 4 }, { 1, 2 }, { 3, 4 }, { 0, 4 }, {} };
        List<Integer> result1 = eventualSafeNodes(graph1);

        System.out.println(result1);
    }
}
