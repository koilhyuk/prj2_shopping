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
import user.view.content.PayCompleteView;
import user.view.content.PayView;
import user.view.content.UserGoodsMainView;
import user.view.content.ZipcodeSearchOrderView;
import user.vo.content.BuyGoodsInformVO;
import user.vo.content.CompleteOrderInformDTO;
import user.vo.content.SelectOrderChkCard;
import user.vo.content.SellNextInformDTO;
import user.vo.content.UnCompleteOrderInformVO;

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

		if ("".equals(jtfOrder.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "�ֹ��ڸ� �Է����ּ���.");
			jtfOrder.requestFocus();
			return false;
		} // end if

		if ("".equals(jtfPhoneFront.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "��ȸ��ȣ�� �Է����ּ���.");
			jtfPhoneFront.requestFocus();
			return false;
		} // end if
		if ("".equals(jtfPhoneBehind.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "��ȸ��ȣ�� �Է����ּ���.");
			jtfPhoneBehind.requestFocus();
			return false;
		} // end if
		if ("".equals(jtfDetailDel.getText().trim())) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "���ּҸ� �Է����ּ���.");
			jtfDetailDel.requestFocus();
			return false;
		} // end if

		if (jtfDemand.getText().trim().length() >= 20) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "��û������ 20�� �̳��� �ۼ����ּ���.");
			jtfDemand.requestFocus();
			return false;
		} // end if

		if (new String(jtfCardTwo.getPassword()).trim().length() < 4) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "ī���ȣ�� Ȯ�����ּ���.");
			jtfCardTwo.requestFocus();
			return false;
		} // end if

		if (new String(jtfCardFour.getPassword()).trim().length() < 4) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "ī���ȣ�� Ȯ�����ּ���.");
			jtfCardFour.requestFocus();
			return false;
		} // end if

		if (new String(jpfCVC.getPassword()).trim().length() < 3) {
			JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "CVC�� Ȯ�����ּ���.");
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
			uDAO.insertNewOrdering(bgiVO);
			// ������ �ߴٸ� �ֱ� oCode��������
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

	private UnCompleteOrderInformVO completeBuyGoods(String oCode) {
		UnCompleteOrderInformVO ucoiVO = null;
		UserDAO uDAO = UserDAO.getInstance();
		try {

			ucoiVO = uDAO.selectEmptyInform(oCode);
			if (ucoiVO == null) {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "DBMS ���� �߻�");
			} // end if

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return ucoiVO;
	}// completeBuyGoods

	private boolean useMoneyUpdate(String memId, int useMoney) {
		boolean updateFlag = false;
		UserDAO uDAO = UserDAO.getInstance();
		try {

			updateFlag = uDAO.updateUseMoney(memId, useMoney);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return updateFlag;
	}// useMoneyUpdate

	@Override
	public void actionPerformed(ActionEvent ae) {

		sniDTO.setmName(pv.getJtfOrder().getText().trim());
		sniDTO.setmDetailAddr(pv.getJtfDetailDel().getText().trim());
		int zipSeq = 0;
		String orderPhone = "";
		String cardCode = "";
		String orderCode = "";
		int orderGoodsQuantity = sniDTO.getmQuantity();
		int goodsInventory = 0;
		String delivery = "";
		String zipcode = "";
		String deliveryDemand = "";

		if (ae.getSource() == pv.getJbtnPay() || ae.getSource() == pv.getJtfCardCVC()) {// �����ϱ� ��ư
			if (JOptionPane.showConfirmDialog(UserGoodsMainEvt.ugmv, "��ǰ�� �����Ͻðڽ��ϱ�?") == JOptionPane.YES_OPTION) {
				String cardMethod = "";
				UnCompleteOrderInformVO ucoiVO = null;
				CompleteOrderInformDTO coiDTO = null;

				if (!chkInputData()) {// �Է� �� ����
					return;
				} // end if

				delivery = pv.getJtfDelivery().getText().trim();
				zipcode = pv.getJtfzipcode().getText().trim();
				cardMethod = pv.getJcbCard().getSelectedItem().toString().trim();
				deliveryDemand = pv.getJtfDemand().getText().trim();

				cardCode = chkInputCard();
				if ("".equals(cardCode) || cardCode == null) {
					JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "�Է��� ī�������� ��ġ���� �ʽ��ϴ�.");
					return;
				} // end if

				goodsInventory = buyGoodsInventory(sniDTO.getgCode().trim());
				if (orderGoodsQuantity > goodsInventory) {
					JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv,
							"��ǰ ����� �ʰ��Ͽ����ϴ�. �˼��մϴ�. (��� : " + goodsInventory + "��)");
					return;
				} // end if

				zipSeq = addrSearchSeq(zipcode, delivery);
				orderPhone = pv.getJcbPhoneNum().getSelectedItem().toString().trim() + "-"
						+ pv.getJtfPhoneFront().getText().trim() + "-" + pv.getJtfPhoneBehind().getText().trim();

				BuyGoodsInformVO bgiVO = new BuyGoodsInformVO(sniDTO.getgCode(), orderPhone, sniDTO.getmDetailAddr(),
						deliveryDemand, sniDTO.getmName(), sniDTO.getmId(), sniDTO.getmQuantity(),
						sniDTO.getTotalMoney(), zipSeq);

				orderCode = paymentNewGoods(bgiVO);

				if (!updateGoodsInventory(sniDTO.getgCode().trim(), orderGoodsQuantity)) {
					JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "DBMS ���� �߻�");
					return;
				} // end if

				insertNewOrderPay(orderCode, cardCode);// ���� ����, �ֹ� �ڵ� ���̺� ���
				// ��� �ݾ� ������Ʈ
				if (!useMoneyUpdate(UserGoodsMainView.id, sniDTO.getTotalMoney())) {
					JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "��� �ݾ� ������Ʈ �����߻�");
					return;
				} // end if

				// ��ǰ ����, ������, �ֹ� ����
				ucoiVO = completeBuyGoods(orderCode);

				coiDTO = new CompleteOrderInformDTO();
				coiDTO.setgImg(ucoiVO.getgImg());
				coiDTO.setgName(sniDTO.getgName());
				coiDTO.setmName(ucoiVO.getmName());
				coiDTO.setoAddressee(sniDTO.getmName());
				coiDTO.setoCode(orderCode);
				coiDTO.setoDate(ucoiVO.getoDate());
				coiDTO.setoDemand(deliveryDemand);
				coiDTO.setoDetailAddr(delivery + " " + sniDTO.getmDetailAddr() + " (" + zipcode + ")");
				coiDTO.setoPhone(orderPhone);
				coiDTO.setoQuantity(sniDTO.getmQuantity());
				coiDTO.setoTotalMoney(sniDTO.getTotalMoney());
				coiDTO.setpMethod(cardMethod);

				pv.dispose();

				new PayCompleteView(coiDTO);

			} // end joptionpane yes
		} // end if

		if (ae.getSource() == pv.getJbtnCancel()) {// ��� ��ư
			pv.dispose();
		} // end if

		if (ae.getSource() == pv.getJcbCard()) {// ī�� ���� �޺��ڽ�
			cardSelectInform(pv.getJcbCard().getSelectedItem().toString().trim());
		} // end if

		if (ae.getSource() == pv.getJbtnaddr()) {// �ּ� �˻� ��ư
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
