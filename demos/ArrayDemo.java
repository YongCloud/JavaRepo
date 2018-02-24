/**
* Java 数组示例
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class ArrayDemo {
	public static void main(String[] args) {
		/*
			什么是数组？
			程序里面的数组=数学里面的集合
			就是相同类型的一组数
		*/
		// 数组的定义
		int[] arr;
		// 分配存储空间
		arr = new int[10];
		// int[] arr = new int[10];

		// 给数组元素赋值
		for(int i = 0;i<arr.length;i++){
			arr[i] = i+1;
		}

		// 遍历数组元素
		for(int i = 0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
}