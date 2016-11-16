/*
	created by yangyong,Oct 12,2016
	function：输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
*/
public class Solution1_1{
	public int NumberOf1(int n){
		int count = 0;
		while(n!=0){
			count++;
			// 每次把最右边的1去掉
			n = n&(n-1); 
		}
		return count;
	}
}