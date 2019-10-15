
/**
 * List implementation, element stored in dynamic array.
 * 
 * @param <T> type of value
 * @author Xingjian
 * @version 2019/10/12
 */
public class ArrayListImpl<T> extends AbstractList<T> {
    private T[] elements;

    private int size;

    @Override
    public boolean add(T e) {
        return false;
    }

    @Override
    public boolean insert(T e, int index) {
        checkRange(index);
        return false;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return null;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void checkRange(int index) {
        if (0 < index || index >= elements.length) {
            throw new IllegalArgumentException("index out of range, index=" + index);
        }
    }
}
