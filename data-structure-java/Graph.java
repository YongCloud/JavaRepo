import java.util.ArrayList;
import java.util.List;

/**
 * Store Graph information with adjacency table and implement some algorithm.
 * 
 * @param <T> type of vertex value
 * @param <A> type of arc value
 * @author Xingjian
 * @version 2019/01/09
 */
public class Graph<T, A> {
    /**
     * default size of adjacency table.
     */
    private final int DEFAULT_TABLE_SIZE = 16;

    private List<Vertex<T, A>> adjTable;

    /**
     * initialize adjacency table with default size
     */
    public Graph() {
        this.adjTable = new ArrayList<>(DEFAULT_TABLE_SIZE);
    }

    /**
     * initialize adjacency table with tableSize
     * 
     * @param tableSize
     * @throws IllegalArgumentException if tableSize <= 0
     */
    public Graph(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("tableSize must be positive, but given is " + tableSize);
        }
        this.adjTable = new ArrayList<>(tableSize);
    }

    /**
     * traverse the Graph with DFS(Depth First Search).<br>
     * suppose that the number of vertex is n, and the number of arc is e. So:<br>
     * Time Complexity: O(n+e), Space Complexity: O(n)
     * 
     * @param iv
     */
    public void traverseDFS(IVisit<T> iv) {
        // vertex visited flag
        boolean[] visited = new boolean[adjTable.size()];
        for (int i = 0; i < adjTable.size(); i++) {
            visited[i] = false;
        }
        for (int i = 0; i < adjTable.size(); i++) {
            if (!visited[i]) {
                dfs(adjTable.get(i), visited, i, iv);
            }
        }
    }

    /**
     * traverse the vertex with DFS recursively.
     * 
     * @param vertex
     * @param visited
     * @param i
     * @param iv
     */
    private void dfs(Vertex<T, A> vertex, boolean[] visited, int i, IVisit<T> iv) {
        iv.visit(vertex.getValue());
        visited[i] = true;
        for (Arc<A> arc = vertex.getFirstArc(); arc != null; arc = arc.getNextArc()) {
            int j = arc.getAdjacentVertex();
            if (!visited[j]) {
                dfs(adjTable.get(j), visited, j, iv);
            }
        }
    }

    /**
     * traverse the Graph with BFS(Breadth First Search).<br>
     * suppose that the number of vertex is n, and the number of arc is e. So:<br>
     * Time Complexity: O(n+e), Space Complexity: O(n)
     * 
     * @param iv
     */
    public void traverseBFS(IVisit<T> iv) {
        // vertex visited flag
        boolean[] visited = new boolean[adjTable.size()];
        for (int i = 0; i < adjTable.size(); i++) {
            visited[i] = false;
        }
        // we need a queue.
        List<Vertex<T, A>> queue = new ArrayList<>(16);
        for (int i = 0; i < adjTable.size(); i++) {
            if (!visited[i]) {
                Vertex<T, A> vertex = adjTable.get(i);
                iv.visit(vertex.getValue());
                visited[i] = true;
                queue.add(vertex);
                while (null != vertex) {
                    for (Arc<A> arc = vertex.getFirstArc(); arc != null; arc = arc.getNextArc()) {
                        int j = arc.getAdjacentVertex();
                        if (!visited[j]) {
                            iv.visit(adjTable.get(j).getValue());
                            visited[j] = true;
                            queue.add(adjTable.get(j));
                        }
                    }
                    if (!queue.isEmpty()) {
                        vertex = queue.remove(0);
                    }
                }
            }
        }
    }
}
