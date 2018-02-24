import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Java���������ʾ �����java.lang.Class,java.lang.Thread,
 * java.lang.ClassLoader,java.net.URLClassLoader, sun.misc.Launcher
 * 
 * @author YangYong
 * @version 1.0.0
 */
public class ClassLoaderDemo {
	public static void main(String[] args) throws IOException {
		// ��ȡϵͳ�������
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println("System Class Loader:" + systemClassLoader);

		/*
		 * ��ȡϵͳ��������ļ���·������ͨ����CLASSPATH��������ָ�� �������ϵͳû��ָ��CLASSPATH������������Ĭ���Ե�ǰ·����Ϊ
		 * ϵͳ��������ļ���·��
		 */
		Enumeration<URL> eml = systemClassLoader.getResources("");
		while (eml.hasMoreElements()) {
			System.out.println(eml.nextElement());
		}

		// Ӧ����������ļ���·��
		System.out.println("App Class Path:" + System.getProperty("java.class.path"));

		// ��ȡϵͳ��������ĸ�����������õ���չ�������
		ClassLoader extendClassLoader = systemClassLoader.getParent();
		System.out.println("Extension Class Loader:" + extendClassLoader);
		System.out.println("��չ��������ļ���·����" + System.getProperty("java.ext.dirs"));

		System.out.println("Bootstrap Class Loader:" + extendClassLoader.getParent());
		System.out.println("����������ļ���·����" + System.getProperty("sun.boot.class.path"));

		// �鿴��ǰ�������ĸ�����������ص�
		Class<?> cs = ClassLoaderDemo.class;
		ClassLoader cl = cs.getClassLoader();
		System.out.println("��ǰ������������" + cl);

		// �鿴��ǰ�̵߳��������������
		System.out.println("��ǰ�̵߳��������������:" + Thread.currentThread().getContextClassLoader());
	}
}
