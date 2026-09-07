package PatternWise.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectedGraphCycleBfs {
    boolean isCycle(int V, List<List<Integer>> adj) {

        int inDegree[] = new int[V];

        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                inDegree[it]++;
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek();
            queue.remove();
            count++;

            // now node is topo sort so we will remove it from inDegree
            for (int it : adj.get(node)) {
                inDegree[it]--;
                if (inDegree[it] == 0) {
                    queue.add(it);
                }
            }
        }
        if (count == V)
            return false;
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>()); // Node 0
        adjList.add(new ArrayList<>()); // Node 1
        adjList.add(new ArrayList<>(Arrays.asList(3))); // Node 2 points to node 3
        adjList.add(new ArrayList<>(Arrays.asList(1))); // Node 3 points to node 1
        adjList.add(new ArrayList<>(Arrays.asList(0, 1))); // Node 4 points to nodes 0 and 1
        adjList.add(new ArrayList<>(Arrays.asList(0, 2))); // Node 5 points to nodes 0 and 2

        int numNodes = 6; // Total number of nodes in the graph
        DirectedGraphCycleBfs obj = new DirectedGraphCycleBfs();

        boolean result = obj.isCycle(numNodes, adjList);

        System.out.println("is cycle :" + result);
        adjList.get(1).add(2);
        boolean result1 = obj.isCycle(numNodes, adjList);

        System.out.println("is cycle :" + result1);
    }
}
