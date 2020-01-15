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

	private void joinConfirm() { // ȸ������ Ȯ��
		JoinDetailVO jdVO = null;
		String id = jdv.getJtfId().getText().trim();// ���̵�

		String tempGen = ""; // ����
//		String addrSeq = (String) jdv.getJtfZipcode().getText();
		String addrDetail= jdv.getJtfAddress().getText();// 
		String addr= jdv.getJtfAddr().getText();//��
		
		String zipcode= jdv.getJtfZipcode().getText();
		String inputPw = new String(jdv.getJpfPw().getPassword());// ��й�ȣ ��ȣ
		String inputPw2= new String(jdv.getJpfPwConfirm().getPassword());
		String cipherText = "";// ��ȣȭ
		String gendername = "";// ����
		int zeq =0;
		if (inputPw.isEmpty()) {
			JOptionPane.showMessageDialog(jdv, "��й�ȣ�� �Է����ּ���.");
			jdv.getJpfPw().setText("");
			return;
		} // end if
		if(inputPw.length()<5) {
			JOptionPane.showMessageDialog(jdv, "��й�ȣ�� 5�ڸ� �̻����� �Է����ּ���");
			return;
		}
		if (inputPw2.isEmpty()) {
			JOptionPane.showMessageDialog(jdv, "��й�ȣ�� �Է����ּ���.");
			jdv.getJpfPwConfirm().setText("");
			return;
		} // end if
		if (!inputPw2.equals(inputPw)) {// �������� ������
			JOptionPane.showMessageDialog(jdv, "�������� ���� ��й�ȣ�Դϴ�. ");
			jdv.getJpfPwConfirm().setText("");
			return;
		} // end if
		
		try {
			///////////////////// ��й�ȣ /////////////////////////////
			cipherText = DataEncrypt.messageDigest("MD5", inputPw);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} // end catch
//////////////////�̸�
		String name = jdv.getJtfName().getText().trim();
		if (name.length() > 5) {
			JOptionPane.showMessageDialog(jdv, "�̸��� 5�� �̳��� �ۼ����ּ���.");
			return;
		} // end if
			///////////////// ����
		String birth = jdv.getJtfBirthYear().getText().trim() + "-" + jdv.getJtfBirthMonth().getText().trim() + "-"
				+ jdv.getJtfBirthday().getText().trim();
		if (jdv.getJtfBirthYear().getText().length() == 4) {// ������ 4�ڸ��� �ԷµǸ�
			jdv.getJtfBirthMonth().requestFocus(); // ���� �Ѿ��.
		} else {
			JOptionPane.showMessageDialog(jdv, "��������� �ùٸ��� �Է����ּ��� ex)1990-01-01");
			return;
		} // end if
			//////////////// ����
		if (jdv.getBgGender().getSelection() == jdv.getJrbF()) {
			gendername = "����";// ����
		} else {
			gendername = "����";
		} // end if
		if (gendername.equals("����")) {
			tempGen = "F";
		} else if (gendername.equals("����")) {
			tempGen = "M";
		} // end if

		phone = jdv.getJtfPhoneNum().getText().trim() + "-" + jdv.getJtfPhoneNum1().getText().trim() + "-"
				+ jdv.getJtfPhoneNum2().getText().trim();
		addr = jdv.getJtfAddr().getText().trim();
		if (addr == null) {
			JOptionPane.showMessageDialog(jdv, "�� �ּҸ� �Է����ּ���");
			return;
		} // end if
		email = jdv.getJtfEmail().getText().trim();
		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(jdv, "�ùٸ� �̸��� �������� �Է����ּ���");
			return;
		} // end if
		if (email == null) {
			JOptionPane.showMessageDialog(jdv, "�̸����� �Է����ּ���");
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
//			JOptionPane.showMessageDialog(jdv, "���̵� �Է����ּ���");
//		} else if (jdv.getJpfPw().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "��й�ȣ�� �Է����ּ���");
//		} else if (jdv.getJpfPwConfirm().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "��й�ȣȮ���� �Է����ּ���");
//		} else if (jdv.getJtfName().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "�̸� �Է����ּ���");
//		} else if (jdv.getJtfBirthYear().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "������ �Է����ּ���");
//		} else if (jdv.getJtfBirthMonth().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "������ �Է����ּ���");
//		} else if (jdv.getJtfBirthday().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "������ �Է����ּ���");
//		} else if (jdv.getJtfPhoneNum1().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "��ȭ��ȣ�� �Է����ּ���");
//		} else if (jdv.getJtfPhoneNum2().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "��ȭ��ȣ�� �Է����ּ���");
//		} else if (jdv.getJtfAddr().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "�ּҸ� �Է����ּ���");
//		} else if (jdv.getJtfEmail().getText().isEmpty()) {
//			JOptionPane.showMessageDialog(jdv, "�̸����� �Է����ּ���");
//		} else {
//		}

		try {
			cDAO.insertMemJoin(jdVO);
				JOptionPane.showMessageDialog(jdv, "�����մϴ� ȸ�������� �Ϸ�Ǿ����ϴ�");
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(jdv, "ȸ�����Կ� �����ϼ̽��ϴ�.");
			e.printStackTrace();
		}

//			JOptionPane.showMessageDialog(jdv, "DBMS���� ���� �߻�");


	}// joinConfirm


	private void chkId() {
		String id = jdv.getJtfId().getText().trim(); // ���̵�
		ClientDAO cDAO = ClientDAO.getInstance();


		try {
			if (jdv.getJtfId() == null) { // �����̸�
				JOptionPane.showMessageDialog(jdv, "���̵� �Է����ּ���");
				return;
			} // end if
				if(cDAO.idConfrim(id)) {
				JOptionPane.showMessageDialog(jdv, "���Ұ����� ���̵��Դϴ�");
				jdv.getJtfId().getText();
				jdv.getJtfId().setText("");
				jdv.getJtfId().requestFocus();
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
//			JOptionPane.showMessageDialog(jdv, "��밡���� ���̵��Դϴ�");
		} // end if

		if (ae.getSource() == jdv.getJbtConfirm()) {// Ȯ��
			if(flag) {
				joinConfirm();
			}else {
				JOptionPane.showMessageDialog(jdv, "���̵� �ߺ�üũ�� ���ּ���.");
				return;
			}//end if
//			JOptionPane.showMessageDialog(jdv, "�����մϴ� ȸ�������� �Ϸ�Ǿ����ϴ�");
		} // end if

		if (ae.getSource() == jdv.getJbtClose()) {// ���
			JOptionPane.showConfirmDialog(jdv, "ȸ������â�� �����ðڽ��ϳ�?");
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
