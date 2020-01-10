package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import user.dao.UserDAO;
import user.view.content.UserGoodsDetailView;
import user.view.content.UserGoodsMainView;
import user.view.content.UserGoodsRecentPanelView;
import user.vo.content.SelectClickGoodsDetailDTO;

public class UserGoodsRecentPanelEvt implements ActionListener {

	private String goodsCode;

	private UserGoodsRecentPanelView ugrpv;

	public UserGoodsRecentPanelEvt(UserGoodsRecentPanelView ugrpv) {
		this.ugrpv = ugrpv;
		String tempName = ugrpv.getJlGoodsName().getText().trim();
		goodsCode = tempName.substring(tempName.indexOf("(") + 1, tempName.indexOf(")")).toString().trim();

	}// GoodsPanelViewEvt


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
		if (e.getSource() == ugrpv.getJbtnGoodsDetail()) {
			searchGoodsDetail(goodsCode);
		} // end if
	}// actionPerformed

}// class
