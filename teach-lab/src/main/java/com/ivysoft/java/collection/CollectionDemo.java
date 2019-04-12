package com.ivysoft.java.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 集合示例
 * 
 * @author xingjiang
 * @since 2019/04/07
 */
public class CollectionDemo {
	/**
	 * cannot new instance
	 */
	private CollectionDemo() {
	}

	/**
	 * 生成一个列表
	 * 
	 * @return List<Integer>
	 */
	public static List<Integer> getList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		return list;
	}

	/**
	 * 错误的遍历，会漏掉元素
	 * 
	 * @param list required not null
	 */
	public static void traverse(List<Integer> list) {
		// 采用这种方法遍历，在遍历集合的过程中移除元素会有坑
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			list.remove(i);
		}
	}

	/**
	 * 正确的遍历方法
	 * 
	 * @param list required not null
	 */
	public static void traversalByIterator(List<Integer> list) {
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
			iter.remove();
		}
	}

	/**
	 * 利用Set中元素不可重复特性对集合中的元素去重
	 * 
	 * @param list required not null
	 */
	public static Set<Integer> removeDuplicate(List<Integer> list) {
		Set<Integer> set = new HashSet<>();
		for (Integer e : list) {
			set.add(e);
		}
		return set;
	}

	public static void main(String[] args) {
		List<Integer> list = getList();
		System.out.println("错误遍历的结果：");
		traverse(list);
		list = getList();
		System.out.println("正确遍历的结果：");
		traversalByIterator(list);

		// 清空
		list.clear();
		// 造数据
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(3));
		}
		// 测试
		System.out.println("原集合：");
		System.out.println(list);
		System.out.println("去重后的集合：");
		System.out.println(removeDuplicate(list));
	}
}
