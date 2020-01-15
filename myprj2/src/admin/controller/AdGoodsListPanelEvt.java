package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import admin.dao.AdminDAO;
import admin.view.AdGoodsListPanelView;
import admin.view.AdGoodsPanelDetailView;
import admin.vo.SelectClickGoodsDetailDTO;

public class AdGoodsListPanelEvt implements ActionListener {

	private AdGoodsListPanelView gpv;
	private String goodsCode;

	public AdGoodsListPanelEvt(AdGoodsListPanelView gpv) {
		this.gpv = gpv;
		String tempName = gpv.getJlGoodsName().getText().trim();
		goodsCode = tempName.substring(tempName.lastIndexOf("(") + 1, tempName.lastIndexOf(")")).toString().trim();

	}// GoodsPanelViewEvt

	private void searchGoodsDetail(String goodsCodes) {
		SelectClickGoodsDetailDTO scgdDTO = new SelectClickGoodsDetailDTO();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
			scgdDTO = aDAO.searchClickGoodsDetail(goodsCodes);
			scgdDTO.setgLikeNum(aDAO.selectGoodsLikeNum(goodsCodes));
			new AdGoodsPanelDetailView(scgdDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// searchGoodsDetail

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gpv.getJbtnGoodsDetail()) {
			searchGoodsDetail(goodsCode);
		} // end if
	}// actionPerformed

}// class
