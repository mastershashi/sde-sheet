import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GraphDFS {

    // Method to perform DFS using a stack and adjacency matrix (list of lists)
    public static void dfs(int start, List<List<Integer>> adjMatrix) {
        // Create a set to store visited nodes
        Set<Integer> visited = new HashSet<>();
        
        // Stack to simulate the recursive calls
        Stack<Integer> stack = new Stack<>();
        
        // Push the starting node onto the stack
        stack.push(start);
        
        while (!stack.isEmpty()) {
            // Pop the top node from the stack
            int node = stack.pop();
            
            // If the node hasn't been visited
            if (!visited.contains(node)) {
                // Mark it as visited
                visited.add(node);
                
                // Print the node (or do something with it)
                System.out.print(node + " ");
                
                // Push all unvisited neighbors onto the stack
                for (int i = 0; i < adjMatrix.get(node).size(); i++) {
                    // Check if there's an edge between 'node' and 'i'
                    if (adjMatrix.get(node).get(i) == 1 && !visited.contains(i)) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a sample graph using an adjacency matrix (List of Lists)
        int n = 6; // Number of nodes (0 to 5)
        List<List<Integer>> adjMatrix = new ArrayList<>();
        
        // Initialize the adjacency matrix
        for (int i = 0; i < n; i++) {
            adjMatrix.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }
        
        // Define edges (for example, 0 -- 1, 1 -- 2, etc.)
        adjMatrix.get(0).set(1, 1); // Edge between 0 and 1
        adjMatrix.get(1).set(0, 1); // Edge between 1 and 0
        adjMatrix.get(1).set(2, 1); // Edge between 1 and 2
        adjMatrix.get(2).set(1, 1); // Edge between 2 and 1
        adjMatrix.get(2).set(3, 1); // Edge between 2 and 3
        adjMatrix.get(3).set(2, 1); // Edge between 3 and 2
        adjMatrix.get(3).set(4, 1); // Edge between 3 and 4
        adjMatrix.get(4).set(3, 1); // Edge between 4 and 3
        adjMatrix.get(4).set(5, 1); // Edge between 4 and 5
        adjMatrix.get(5).set(4, 1); // Edge between 5 and 4
        
        // Start DFS from node 0
        System.out.println("DFS Traversal (using stack with adjacency matrix):");
        dfs(0, adjMatrix);
    }
}
