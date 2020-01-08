package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.view.AdGoodsListPanelView;

public class AdListPanelEvt implements ActionListener {

	private String goodsCode;

	private AdGoodsListPanelView aglpv;

	public AdListPanelEvt(AdGoodsListPanelView aglpv) {
		this.aglpv = aglpv;
		String tempName = aglpv.getJlGoodsName().getText().trim();
		goodsCode = tempName.substring(tempName.indexOf("(") + 1, tempName.indexOf(")")).toString().trim();

	}// GoodsPanelViewEvt

//
//	private void insertNmRecent() {
//		boolean flag = false;
//		AdDAO aDAO = AdDAO.getInstance();
//		try {
//			flag = aDAO.insertNmRecentGoods(goodsCode);
//			if (flag == false) {
//				return;
//			} // end if
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}// insertRecent

	@Override
	public void actionPerformed(ActionEvent e) {

	}// actionPerformed

}// class
