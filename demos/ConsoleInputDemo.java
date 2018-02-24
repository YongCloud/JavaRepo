import java.util.Scanner;
/**
* 控制台输入相关基础知识演示
*
* @author yangyong
* @version 1.0
* date 2018/02/13
*/
public class ConsoleInputDemo {
	public static void main(String[] args) {
		// 获取控制台输入对象
		Scanner sc = new Scanner(System.in);

		// 输入一个字符串
		System.out.println("请输入一个字符串:");
		String str = sc.nextLine();
		System.out.println(str);

		// 输入一个整数
		System.out.println("请输入一个整数:");
		int a = sc.nextInt();
		System.out.println(a);

		// 输入一个小数
		System.out.println("请输入一个小数:");
		double d = sc.nextDouble();
		System.out.println(d);

		// 关闭
		sc.close();

		System.out.println("Good bye.");
	}
}