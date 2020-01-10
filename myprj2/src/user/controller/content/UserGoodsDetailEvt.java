package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataDecrypt;
import user.dao.SelectAddrDAO;
import user.dao.UserDAO;
import user.view.content.PayView;
import user.view.content.UserGoodsDetailView;
import user.view.content.UserGoodsMainView;
import user.view.login.LoginDialogView;
import user.vo.selectZipcodeVO;
import user.vo.content.SellNextInformDTO;

public class UserGoodsDetailEvt implements ActionListener {

	private UserGoodsDetailView ugdv;
	private String gCode;

	public UserGoodsDetailEvt(UserGoodsDetailView ugdv, String gCode) {
		this.ugdv = ugdv;
		this.gCode = gCode;
	}// UserProductDetailEvt

	private void sellGoodsProcess() {

		SellNextInformDTO sniDTO = new SellNextInformDTO();
		selectZipcodeVO zipcodeData = null;

		String gName = String.valueOf(ugdv.getJlGName().getText());
		String gPrice = ugdv.getJlGPrice().getText().substring(0, ugdv.getJlGPrice().getText().lastIndexOf("원"));
		String gSu = ugdv.getJtfSelectNum().getText();

		sniDTO.setgCode(gCode);
		sniDTO.setgName(gName);
		sniDTO.setmQuantity(Integer.parseInt(gSu));
		sniDTO.setTotalMoney(Integer.parseInt(gPrice) * Integer.parseInt(gSu));
		sniDTO.setgPrice(Integer.parseInt(gPrice));
		sniDTO.setmId(UserGoodsMainView.id);

		UserDAO uDAO = UserDAO.getInstance();
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();
		try {
			sniDTO = uDAO.searchBuyGoodsInform(sniDTO);
			sniDTO.setCardList(uDAO.selectMemberCard(sniDTO));
			if (sniDTO.getCardList().size() == 0) {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "등록 된 결제수단이 없습니다. 새로 등록해주세요.");
				ugdv.dispose();
				return;
			}
			String tempCardNum = sniDTO.getCardList().get(0).getCarNum();
			DataDecrypt dd = new DataDecrypt(UserGoodsMainView.KEY);
			sniDTO.getCardList().get(0).setCarNum(dd.decryption(tempCardNum));
			zipcodeData = saDAO.zipCodeSearch(sniDTO.getmSeq());
			int getsu = Integer.parseInt(gSu);
			if (getsu > 0) {
				ugdv.dispose();// 현재창 닫기
				new PayView(sniDTO, zipcodeData);
			} else {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "1개 이상 상품을 선택하여야합니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} // end catch

	}// UserGoodsDetailView

	@Override
	public void actionPerformed(ActionEvent ae) {
		int realPrice = Integer
				.parseInt(ugdv.getJlGPrice().getText().substring(0, ugdv.getJlGPrice().getText().lastIndexOf("원")));
		DecimalFormat priceFormat = new DecimalFormat("###,###");
		if (ae.getSource() == ugdv.getJbtnBuy()) {
			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원
				sellGoodsProcess();
			} else {// 비회원일 때 로그인 하게 하기
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "상품 구매는 로그인 후 가능합니다. 로그인 해주세요.");
				ugdv.dispose();// 현재창 닫기
				new LoginDialogView(UserGoodsMainEvt.ugmv, UserGoodsMainEvt.rt);
			} // end else
		} // end if

		if (ae.getSource() == ugdv.getJbtnMinus()) {
			int minus = Integer.parseInt(ugdv.getJtfSelectNum().getText());
			if (minus > 1) {
				minus = minus - 1;
				ugdv.getJtfSelectNum().setText(String.valueOf(minus));
				ugdv.getJlGTotalPrice().setText(priceFormat.format(minus * realPrice) +"원");
			} // end if
		} // end if

		if (ae.getSource() == ugdv.getJbtnPlus()) {
			int plus = Integer.parseInt(ugdv.getJtfSelectNum().getText());
			plus = plus + 1;
			ugdv.getJtfSelectNum().setText(String.valueOf(plus));
			ugdv.getJlGTotalPrice().setText(priceFormat.format(plus * realPrice) +"원");
		} // end if
	}// action

}// class
