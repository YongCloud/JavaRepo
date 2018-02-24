import java.util.Scanner;
/**
* while循环结构演示
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class WhileLoopDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int sum = 0;
		int i = 0;
		while (i<10) {
			System.out.println("请输入一个整数：");
			int input = sc.nextInt();
			sum = sum + input;

			i++;
		}
		System.out.println("输入的整数和："+sum);

		// 关闭输入流
		sc.close();
	}
}