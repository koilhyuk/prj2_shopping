package admin.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;

@SuppressWarnings("serial")
public class AdGoodsRecentPanelView extends JPanel {
	private JButton jbtnGoodsDetail;
	private JLabel jlGoodsImg, jlGoodsName;

	public AdGoodsRecentPanelView(String img, String name) {
		jbtnGoodsDetail = new JButton("�󼼺���");
		jlGoodsName = new JLabel(name);

		jlGoodsImg = new JLabel(new ImageIcon(StaticCla.FILE_PATH + "/" + img));
		jlGoodsImg.setBorder(new LineBorder(Color.BLACK));

		jlGoodsImg.setBounds(20, 10, 130, 140);
		jlGoodsName.setBounds(20, 150, 150, 20);
		jbtnGoodsDetail.setBounds(20, 170, 140, 20);

		setLayout(null);
		setBorder(new LineBorder(Color.BLACK));

//		UserGoodsListPanelEvt uglp = new UserGoodsListPanelEvt(this);

		add(jbtnGoodsDetail);
		add(jlGoodsName);
		add(jlGoodsImg);
		setBackground(Color.white);

		setPreferredSize(new Dimension(170, 200));
//		setBounds(200, 200, 170, 240);
		setVisible(true);

	}// TestGoodsJpanel

	public JButton getJbtnGoodsDetail() {
		return jbtnGoodsDetail;
	}

	public JLabel getJlGoodsImg() {
		return jlGoodsImg;
	}

	public JLabel getJlGoodsName() {
		return jlGoodsName;
	}
//
//	public static void main(String[] args) {
//		new UserGoodsRecentPanelView("", "goods3(g_00001)");
//	}
}// class
