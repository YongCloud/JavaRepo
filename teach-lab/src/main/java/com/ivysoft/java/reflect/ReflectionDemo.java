package com.ivysoft.java.reflect;

import com.ivysoft.java.io.Student;

public class ReflectionDemo {
	/**
	 * Process 进程<br>
	 * 反射 Reflect，来源于物理学光学<br>
	 * 核心就是Class<T>
	 */
	public static void main(String[] args) {
		if (null == args || args.length < 1) {
			return;
		}
		try {
			/**
			 * step1：获取Class对象的三种方法
			 */
			// 第一种方法
			Class<?> clazz = Class.forName(args[0]);
			System.out.println(clazz);

			// 第二种方法
			clazz = String.class;
			System.out.println(clazz);

			// 第三种
			Student stu = new Student(19213211L, "Jack");
			clazz = stu.getClass();
			System.out.println(clazz);

			/**
			 * Step2：分析clazz对象
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
