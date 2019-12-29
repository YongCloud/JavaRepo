/**
 * method to skip dead loop.
 *
 * @author xingjian
 * @since 2016/11/16
 */
public class SkipDeadLoop {
	public static void main(String[] args) {
		method1();
		method2();
		method3();
	}
	
	/**
	 * use return to skip dead loop
	 */
	public static void method1(){
		int i = 0;
		while(true){
			for(i=0;i<7;i++){
				System.out.println("loop1:"+i);
			}
			if(7==i){
				return;
			}
		}
	}
	
	/**
	 * use break to skip dead loop
	 */
	public static void method2(){
		int i = 0;
		while(true){
			for(i=0;i<7;i++){
				System.out.println("loop2:"+i);
			}
			if(7==i){
				break;
			}
		}
	}
	
	/**
	 * use System.exit(0) method to terminate
	 * the currently running Java Virtual Machine
	 */
	public static void method3(){
		int i = 0;
		while(true){
			for(i=0;i<7;i++){
				System.out.println("loop3:"+i);
			}
			if(7==i){
				System.exit(0);;
			}
		}
	}
}
