package user.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.login.LoginFound;
import user.view.login.LoginPwReset;
import user.vo.login.LoginFoundPwVO;

public class LoginFoundEvt2 extends KeyAdapter implements ActionListener {
	private LoginFound lf;

	public LoginFoundEvt2(LoginFound lf) {
		this.lf = lf;
	}// LoginFoundEvt

	public void foundPw() {
		String name = lf.getJtfName2().getText();
		String phone = lf.getJtfPhoneF2().getText() + "-" + lf.getJtfPhoneH3().getText() + "-"
				+ lf.getJtfPhoneH4().getText();
		String id = lf.getJtfId2().getText();
		LoginFoundPwVO lfpVO = new LoginFoundPwVO(name, id, phone);

		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.selectPwFound(lfpVO)) {// �Է��� ������ �����ϴٸ�
				switch (JOptionPane.showConfirmDialog(lf, "��й�ȣ�� �缳�� �Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
					new LoginPwReset(id, lf);// ��й�ȣ �缳�� â
					break;
				}// switch
			} else {// �ٸ��ٸ�
				lf.getJtfId2().setText("");
				lf.getJtfName2().setText("");
				lf.getJtfPhoneH3().setText("");
				lf.getJtfPhoneH4().setText("");
				JOptionPane.showMessageDialog(lf, "���� ���� �����Դϴ�");
				lf.getJtfId2().requestFocus();// Ŀ���� ���̵� ��ġ
			} // end else
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	} // end if

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lf.getJbtConfirm2()) {
			foundPw();
		} // end if

		if (ae.getSource() == lf.getJbtCancle2()) {
			switch (JOptionPane.showConfirmDialog(lf, "��й�ȣ ã�� â�� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				lf.dispose();
			}// end if
		} // end switch
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent ke) {
		if (ke.getSource() == lf.getJtfPhoneF2()) {
			if (lf.getJtfPhoneF2().getText().length() >= 2) {
				lf.getJtfPhoneH3().requestFocus();
			} // end if
		} // end if
		if (ke.getSource() == lf.getJtfPhoneH3()) {
			if (lf.getJtfPhoneH3().getText().length() >= 3) {
				lf.getJtfPhoneH4().requestFocus();
			}
		} // end if
		if (ke.getSource() == lf.getJtfPhoneH4()) {
			if (lf.getJtfPhoneH4().getText().length() >= 3) {

			} // end if
		} // end if

	}// keyTyped
}// class
