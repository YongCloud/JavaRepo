package com.ivysoft.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池示例
 * 
 * @author xingjiang
 * @see java.util.concurrent.ThreadPoolExecutor
 * @see java.util.concurrent.Executors
 * @since 2019/04/10
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		// 创建一个大小为5的线程池
//		ExecutorService es = Executors.newFixedThreadPool(5);
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ExecutorService es = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(16), threadFactory);
		// 提交10个线程
		for (int i = 0; i < 10; i++) {
			es.submit(() -> {
				for (int j = 0; j < 30; j++) {
					System.out.println(Thread.currentThread().getName() + " " + j);
				}
			});
		}
		// 关闭线程池
		es.shutdown();
	}
}
