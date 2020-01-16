package user.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.content.UserGoodsMainView;
import user.view.login.ClientLoginView;
import user.view.login.JoinDetailView;
import user.view.login.LoginFound;
import user.vo.login.LoginUpdateIpVO;
import user.vo.login.LoginVO;

public class ClientLoginEvt implements ActionListener {
	private ClientLoginView clv;
	private String inputId;

	public ClientLoginEvt(ClientLoginView clv) {
		this.clv = clv;
	}// clvientLoginEvt

	private void checkMember() {
		LoginUpdateIpVO luVO = null;
		String inputPw = new String(clv.getJpfPw().getPassword());
		inputId = clv.getJtfId().getText().trim();
		String cipherText = "";// ��ȣȭ
		String curIp = "";
		try {
			cipherText = DataEncrypt.messageDigest("MD5", inputPw);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch

		LoginVO lVO = new LoginVO(inputId, cipherText);
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			String id = cDAO.selectLogin(lVO);

			if (!id.isEmpty()) {
				try {
					curIp = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				luVO = new LoginUpdateIpVO(id, curIp);
				updateIp(luVO);

				new UserGoodsMainView(id, curIp);
				clv.dispose();
			} else {
				clv.getJtfId().setText("");
				clv.getJpfPw().setText("");
				JOptionPane.showMessageDialog(clv, "���̵� ��й�ȣ�� Ȯ���ϼ���");
				clv.getJtfId().requestFocus();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// checkMember

	private void updateIp(LoginUpdateIpVO luVO) {
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.updateMemIp(luVO)) {
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// updateIp

	private void loginNonMember() {
		String curIp = "";
		ClientDAO cDAO = ClientDAO.getInstance();

		try {
			curIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			cDAO.insertNonMem(curIp);
			new UserGoodsMainView("", curIp);
			clv.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// loginNonMember

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == clv.getJtfId()) {// ���̵�

			if (!clv.getJtfId().getText().equals("")) {// ���̵�ĭ�� ����ִ��� ����
				clv.getJpfPw().requestFocus(); // ���������ϸ� Ŀ���� ������� �̵�
			} // end if
		} // end if

		if (ae.getSource() == clv.getJpfPw() || ae.getSource() == clv.getJbtLogin()) {
			checkMember();
		} // end if

		if (ae.getSource() == clv.getJbtBLogin()) {// ��ȸ���α��ι�ư
			loginNonMember();
		} // end if

		if (ae.getSource() == clv.getJbtJoin()) {// ȸ�����Թ�ư
			new JoinDetailView(clv);
		} // end if

		if (ae.getSource() == clv.getJbtfound()) {
			new LoginFound(clv);
		}
	}// actionPerformed

}// class
