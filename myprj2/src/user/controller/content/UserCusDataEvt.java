package user.controller.content;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import user.view.content.UserCusDataView;
import user.view.content.UserCusPwResetView;
import user.view.content.ZipcodeSearchCusView;

public class UserCusDataEvt implements ActionListener {
	private UserCusDataView ucd;
	private static String id;

	public UserCusDataEvt(UserCusDataView ucd, String id) {
		this.ucd = ucd;
		this.id = id;
	}// UserCusDataEvt
	
	// 수정
	public void modifyMyPage() {
		String id = ucd.getJtfId().getText().trim();
//		String pass = ucd.getJpfPw().getPassword().toString();
//		String newpass = ucd.getJpfPwConfirm().getPassword().toString();
		String addr = ucd.getJtfAddr().getText().trim();
		String birth = ucd.getJtfBirth().getText().trim();
//		String phone = "-" + ucd.getJtfPhoneFront().getText() + "-" + ucd.getJtfPhoneBehind().getText();
		String email = ucd.getJtfEmail().getText().trim();
		String cipherText = "";// 암호화
		String cipherText1 = "";// 암호화
		
//		try {
//			cipherText = DataEncrypt.messageDigest("MD5", pass);
//			cipherText1 = DataEncrypt.messageDigest("MD5", newpass);
//		} catch (NoSuchAlgorithmException e1) {
//			e1.printStackTrace();

//			MyPageUpdateVO mpuVO = new MyPageUpdateVO(id, pass, newpass, addr, birth, phone, email, cipherText);
//			ClientDAO cDAO = ClientDAO.getInstance();

//			try {
//				if (cDAO.updateMyPage(mpuVO)) {
//					JOptionPane.showMessageDialog(ucd, "수정이 완료되었습니다");
//					ucd.dispose();
//				} // end if
//			} catch (SQLException e) {
//				e.printStackTrace();
//				JOptionPane.showMessageDialog(ucd, "수정을 실패하였습니다");
//			} // end catch
//		} // end switch
	}// modifyMyPage

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ucd.getJbtConfirm()) {// 수정 버튼 클릭
			modifyMyPage();
//			if (ucd.getJtfPassword().getText().equals(ucd.getJtfChangePass().getText())) {
//				modifyMyPage();
//			} else {
//				JOptionPane.showMessageDialog(ucd, "입력된 비밀번호가 일치하지 않습니다");
//			} // end if
		} // end if

		if (ae.getSource() == ucd.getJbtWithdrawal()) {// 탈퇴 버튼 클릭
			switch (JOptionPane.showConfirmDialog(ucd, "회원 탈퇴를 하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
			
			}// end switch
		} // end if
		if (ae.getSource() == ucd.getJbtnPass()) {// 비밀번호 수정
				new UserCusPwResetView(ucd,id);
		} // end if
		if (ae.getSource() == ucd.getJbtnSearchAddr()) {// 주소검색
				new ZipcodeSearchCusView(ucd);
		} // end if
	}// actionPerformed
}// class








