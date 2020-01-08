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
import user.dao.UserDAO;
import user.helper.RecentThread;
import user.view.content.UserGoodsMainView;
import user.view.login.LoginDialogView;
import user.vo.login.LoginUpdateIpVO;
import user.vo.login.LoginVO;

public class LoginDialogEvt implements ActionListener {

	private String inputId;

	private LoginDialogView ldv;
	private UserGoodsMainView ugmv;
	private RecentThread rt;

	public LoginDialogEvt(LoginDialogView ldv, UserGoodsMainView ugmv, RecentThread rt) {
		this.ldv = ldv;
		this.ugmv = ugmv;
		this.rt = rt;

	}// loginEvt
	

	private void deleteNonMember() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			uDAO.deleteNonMem();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// deleteNonMember

	private void deleteNmemRecent() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			uDAO.deleteNmemRe();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// deleteNonMember

	private void checkAdmin() {
		LoginUpdateIpVO luVO = null;
		String inputPw = new String(ldv.getJpfPw().getPassword());
		inputId = ldv.getJtfId().getText().trim();
		String encPw = "";
		String curIp = "";
		try {
			encPw = DataEncrypt.messageDigest("MD5", inputPw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} // end catch

		LoginVO ldvO = new LoginVO(inputId, encPw);

//		LoginDAO loDAO = LoginDAO.getInstance();
		ClientDAO cDAO = ClientDAO.getInstance();

		try {
			String outputId = cDAO.selectLogin(ldvO);
			if (!outputId.isEmpty()) {// 회원일 때
				try {
					curIp = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				luVO = new LoginUpdateIpVO(outputId, curIp);
				updateIp(luVO);
				ugmv.dispose();
				deleteNmemRecent();
				deleteNonMember();
				rt.setStop(true);
				new UserGoodsMainView(outputId, curIp);
				ldv.dispose();
			} else {// 아닐 때
				ldv.getJtfId().setText("");
				ldv.getJpfPw().setText("");
				JOptionPane.showMessageDialog(ldv, "아이디, 비밀번호를 확인해주세요.");
				ldv.getJtfId().requestFocus();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// checkAdmin

	private void updateIp(LoginUpdateIpVO luVO) {
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.updateMemIp(luVO)) {
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// updateIp

	@Override
	public void actionPerformed(ActionEvent me) {
		if (me.getSource() == ldv.getJbtLogin() || me.getSource() == ldv.getJpfPw()) {
			checkAdmin();
		} // end if
		if (me.getSource() == ldv.getJbtClose()) {
			ldv.dispose();
		} // end if
	}// actionPerformed

}// class
