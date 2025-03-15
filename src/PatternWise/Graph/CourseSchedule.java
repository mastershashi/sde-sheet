package PatternWise.Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false
 */
public class CourseSchedule {
    // steps 
    // first form a graph of size numCourse where edges will be consutructed based on prerequisites 
    // for a inDegree of this graph .
    // create a Queue of size numCourses and add the value if inDegree == 0
    // process topological sorting with the intension of identifying a cycle in DAG
    // after processing all nodes you still have nodes left with non-zero in-degrees, it indicates a cycle( course cannot be completed).

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for( int i = 0 ;i < numCourses ;i++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int[] prerequisite : prerequisites){
            int pre = prerequisite[1];
            int course = prerequisite[0];
            adjacencyList.get(pre).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);  // No prerequisites, so can take this course first
            }
        }
        int count =0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            count++;
            for(int neighbour : adjacencyList.get(course)){
                inDegree[neighbour]--;
                if(inDegree[neighbour] == 0){
                    queue.offer(neighbour);
                }
            }
        }
        return count == numCourses;
        
    }
    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        
        // Example 1:
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}}; // 1 depends on 0
        System.out.println(cs.canFinish(numCourses1, prerequisites1));  // Output: true

        // Example 2:
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}}; // 1 depends on 0 and 0 depends on 1 (cycle)
        System.out.println(cs.canFinish(numCourses2, prerequisites2));  // Output: false
   
    }
    
}
