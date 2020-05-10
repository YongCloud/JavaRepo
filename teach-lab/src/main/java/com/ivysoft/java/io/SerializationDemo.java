package com.ivysoft.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationDemo {
	/**
	 * Process 进程<br>
	 * 序列化就是把Java进程中对象数据进行【编码 encode】为平台无关的二进制流，以便持久化到磁盘，或者网络传输<br>
	 * 反序列化就是加载序列化后数据，【解码 decode】出原来的数据
	 */
	public static void main(String[] args) {
		Student tom = new Student(19213210L, "Tom");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tom.txt", false))) {
			oos.writeObject(tom);
			System.out.println("序列化完成");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tom.txt"))) {
			Object obj = ois.readObject();
			if (obj instanceof Student) {
				Student stu = (Student) obj;
				System.out.println(stu.getId() + " " + stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
