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
	
	// ����
	public void modifyMyPage() {
		String id = mpv.getJtfId().getText().trim();
		String pass = mpv.getJtfPassword().getText().trim();
		String newpass = mpv.getJtfChangePass().getText().trim();
		String addr = mpv.getJtfAddr().getText().trim();
		String birth = mpv.getJtfBirth().getText().trim();
		String phone = "-" + mpv.getJtfPhoneFront().getText() + "-" + mpv.getJtfPhoneBehind().getText();
		String email = mpv.getJtfEmail().getText().trim();
		String cipherText = "";// ��ȣȭ
		
		try {
			cipherText = DataEncrypt.messageDigest("MD5", newpass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();

			MyPageUpdateVO mpuVO = new MyPageUpdateVO(id, pass, newpass, addr, birth, phone, email, cipherText);
			ClientDAO cDAO = ClientDAO.getInstance();

			try {
				if (cDAO.updateMyPage(mpuVO)) {
					JOptionPane.showMessageDialog(mpv, "������ �Ϸ�Ǿ����ϴ�");
					mpv.dispose();
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(mpv, "������ �����Ͽ����ϴ�");
			} // end catch
		} // end switch
	}// modifyMyPage

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mpv.getJbtChange()) {// ���� ��ư Ŭ��
			if (mpv.getJtfPassword().getText().equals(mpv.getJtfChangePass().getText())) {
				modifyMyPage();
			} else {
				JOptionPane.showMessageDialog(mpv, "�Էµ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			} // end if
		} // end if

		if (ae.getSource() == mpv.getJbtClose()) {// ��� ��ư Ŭ��
			switch (JOptionPane.showConfirmDialog(mpv, "��й�ȣ �缳�� â�� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				mpv.dispose();
			}// end switch
		} // end if
	}// actionPerformed
}// class








