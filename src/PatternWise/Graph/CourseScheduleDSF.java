package PatternWise.Graph;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleDSF {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

    // Build Graph
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
        graph.add(new ArrayList<>());
    }

    // Build edges
    for (int[] prerequisite : prerequisites) {

        int course = prerequisite[0];
        int prerequisiteCourse = prerequisite[1];

        // prerequisiteCourse -> course
        graph.get(prerequisiteCourse).add(course);
    }

    boolean[] visited = new boolean[numCourses];
    boolean[] pathVisited = new boolean[numCourses];

    for (int course = 0; course < numCourses; course++) {

        if (!visited[course]) {

            if (dfs(course, graph, visited, pathVisited)) {
                return false;      // <-- ONLY CHANGE
            }
        }
    }

    return true;                   // <-- ONLY CHANGE
}

private boolean dfs(int course,
                    List<List<Integer>> graph,
                    boolean[] visited,
                    boolean[] pathVisited) {

    visited[course] = true;
    pathVisited[course] = true;

    for (int nextCourse : graph.get(course)) {

        if (!visited[nextCourse]) {

            if (dfs(nextCourse, graph, visited, pathVisited))
                return true;

        } else if (pathVisited[nextCourse]) {

            return true;
        }
    }

    pathVisited[course] = false;

    return false;
}
public static void main(String[] args) {
    int numCourses = 4;

    int[][] prerequisites = {
         {1, 0}, // 0 -> 1
        {2, 1}, // 1 -> 2
        {0, 2}  // 2 -> 0 (Cycle)
    };

    CourseScheduleDSF solution = new CourseScheduleDSF();

    boolean canFinish = solution.canFinish(numCourses, prerequisites);

    System.out.println(canFinish);
}
    
}
