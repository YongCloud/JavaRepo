package com.ivysoft.java.collection;

/**
 * Circular Queue implementation.
 * 
 * @author Xingjian
 * @since 2019/09/20
 * @param <T> element type
 */
public class CircularQueue<T> implements Queue<T> {
	/**
	 * element stored in this array.
	 */
	private T elements[];

	/***
	 * front pointer.
	 */
	private int front;

	/**
	 * tail pointer.
	 */
	private int tail;

	/**
	 * construct a Circular Queue with given length.
	 * 
	 * @param length
	 */
	@SuppressWarnings("unchecked")
	public CircularQueue(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("length must be a positive integer.");
		}
		this.elements = (T[]) new Object[length];
		this.front = 0;
		this.tail = 0;
	}

	@Override
	public boolean enQueue(T element) {
		if ((tail + 1) % elements.length == front) {
			// there is no space to add element.
			return false;
		}
		elements[tail] = element;
		tail = (tail + 1) % elements.length;
		return true;
	}

	@Override
	public T deQueue() {
		if (isEmpty()) {
			return null;
		}
		T e = elements[front];
		front = (front + 1) % elements.length;
		return e;
	}

	@Override
	public boolean isEmpty() {
		return front == tail;
	}

	@Override
	public int size() {
		return (tail - front + elements.length) % elements.length;
	}
}
