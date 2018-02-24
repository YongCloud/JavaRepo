/**
* 定义Student类
*
* @author yangyong
* @version 1.0
* date 2018/02/20
*/
public class Student {
	/**
	* 学生姓名
	*/
	private String name;

	/**
	* 学生年龄
	*/
	private int age;

	/**
	* 学生居住地址
	*/
	private String address;

	/**
	* 不带参数的构造函数
	*/
	public Student(){

	}


	/**
	* 带参数的构造函数
	*/
	public Student(String name,int age,String address){
		this.name = name;
		this.age = age;
		this.address = address;
	}

	/**
	* 设置学生的姓名
	*/
	public void setName(String name){
		this.name = name;
	}

	/**
	* 设置学生的年龄
	*/
	public void setAge(int age){
		this.age = age;
	}

	/**
	* 设置学生的地址
	*/
	public void setAddress(String address){
		this.address = address;
	}
	/**
	* 获取学生的姓名
	*/
	public String getName(){
		return name;
	}

	/**
	* 获取学生的年龄
	*/
	public int getAge(){
		return age;
	}

	/**
	* 获取学生的地址
	*/
	public String getAddress(){
		return address;
	}
}