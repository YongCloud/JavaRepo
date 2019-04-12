package com.ivysoft.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池示例
 * 
 * @author xingjiang
 * @since 2019/04/10
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		// 创建一个大小为5的线程池
		ExecutorService es = Executors.newFixedThreadPool(5);
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
