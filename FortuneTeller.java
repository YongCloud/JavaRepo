
/**
 * ҡһ��:���ݡ����ס�ԭ��ʵ������
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
		frame = new JFrame("ҡһ��");
		int height, widht;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		widht = Toolkit.getDefaultToolkit().getScreenSize().width;
		frame.setBounds((widht - 500) >> 2, (height - 309) >> 2, 500, 309);
		label = new JLabel[2];
		text = new JTextField[2];
		button = new JButton[2];
		frame.setLayout(new GridLayout(3, 2));
		label[0] = new JLabel("������");
		label[1] = new JLabel("س�򣨴��µ��ϣ���");
		text[0] = new JTextField();
		text[1] = new JTextField();
		button[0] = new JButton("���");
		button[1] = new JButton("ҡһ��");
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
		final String[] gua = new String[] { "Ǭ", "��", "��", "��", "��", "��", "ʦ", "��", "С��", "��", "̩", "��", "ͬ��", "����",
				"ǫ", "ԥ", "��", "��", "��", "��", "���", "��", "��", "��", "����", "����", "��", "���", "��", "��", "��", "��", "��", "��׳",
				"��", "����", "����", "�", "�", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "����",
				"��", "��", "��", "��", "��", "��", "����", "С��", "�ȼ�", "δ��" };
		final Random ra = new Random();
		button[1].addActionListener(e -> {
			text[0].setText(gua[ra.nextInt(gua.length)] + "��");
			text[1].setText("" + (ra.nextInt(6) + 1));
		});
	}
}