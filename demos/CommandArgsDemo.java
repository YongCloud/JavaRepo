/**
* 命令行参数基础知识演示
*
* @author yangyong
* @version 1.0
* date 2018/02/13
*/
public class CommandArgsDemo {
	public static void main(String[] args) {
		if (null != args && args.length>0) {
			System.out.println("Hello "+args[0]);
		} else {
			System.out.println("Hello World");	
		}
	}
}