import java.util.Random;

/**
 * 模拟扑克牌
 * 
 * @author Xingjian
 * @since 2016/09/19
 */
public class Pork52 {
    private String[] nums;

    private String[] colors;

    private int[] pork52;

    /**
     * 制作扑克牌
     */
    Pork52() {
        colors = new String[] {
            "黑桃", "梅花", "红桃", "方块"
        };
        nums = new String[] {
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
        };
        pork52 = new int[52];
        for (int i = 0; i < colors.length * nums.length; i++) {
            pork52[i] = i;
        }
    }

    /**
     * 洗牌
     */
    public void shuffle() {
        Random ra = new Random();
        for (int i = 0; i < pork52.length; i++) {
            int index = ra.nextInt(52);
            swap(i, index);
        }
    }
    
    /**
     * 交换数组中索引i和j位置上的两个整数
     */
    private void swap(int i, int j) {
        // 务必关注i=j的情况，不然该位置上的元素将变为0
        if (i == j) {
            return;
        }
        pork52[i] = pork52[i] ^ pork52[j];
        pork52[j] = pork52[i] ^ pork52[j];
        pork52[i] = pork52[i] ^ pork52[j];
    }

    /**
     * 发牌
     */
    public void distribute(int n) {
        // 生成并且初始化n个玩家
        StringBuilder[] player = new StringBuilder[n];
        for (int i = 0; i < player.length; i++) {
            player[i] = new StringBuilder(64);
            player[i].append("玩家");
            player[i].append((i + 1));
            player[i].append("：");
        }
        // 发牌
        for (int i = 0; i < pork52.length; i++) {
            int color = pork52[i] / nums.length;
            int num = pork52[i] % nums.length;
            int turn = i % player.length;
            player[turn].append(colors[color]);
            player[turn].append(nums[num]);
            player[turn].append(" ");
        }

        // 打印发牌结果
        System.out.println("发牌结果如下：");
        for (int i = 0; i < player.length; i++) {
            System.out.println(player[i].toString());
        }
    }

    /**
     * test
     */
    public static void main(String[] args) {
        Pork52 pork = new Pork52();
        pork.shuffle();
        pork.distribute(4);
    }
}
