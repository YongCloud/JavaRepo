
/**
 * 摇一卦:根据《周易》原理实现算卦
 * 
 * @author YangYong
 *
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FortuneTeller {
	private JFrame frame = null;
	private JLabel[] label = null;
	private JTextField[] text = null;
	private JButton[] button = null;

	public static void main(String[] args) {
		new FortuneTeller().myHandler();
	}

	public FortuneTeller() {
		frame = new JFrame("摇一卦");
		int height, widht;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		widht = Toolkit.getDefaultToolkit().getScreenSize().width;
		frame.setBounds((widht - 500) >> 2, (height - 309) >> 2, 500, 309);
		label = new JLabel[2];
		text = new JTextField[2];
		button = new JButton[2];
		frame.setLayout(new GridLayout(3, 2));
		label[0] = new JLabel("卦名：");
		label[1] = new JLabel("爻序（从下到上）：");
		text[0] = new JTextField();
		text[1] = new JTextField();
		button[0] = new JButton("清空");
		button[1] = new JButton("摇一卦");
		int i;
		for (i = 0; i < label.length; i++) {
			frame.add(label[i]);
			text[i].setEditable(false);
			text[i].setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			frame.add(text[i]);
		}
		frame.add(button[0]);
		frame.add(button[1]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void myHandler() {
		// lambda expression
		button[0].addActionListener(e -> {
			text[0].setText(null);
			text[1].setText(null);
		});
		final String[] gua = new String[] { "乾", "坤", "屯", "蒙", "需", "讼", "师", "比", "小畜", "履", "泰", "否", "同人", "大有",
				"谦", "豫", "随", "蛊", "临", "观", "噬嗑", "贲", "剥", "复", "无妄", "大畜", "颐", "大过", "坎", "离", "咸", "恒", "遁", "大壮",
				"晋", "明夷", "家人", "睽", "蹇", "解", "损", "益", "夬", "姤", "萃", "升", "困", "井", "革", "鼎", "震", "艮", "渐", "归妹",
				"丰", "旅", "巽", "兑", "涣", "节", "中孚", "小过", "既济", "未济" };
		final Random ra = new Random();
		button[1].addActionListener(e -> {
			text[0].setText(gua[ra.nextInt(gua.length)] + "卦");
			text[1].setText("" + (ra.nextInt(6) + 1));
		});
	}
}