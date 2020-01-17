package user.controller.content;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import user.dao.UserDAO;
import user.view.content.UserGoodsLikeView;
import user.view.content.UserGoodsListPanelView;
import user.view.content.UserGoodsMainView;
import user.vo.content.SelectAllGoodsVO;

public class UserGoodsLikeEvt implements ActionListener {

	private UserGoodsLikeView uglv;
	private JPanel jpGoods;

	public UserGoodsLikeEvt(UserGoodsLikeView uglv) {
		this.uglv = uglv;
		selectAllGoodsList();
	}// UserGoodsLikeEvt

	private void selectAllGoodsList() {
		int goodsNum = 0;// ��ǰ ���� ����
		JScrollPane jspGoods = uglv.getJspGoods();
		jpGoods = uglv.getJpGoods();
		jspGoods.getVerticalScrollBar().setValue(0);
		jpGoods.removeAll();

		UserDAO uDAO = UserDAO.getInstance();
		try {
			List<SelectAllGoodsVO> list = uDAO.selectLikeAllGoods(UserGoodsMainView.id);

			if (list.isEmpty()) {
				jpGoods.setPreferredSize(new Dimension(700, 390));
				uglv.setVisible(true);
				return;
			} // end if
			String tempInven = "";
			SelectAllGoodsVO sagVO = null;
			for (int i = 0; i < list.size(); i++) {
				sagVO = list.get(i);
				if (sagVO.getG_inventory() <= 5) {
					tempInven = " (�� ǰ���ӹ� ��)";
				} else {
					tempInven = "";
				} // end else
				jpGoods.add(new UserGoodsListPanelView(sagVO.getG_img(),
						sagVO.getG_name() + "(" + sagVO.getG_code() + ")", sagVO.getB_name(), sagVO.getG_price(),
						sagVO.getG_salenum() + tempInven, sagVO.getG_score()));
				goodsNum++;
			} // end for
			int plusCnt = 0;
			if (goodsNum % 4 > 0) {// 6�ǹ���� �ƴ� ��
				plusCnt = 1;
			}
			jpGoods.setPreferredSize(new Dimension(700, 285 * ((goodsNum / 4) + plusCnt)));
			uglv.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(uglv, "���񽺰� ��Ȱ���� �ʽ��ϴ�. �˼��մϴ�.");
			e.printStackTrace();
		} // end catch
	}// selectAllGoodsList

	@Override
	public void actionPerformed(ActionEvent ae) {// ī�װ� ��ư Ŭ�� �� ȸ�� ��ȸ�� ������

		if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// ȸ���϶���
			if (ae.getSource() == uglv.getJbtnComfirm()) {
				uglv.dispose();
			}
		} // end if
	}// actionPerformed

}// class
