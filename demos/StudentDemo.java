/**
* 使用Student类
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class StudentDemo {
	public static void main(String[] args) {
		Student stu = new Student("Tom",22,"NanJing");
		System.out.println("name:"+stu.getName());
		System.out.println("age:"+stu.getAge());
		System.out.println("address:"+stu.getAddress());
	}
}