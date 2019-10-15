/**
 * A arc in the graph.
 * 
 * @param <T> type of arc value
 * @author Xingjian
 * @version 2019/01/09
 */
public class Arc<T> {
    /**
     * vertex index in adjacency table
     */
    private int adjacentVertex;

    /**
     * pointer which points to next arc
     */
    private Arc<T> nextArc;

    /**
     * value in the arc
     */
    private T value;

    public int getAdjacentVertex() {
        return adjacentVertex;
    }

    public void setAdjacentVertex(int adjacentVertex) {
        this.adjacentVertex = adjacentVertex;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Arc<T> getNextArc() {
        return nextArc;
    }

    public void setNextArc(Arc<T> nextArc) {
        this.nextArc = nextArc;
    }
}
