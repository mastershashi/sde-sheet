package CompanyWise.PureStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSortBfs {
    static int[] toposort(int V, List<List<Integer>> adj) {
        // create an indegree array from given adj list
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (Integer ad : adj.get(i)) {
                indegree[ad]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        // insert all the nodes to queue whose indegree is 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Interate through queue ,
        // pop from queue ,
        // add the popped result to topo array
        // loop through the adjency list and reduce indegree , if indegree become 0 add
        // to queue
        int[] topo = new int[V];
        int i = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek();
            topo[i++] = node;
            queue.remove();
            for (Integer ad : adj.get(node)) {
                indegree[ad]--;
                if (indegree[ad] == 0) {
                    queue.add(ad);
                }
            }
        }
        return topo;
    }

    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(1, 4))); // Node 0 points to 1,4
        adj.add(new ArrayList<>()); // Node 1 does not point to any other node
        adj.add(new ArrayList<>(Arrays.asList(1))); // Node 2 points to 1
        adj.add(new ArrayList<>(Arrays.asList(2))); // Node 3 point 2
        adj.add(new ArrayList<>()); // node 4 does not point to any other node
        adj.add(new ArrayList<>(Arrays.asList(3, 4))); // Node 5 points to 4, 3

        int v = 6;
        int result[] = toposort(v, adj);
        for (int i = 0; i < v; i++) {
            System.out.print(result[i] + "->");
        }
        System.out.println();

    }

}
