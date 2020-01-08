package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.view.AdGoodsListPanelView;

public class AdGoodsListPanelEvt implements ActionListener {

	private AdGoodsListPanelView gpv;
	private String goodsCode;

	public AdGoodsListPanelEvt(AdGoodsListPanelView gpv) {
		this.gpv = gpv;
		String tempName = gpv.getJlGoodsName().getText().trim();
		goodsCode = tempName.substring(tempName.indexOf("(") + 1, tempName.indexOf(")")).toString().trim();

	}// GoodsPanelViewEvt

//	private void insertNmRecent() {
//		boolean flag = false;
//
//		AdDAO aDAO = AdDAO.getInstance();
//		try {
//			flag = aDAO.insertNmRecentGoods(goodsCode);
//			if (flag == false) {
//				return;
//			} // end if
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}// insertRecent

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gpv.getJbtnGoodsDetail()) {
//			insertNmRecent();
		}

	}// actionPerformed

}// class
