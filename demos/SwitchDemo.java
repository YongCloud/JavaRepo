import java.util.Scanner;
/**
* switch选择结构演示
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class SwitchDemo {
	public static void main(String[] args) {
		System.out.println("你想去哪个国家旅游：");
		System.out.println("---------------------------");
		System.out.println("1 美国");
		System.out.println("2 中国");
		System.out.println("3 法国");
		System.out.println("4 澳大利亚");
		System.out.println("5 英国");
		System.out.println("---------------------------");
		System.out.println("请选择：");
		
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		switch (input) {
			case 1:System.out.println("你想去美国");break;
			case 2:System.out.println("你想去中国");break;
			case 3:System.out.println("你想去法国");break;
			case 4:System.out.println("你想去澳大利亚");break;
			case 5:System.out.println("你想去英国");break;
			default:System.out.println("我们目前不支持你的输入"+input);
		}
	}
}