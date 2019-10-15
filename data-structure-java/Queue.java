package com.ivysoft.java.collection;

/**
 * A First In Fist Out (FIFO) data structure.
 * 
 * @author Xingjian
 * @since 2019/09/20
 * @param <T> element type
 */
public interface Queue<T> {
	/**
	 * add element to Queue.
	 * 
	 * @param element
	 * @return true, element added successfully. Otherwise,false.
	 */
	boolean enQueue(T element);

	/**
	 * get element from Queue.
	 * 
	 * @return element, removed from Queue.Or null if Queue is empty.
	 */
	T deQueue();

	/**
	 * test whether this Queue is empty.
	 * 
	 * @return true, if Queue is empty. Otherwise,false.
	 */
	boolean isEmpty();

	/**
	 * size of queue.
	 * 
	 * @return size of queue.
	 */
	int size();
}
