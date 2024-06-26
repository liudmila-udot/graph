import java.util.*;

/**
 * We can do DSF of BSF from each key in adjacency list and store visited into shared set:
 * when adjacency list key is not visited yet - we increase number of connected components by one.
 */
public class NumberOfConnectedComponents {

    public int numberOfConnectedComponentsBfs(Map<Integer, List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        int ret = 0;
        for (Integer key : adj.keySet()) {
            if (!visited.contains(key)) {
                ret++;
            }
            bfs(adj, key, visited);
        }
        return ret;
    }

    public void bfs(Map<Integer, List<Integer>> adj, int startVertex, Set<Integer> visited) {
        LinkedList<Integer> queue = new LinkedList<>(); // Queue for BFS

        visited.add(startVertex); // Mark the start vertex as visited
        queue.add(startVertex); // Enqueue the start vertex

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll(); // Dequeue a vertex

            // Visit all adjacent vertices of the dequeued vertex
            for (int neighbor : adj.getOrDefault(currentVertex, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public int numberOfConnectedComponentsDfs(Map<Integer, List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        int ret = 0;
        for (Integer key : adj.keySet()) {
            if (!visited.contains(key)) {
                ret++;
            }
            dfs(adj, key, visited);
        }
        return ret;
    }

    public void dfs(Map<Integer, List<Integer>> adjList, int startVertex, Set<Integer> visited) {
        Stack<Integer> stack = new Stack<>(); // Stack for DFS

        stack.push(startVertex); // Push the start vertex onto the stack

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop(); // Pop a vertex from the stack

            // Visit the current vertex if it has not been visited before
            if (!visited.contains(currentVertex)) {
                visited.add(currentVertex);

                // Push all adjacent vertices of the current vertex onto the stack
                for (int neighbor : adjList.getOrDefault(currentVertex, Collections.emptyList())) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    // number of provinces test method
    // https://leetcode.com/problems/number-of-provinces
    public int findCircleNum(int[][] isConnected) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i <= isConnected.length - 1; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int i = 0; i <= isConnected.length - 1; i++) {
            for (int j = 0; j <= isConnected.length - 1; j++) {
                if (isConnected[i][j] == 1) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        // return numberOfConnectedComponentsBfs(adj);
        return numberOfConnectedComponentsDfs(adj);
    }
}
