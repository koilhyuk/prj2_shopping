package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.content.UserCusDataView;
import user.view.content.UserCusPwResetView;
import user.view.content.ZipcodeSearchCusView;
import user.view.login.ClientLoginView;
import user.vo.content.UpdateCusDataVO;

public class UserCusDataEvt implements ActionListener {
	private UserCusDataView ucd;
	private String id;

	public UserCusDataEvt(UserCusDataView ucd, String id) {
		this.ucd = ucd;
		this.id = id;
	}// UserCusDataEvt

	// 수정
	// m_phone,m_detail_addr,m_email , z_seq, m_id,z_zipcode, z_addr
	public void modifyMyPage() {
		String id = ucd.getJtfId().getText().trim();
		String addr = ucd.getJtfAddr().getText().trim();
		String email = ucd.getJtfEmail().getText().trim();
		String detail_addr = ucd.getJtfAddress().getText().trim();
		String phone1 = ucd.getJcbPhoneNum().getSelectedItem().toString();
		String phone2 = "-" + ucd.getJtfPhoneNum1().getText().trim();
		String phone3 = "-" + ucd.getJtfPhoneNum2().getText().trim();
		String zipcode = ucd.getJtfZipcode().getText().trim();
		String strPhone2 = phone2.substring(1);
		String strPhone3 = phone3.substring(1);

		if (addr.isEmpty()) {
			JOptionPane.showMessageDialog(ucd, "주소를 입력해주세요");
			ucd.getJtfAddr().setText("");
			ucd.getJtfAddr().requestFocus();
			return;
		} // end if
		if (zipcode.isEmpty()) {
			JOptionPane.showMessageDialog(ucd, "주소를 입력해주세요");
			ucd.getJtfZipcode().setText("");
			ucd.getJtfZipcode().requestFocus();
			return;
		} // end if
		if (email.isEmpty()) {
			JOptionPane.showMessageDialog(ucd, "이메일을 올바르게 입력해주세요");
			ucd.getJtfEmail().setText("");
			ucd.getJtfEmail().requestFocus();
			return;
		} else if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(ucd, "이메일을 올바르게 입력해주세요");
			ucd.getJtfEmail().setText("");
			ucd.getJtfEmail().requestFocus();
			return;
		} // end if
		if (strPhone2.isEmpty()) {
			JOptionPane.showMessageDialog(ucd, "전화번호4자리를 입력해주세요");
			ucd.getJtfPhoneNum1().setText("");
			ucd.getJtfPhoneNum1().requestFocus();
			return;
		} else if (strPhone2.length() < 4 || strPhone2.length() > 4) {
			JOptionPane.showMessageDialog(ucd, "전화번호4자리를 입력해주세요");
			ucd.getJtfPhoneNum1().setText("");
			ucd.getJtfPhoneNum1().requestFocus();
			return;
		} // end if
		if (strPhone3.isEmpty()) {
			JOptionPane.showMessageDialog(ucd, "전화번호4자리를 입력해주세요");
			ucd.getJtfPhoneNum2().setText("");
			ucd.getJtfPhoneNum2().requestFocus();
			return;

		} else if (strPhone3.length() < 4 || strPhone3.length() > 4) {
			JOptionPane.showMessageDialog(ucd, "전화번호4자리를 입력해주세요");
			ucd.getJtfPhoneNum2().setText("");
			ucd.getJtfPhoneNum2().requestFocus();
			return;
		} // end if
		String phone = phone1 + phone2 + phone3;
		UpdateCusDataVO ucVO = new UpdateCusDataVO(phone, addr, email, id, zipcode, detail_addr);
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.updateCusData(ucVO)) {

				JOptionPane.showMessageDialog(ucd, "수정이 완료되었습니다");
				ucd.dispose();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// modifyMyPage

	/**
	 * 회원탈퇴
	 */
	public void cusWithdrawal() {
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (cDAO.deleteCus(id)) {
				JOptionPane.showMessageDialog(ucd, "성공적으로 회원탈퇴 되었습니다.");
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// cusWithdrawal

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ucd.getJbtConfirm()) {// 수정 버튼 클릭
			modifyMyPage();
		} // end if
		if (ae.getSource() == ucd.getJbtBack()) {// 확인 버튼 클릭
			ucd.dispose();
		} // end if

		if (ae.getSource() == ucd.getJbtWithdrawal()) {// 탈퇴 버튼 클릭
			switch (JOptionPane.showConfirmDialog(ucd, "회원 탈퇴를 하시겠습니까? \n ※회원탈퇴시 회원님의 모든 정보는 소멸됩니다.")) {
			case JOptionPane.OK_OPTION:
				cusWithdrawal();
				ucd.dispose();
				UserMyPageEvt.mdv.dispose();
				UserGoodsMainEvt.ugmv.dispose();
				new ClientLoginView();
			}// end switch
		} // end if
		if (ae.getSource() == ucd.getJbtnPass()) {// 비밀번호 수정
			new UserCusPwResetView(ucd, id);
		} // end if
		if (ae.getSource() == ucd.getJbtnSearchAddr()) {// 주소검색
			new ZipcodeSearchCusView(ucd);
		} // end if
	}// actionPerformed
}// class
