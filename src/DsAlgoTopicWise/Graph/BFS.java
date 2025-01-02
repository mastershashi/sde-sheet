import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    static List<Integer> bfs(List<List<Integer>> adj, int s) {

        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[adj.size()];
        List<Integer> bfsResult = new ArrayList<>();
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()){
           int curr = queue.poll();
           bfsResult.add(curr);
           for(int neighbour : adj.get(curr)){
               if(!visited[neighbour]){
                   visited[neighbour] = true;
                   queue.add(neighbour);
               }
           }
        }
return bfsResult;
        
    }

     // Function to add an edge to the graph
     static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // Undirected graph
    }

    public static void main(String args[]){
         // Number of vertices in the graph
        int V = 5;
        
        // Adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Add edges to the graph
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 4);
        
        // Perform BFS traversal starting from vertex 0
        System.out.println("BFS starting from 0:");
        List<Integer> bfsResult = bfs(adj, 0);
        for (int i = 0; i < bfsResult.size(); i++) {
            System.out.print(bfsResult.get(i) + " ");
        }
    }
    
}
