package com.ivysoft.java.collection;

public class CircularQueueTest {
	public static void main(String[] args) {
		Queue<Integer> queue = new CircularQueue<>(5);
		System.out.println(queue.isEmpty());
		System.out.println(queue.size());
		System.out.println("---------------");

		for (int i = 0; i < 5; i++) {
			System.out.println(queue.enQueue(i));
		}

		System.out.println("---------------");
		System.out.println(queue.size());
		System.out.println(queue.deQueue());
		System.out.println(queue.enQueue(5));

		System.out.println("---------------");
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());

		System.out.println("---------------");
		for (int i = 0; i < 5; i++) {
			System.out.println(queue.deQueue());
		}

		System.out.println("---------------");
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
	}
}
