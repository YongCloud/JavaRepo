/**
 * 位运算符demo，几乎所有的编程语言都支持位运算符，它们是：<br>
 * & 按位与<br>
 * | 按位或<br>
 * ~ 按位取反<br>
 * << 左移<br>
 * >> 右移<br>
 * >>> 无符号右移
 * 
 * @author xingjian
 * @since 2020/01/03
 */
public class BitOperatorDemo {
    public static void main(String[] args) {
        int a = 0b1010;
        int b = 0b1111;
        int c = -8;
        // 按位与
        System.out.println(Integer.toBinaryString(a & b));
        // 按位或
        System.out.println(Integer.toBinaryString(a | b));
        // 按位取反
        System.out.println(Integer.toBinaryString(~a));
        System.out.println(Integer.toBinaryString(~b));
        // 左移
        System.out.println(Integer.toBinaryString(a << 1));
        System.out.println(Integer.toBinaryString(b << 1));
        // 右移
        System.out.println(Integer.toBinaryString(b >> 1));
        // 最高位补符号位，正数是0，负数是1
        System.out.println(Integer.toBinaryString(c >> 1));
        System.out.println(c >> 1);
        // 无符号右移，最高位补0，所以负数变成了正数
        System.out.println(Integer.toBinaryString(c >>> 1));
        System.out.println(c >>> 1);
    }
}
