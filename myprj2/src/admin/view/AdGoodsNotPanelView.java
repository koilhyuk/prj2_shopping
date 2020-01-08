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
		JLabel jlNothave = new JLabel("��ȸ�� ��ǰ�� ��ϵǾ� ���� �ʽ��ϴ�.");
		Font ft = new Font("���� ���", Font.BOLD, 30);
		jlNothave.setFont(ft);
		
		setBackground(Color.white);

		jlNothave.setBounds(250, 130, 800, 100);
		add(jlNothave);
		setPreferredSize(new Dimension(ugmv.getJspGoods().getWidth(), ugmv.getJspGoods().getHeight()));
		setVisible(true);
	}// UserGoodsNotPanelView

}// class
