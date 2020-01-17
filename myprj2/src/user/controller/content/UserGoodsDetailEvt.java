package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataDecrypt;
import user.dao.SelectAddrDAO;
import user.dao.UserDAO;
import user.view.content.PayView;
import user.view.content.UserGoodsDetailView;
import user.view.content.UserGoodsMainView;
import user.view.login.ClientLoginView;
import user.view.login.LoginDialogView;
import user.vo.selectZipcodeVO;
import user.vo.content.SelectClickGoodsDetailDTO;
import user.vo.content.SellNextInformDTO;

public class UserGoodsDetailEvt implements ActionListener {

	private UserGoodsDetailView ugdv;
	private SelectClickGoodsDetailDTO scgdDTO;

	public UserGoodsDetailEvt(UserGoodsDetailView ugdv, SelectClickGoodsDetailDTO scgdDTO) {
		this.ugdv = ugdv;
		this.scgdDTO = scgdDTO;
	}// UserProductDetailEvt

	private void sellGoodsProcess() {

		SellNextInformDTO sniDTO = new SellNextInformDTO();
		selectZipcodeVO zipcodeData = null;

		String gName = String.valueOf(ugdv.getJlGName().getText());
		String gPrice = ugdv.getJlGPrice().getText().substring(0, ugdv.getJlGPrice().getText().lastIndexOf("원"));
		String gSu = ugdv.getJtfSelectNum().getText();

		sniDTO.setgCode(scgdDTO.getgCode());
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

	}// sellGoodsProcess

	private void goodsLikeChange(boolean likeStatus) {
		UserDAO uDAO = UserDAO.getInstance();

		ImageIcon changeLikeIcon = null;
		ImageIcon rolloverLikeImg = null;
		try {
			if (likeStatus) {// 좋아요가 되어있을 때 -> 삭제
				if (!uDAO.deleteGoodsLike(UserGoodsMainView.id, scgdDTO.getgCode())) {
					JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "DBMS 문제 발생");
					return;
				} // end if
					// 이미지 회색하트로 변경
				changeLikeIcon = new ImageIcon(ClientLoginView.USER_FILE_PATH + "/unlike_heart.png");
				rolloverLikeImg = new ImageIcon(ClientLoginView.USER_FILE_PATH + "/like_heart.PNG");
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "\""+scgdDTO.getgName()+"\" 찜하기 해제되었습니다.");
				scgdDTO.setgLikeStatus(false);
			} else {// 좋아요가 되어 X 때 -> 추가
				uDAO.insertGoodsLike(UserGoodsMainView.id, scgdDTO.getgCode());
				changeLikeIcon = new ImageIcon(ClientLoginView.USER_FILE_PATH + "/like_heart.PNG");
				rolloverLikeImg = new ImageIcon(ClientLoginView.USER_FILE_PATH + "/unlike_heart.png");
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "\""+scgdDTO.getgName()+"\" 찜하기 되었습니다.");
				scgdDTO.setgLikeStatus(true);
			} // end else
			ugdv.getJbtnGoodsLike().setIcon(changeLikeIcon);
			ugdv.getJbtnGoodsLike().setRolloverIcon(rolloverLikeImg);
			ugdv.getJlLike().setText(uDAO.selectGoodsLikeNum(scgdDTO.getgCode())+" 개");
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// goodsLikeChange

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
				ugdv.getJlGTotalPrice().setText(priceFormat.format(minus * realPrice) + "원");
			} // end if
		} // end if

		if (ae.getSource() == ugdv.getJbtnPlus()) {
			int plus = Integer.parseInt(ugdv.getJtfSelectNum().getText());
			plus = plus + 1;
			ugdv.getJtfSelectNum().setText(String.valueOf(plus));
			ugdv.getJlGTotalPrice().setText(priceFormat.format(plus * realPrice) + "원");
		} // end if

		if (ae.getSource() == ugdv.getJbtnGoodsLike()) {// 좋아요 버튼 클릭 시
			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원
				goodsLikeChange(scgdDTO.isgLikeStatus());
			} else {// 비회원일 때 로그인 하게 하기
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "찜하기는 로그인 후 가능합니다. 로그인 해주세요.");
				ugdv.dispose();// 현재창 닫기
				new LoginDialogView(UserGoodsMainEvt.ugmv, UserGoodsMainEvt.rt);
			} // end else
		} // end if
	}// actionPerformed

}// class
