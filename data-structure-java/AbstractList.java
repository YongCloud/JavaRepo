/**
 * Supplying basic implementation of List, to minimize implementation of List.
 * 
 * @param <T> type of value
 * @author Xingjian
 * @version 2019/10/12
 */
public abstract class AbstractList<T> implements List<T> {
    public boolean isEmpty() {
        return size() == 0;
    }
}
