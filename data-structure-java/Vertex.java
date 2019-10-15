/**
 * A vertex in the graph.
 * 
 * @param <T> type of vertex value
 * @param <A> type of arc value
 * @author Xingjian
 * @version 2019/01/09
 */
public class Vertex<T, A> {
    /**
     * vertex value
     */
    private T value;

    /**
     * pointer which points to the first arc that adjacent to this vertex
     */
    private Arc<A> firstArc;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Arc<A> getFirstArc() {
        return firstArc;
    }

    public void setFirstArc(Arc<A> firstArc) {
        this.firstArc = firstArc;
    }
}
