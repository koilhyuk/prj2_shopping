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

	private void joinConfirm() { // ȸ������ Ȯ��
		JoinDetailVO jdVO = null;
		String id = jdv.getJtfId().getText().trim();// ���̵�
		String tempGen ="";
		if (jdv.getBgGender().getSelection() == jdv.getJrbF()||jdv.getBgGender().isSelected(jdv.getJrbF().getModel())) { //����
			tempGen = "F";
		} else {
			tempGen = "M";
		} // end if
		
		String addrDetail = jdv.getJtfAddr().getText().trim();//���ּ�
		String addr = jdv.getJtfAddress().getText();//
		String zipcode = jdv.getJtfZipcode().getText();
		
		String inputPw = new String(jdv.getJpfPw().getPassword()).trim();// ��й�ȣ ��ȣ
		String inputPw2= new String(jdv.getJpfPwConfirm().getPassword()).trim();
		String name = jdv.getJtfName().getText().trim();
		
		String birth = jdv.getJcbBirthYear().getSelectedItem() + "-" + jdv.getJcbBirthMonth().getSelectedItem() + "-"
				+ jdv.getJcbBirthDay().getSelectedItem(); //�������

		String phone = jdv.getJcbPhoneNum().getSelectedItem() + "-" + jdv.getJtfPhoneNum1().getText().trim() + "-"
				+ jdv.getJtfPhoneNum2().getText().trim();
		
		String email = jdv.getJtfEmail().getText().trim();
		
		int zeq = 0;
		//��ȿ�� 
		if (inputPw.trim().isEmpty()|| "".equals(inputPw.trim())) {//��й�ȣ�� ��ĭ�� ��
			JOptionPane.showMessageDialog(jdv, "�غ�й�ȣ�� �Է����ּ���.");
			jdv.getJpfPw().setText("");
			jdv.getJpfPw().requestFocus();
			return;
		}else if (inputPw.trim().length() < 5||inputPw.trim().length() > 20) {//��й�ȣ�� 5�ڸ� �̻����� �Է� 
			JOptionPane.showMessageDialog(jdv, "�غ�й�ȣ�� 5~20�� ���̷� �Է����ּ���");
			jdv.getJpfPw().setText("");
			jdv.getJpfPw().requestFocus();
			return;
		}//end if
		if (inputPw2.isEmpty()||"".equals(inputPw2)) {//��й�ȣ Ȯ�ζ��� ��ĭ�϶�
			JOptionPane.showMessageDialog(jdv, "�غ�й�ȣ�� �ѹ� �� �Է����ּ���.");
			jdv.getJpfPwConfirm().setText("");
			jdv.getJpfPwConfirm().requestFocus();
			return;
		}//end if 
		if (!inputPw2.equals(inputPw)) {// �������� ������
			JOptionPane.showMessageDialog(jdv, "�غ�й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�. ");
			jdv.getJpfPwConfirm().setText("");
			jdv.getJpfPwConfirm().requestFocus();
			return;
		} // end if
		if(name.isEmpty()||"".equals(name)) {//�̸��� ��ĭ�϶�
			JOptionPane.showMessageDialog(jdv, "���̸��� �Է����ּ���.");
			jdv.getJtfName().setText("");
			jdv.getJtfName().requestFocus();
			return;
		}else if (name.length() > 5) {//�̸��� ���� 
			JOptionPane.showMessageDialog(jdv, "���̸��� 5�� �̳��� �ۼ����ּ���.");
			jdv.getJtfName().setText("");
			jdv.getJtfName().requestFocus();
			return;
		} // end if
		
		if(jdv.getBgGender().getSelection()==null|| jdv.getBgGender().isSelected(null)) {// ������ �������� �ʾ�����
			JOptionPane.showMessageDialog(jdv, "�ؼ����� �������ּ���.");
			return;
		}//end if

		if (addrDetail.isEmpty()||"".equals(addrDetail)) {//���ּҰ� null�϶�
			JOptionPane.showMessageDialog(jdv, "�ػ� �ּҸ� �Է����ּ���");
			jdv.getJtfAddr().requestFocus();
			return;
		} // end if

		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(jdv, "�ؿùٸ� �̸��� �������� �Է����ּ���");
			jdv.getJtfEmail().setText("");
			jdv.getJtfEmail().requestFocus();
			return;
		}else if (email.isEmpty()|| "".equals(email)) {
			JOptionPane.showMessageDialog(jdv, "���̸����� �Է����ּ���");
			jdv.getJtfEmail().requestFocus();
			return;
		} // end if
		
		if(jdv.getJtfPhoneNum1().getText().trim().isEmpty()||"".equals(jdv.getJtfPhoneNum1().getText().trim())) {
			JOptionPane.showMessageDialog(jdv, "����ȭ��ȣ 4�ڸ��� �Է����ּ���");
			jdv.getJtfPhoneNum1().requestFocus();
			return;
		}else if(jdv.getJtfPhoneNum2().getText().trim().isEmpty()||"".equals(jdv.getJtfPhoneNum2().getText().trim())) {
			JOptionPane.showMessageDialog(jdv, "����ȭ��ȣ 4�ڸ��� ���Է����ּ���");
			jdv.getJtfPhoneNum2().requestFocus();
			return;
		}//end if 
		if (jdv.getJtfPhoneNum1().getText().trim().length() < 4) {
			JOptionPane.showMessageDialog(jdv, "����ȭ��ȣ 4�ڸ��� �Է����ּ���.");
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
				JOptionPane.showMessageDialog(jdv, "��ȭ��ȣ�� ���ڸ� �Է°����մϴ�.");
				jdv.getJtfPhoneNum1().setText("");
				jdv.getJtfPhoneNum1().requestFocus();
				return;
			}//end if 
			if (!phoneNum2.contains(num[i])) {
				JOptionPane.showMessageDialog(jdv, "��ȭ��ȣ�� ���ڸ� �Է°����մϴ�.");
				jdv.getJtfPhoneNum2().setText("");
				jdv.getJtfPhoneNum2().requestFocus();
				return;
			}//end if 
		}
