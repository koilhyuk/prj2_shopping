package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.content.MyPageView;
import user.vo.content.MyPageUpdateVO;

public class MyPageEvt implements ActionListener {
	private MyPageView mpv;
	private String id, pass, newpass, addr, birth, phone, email;

	public MyPageEvt(MyPageView mpv) {
		this.mpv = mpv;
		this.id = id;
//		this.pass = pass;
	}// myPageEvt
	
	// 수정
	public void modifyMyPage() {
		String id = mpv.getJtfId().getText().trim();
		String pass = mpv.getJtfPassword().getText().trim();
		String newpass = mpv.getJtfChangePass().getText().trim();
		String addr = mpv.getJtfAddr().getText().trim();
		String birth = mpv.getJtfBirth().getText().trim();
		String phone = "-" + mpv.getJtfPhoneFront().getText() + "-" + mpv.getJtfPhoneBehind().getText();
		String email = mpv.getJtfEmail().getText().trim();
		String cipherText = "";// 암호화
		
		try {
			cipherText = DataEncrypt.messageDigest("MD5", newpass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();

			MyPageUpdateVO mpuVO = new MyPageUpdateVO(id, pass, newpass, addr, birth, phone, email, cipherText);
			ClientDAO cDAO = ClientDAO.getInstance();

			try {
				if (cDAO.updateMyPage(mpuVO)) {
					JOptionPane.showMessageDialog(mpv, "수정이 완료되었습니다");
					mpv.dispose();
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(mpv, "수정을 실패하였습니다");
			} // end catch
		} // end switch
	}// modifyMyPage

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mpv.getJbtChange()) {// 수정 버튼 클릭
			if (mpv.getJtfPassword().getText().equals(mpv.getJtfChangePass().getText())) {
				modifyMyPage();
			} else {
				JOptionPane.showMessageDialog(mpv, "입력된 비밀번호가 일치하지 않습니다");
			} // end if
		} // end if

		if (ae.getSource() == mpv.getJbtClose()) {// 취소 버튼 클릭
			switch (JOptionPane.showConfirmDialog(mpv, "비밀번호 재설정 창을 종료하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				mpv.dispose();
			}// end switch
		} // end if
	}// actionPerformed
}// class








