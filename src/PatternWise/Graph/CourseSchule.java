package PatternWise.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchule {
    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to
     * numCourses - 1. You are given an array prerequisites where prerequisites[i] =
     * [ai, bi] indicates that you must take course bi first if you want to take
     * course ai.
     * 
     * For example, the pair [0, 1], indicates that to take course 0 you have to
     * first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: numCourses = 2, prerequisites = [[1,0]]
     * Output: true
     * Explanation: There are a total of 2 courses to take.
     * To take course 1 you should have finished course 0. So it is possible.
     * Example 2:
     * 
     * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
     * Output: false
     * Explanation: There are a total of 2 courses to take.
     * To take course 1 you should have finished course 0, and to take course 0 you
     * should also have finished course 1. So it is impossible.
     * 
     * 
     * Constraints:
     * 
     * 1 <= numCourses <= 2000
     * 0 <= prerequisites.length <= 5000
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * All the pairs prerequisites[i] are unique.
     */
    // ==========Solution ==============
    // we know for a given pair u,v is u comes first strictly in DAG , then we can
    // apply topo sort .
    // we also know topo sort is only possible if there is not cyclein DAG
    // to solve this question if we can find if if graph has a cycle of not then
    // that would be the solution of above question
    // only thing is we need to create graph using given matrics
    // course schedule 1
    public static boolean canFinish(int numCourses, int[][] prerequisites) {

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
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (Integer ad : graph.get(i)) {
                indegree[ad]++;
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        int i = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek();
            count++;
            queue.remove();
            for (Integer ad : graph.get(node)) {
                indegree[ad]--;
                if (indegree[ad] == 0) {
                    queue.add(ad);
                }
            }
        }
        if (count == numCourses)
            return true;
        return false;
    }

    // course schedule 2
    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to
     * numCourses - 1. You are given an array prerequisites where prerequisites[i] =
     * [ai, bi] indicates that you must take course bi first if you want to take
     * course ai.
     * 
     * For example, the pair [0, 1], indicates that to take course 0 you have to
     * first take course 1.
     * Return the ordering of courses you should take to finish all courses. If
     * there are many valid answers, return any of them. If it is impossible to
     * finish all courses, return an empty array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: numCourses = 2, prerequisites = [[1,0]]
     * Output: [0,1]
     * Explanation: There are a total of 2 courses to take. To take course 1 you
     * should have finished course 0. So the correct course order is [0,1].
     * Example 2:
     * 
     * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * Output: [0,2,1,3]
     * Explanation: There are a total of 4 courses to take. To take course 3 you
     * should have finished both courses 1 and 2. Both courses 1 and 2 should be
     * taken after you finished course 0.
     * So one correct course order is [0,1,2,3]. Another correct ordering is
     * [0,2,1,3].
     * Example 3:
     * 
     * Input: numCourses = 1, prerequisites = []
     * Output: [0]
     * 
     * 
     * Constraints:
     * 
     * 1 <= numCourses <= 2000
     * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * ai != bi
     * All the pairs [ai, bi] are distinct.
     */
    // ==========+Solution ==============
    // since there is change in order , while forming a graph change the order
    // create topo sort array
    // once done , check if size of toposort array == verices , then return the topo
    // sort
    // else return and empty array ;
    public static int[] courseOrdering(int numCourses, int[][] prerequisites) {
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
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (Integer ad : graph.get(i)) {
                indegree[ad]++;
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] topo = new int[numCourses];
        int i = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek();
            topo[i++] = node;
            queue.remove();
            for (Integer ad : graph.get(node)) {
                indegree[ad]--;
                if (indegree[ad] == 0) {
                    queue.add(ad);
                }
            }
        }
        if (i == numCourses)
            return topo;
        return new int[] {};

    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = { { 1, 0 } };
        System.out.println("========= course schedule 1===========");
        System.out.println(canFinish(numCourses, prerequisites));
        numCourses = 2;
        int[][] prerequisites1 = { { 1, 0 }, { 0, 1 } };
        System.out.println(canFinish(numCourses, prerequisites1));

        System.out.println("========= course schedule 2===========");
        int result[] = courseOrdering(numCourses, prerequisites);
        for (int i : result)
            System.out.print(i + ",");
        numCourses = 4;
        System.out.println();
        int[][] prerequisites2 = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        result = courseOrdering(numCourses, prerequisites2);
        for (int i : result)
            System.out.print(i + ",");
    }

}
