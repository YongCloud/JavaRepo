/**
 * 求指定区间内的所有素数
 * @author xingjian
 *
 */
public class PrimeNumber {
	private int low; // 所求区间下界
	private int high; // 所求区间上界
	
	// 构造函数
	public PrimeNumber(){
	}
	
	public PrimeNumber(int low,int high){
		this.low = low;
		this.high = high;
	}
	
	// setter and getter
	public void setLow(int low){
		this.low = low;
	}

	public int getLow(){
		return this.low;
	}

	public void setHigh(int high){
		this.high = high;
	}

	public int getHigh(){
		return high;
	}

	public void printPrime(){
		// 这个方法写具体实现逻辑
		int count = 0;
		for(int i=low;i<=high;i++){
			if(this.isPrime(i)){
				System.out.print(i+" ");
				count++;
			}
			if(count%10 == 0){
				System.out.println();
			}
		}
		System.out.println("\ntotal nums:"+count);
	}

	// 判断整数n是不是素数
	public boolean isPrime(int n){
		if(n<1){
			return false;
		}
		if(2 == n){
			return true;
		}
		for(int i=2;i<=(int)Math.sqrt(n);i++){
			if(n%i == 0){
				return false;
			}
		}
		return true;
	}

	public void otherMethod(){

	}
}
