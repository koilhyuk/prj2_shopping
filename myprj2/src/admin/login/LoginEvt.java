package admin.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.view.MainView;
import kr.co.sist.util.cipher.DataEncrypt;


public class LoginEvt implements ActionListener {

	private LoginView lv;

	public LoginEvt(LoginView lv) {
		this.lv = lv;
	}// loginEvt

	private void checkAdmin() {
		String curIp = "";
		String inputPw = new String(lv.getJpfPw().getPassword());
		String inputId = lv.getJtfId().getText().trim();
		String encPw = "";
		try {
			encPw = DataEncrypt.messageDigest("MD5", inputPw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} // end catch

		LoginVO lVO = new LoginVO(inputId, encPw);
		LoginDAO lDAO = LoginDAO.getInstance();

		try {
			String name = lDAO.selectAdminId(lVO);
			if (!name.isEmpty()) {
				try {
					curIp = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				new MainView(name, curIp);
				lv.dispose();
			} else {
				lv.getJtfId().setText("");
				lv.getJpfPw().setText("");
				JOptionPane.showMessageDialog(lv, "아이디, 비밀번호를 확인해주세요.");
				lv.getJtfId().requestFocus();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// checkAdmin

	@Override
	public void actionPerformed(ActionEvent me) {
		if (me.getSource() == lv.getJbtLogin() || me.getSource() == lv.getJpfPw()) {
			checkAdmin();
		} // end if
	}// actionPerformed

}// class
