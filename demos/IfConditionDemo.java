import java.util.Scanner;
/**
* if选择结构演示
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class IfConditionDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个整数：");
		int input = sc.nextInt();
		// 关闭输入流
		sc.close();

		/*
			Java if结构的三种形式
			第一种
			if () {
	
			}
			第二种
			if () {
	
			} else {
	
			}

			第三种
			if () {
	
			} else if () {
	
			} else if () {
	
			} else {
	
			}
		*/
		if (input > 0) {
			System.out.println(input+"是正数");
		} else if (input == 0){
			System.out.println(input+"既不是正数也不是负数");
		} else {
			System.out.println(input+"是负数");
		}

		if (input % 2 == 0) {
			System.out.println(input+"是偶数");
		}else {
			System.out.println(input+"是奇数");
		}
	}
}