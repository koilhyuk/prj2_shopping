package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

		String g_name = String.valueOf(ugdv.getJlGName().getText());
		String g_price = String
				.valueOf(ugdv.getJlGPrice().getText().substring(0, ugdv.getJlGPrice().getText().lastIndexOf("��")));
		System.err.println("����================================" + g_price);
		String g_su = String.valueOf(ugdv.getJtfSelectNum().getText());

		sniDTO.setgCode(gCode);
		sniDTO.setgName(g_name);
		sniDTO.setmQuantity(Integer.parseInt(g_su));
		sniDTO.setTotalMoney(Integer.parseInt(g_price) * Integer.parseInt(g_su));
		sniDTO.setgPrice(Integer.parseInt(g_price));
		sniDTO.setmId(UserGoodsMainView.id);

		UserDAO uDAO = UserDAO.getInstance();
		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();
		try {
			sniDTO = uDAO.searchBuyGoodsInform(sniDTO);
			sniDTO.setCardList(uDAO.selectMemberCard(sniDTO));
			if (sniDTO.getCardList().size() == 0) {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "��� �� ���������� �����ϴ�. ���� ������ּ���.");
				ugdv.dispose();
				return;
			}
			String tempCardNum = sniDTO.getCardList().get(0).getCarNum();
			DataDecrypt dd = new DataDecrypt(UserGoodsMainView.KEY);
			sniDTO.getCardList().get(0).setCarNum(dd.decryption(tempCardNum));
			zipcodeData = saDAO.zipCodeSearch(sniDTO.getmSeq());
			int getsu = Integer.parseInt(g_su);
			if (getsu > 0) {
				ugdv.dispose();// ����â �ݱ�
				new PayView(sniDTO, zipcodeData);
			} else {
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "1�� �̻� ��ǰ�� �����Ͽ����մϴ�.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

	}// UserGoodsDetailView

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ugdv.getJbtnBuy()) {
			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// ȸ��
				sellGoodsProcess();
			} else {// ��ȸ���� �� �α��� �ϰ� �ϱ�
				JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "��ǰ ���Ŵ� �α��� �� �����մϴ�. �α��� ���ּ���.");
				ugdv.dispose();// ����â �ݱ�
				new LoginDialogView(UserGoodsMainEvt.ugmv, UserGoodsMainEvt.rt);
			} // end else
		} // end if

		if (ae.getSource() == ugdv.getJbtnMinus()) {
			int minus = Integer.parseInt(ugdv.getJtfSelectNum().getText());
			if (minus > 1) {
				minus = minus - 1;
				ugdv.getJtfSelectNum().setText(String.valueOf(minus));
			} // end if
		} // end if

		if (ae.getSource() == ugdv.getJbtnPlus()) {
			int plus = Integer.parseInt(ugdv.getJtfSelectNum().getText());
			plus = plus + 1;
			ugdv.getJtfSelectNum().setText(String.valueOf(plus));
		} // end if
	}// action

}// class
