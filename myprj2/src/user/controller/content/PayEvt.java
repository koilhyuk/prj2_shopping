package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.SelectAddrDAO;
import user.dao.UserDAO;
import user.view.content.PayView;
import user.view.content.UserGoodsMainView;
import user.view.content.ZipcodeSearchOrderView;
import user.vo.content.BuyGoodsInformVO;
import user.vo.content.SelectOrderChkCard;
import user.vo.content.SellNextInformDTO;

public class PayEvt extends KeyAdapter implements ActionListener {

	private SellNextInformDTO sniDTO;

	private PayView pv;

	public PayEvt(PayView pv, SellNextInformDTO sniDTO) {
		this.pv = pv;
		this.sniDTO = sniDTO;
	}// PayFinishEvt

	private void cardSelectInform(String selectCard) {
		String cardNum = "";
		JTextField jtfCardOne = pv.getJtfCardOne();
		JTextField jtfCardThree = pv.getJtfCardThree();

		jtfCardOne.setText("");
		jtfCardThree.setText("");

		UserDAO uDAO = UserDAO.getInstance();

		try {
			uDAO.selectCardNum(selectCard, UserGoodsMainView.id);

			DataDecrypt dd = new DataDecrypt(UserGoodsMainView.KEY);
			cardNum = dd.decryption(uDAO.selectCardNum(selectCard, UserGoodsMainView.id));

			System.err.println("==" + cardNum);
			jtfCardOne.setText(cardNum.substring(0, 4));
			jtfCardThree.setText(cardNum.substring(cardNum.indexOf("-", 2) + 1, cardNum.indexOf("-", 2) + 5));

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (GeneralSecurityException gse) {
			gse.printStackTrace();
		} // end catch
	}// cardSelectInform

	private boolean chkInputData() {
		boolean successFlag = true;
		JPasswordField jtfCardTwo = pv.getJtfCardTwo();
		JPasswordField jtfCardFour = pv.getJtfCardFour();
		JPasswordField jpfCVC = pv.getJtfCardCVC();

		JTextField jtfOrder = pv.getJtfOrder();
		JTextField jtfPhoneFront = pv.getJtfPhoneFront();
		JTextField jtfPhoneBehind = pv.getJtfPhoneBehind();
		JTextField jtfDemand = pv.getJtfDemand();
		JTextField jtfDetailDel = pv.getJtfDetailDel();

//		System.err.println("======================"+new String(jpfCVC.getPassword()));
		if ("".equals(jtfOrder.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "주문자를 입력해주세요.");
			jtfOrder.requestFocus();
			return false;
		} // end if

		if ("".equals(jtfPhoneFront.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "전회번호를 입력해주세요.");
			jtfPhoneFront.requestFocus();
			return false;
		} // end if
		if ("".equals(jtfPhoneBehind.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "전회번호를 입력해주세요.");
			jtfPhoneBehind.requestFocus();
			return false;
		} // end if
		if ("".equals(jtfDetailDel.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "상세주소를 입력해주세요.");
			jtfDetailDel.requestFocus();
			return false;
		} // end if

		if (jtfDemand.getText().trim().length() >= 20) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "요청사항은 20자 이내로 작성해주세요.");
			jtfDemand.requestFocus();
			return false;
		} // end if

