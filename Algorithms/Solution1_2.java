/*
	created by yangyong,Oct 12,2016
	function：输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
*/
// 使用Java内置的toBinaryString实现
public class Solution1_2{
    public int NumberOf1(int n) {
		String temp = Integer.toBinaryString(n);
        int count = 0;
        for(int i=0;i<temp.length();i++){
            if('1' == temp.charAt(i)){
                count++;
            }
        }
        return count;
    }
}