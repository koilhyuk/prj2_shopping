package user.view.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UserGoodsNotPanelView extends JPanel {

	public UserGoodsNotPanelView(UserGoodsMainView ugmv) {
		setLayout(null);
		JLabel jlNothave = new JLabel("�˻��� ��ǰ�� �����ϴ�. �˻�� ������ ������.");
		Font ft = new Font("���� ���", Font.BOLD, 30);
		jlNothave.setFont(ft);

		setBackground(Color.white);

		jlNothave.setBounds(250, 130, 800, 100);
		add(jlNothave);
		setPreferredSize(new Dimension(ugmv.getJspGoods().getWidth(), ugmv.getJspGoods().getHeight()));
		setVisible(true);
	}// UserGoodsNotPanelView

}// class