		if (new String(jtfCardTwo.getPassword()).trim().length() < 4) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "카드번호를 확인해주세요.");
			jtfCardTwo.requestFocus();
			return false;
		} // end if

		if (new String(jtfCardFour.getPassword()).trim().length() < 4) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "카드번호를 확인해주세요.");
			jtfCardFour.requestFocus();
			return false;
		} // end if

		if (new String(jpfCVC.getPassword()).trim().length() < 3) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "CVC를 확인해주세요.");
			jpfCVC.requestFocus();
			return false;
		} // end if

		return successFlag;
	}// chkInputData

	private String chkInputCard() {
		String cardCode = "";
		String cardMethod = pv.getJcbCard().getSelectedItem().toString().trim();
		String cardOne = new String(pv.getJtfCardOne().getPassword()).trim();
		String cardTwo = new String(pv.getJtfCardTwo().getPassword()).trim();
		String cardThree = new String(pv.getJtfCardThree().getPassword()).trim();
		String cardFour = new String(pv.getJtfCardFour().getPassword()).trim();
		String cardCVC = new String(pv.getJtfCardCVC().getPassword()).trim();

		String sumCardNum = cardOne + "-" + cardTwo + "-" + cardThree + "-" + cardFour;

		String transCardNum = "";
		String transCardCVC = "";

		try {
			DataEncrypt de = new DataEncrypt(UserGoodsMainView.KEY);
			transCardNum = de.encryption(sumCardNum);
			transCardCVC = de.encryption(cardCVC);

			UserDAO uDAO = UserDAO.getInstance();
			SelectOrderChkCard socc = new SelectOrderChkCard(transCardNum, transCardCVC, cardMethod);
			cardCode = uDAO.selectChkCard(socc);
			System.err.println(cardCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cardCode;
	}// chkInputCard

	private int addrSearchSeq(String zipcode, String addr) {
		int seq = 0;
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();

		try {
			seq = saDAO.seqSearch(zipcode, addr);

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return seq;
	}// addrSearchSeq

	private String paymentNewGoods(BuyGoodsInformVO bgiVO) {
		String oCode = "";

		UserDAO uDAO = UserDAO.getInstance();
		try {
			System.err.println(bgiVO);
			uDAO.insertNewOrdering(bgiVO);
			// 성공을 했다면 최근 oCode가져오기
			oCode = uDAO.selectRecentOrdering(bgiVO.getmId());

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return oCode;
	}// paymentNewGoods

	private int buyGoodsInventory(String goodsGode) {
		int goodsInventory = 0;

		UserDAO uDAO = UserDAO.getInstance();
		try {
			goodsInventory = uDAO.selectGoodsInventory(goodsGode);

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return goodsInventory;
	}// buyGoodsInventory

	private boolean updateGoodsInventory(String gCode, int goodsQuantity) {
		boolean successFlag = false;
		UserDAO uDAO = UserDAO.getInstance();
		try {
			successFlag = uDAO.updateInventoryGoods(gCode, goodsQuantity);

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return successFlag;
	}// updateGoodsInventory

	private void insertNewOrderPay(String oCode, String pCode) {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			uDAO.insertOrderPay(oCode, pCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		
	}// insertNewOrderPay

	@Override
	public void actionPerformed(ActionEvent ae) {

		int zipSeq = 0;
		String orderPhone = "";
		String cardCode = "";
		String orderCode = "";
		int orderGoodsQuantity = sniDTO.getmQuantity();
		int goodsInventory = 0;

		if (ae.getSource() == pv.getJbtnPay() || ae.getSource() == pv.getJtfCardCVC()) {// 결제하기 버튼
			if (!chkInputData()) {// 입력 값 부족
				return;
			} // end if

			cardCode = chkInputCard();

			if ("".equals(cardCode) || cardCode == null) {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "입력한 카드정보가 일치하지 않습니다.");
				return;
			} // end if

			goodsInventory = buyGoodsInventory(sniDTO.getgCode().trim());
			if (orderGoodsQuantity > goodsInventory) {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv,
						"상품 재고량을 초과하였습니다. 죄송합니다. (재고량 : " + goodsInventory + "개)");
				return;
			} // end if

			zipSeq = addrSearchSeq(pv.getJtfzipcode().getText().trim(), pv.getJtfDelivery().getText().trim());
			orderPhone = pv.getJcbPhoneNum().getSelectedItem().toString().trim() + "-"
					+ pv.getJtfPhoneFront().getText().trim() + "-" + pv.getJtfPhoneBehind().getText().trim();

			BuyGoodsInformVO bgiVO = new BuyGoodsInformVO(sniDTO.getgCode(), orderPhone, sniDTO.getmDetailAddr(),
					pv.getJtfDemand().getText().trim(), pv.getJtfOrder().getText().trim(), sniDTO.getmId(),
					sniDTO.getmQuantity(), sniDTO.getTotalMoney(), zipSeq);

			orderCode = paymentNewGoods(bgiVO);

			if (!updateGoodsInventory(sniDTO.getgCode().trim(), orderGoodsQuantity)) {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "DBMS 문제 발생");
			} // end if
			
			insertNewOrderPay(orderCode, cardCode);// 결제 수단, 주문 코드 테이블에 등록

		} // end if

		if (ae.getSource() == pv.getJbtnCancel()) {// 취소 버튼
			pv.dispose();
		} // end if

		if (ae.getSource() == pv.getJcbCard()) {// 카드 선택 콤보박스
			cardSelectInform(pv.getJcbCard().getSelectedItem().toString().trim());
		} // end if

		if (ae.getSource() == pv.getJbtnaddr()) {// 주소 검색 버튼
			new ZipcodeSearchOrderView(pv);
		} // end if
	}// action

	@Override
	public void keyTyped(KeyEvent ke) {
		JPasswordField jtfCardTwo = pv.getJtfCardTwo();
		JPasswordField jtfCardFour = pv.getJtfCardFour();
		JPasswordField jpfCVC = pv.getJtfCardCVC();

		JTextField jtfPhoneFront = pv.getJtfPhoneFront();
		JTextField jtfPhoneBehind = pv.getJtfPhoneBehind();
		JTextField jtfDemand = pv.getJtfDemand();

		if (ke.getSource() == jtfCardTwo) {
			if (new String(jtfCardTwo.getPassword()).trim().length() >= 4) {
				ke.consume();
				jtfCardFour.requestFocus();
			} // end if
		} // end if

		if (ke.getSource() == jtfCardFour) {
			if (new String(jtfCardFour.getPassword()).trim().length() >= 4) {
				ke.consume();
				jpfCVC.requestFocus();
			} // end if
		} // end if

		if (ke.getSource() == jpfCVC) {
			if (new String(jpfCVC.getPassword()).trim().length() >= 3) {
				ke.consume();
			} // end if
		} // end if

		if (ke.getSource() == jtfPhoneFront) {
			if (jtfPhoneFront.getText().trim().length() >= 4) {
				ke.consume();
				jtfPhoneBehind.requestFocus();
			} // end if
		} // end if

		if (ke.getSource() == jtfPhoneBehind) {
			if (jtfPhoneBehind.getText().trim().length() >= 4) {
				ke.consume();
				jtfDemand.requestFocus();
			} // end if
		} // end if

		if (ke.getSource() == jtfDemand) {
			if (jtfDemand.getText().trim().length() >= 20) {
				ke.consume();
			} // end if
		} // end if

	}// keyTyped
}// class
