/**
* 浮点数相关基础知识演示
*
* @author yangyong
* @version 1.0
* date 2018/02/13
*/
public class FloatDemo {
	public static void main(String[] args) {
		float pi = 3.1415926F;
		// 输出pi的值，看看是不是你想要的
		System.out.println(pi);

		// 浮点运算溢出和出错
		System.out.println(8/0.0); // 正无穷大
		System.out.println(-8/0.0); // 负无穷大
		System.out.println(0.0/0.0); // 非数，NaN

		/*
			整数除0异常
			java.lang.ArithmeticException: / by zero
		 */
		System.out.println(8/0);
		System.out.println(-8/0);
		System.out.println(0/0);
	}
}