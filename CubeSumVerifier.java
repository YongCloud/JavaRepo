/**
 * ��Ȼ��������������̽��
 * 
 * @author YangYong 2018/03/12
 * @version 1.0.0
 */
public class CubeSumVerifier {
	public static void main(String[] args) {
		check();
		findMore();
//		System.out.println(verify(11, 109, 330));
	}

	/**
	 * ��֤��Ŀ�и�����������ʽ����
	 */
	public static void check() {
		if (verify(3, 5, 6)) {
			System.out.println("3^3+4^3+5^3=6^3");
		} else {
			System.out.println("3^3+4^3+5^3!=6^3");
		}

		if (verify(6, 69, 180)) {
			System.out.println("6^3+7^3+...+69^3=180^3");
		} else {
			System.out.println("6^3+7^3+...+69^3!=180^3");
		}
	}

	/**
	 * ��һ������������
	 * 
	 * @return һ������������
	 */
	public static int cube(int a) {
		return a * a * a;
	}

	/**
	 * ����ָ������[low,up]�ڵ�������Ȼ���������Ƿ��������һ����Ȼ��������
	 * 
	 * @param low �����½磨inclusive��
	 * @param up �����Ͻ��ޣ�inclusive��
	 * @param another ����һ����Ȼ��
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean verify(int low, int up, int another) {
		int sum = 0;
		for (int i = low; i <= up; i++) {
			sum += cube(i);
		}

		if (sum == cube(another)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Ѱ�Ҹ�������˹��ɵ���Ȼ����
	 * �㷨ʱ�临�Ӷ�ΪO��n^2���� nΪѰ�ҵ���Ȼ������
	 */
	public static void findMore() {
		/**
		 * Ѱ�ҵ���Ȼ�����ޣ��������ֵ�����η���int���;ʹ治����
		 */
		int max = (int) Math.cbrt(Integer.MAX_VALUE);
		System.out.println("Ѱ�ҵ���Ȼ������Ϊ" + max);
		// ���������Ȼ���������ͣ��������鱣������
		int[] cubeSumArr = new int[max];
		for (int i = 1; i <= max; i++) {
			cubeSumArr[i - 1] = cube(i);
		}

		for (int i = 0; i < cubeSumArr.length - 1; i++) {
			long sum = cubeSumArr[i];
			for (int j = i + 1; j < cubeSumArr.length - 1; j++) {
				sum += cubeSumArr[j];
				double root = Math.cbrt(sum);
				if (Math.abs(root - (int) root) <= 0.00001 && sum == cube((int) root)) {
					System.out.println((i + 1) + "^3+...+" + (j + 1) + "^3=" + (int) root + "^3");
				}
			}
		}
	}
}
