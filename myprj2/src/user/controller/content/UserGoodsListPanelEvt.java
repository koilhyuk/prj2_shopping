package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import user.dao.UserDAO;
import user.view.content.UserGoodsDetailView;
import user.view.content.UserGoodsListPanelView;
import user.view.content.UserGoodsMainView;
import user.vo.content.SelectClickGoodsDetailDTO;

public class UserGoodsListPanelEvt implements ActionListener {

	private String goodsCode;

	private UserGoodsListPanelView uglpv;

	public UserGoodsListPanelEvt(UserGoodsListPanelView uglpv) {
		this.uglpv = uglpv;
		String tempName = uglpv.getJlGoodsName().getText().trim();
		goodsCode = tempName.substring(tempName.indexOf("(") + 1, tempName.indexOf(")")).toString().trim();

	}// GoodsPanelViewEvt

/////////////////// 2019-09-23
	private void insertRecent() {
		boolean flag = false;
		UserDAO uDAO = UserDAO.getInstance();
		try {
			flag = uDAO.insertRecentGoods(goodsCode);
			if (flag == false) {
				return;
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// insertRecent

	private void insertNmRecent() {
		boolean flag = false;
		UserDAO uDAO = UserDAO.getInstance();
		try {
			flag = uDAO.insertNmRecentGoods(goodsCode);
			if (flag == false) {
				return;
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// insertRecent

	private void searchGoodsDetail(String goodsCodes) {
		SelectClickGoodsDetailDTO scgdDTO = new SelectClickGoodsDetailDTO();
		UserDAO uDAO = UserDAO.getInstance();

		try {
			scgdDTO = uDAO.searchClickGoodsDetail(goodsCodes);
			new UserGoodsDetailView(scgdDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// searchGoodsDetail

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == uglpv.getJbtnGoodsDetail()) {
			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원일때만
				insertRecent();
			} else {// 비회원일 때
				insertNmRecent();
			} // end if
				//////////////////////////////////////////// 2019-12-30 상품 조회
			searchGoodsDetail(goodsCode);
		} // end if
	}// actionPerformed

}// class
