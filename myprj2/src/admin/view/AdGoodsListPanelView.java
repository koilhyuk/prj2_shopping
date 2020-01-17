package admin.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import admin.controller.AdGoodsListPanelEvt;
import admin.run.StaticCla;
import user.controller.content.UserGoodsListPanelEvt;

@SuppressWarnings("serial")
public class AdGoodsListPanelView extends JPanel {
	private JButton jbtnGoodsDetail;
	private JLabel jlGoodsImg, jlGoodsName, jlGoodsBrand, jlGoodsPrice, jlGoodsSell, jlGoodsStar;

	public AdGoodsListPanelView(String img, String name, String brand, int price, String sell, int star) {
		jbtnGoodsDetail = new JButton("상세보기");
		jbtnGoodsDetail.setBackground(new Color(0x043424));
		jbtnGoodsDetail.setForeground(Color.white);
		jbtnGoodsDetail.setBorder(new LineBorder(new Color(0x043424)));

		jlGoodsName = new JLabel(name);
		jlGoodsBrand = new JLabel(brand);
		jlGoodsPrice = new JLabel(price + " 원");
		jlGoodsSell = new JLabel("판매량 : " + sell);

		switch (star) {
		case 5:
			jlGoodsStar = new JLabel("★★★★★");
			break;
		case 4:
			jlGoodsStar = new JLabel("★★★★☆");
			break;
		case 3:
			jlGoodsStar = new JLabel("★★★☆☆");
			break;
		case 2:
			jlGoodsStar = new JLabel("★★☆☆☆");
			break;
		case 1:
			jlGoodsStar = new JLabel("★☆☆☆☆");
			break;
		case 0:
			jlGoodsStar = new JLabel("☆☆☆☆☆");
			break;
		default:
			jlGoodsStar = new JLabel("");
			break;
		}// switch
		jlGoodsStar.setForeground(Color.red);
		jlGoodsImg = new JLabel(new ImageIcon(StaticCla.FILE_PATH + "/rs_gds_" + img));
		jlGoodsImg.setBorder(new LineBorder(Color.BLACK));

		jlGoodsImg.setBounds(20, 10, 130, 140);
		jlGoodsName.setBounds(10, 150, 150, 20);
		jlGoodsBrand.setBounds(10, 170, 150, 20);
		jlGoodsPrice.setBounds(10, 190, 150, 20);
		jlGoodsSell.setBounds(10, 210, 150, 20);
		jlGoodsStar.setBounds(10, 230, 150, 20);

		jbtnGoodsDetail.setBounds(10, 250, 140, 20);

		setLayout(null);
		setBorder(new LineBorder(Color.BLACK));

//		UserGoodsListPanelEvt uglp = new UserGoodsListPanelEvt(this);
		AdGoodsListPanelEvt aglpe = new AdGoodsListPanelEvt(this);

		jbtnGoodsDetail.addActionListener(aglpe);

		add(jbtnGoodsDetail);
		add(jlGoodsName);
		add(jlGoodsBrand);
		add(jlGoodsPrice);
		add(jlGoodsSell);
		add(jlGoodsStar);
		add(jlGoodsImg);
		setBackground(Color.white);

		setPreferredSize(new Dimension(170, 280));

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

	public JLabel getJlGoodsBrand() {
		return jlGoodsBrand;
	}

	public JLabel getJlGoodsPrice() {
		return jlGoodsPrice;
	}

	public JLabel getJlGoodsSell() {
		return jlGoodsSell;
	}

	public JLabel getJlGoodsStar() {
		return jlGoodsStar;
	}

}// class
