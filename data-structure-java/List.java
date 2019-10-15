
/**
 * A abstract interface of List data structure.
 * 
 * @param <T> type of value
 * @author Xingjian
 * @version 2019/10/12
 */
public interface List<T> {
    // add operations
    /**
     * add element to the tail of list.
     * 
     * @param e
     * @return true if OK, false otherwise
     */
    boolean add(T e);

    /**
     * insert element at given position.
     * 
     * @param e
     * @param index
     * @return true if OK, false otherwise
     */
    boolean insert(T e, int index);

    // get operation
    /**
     * get element by index.
     * 
     * @param index
     * @return element indicated by index
     */
    T get(int index);

    // remove operation
    /**
     * remove element at given position.
     * 
     * @param index
     * @return element removed
     */
    T remove(int index);

    // other operations
    /**
     * get size of list.
     * 
     * @return number of element in the list
     */
    int size();

    /**
     * test if this list is empty.
     * 
     * @return true if empty, false otherwise
     */
    boolean isEmpty();
}
