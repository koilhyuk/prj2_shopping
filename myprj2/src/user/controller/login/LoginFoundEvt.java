package user.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.login.LoginFound;
import user.vo.login.LoginFoundIdVO;

public class LoginFoundEvt implements ActionListener {
	private LoginFound lf;

	public LoginFoundEvt(LoginFound lf) {
		this.lf = lf;
	}// LoginFoundEvt

	public void foundId() {
		if (!lf.getJtfName().getText().equals("")) {// �̸��� ��ĭ�� �ƴ϶��
			lf.getJtfPhoneH1().requestFocus();// ���� �����Ѵٸ� Ŀ���� ��ȭ��ȣ�� �̵�
		} // end if

		String name = lf.getJtfName().getText();
		String phone = lf.getJtfPhoneF1().getText() + "-" + lf.getJtfPhoneH1().getText() + "-"
				+ lf.getJtfPhoneH2().getText();
		String id = "";
		LoginFoundIdVO lfIVO = new LoginFoundIdVO(name, phone, id);

		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			id = cDAO.selectIdFound(lfIVO);
			if (!id.isEmpty()) {
				JOptionPane.showMessageDialog(lf, "ȸ������ ���̵�� [ " + id + " ] �Դϴ�");
				lf.dispose();
			} else {
				lf.getJtfName();
				lf.getJtfPhoneH1().setText("");
				lf.getJtfPhoneH2().setText("");
				JOptionPane.showMessageDialog(lf, "���� ���� �����Դϴ�");
				lf.getJtfName().requestFocus();// Ŀ���� ���̵� ��ġ
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	} // end if

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lf.getJbtConfirm()) {
			foundId();
		} // end if

		if (ae.getSource() == lf.getJbtCancle()) {
			switch (JOptionPane.showConfirmDialog(lf, "���̵� ã�� â�� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				lf.dispose();
			}// end switch
		} // end if

	}// actionPerformed
}// class
