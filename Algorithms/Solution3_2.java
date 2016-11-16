/*
	created by yangyong,Oct 12,2016
	function：变态跳台阶问题
*/
// 迭代方法实现
public class Solution3_2 {
    public int JumpFloorII(int target) {
        if(0>target){
            return 0;
        }else if(1== target){
            return 1;
        }
        int before = 1;
        int now = 0;
        for(int i=1;i<target;i++){
            now = 2*before;
            before = now;
        }
        return now;
    }
}