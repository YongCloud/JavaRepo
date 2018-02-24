import java.util.Scanner;
/**
* for循环结构演示
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class ForLoopDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
			Java循环有三种
			for(expression;condition;expression) {
	
			}

			while(condition){
	
			}

			do {
	
			} while(condition);
		*/
		int sum = 0;
		for (int i=0 ; i<10 ; i++) {
			System.out.println("请输入一个整数：");
			int input = sc.nextInt();
			sum = sum + input;
		}
		System.out.println("输入的整数和："+sum);

		// 关闭输入流
		sc.close();
	}
}