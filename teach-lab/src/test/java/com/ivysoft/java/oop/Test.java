package com.ivysoft.java.oop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
//		String str1 = null;
//		String str2 = "abc";
//		if (str1 == str2) {
//			System.out.println("XXX");
//		}
//		if (null != str1 && str1.equals(str2)) {
//			System.out.println("YYY");
//		}
//		
//		Base base = new SubClass("Hello");
//		base.say();
//		listIterator();
		removeSame();
	}

	public int max(int a, int b) {
//		if(a<=b) {
//			return b;
//		}else {
//			return a;
//		}
		return a >= b ? a : b;
	}
	
	public static void listIterator() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//			list.remove(i);
//		}
		System.out.println(list);
		
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
			iter.remove();
		}
		System.out.println(list);
	}
	
	public static void removeSame() {
		List<Integer> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			list.add(random.nextInt(50));
		}
		System.out.println(list);
		
		Set<Integer> set = new HashSet<>();
		for(int i : list) {
			set.add(i);
		}
		System.out.println(set);
	}
}
