import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Java类加载器演示 相关类java.lang.Class,java.lang.Thread,
 * java.lang.ClassLoader,java.net.URLClassLoader, sun.misc.Launcher
 * 
 * @author YangYong
 * @version 1.0.0
 */
public class ClassLoaderDemo {
	public static void main(String[] args) throws IOException {
		// 获取系统类加载器
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println("System Class Loader:" + systemClassLoader);

		/*
		 * 获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定 如果操作系统没有指定CLASSPATH环境变量，则默认以当前路径作为
		 * 系统类加载器的加载路径
		 */
		Enumeration<URL> eml = systemClassLoader.getResources("");
		while (eml.hasMoreElements()) {
			System.out.println(eml.nextElement());
		}

		// 应用类加载器的加载路径
		System.out.println("App Class Path:" + System.getProperty("java.class.path"));

		// 获取系统类加载器的父类加载器，得到扩展类加载器
		ClassLoader extendClassLoader = systemClassLoader.getParent();
		System.out.println("Extension Class Loader:" + extendClassLoader);
		System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));

		System.out.println("Bootstrap Class Loader:" + extendClassLoader.getParent());
		System.out.println("根类加载器的加载路径：" + System.getProperty("sun.boot.class.path"));

		// 查看当前类是由哪个类加载器加载的
		Class<?> cs = ClassLoaderDemo.class;
		ClassLoader cl = cs.getClassLoader();
		System.out.println("当前类的类加载器：" + cl);

		// 查看当前线程的上下文类加载器
		System.out.println("当前线程的上下文类加载器:" + Thread.currentThread().getContextClassLoader());
	}
}
