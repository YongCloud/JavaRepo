package com.njau.src;
/**
 * 
 * @author YangYong
 *
 */
import java.util.Random;

public class Pork52 {
	private String[] nums = null;
	private String[] colors =null;
	private String[] pork52 = null;
	
	/**
	 * 制作扑克牌
	 */
	public Pork52(){
		colors = new String[]{"黑桃","梅花","红桃","方块"};
		nums = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		pork52 = new String[52];
		for(int i=0;i<colors.length;i++){
			for(int j=0;j<nums.length;j++){
				pork52[13*i+j] = new String(colors[i]+nums[j]);
			}
		}
	}
	
	/**
	 * 洗牌
	 */
	public void shuffle(){
		Random ra = new Random();
		String temp = null;
		int index;
		for(int i=0;i<pork52.length;i++){
			index = ra.nextInt(52);
			temp = pork52[i];
			pork52[i] = pork52[index];
			pork52[index] = temp;
		}
	}
	
	/**
	 * 给n个玩家发牌
	 * @param n
	 */
	public void distribute(int n){
		if(n<2){
			return; // 谓语句出去
		}
		
		// 生成并且初始化n个玩家
		StringBuilder[] player = new StringBuilder[n];
		for(int i=0;i<player.length;i++){
			player[i] = new StringBuilder("玩家"+(i+1)+"：");
		}
		// 发牌
		for(int i=0;i<pork52.length;i++){
			player[i%n].append(pork52[i]+" ");
		}
		
		// 打印发牌结果
		System.out.println("发牌结果如下：");
		for(int i=0;i<player.length;i++){
			System.out.println(player[i].toString());
		}
	}
}
