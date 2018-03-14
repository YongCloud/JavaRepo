/**
 * 素数工具类
 * 
 * @author YangYong 2018年3月12日下午4:38:58
 * @version 1.0.0
 */
public class PrimeNumberUtil {
	public static void main(String[] args) {
		erwinSieve(100);
	}

	/**
	 * 使用“埃氏筛法”求区间[2,up]内的素数
	 * 
	 * @param up 区间上限（inclusive）
	 */
	public static void erwinSieve(int up) {
		if (up < 2) {
			return;
		}
		
		int[] arr = new int[up - 2 + 1];
		for (int i = 2; i <= up; i++) {
			arr[i - 2] = i;
		}
		
		// 统计素数的个数
		int num = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				num++;
				System.out.print(arr[i] + " ");
				// 没行输出7个数
				if (num % 7 == 0) {
					System.out.println();
				}
				
				// 则当前素数的倍数全部不是素数
				for (int j = 2;; j++) {
					int temp = arr[i] * j;
					if (temp <= up) {
						// 赋值为0
						arr[temp - 2] = 0;
					} else {
						break;
					}
				}
			}
		}
		
		System.out.println();
		System.out.println("total:"+num);
	}
}
