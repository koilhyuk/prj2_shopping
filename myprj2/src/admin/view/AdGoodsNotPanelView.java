package admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AdGoodsNotPanelView extends JPanel {

	private AdGoodsMainView ugmv;

	public AdGoodsNotPanelView(AdGoodsMainView ugmv) {
		this.ugmv = ugmv;
		setLayout(null);
		JLabel jlNothave = new JLabel("조회한 상품은 등록되어 있지 않습니다.");
		Font ft = new Font("맑은 고딕", Font.BOLD, 30);
		jlNothave.setFont(ft);
		
		setBackground(Color.white);

		jlNothave.setBounds(250, 130, 800, 100);
		add(jlNothave);
		setPreferredSize(new Dimension(ugmv.getJspGoods().getWidth(), ugmv.getJspGoods().getHeight()));
		setVisible(true);
	}// UserGoodsNotPanelView

}// class
