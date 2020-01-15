package user.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.login.JoinDetailView;
import user.view.login.ZipcodeSearchView;
import user.vo.login.JoinDetailVO;
import user.vo.login.SearchAddrVO;

public class JoinDetailEvt extends KeyAdapter implements ActionListener {
	private JoinDetailView jdv;
	private String  phone, addr, email;
	private boolean flag;
	private ZipcodeSearchView scv; 

	public JoinDetailEvt(JoinDetailView jdv) {
		this.jdv = jdv;
	}// JoinDetailEvt

	ClientDAO cDAO = ClientDAO.getInstance();

	private void joinConfirm() { // 회원가입 확인
		JoinDetailVO jdVO = null;
		String id = jdv.getJtfId().getText().trim();// 아이디

		String tempGen = ""; // 성별
//		String addrSeq = (String) jdv.getJtfZipcode().getText();
		String addrDetail= jdv.getJtfAddress().getText();// 
		String addr= jdv.getJtfAddr().getText();//상세
		
		String zipcode= jdv.getJtfZipcode().getText();
		String inputPw = new String(jdv.getJpfPw().getPassword());// 비밀번호 보호
		String inputPw2= new String(jdv.getJpfPwConfirm().getPassword());
		String cipherText = "";// 암호화
		String gendername = "";// 성별
		int zeq =0;
		if (inputPw.isEmpty()) {
			JOptionPane.showMessageDialog(jdv, "비밀번호를 입력해주세요.");
			jdv.getJpfPw().setText("");
			return;
		} // end if
		if(inputPw.length()<5) {
			JOptionPane.showMessageDialog(jdv, "비밀번호는 5자리 이상으로 입력해주세요");
			return;
		}
		if (inputPw2.isEmpty()) {
			JOptionPane.showMessageDialog(jdv, "비밀번호를 입력해주세요.");
			jdv.getJpfPwConfirm().setText("");
			return;
		} // end if
		if (!inputPw2.equals(inputPw)) {// 동일하지 않으면
			JOptionPane.showMessageDialog(jdv, "동일하지 않은 비밀번호입니다. ");
			jdv.getJpfPwConfirm().setText("");
			return;
		} // end if
		
		try {
			///////////////////// 비밀번호 /////////////////////////////
			cipherText = DataEncrypt.messageDigest("MD5", inputPw);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch
//////////////////이름
		String name = jdv.getJtfName().getText().trim();
		if (name.length() > 5) {
			JOptionPane.showMessageDialog(jdv, "이름은 5자 이내로 작성해주세요.");
			return;
		} // end if
			///////////////// 생일
		String birth = jdv.getJtfBirthYear().getText().trim() + "-" + jdv.getJtfBirthMonth().getText().trim() + "-"
				+ jdv.getJtfBirthday().getText().trim();
		if (jdv.getJtfBirthYear().getText().length() == 4) {// 생년의 4자리가 입력되면
			jdv.getJtfBirthMonth().requestFocus(); // 월로 넘어간다.
		} else {
			JOptionPane.showMessageDialog(jdv, "생년월일을 올바르게 입력해주세요 ex)1990-01-01");
			return;
		} // end if
			//////////////// 성별
		if (jdv.getBgGender().getSelection() == jdv.getJrbF()) {
			gendername = "여자";// 여자
		} else {
			gendername = "남자";
		} // end if
		if (gendername.equals("여자")) {
			tempGen = "F";
		} else if (gendername.equals("남자")) {
			tempGen = "M";
		} // end if

		phone = jdv.getJtfPhoneNum().getText().trim() + "-" + jdv.getJtfPhoneNum1().getText().trim() + "-"
				+ jdv.getJtfPhoneNum2().getText().trim();
		addr = jdv.getJtfAddr().getText().trim();
		if (addr == null) {
			JOptionPane.showMessageDialog(jdv, "상세 주소를 입력해주세요");
			return;
		} // end if
		email = jdv.getJtfEmail().getText().trim();
		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(jdv, "올바른 이메일 형식으로 입력해주세요");
			return;
		} // end if
		if (email == null) {
			JOptionPane.showMessageDialog(jdv, "이메일을 입력해주세요");
			return;
		} // end if

		ClientDAO cDAO = ClientDAO.getInstance();
		

		try {
			zeq=cDAO.seqSearch(zipcode, addrDetail);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//end catch
		
		jdVO = new JoinDetailVO(id, cipherText,name, birth, tempGen, phone, addr,email, zeq);
//		if (jdv.getJtfId().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "아이디를 입력해주세요");
//		} else if (jdv.getJpfPw().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "비밀번호를 입력해주세요");
//		} else if (jdv.getJpfPwConfirm().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "비밀번호확인을 입력해주세요");
//		} else if (jdv.getJtfName().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "이름 입력해주세요");
//		} else if (jdv.getJtfBirthYear().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "생일을 입력해주세요");
//		} else if (jdv.getJtfBirthMonth().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "생일을 입력해주세요");
//		} else if (jdv.getJtfBirthday().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "생일을 입력해주세요");
//		} else if (jdv.getJtfPhoneNum1().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "전화번호를 입력해주세요");
//		} else if (jdv.getJtfPhoneNum2().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "전화번호를 입력해주세요");
//		} else if (jdv.getJtfAddr().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "주소를 입력해주세요");
//		} else if (jdv.getJtfEmail().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "이메일을 입력해주세요");
//		} else {
//		}

		try {
			cDAO.insertMemJoin(jdVO);
				JOptionPane.showMessageDialog(jdv, "축하합니다 회원가입이 완료되었습니다");
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(jdv, "회원가입에 실패하셨습니다.");
			e.printStackTrace();
		}

//			JOptionPane.showMessageDialog(jdv, "DBMS에서 문제 발생");


	}// joinConfirm


	private void chkId() {
		String id = jdv.getJtfId().getText().trim(); // 아이디
		ClientDAO cDAO = ClientDAO.getInstance();


		try {
			if (jdv.getJtfId() == null) { // 공백이면
				JOptionPane.showMessageDialog(jdv, "아이디를 입력해주세요");
				return;
			} // end if
				if(cDAO.idConfrim(id)) {
				JOptionPane.showMessageDialog(jdv, "사용불가능한 아이디입니다");
				jdv.getJtfId().getText();
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();
			} else {
				JOptionPane.showMessageDialog(jdv, "사용가능한 아이디입니다");
				flag=true;
				jdv.getJpfPw().requestFocus();
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// chkId

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jdv.getJbtIdConfirm()) {// 중복버튼
			chkId();//
//			JOptionPane.showMessageDialog(jdv, "사용가능한 아이디입니다");
		} // end if

		if (ae.getSource() == jdv.getJbtConfirm()) {// 확인
			if(flag) {
				joinConfirm();
			}else {
				JOptionPane.showMessageDialog(jdv, "아이디 중복체크를 해주세요.");
				return;
			}//end if
//			JOptionPane.showMessageDialog(jdv, "축하합니다 회원가입이 완료되었습니다");
		} // end if

		if (ae.getSource() == jdv.getJbtClose()) {// 취소
			JOptionPane.showConfirmDialog(jdv, "회원가입창을 닫으시겠습니끼?");
			jdv.dispose();
		} // end if
		if (ae.getSource() == jdv.getJbtnSearchAddr()) {
			new ZipcodeSearchView(jdv);
		} // end if

	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent ke) {
		JTextField jtfYear = jdv.getJtfBirthYear();
		JTextField jtfMonth = jdv.getJtfBirthMonth();
		JTextField jtfDay = jdv.getJtfBirthday();
		JTextField jtfPhone = jdv.getJtfPhoneNum();
		JTextField jtfPhone1 = jdv.getJtfPhoneNum1();
		JTextField jtfPhone2 = jdv.getJtfPhoneNum2();
		if (ke.getSource() == jtfYear) {
			if (jtfYear.getText().trim().length() >= 4) {
				ke.consume();
				jtfMonth.requestFocus();
			} // end if
		} // end if
		if (ke.getSource() == jtfMonth) {
			if (jtfMonth.getText().trim().length() >= 2) {
				ke.consume();
				jtfDay.requestFocus();
			} // end if
		} // end if
		if (ke.getSource() == jtfDay) {
			if (jtfDay.getText().trim().length() >= 2) {
				ke.consume();
			} // end if
		} // end if
		if (ke.getSource() == jtfPhone) {
			if (jtfPhone.getText().trim().length() >= 3) {
				ke.consume();
				jtfPhone1.requestFocus();
			} // end if
		} // end if
		if (ke.getSource() == jtfPhone1) {
			if (jtfPhone1.getText().trim().length() >= 4) {
				ke.consume();
				jtfPhone2.requestFocus();
			} // end if
		} // end if
		if (ke.getSource() == jtfPhone2) {
			if (jtfPhone2.getText().trim().length() >= 4) {
				ke.consume();
				jdv.getJtfEmail().requestFocus();
			} // end if
		} // end if
	}// keyTyped

}// class
