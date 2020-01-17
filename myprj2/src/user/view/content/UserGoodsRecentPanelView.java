package user.view.content;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;
import user.controller.content.UserGoodsRecentPanelEvt;

@SuppressWarnings("serial")
public class UserGoodsRecentPanelView extends JPanel {
	private JButton jbtnGoodsDetail;
	private JLabel jlGoodsImg, jlGoodsName;

	public UserGoodsRecentPanelView(String img, String name) {
		jbtnGoodsDetail = new JButton("상세보기");
		jlGoodsName = new JLabel(name);

		jlGoodsImg = new JLabel(new ImageIcon(StaticCla.FILE_PATH + "/rs_gds_" + img));
		jlGoodsImg.setBorder(new LineBorder(Color.BLACK));

		jlGoodsImg.setBounds(20, 10, 130, 140);
		jlGoodsName.setBounds(20, 150, 150, 20);
		jbtnGoodsDetail.setBounds(20, 170, 140, 20);

		setLayout(null);
		setBorder(new LineBorder(Color.BLACK));

		UserGoodsRecentPanelEvt ugrppe = new UserGoodsRecentPanelEvt(this);
		jbtnGoodsDetail.addActionListener(ugrppe);

		add(jbtnGoodsDetail);
		add(jlGoodsName);
		add(jlGoodsImg);
		setBackground(Color.white);

		setBackground(Color.white);
		jbtnGoodsDetail.setBackground(new Color(0x043424));
		jbtnGoodsDetail.setForeground(Color.white);
		
		
		setPreferredSize(new Dimension(170, 200));
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
}// class
