/**
 * 自然数的立方和性质探讨
 * 
 * @author xingjian
 * @version 2018/03/12
 */
public class CubeSumVerifier {
	public static void main(String[] args) {
		check();
		findMore();
//		test(406,917,5544);
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
	 * 寻找更多满足此规律的自然数。 算法时间复杂度为O（n^2）， n为寻找的自然数上限
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
			for (int j = i + 1; j < cubeSumArr.length; j++) {
				sum += cubeSumArr[j];
				double root = Math.cbrt(sum);
				int temp = (int) root;
				if (Double.compare(root, temp) == 0) {
					System.out.println((i + 1) + "^3+...+" + (j + 1) + "^3=" + (int) root + "^3");
				}
			}
		}
	}
	
	/**
	 * 测试发现的等式是否成立
	 * @param low 区间下限
	 * @param up 区间上限
	 * @param another
	 */
	public static void test(int low, int up, int another) {
		long left = 0L;
		for (int i = low; i <= up; i++) {
			left += i * i * i;
		}
		long right = (long) another * another * another;
		System.out.println("left:" + left);
		System.out.println("right:" + right);
		System.out.println("left=right:" + (left == right));
	}
}
