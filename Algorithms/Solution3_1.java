/*
	created by yangyong,Oct 12,2016
	function：变态跳台阶问题
*/
// 递归法实现
public class Solution3_1 {
    public int JumpFloorII(int target) {
        if(0>target){
            return 0;
        }else if(1== target){
            return 1;
        }else{
            return 2*JumpFloorII(target-1);
        }
    }
}