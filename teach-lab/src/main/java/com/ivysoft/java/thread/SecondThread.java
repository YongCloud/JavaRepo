package com.ivysoft.java.thread;

/**
 * 通过实现Runnable接口创建线程
 * 
 * @author Xingjian
 * @see java.lang.Runnable
 * @since 2019/04/09
 */
public class SecondThread implements Runnable {

	/**
	 * 实现run方法，run方法就是线程体
	 */
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (5 == i) {
				// 创建现场
				Runnable target = new SecondThread();
				Thread thread = new Thread(target);
				// 启动现场
				thread.start();

				// 使用lambda表达式创建线程
				createThread();
			}
		}
	}

	/**
	 * Java8以后，可以使用lambda表达式创建线程
	 */
	public static void createThread() {
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
		}).start();
	}
}
