package com.ivysoft.java.oop;

/**
 * 子类
 * 
 * @author xingjian
 * @since 2019/04/07
 */
public class SubClass extends Base {
	private String message;

	public SubClass(String message) {
		this.message = message;
	}

	@Override
	public void say() {
		super.say();
		System.out.println("SubClass say " + message);
	}

	@Override
	public void hello() {
		System.out.println("SubClass say hello");
	}
}
