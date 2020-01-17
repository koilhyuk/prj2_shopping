package user.view.content;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import user.controller.content.UserGoodsLikeEvt;
import user.controller.content.UserGoodsMainEvt;

@SuppressWarnings("serial")
public class UserGoodsLikeView extends JDialog {
	

	// 1. jpContent(전체화면)
	// 2. jspGoods(scroll)
	// 3. jpGoods(상품 상세)
	private JPanel jpContent;
	private JPanel jpGoods;
	private JScrollPane jspGoods;
	private JButton jbtnComfirm;

	public UserGoodsLikeView() {
		super(UserGoodsMainEvt.ugmv, "찜하기", false);
		setLayout(null);
		JPanel jpChannel = new JPanel();
		jpChannel.setBackground(new Color(0x3F4040));
		jpChannel.setLayout(null);

		JLabel jlHeader = new JLabel("♥ 찜한 목록 ♥", JLabel.CENTER);
		jlHeader.setBounds(0, 0, 760, 80);
		Font headerFont = new Font("맑은 고딕)", Font.BOLD, 30);
		jlHeader.setFont(headerFont);
		jlHeader.setForeground(Color.white);

		JPanel jpHeader = new JPanel();
		jpHeader.setLayout(null);
		jpHeader.setBounds(0, 0, 760, 80);
		jpHeader.setBackground(new Color(0x352A26));
		
		jbtnComfirm = new JButton("확인");
		jbtnComfirm.setBounds(325, 705, 80, 35);
		jbtnComfirm.setBackground(new Color(0x043424));
		jbtnComfirm.setForeground(Color.white);
		

		jpHeader.add(jlHeader);
		jpContent = new JPanel();
		jpContent.setLayout(null);

		jpGoods = new JPanel();
		jpGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpGoods.setBackground(Color.white);
		jspGoods = new JScrollPane(jpGoods);

		jspGoods.setBounds(10, 120, 725, 577);

		jpContent.add(jpHeader);
		jpContent.add(jspGoods);
		jpContent.add(jbtnComfirm);

		jpContent.setBounds(0, 0, 750, 775);// 고정 값
		jpContent.setBackground(Color.white);
		jpGoods.setBackground(Color.white);
		// 이벤트
		UserGoodsLikeEvt ugle = new UserGoodsLikeEvt(this);
		jbtnComfirm.addActionListener(ugle);

		add(jpContent);
		setBounds(350, 20, 750, 775);

		setResizable(false);
		setVisible(true);

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

	public JButton getJbtnComfirm() {
		return jbtnComfirm;
	}

}// class