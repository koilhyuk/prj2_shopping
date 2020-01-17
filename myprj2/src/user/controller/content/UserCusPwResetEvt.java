package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.content.UserCusPwResetView;
import user.vo.login.LoginPwResetVO;

public class UserCusPwResetEvt implements ActionListener {
	private UserCusPwResetView lpr;
	private String id;

	public UserCusPwResetEvt(UserCusPwResetView lpr, String id) {
		this.lpr = lpr;
		this.id = id;
	}// UserCusPwResetEvt

	public void pwReset() {
		String pass = lpr.getJtfNewPw().getText().trim();
		String cipherText = "";
		try {
			cipherText = DataEncrypt.messageDigest("MD5", pass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch
			// 암호화
		if (pass.isEmpty() || !pass.equals(lpr.getJtfNewPwConfirm().getText())) {
			JOptionPane.showMessageDialog(lpr, "비밀번호와 비밀번호 확인이 일치하지 않습니다");
			lpr.getJtfNewPw().setText("");
			lpr.getJtfNewPwConfirm().setText("");
			lpr.getJtfNewPw().requestFocus();
			return;
		} //
		LoginPwResetVO lprVO = new LoginPwResetVO(id, cipherText);
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.updateResetPw(lprVO)) {
				JOptionPane.showMessageDialog(lpr, "비밀번호가 재설정 되었습니다");
				lpr.dispose();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(lpr, "비밀번호가 변경되지 않았습니다");
		} // end catch
	}// pwReset

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lpr.getJbtConfirm()) {// 확인 버튼 클릭
			pwReset();
		} // end if

		if (ae.getSource() == lpr.getJbtCancle()) {// 취소 버튼 클릭
			switch (JOptionPane.showConfirmDialog(lpr, "비밀번호 재설정 창을 종료하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				lpr.dispose();
			}// end switch
		} // end if
	}// actionPerformed

}// class
