package user.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.ClientDAO;
import user.view.login.JoinDetailView;
import user.view.login.ZipcodeSearchView;
import user.vo.login.JoinDetailVO;

public class JoinDetailEvt extends KeyAdapter implements ActionListener {
	private JoinDetailView jdv;
	private boolean flag;

	public JoinDetailEvt(JoinDetailView jdv) {
		this.jdv = jdv;
	}// JoinDetailEvt

	private void joinConfirm() { // 회원가입 확인
		JoinDetailVO jdVO = null;
		String id = jdv.getJtfId().getText().trim();// 아이디
		String tempGen ="";
		if (jdv.getBgGender().getSelection() == jdv.getJrbF()||jdv.getBgGender().isSelected(jdv.getJrbF().getModel())) { //성별
			tempGen = "F";
		} else {
			tempGen = "M";
		} // end if
		
		String addrDetail = jdv.getJtfAddr().getText().trim();//상세주소
		String addr = jdv.getJtfAddress().getText();//
		String zipcode = jdv.getJtfZipcode().getText();
		
		String inputPw = new String(jdv.getJpfPw().getPassword()).trim();// 비밀번호 보호
		String inputPw2= new String(jdv.getJpfPwConfirm().getPassword()).trim();
		String name = jdv.getJtfName().getText().trim();
		
		String birth = jdv.getJcbBirthYear().getSelectedItem() + "-" + jdv.getJcbBirthMonth().getSelectedItem() + "-"
				+ jdv.getJcbBirthDay().getSelectedItem(); //생년월일

		String phone = jdv.getJcbPhoneNum().getSelectedItem() + "-" + jdv.getJtfPhoneNum1().getText().trim() + "-"
				+ jdv.getJtfPhoneNum2().getText().trim();
		
		String email = jdv.getJtfEmail().getText().trim();
		
		int zeq = 0;
		//유효성 
		if (inputPw.trim().isEmpty()|| "".equals(inputPw.trim())) {//비밀번호가 빈칸일 때
			JOptionPane.showMessageDialog(jdv, "※비밀번호를 입력해주세요.");
			jdv.getJpfPw().setText("");
			jdv.getJpfPw().requestFocus();
			return;
		}else if (inputPw.trim().length() < 5||inputPw.trim().length() > 20) {//비밀번호는 5자리 이상으로 입력 
			JOptionPane.showMessageDialog(jdv, "※비밀번호는 5~20자 사이로 입력해주세요");
			jdv.getJpfPw().setText("");
			jdv.getJpfPw().requestFocus();
			return;
		}//end if
		if (inputPw2.isEmpty()||"".equals(inputPw2)) {//비밀번호 확인란이 빈칸일때
			JOptionPane.showMessageDialog(jdv, "※비밀번호를 한번 더 입력해주세요.");
			jdv.getJpfPwConfirm().setText("");
			jdv.getJpfPwConfirm().requestFocus();
			return;
		}//end if 
		if (!inputPw2.equals(inputPw)) {// 동일하지 않으면
			JOptionPane.showMessageDialog(jdv, "※비밀번호와 비밀번호 확인이 일치하지 않습니다. ");
			jdv.getJpfPwConfirm().setText("");
			jdv.getJpfPwConfirm().requestFocus();
			return;
		} // end if
		if(name.isEmpty()||"".equals(name)) {//이름이 빈칸일때
			JOptionPane.showMessageDialog(jdv, "※이름을 입력해주세요.");
			jdv.getJtfName().setText("");
			jdv.getJtfName().requestFocus();
			return;
		}else if (name.length() > 5) {//이름의 길이 
			JOptionPane.showMessageDialog(jdv, "※이름은 5자 이내로 작성해주세요.");
			jdv.getJtfName().setText("");
			jdv.getJtfName().requestFocus();
			return;
		} // end if
		
		if(jdv.getBgGender().getSelection()==null|| jdv.getBgGender().isSelected(null)) {// 성별을 선택하지 않았을때
			JOptionPane.showMessageDialog(jdv, "※성별을 선택해주세요.");
			return;
		}//end if

		if (addrDetail.isEmpty()||"".equals(addrDetail)) {//상세주소가 null일때
			JOptionPane.showMessageDialog(jdv, "※상세 주소를 입력해주세요");
			jdv.getJtfAddr().requestFocus();
			return;
		} // end if

		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(jdv, "※올바른 이메일 형식으로 입력해주세요");
			jdv.getJtfEmail().setText("");
			jdv.getJtfEmail().requestFocus();
			return;
		}else if (email.isEmpty()|| "".equals(email)) {
			JOptionPane.showMessageDialog(jdv, "※이메일을 입력해주세요");
			jdv.getJtfEmail().requestFocus();
			return;
		} // end if
		
