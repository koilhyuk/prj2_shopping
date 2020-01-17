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
		if (!lf.getJtfName().getText().equals("")) {// 이름이 빈칸이 아니라면
			lf.getJtfPhoneH1().requestFocus();// 값이 존재한다면 커서를 전화번호로 이동
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
				JOptionPane.showMessageDialog(lf, "회원님의 아이디는 [ " + id + " ] 입니다");
				lf.dispose();
			} else {
				lf.getJtfName();
				lf.getJtfPhoneH1().setText("");
				lf.getJtfPhoneH2().setText("");
				JOptionPane.showMessageDialog(lf, "옳지 않은 정보입니다");
				lf.getJtfName().requestFocus();// 커서를 아이디에 위치
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
			switch (JOptionPane.showConfirmDialog(lf, "아이디 찾기 창을 종료하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				lf.dispose();
			}// end switch
		} // end if

	}// actionPerformed
}// class
