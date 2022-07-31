import java.util.List;
import java.util.ArrayList;
class Graph{
    private int verticies;
    private List<List<Integer>> adjencyList ;

    Graph(int verticies){
     this.verticies = verticies;  
     adjencyList = new ArrayList<>(verticies);
     for(int i=1 ; i <= verticies ;i++){
       adjencyList.add(new ArrayList<Integer>());
     }
      
    }
    /**
    @direction
        1- undirected
        0- directed
     */
    private void addEdge(int source, int destination, int direction){
        adjencyList.get(source).add(destination);
        if(direction == 1)
        adjencyList.get(destination).add(source);
    }
    private void printGraph(){
        for(int i = 0; i<adjencyList.size() ;i++){
            System.out.println("Vertex " + i + ":");
            for (int j = 0; j < adjencyList.get(i).size(); j++) {
                System.out.print(" -> " + adjencyList.get(i).get(j));
            }
        }
    }
    public static void main(String args[]){
        Graph obj = new Graph(5);
        obj.addEdge(0,1,0);
        obj.addEdge(0,2,0);
        obj.addEdge(0,3,0);
        obj.addEdge(2,3,0);
        obj.addEdge(3,4,0);
        obj.addEdge(4,1,0);

        obj.printGraph();
        System.out.println();
    }
}