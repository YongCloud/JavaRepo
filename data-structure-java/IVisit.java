
/**
 * A interface encapsulates only one method(visit).
 * 
 * @param <T> type of value
 * @author Xingjian
 * @version 2019/01/09
 */
@FunctionalInterface
public interface IVisit<T> {
    /**
     * to do something with value.
     * 
     * @param value
     */
    void visit(T value);
}