		if(jdv.getJtfPhoneNum1().getText().trim().isEmpty()||"".equals(jdv.getJtfPhoneNum1().getText().trim())) {
			JOptionPane.showMessageDialog(jdv, "※전화번호 4자리를 입력해주세요");
			jdv.getJtfPhoneNum1().requestFocus();
			return;
		}else if(jdv.getJtfPhoneNum2().getText().trim().isEmpty()||"".equals(jdv.getJtfPhoneNum2().getText().trim())) {
			JOptionPane.showMessageDialog(jdv, "※전화번호 4자리를 입입력해주세요");
			jdv.getJtfPhoneNum2().requestFocus();
			return;
		}//end if 
		if (jdv.getJtfPhoneNum1().getText().trim().length() < 4) {
			JOptionPane.showMessageDialog(jdv, "※전화번호 4자리를 입력해주세요.");
			jdv.getJtfPhoneNum1().setText("");
			jdv.getJtfPhoneNum1().requestFocus();
			return;
		}//end if 
		/*String phoneNum1=jdv.getJtfPhoneNum1().getText().trim();
		String phoneNum2=jdv.getJtfPhoneNum2().getText().trim();
		String num[]=new String[10];
		for(int i=0; i<=9; i++) {
			num[i]=String.valueOf(i);
		}//end for
		for(int i=0; i<num.length; i++) {
			if (!phoneNum1.matches(num[i])) {
				JOptionPane.showMessageDialog(jdv, "전화번호는 숫자만 입력가능합니다.");
				jdv.getJtfPhoneNum1().setText("");
				jdv.getJtfPhoneNum1().requestFocus();
				return;
			}//end if 
			if (!phoneNum2.contains(num[i])) {
				JOptionPane.showMessageDialog(jdv, "전화번호는 숫자만 입력가능합니다.");
				jdv.getJtfPhoneNum2().setText("");
				jdv.getJtfPhoneNum2().requestFocus();
				return;
			}//end if 
		}
*/
		String cipherText = "";// 암호화
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			cipherText = DataEncrypt.messageDigest("MD5", inputPw);
			zeq = cDAO.seqSearch(zipcode, addr);
			jdVO = new JoinDetailVO(id, cipherText, name, birth, tempGen, phone, addrDetail, email, zeq);
			if(cDAO.insertMemJoin(jdVO)) {
				JOptionPane.showMessageDialog(jdv, "♡ 축하합니다 회원가입이 완료되었습니다 ♡");
				jdv.dispose();
			}//end if 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(jdv, "※회원가입에 실패하셨습니다.");
			e1.printStackTrace();
		}catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch

	}// joinConfirm

	private void chkId() {//아이디 중복 
		String id = jdv.getJtfId().getText().trim(); // 아이디
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (id.isEmpty()|| "".equals(id)) { // 공백이면
				JOptionPane.showMessageDialog(jdv, "※아이디는 5~20자사이로 입력해주세요");
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();
				return;
			} // end if
			if (id.length()< 5|| id.length()>20) { 
				JOptionPane.showMessageDialog(jdv, "※아이디는 5~20자사이로 입력해주세요.");
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();
				return;
			} // end if
			if (cDAO.idConfrim(id)) {// 조회된 아이디가 있으면
				JOptionPane.showMessageDialog(jdv, "※사용불가능한 아이디입니다");
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();;
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
		} // end if
		if (ae.getSource() == jdv.getJbtnSearchAddr()) {
			new ZipcodeSearchView(jdv);
		} // end if
		if (ae.getSource() == jdv.getJbtConfirm()) {// 확인
			if (flag==true) {
				if(jdv.getJtfZipcode().getText().trim().isEmpty()||"".equals(jdv.getJtfZipcode().getText().trim())) {
					JOptionPane.showMessageDialog(jdv, "※주소를 입력해주세요.");
					return;
				}else {
					joinConfirm();
				}//end if 
			} else {
				JOptionPane.showMessageDialog(jdv, "※아이디 중복체크를 해주세요.");
				return;
			} // end if
		} // end if
		if (ae.getSource() == jdv.getJbtClose()) {// 취소
			JOptionPane.showConfirmDialog(jdv, "회원가입창을 닫으시겠습니끼?");
			jdv.dispose();
		} // end if
	
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent ke) {

		if (ke.getSource() == jdv.getJtfPhoneNum1()) {
			if (jdv.getJtfPhoneNum1().getText().trim().length() >= 4) {
				ke.consume();
				 jdv.getJtfPhoneNum2().requestFocus();
			}//end if
		} // end if
		if (ke.getSource() ==  jdv.getJtfPhoneNum2()) {
			if ( jdv.getJtfPhoneNum2().getText().trim().length() >=4) {
				ke.consume();
				jdv.getJtfPhoneNum2().requestFocus();
			}//end if
		} // end if
	}// keyTyped

}// class
