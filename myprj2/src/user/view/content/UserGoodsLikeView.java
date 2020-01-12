package user.view.content;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import user.controller.content.UserGoodsLikeEvt;

@SuppressWarnings("serial")
public class UserGoodsLikeView extends JFrame {

	// 1. jpContent(전체화면)
	// 2. jspGoods(scroll)
	// 3. jpGoods(상품 상세)
	private JPanel jpContent;
	private JPanel jpGoods;
	private JScrollPane jspGoods;

	public UserGoodsLikeView() {
		setLayout(null);
		JPanel jpChannel = new JPanel();
		jpChannel.setBackground(new Color(0x3F4040));
		jpChannel.setLayout(null);

		jpContent = new JPanel();
		jpContent.setLayout(null);

		jpGoods = new JPanel();
		jpGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpGoods.setBackground(Color.white);
		jspGoods = new JScrollPane(jpGoods);

		jspGoods.setBounds(10, 120, 725, 577);

		jpContent.add(jspGoods);
		setResizable(false);

		add(jpContent);
		jpContent.setBounds(0, 0, 740, 775);// 고정 값
		jpContent.setBackground(Color.white);
		jpGoods.setBackground(Color.white);
		
		// 이벤트
		UserGoodsLikeEvt ugle = new UserGoodsLikeEvt(this);
		

		setBounds(350, 20, 750, 775);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// HomeMainView

	public JPanel getJpContent() {
		return jpContent;
	}

	public JPanel getJpGoods() {
		return jpGoods;
	}

	public JScrollPane getJspGoods() {
		return jspGoods;
	}

	public static void main(String[] args) {
		new UserGoodsLikeView();
	}
}// UserMainView