*/
		String cipherText = "";// ��ȣȭ
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			cipherText = DataEncrypt.messageDigest("MD5", inputPw);
			zeq = cDAO.seqSearch(zipcode, addr);
			jdVO = new JoinDetailVO(id, cipherText, name, birth, tempGen, phone, addrDetail, email, zeq);
			if(cDAO.insertMemJoin(jdVO)) {
				JOptionPane.showMessageDialog(jdv, "�� �����մϴ� ȸ�������� �Ϸ�Ǿ����ϴ� ��");
				jdv.dispose();
			}//end if 
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(jdv, "��ȸ�����Կ� �����ϼ̽��ϴ�.");
			e1.printStackTrace();
		}catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch

	}// joinConfirm

	private void chkId() {//���̵� �ߺ� 
		String id = jdv.getJtfId().getText().trim(); // ���̵�
		ClientDAO cDAO = ClientDAO.getInstance();
		try {
			if (id.isEmpty()|| "".equals(id)) { // �����̸�
				JOptionPane.showMessageDialog(jdv, "�ؾ��̵�� 5~20�ڻ��̷� �Է����ּ���");
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();
				return;
			} // end if
			if (id.length()< 5|| id.length()>20) { 
				JOptionPane.showMessageDialog(jdv, "�ؾ��̵�� 5~20�ڻ��̷� �Է����ּ���.");
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();
				return;
			} // end if
			if (cDAO.idConfrim(id)) {// ��ȸ�� ���̵� ������
				JOptionPane.showMessageDialog(jdv, "�ػ��Ұ����� ���̵��Դϴ�");
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();;
			} else {
				JOptionPane.showMessageDialog(jdv, "��밡���� ���̵��Դϴ�");
				flag=true;
				jdv.getJpfPw().requestFocus();
		
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// chkId

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jdv.getJbtIdConfirm()) {// �ߺ���ư
			chkId();//
		} // end if
		if (ae.getSource() == jdv.getJbtnSearchAddr()) {
			new ZipcodeSearchView(jdv);
		} // end if
		if (ae.getSource() == jdv.getJbtConfirm()) {// Ȯ��
			if (flag==true) {
				if(jdv.getJtfZipcode().getText().trim().isEmpty()||"".equals(jdv.getJtfZipcode().getText().trim())) {
					JOptionPane.showMessageDialog(jdv, "���ּҸ� �Է����ּ���.");
					return;
				}else {
					joinConfirm();
				}//end if 
			} else {
				JOptionPane.showMessageDialog(jdv, "�ؾ��̵� �ߺ�üũ�� ���ּ���.");
				return;
			} // end if
		} // end if
		if (ae.getSource() == jdv.getJbtClose()) {// ���
			JOptionPane.showConfirmDialog(jdv, "ȸ������â�� �����ðڽ��ϳ�?");
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
