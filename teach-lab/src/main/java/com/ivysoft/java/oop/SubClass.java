package com.ivysoft.java.oop;

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
