package user.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.login.LoginFound;
import user.view.login.LoginPwReset;
import user.vo.login.LoginPwResetVO;

public class LoginPwResetEvt implements ActionListener {
	private LoginPwReset lpr;
	private String id, pass;
	private LoginFound lf;

	public LoginPwResetEvt(LoginPwReset lpr, String id, LoginFound lf) {
		this.lpr = lpr;
		this.id = id;
		this.lf = lf;
	}// LoginPwResetEvt

	public void pwReset() {
		pass = lpr.getJtfNewPw().getText().trim();
		String cipherText = "";
		try {
			cipherText = DataEncrypt.messageDigest("MD5", pass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch
			// ��ȣȭ

		LoginPwResetVO lprVO = new LoginPwResetVO(id, cipherText);
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.updateResetPw(lprVO)) {
				JOptionPane.showMessageDialog(lpr, "��й�ȣ�� �缳�� �Ǿ����ϴ�");
				lpr.dispose();
				lf.dispose();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(lpr, "��й�ȣ�� ������� �ʾҽ��ϴ�");
		} // end catch
	}// pwReset

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lpr.getJbtConfirm()) {// Ȯ�� ��ư Ŭ��
			if (lpr.getJtfNewPw().getText().equals(lpr.getJtfNewPwConfirm().getText())) {
				pwReset();
			} else {
				JOptionPane.showMessageDialog(lpr, "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�");
			} // end if
		} // end if

		if (ae.getSource() == lpr.getJbtCancle()) {// ��� ��ư Ŭ��
			switch (JOptionPane.showConfirmDialog(lpr, "��й�ȣ �缳�� â�� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				lpr.dispose();
			}// end switch
		} // end if
	}// actionPerformed

}// class
