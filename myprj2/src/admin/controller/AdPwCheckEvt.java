package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.dao.AdDAO;
import admin.run.StaticCla;
import admin.view.AdPwCheckView;
import admin.view.AdResetPwView;
import kr.co.sist.util.cipher.DataEncrypt;

public class AdPwCheckEvt implements ActionListener {

	private AdPwCheckView apcw;

	public AdPwCheckEvt(AdPwCheckView apcw) {
		this.apcw = apcw;
	}// AdPwCheckEvt

	private void pwCheck(String pw) {
		String transPw = "";

		try {
			transPw = DataEncrypt.messageDigest("MD5", pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} // end catch

		AdDAO aDAO = AdDAO.getInstance();

		try {
			if (aDAO.adCheckPw(transPw)) {
				// 재설정 창
				apcw.dispose();
				new AdResetPwView();
			} else {
				JOptionPane.showMessageDialog(StaticCla.mv, "비밀번호가 일치하지 않습니다.");
				apcw.getJtpPw().setText("");
				apcw.getJtpPw().requestFocus();
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// pwCheck

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == apcw.getJtpPw() || e.getSource() == apcw.getBtnConfirm()) {
			pwCheck(new String(apcw.getJtpPw().getPassword()));
		} // end if
		if (e.getSource() == apcw.getBtnClose()) {
			apcw.dispose();
		} // end if

	}// actionPerformed

}// class
