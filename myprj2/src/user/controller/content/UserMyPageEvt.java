package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.content.UserCardUploadView;
import user.view.content.UserCusDataView;
import user.view.content.UserGoodsLikeView;
import user.view.content.UserMyOrderView;
import user.view.content.UserMyPageView;
import user.vo.content.SelectCusDataVO;

public class UserMyPageEvt implements ActionListener {
	public static UserMyPageView mdv;
	private String id;

	public UserMyPageEvt(UserMyPageView mdv, String id) {
		this.mdv = mdv;
		this.id = id;
	}// myDataEvt

	public void searchCusData() {
		SelectCusDataVO cdVO = new SelectCusDataVO();
		cdVO.setM_id(id);
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			cdVO = cDAO.selectCusData(cdVO);
			new UserCusDataView(id, cdVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// searchCusData

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mdv.getJbtOrderList()) {// �ֹ�����
			new UserMyOrderView(id);
		} // end if
		if (ae.getSource() == mdv.getJbtMyData()) {// ����������
			searchCusData();
		} // end if

		if (ae.getSource() == mdv.getJbtWithdrawal()) {// ī����
			new UserCardUploadView(mdv);
		} // end if
		if (ae.getSource() == mdv.getJbtJJim()) {// ī����
			new UserGoodsLikeView();
		} // end if

		if (ae.getSource() == mdv.getJbtOk()) {// Ȯ��
			switch (JOptionPane.showConfirmDialog(mdv, "���������� â�� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				mdv.dispose();
			}// end if
		} // end if
	}// actionPerformed
}// class
