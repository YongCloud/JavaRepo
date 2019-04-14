package com.ivysoft.java.thread;

/**
 * 通过继承Thread类创建线程
 * 
 * @author Xingjian
 * @see java.lang.Thread
 * @since 2019/04/09
 */
public class FirstThread extends Thread {
	/**
	 * 重写run方法，run方法就是线程体
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
				FirstThread thread = new FirstThread();
				// 启动现场
				thread.start();
			}
		}
	}
}
