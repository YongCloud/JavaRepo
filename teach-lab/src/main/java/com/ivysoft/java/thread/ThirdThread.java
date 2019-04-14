package com.ivysoft.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过实现Callable<V>接口创建线程
 * 
 * @author Xingjian
 * @see java.util.concurrent.Callable
 * @see java.util.concurrent.Future
 * @see java.util.concurrent.FutureTask
 * @since 2019/04/09
 */
public class ThirdThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + " add " + i);
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (5 == i) {
				// 创建现场
				Callable<Integer> callable = new ThirdThread();
				FutureTask<Integer> futureTask = new FutureTask<>(callable);
				Thread thread = new Thread(futureTask);
				// 启动现场
				thread.start();
				// 获取现场返回值，主线程将阻塞直到子线程返回结果
				System.out.println(futureTask.get());

				// 使用lambda表达式创建线程
				createThread();
			}
		}
	}

	/**
	 * Java8以后，可以使用lambda表达式创建线程
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void createThread() throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + " add " + i);
				sum += i;
			}
			return sum;
		});
		new Thread(futureTask).start();
		System.out.println(futureTask.get());
	}
}
