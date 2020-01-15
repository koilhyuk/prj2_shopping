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
		goodsCode = tempName.substring(tempName.lastIndexOf("(") + 1, tempName.lastIndexOf(")")).toString().trim();

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

	//현재 아이디가 찜했는지 안했는지
	private boolean goodsLikeStatus(String goodsCode) {
		boolean likeFlag = false;// 찜하기가 안되있는 상태
		String goodsLikeResult = "";
		UserDAO uDAO = UserDAO.getInstance();
		try {
			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {
				goodsLikeResult = uDAO.selectGoodsLike(goodsCode, UserGoodsMainView.id);
				if (goodsLikeResult != null && !goodsLikeResult.isEmpty()) {
					likeFlag = true;// 찜하기가 되어있는 상품
				} // end if
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return likeFlag;
	}// goodsLikeStatus

	
	private void searchGoodsDetail(String goodsCodes, boolean likeFlag) {
		SelectClickGoodsDetailDTO scgdDTO = new SelectClickGoodsDetailDTO();
		UserDAO uDAO = UserDAO.getInstance();

		try {
			scgdDTO = uDAO.searchClickGoodsDetail(goodsCodes);
			scgdDTO.setgLikeNum(uDAO.selectGoodsLikeNum(goodsCodes));
			scgdDTO.setgLikeStatus(likeFlag);
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
			searchGoodsDetail(goodsCode, goodsLikeStatus(goodsCode));
		} // end if
	}// actionPerformed

}// class
