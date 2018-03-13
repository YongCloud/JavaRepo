/**
 * 自然数的立方和性质探讨
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
	 * 验证题目中给出的两个等式成立
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
	 * 求一个整数的立方
	 * 
	 * @return 一个整数的立方
	 */
	public static int cube(int a) {
		return a * a * a;
	}

	/**
	 * 检验指定区间[low,up]内的连续自然数立方和是否等于另外一个自然数的立方
	 * 
	 * @param low 区间下界（inclusive）
	 * @param up 区间上界限（inclusive）
	 * @param another 另外一个自然数
	 * @return 相等返回true，否则返回false
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
	 * 寻找更多满足此规律的自然数。
	 * 算法时间复杂度为O（n^2）， n为寻找的自然数上限
	 */
	public static void findMore() {
		/**
		 * 寻找的自然数上限，超过这个值，三次方后，int类型就存不下了
		 */
		int max = (int) Math.cbrt(Integer.MAX_VALUE);
		System.out.println("寻找的自然数上限为" + max);
		// 先求各个自然数的立方和，并用数组保存起来
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
