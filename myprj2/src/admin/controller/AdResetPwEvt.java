package admin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import admin.dao.AdDAO;
import admin.view.AdResetPwView;
import kr.co.sist.util.cipher.DataEncrypt;

public class AdResetPwEvt extends KeyAdapter implements ActionListener {
	private AdResetPwView arpw;

	public AdResetPwEvt(AdResetPwView arpw) {
		this.arpw = arpw;
	}// AdResetPwEvt

	private void updateNewPw() {
		String inputPw = new String(arpw.getJpResetPw().getPassword());
		String transPw = "";

		try {
			transPw = DataEncrypt.messageDigest("MD5", inputPw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} // end catch

		AdDAO aDAO = AdDAO.getInstance();
		JOptionPane.showMessageDialog(arpw, "��й�ȣ�� �缳���Ǿ����ϴ�.");
		arpw.dispose();

		try {
			aDAO.updatePw(transPw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// insertNewPw

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton btnReset = arpw.getBtnReset();
		JButton btnClose = arpw.getBtnClose();
		JPasswordField jpPwChk = arpw.getJpResetPwChk();
		JPasswordField jpPw = arpw.getJpResetPw();

		if (ae.getSource() == btnClose) {
			arpw.dispose();
		} // end if
		if (ae.getSource() == jpPwChk || ae.getSource() == btnReset) {
			if (new String(jpPwChk.getPassword()).equals(new String(jpPw.getPassword()))) {
				if (new String(jpPwChk.getPassword()).length() < 6) {
					JOptionPane.showMessageDialog(arpw, "��й�ȣ�� 6�� �̻��̾�� �մϴ�.");
					jpPwChk.setText("");
					jpPw.setText("");
					jpPwChk.requestFocus();
					return;
				} // end if
				if (new String(jpPw.getPassword()).length() < 6) {
					JOptionPane.showMessageDialog(arpw, "��й�ȣ�� 6�� �̻��̾�� �մϴ�.");
					jpPwChk.setText("");
					jpPw.setText("");
					jpPw.requestFocus();
					return;
				} // end if
				updateNewPw();
			} else {
				JOptionPane.showMessageDialog(arpw, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				jpPwChk.setText("");
				jpPw.requestFocus();
				return;
			}
		} // end if

	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent ke) {
		JPasswordField jpPwChk = arpw.getJpResetPwChk();
		JPasswordField jpPw = arpw.getJpResetPw();

		if (ke.getSource() == jpPwChk) {
			if (new String(jpPwChk.getPassword()).length() >= 20) {
				JOptionPane.showMessageDialog(arpw, "��й�ȣ�� 20�ڸ� �ʰ��� �� �����ϴ�.");
				ke.consume();
			} // end if

		} // end if
		if (ke.getSource() == jpPw) {
			if (new String(jpPw.getPassword()).length() >= 20) {
				jpPw.requestFocus();
				JOptionPane.showMessageDialog(arpw, "��й�ȣ�� 20�ڸ� �ʰ��� �� �����ϴ�.");
				ke.consume();
			} // end if
		} // end if
	}// keyTyped

	@Override
	public void keyReleased(KeyEvent ke) {
		JPasswordField jpPwChk = arpw.getJpResetPwChk();
		JPasswordField jpPw = arpw.getJpResetPw();
		if (ke.getSource() == jpPwChk) {
			if (new String(jpPwChk.getPassword()).length() > 0) {
				if (new String(jpPwChk.getPassword()).equals(new String(jpPw.getPassword()))) {
					arpw.getJlChk().setForeground(Color.BLUE);
					arpw.getJlChk().setText("��й�ȣ�� ��ġ�մϴ�.");
				} else {
					arpw.getJlChk().setForeground(Color.RED);
					arpw.getJlChk().setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			}
			if (new String(jpPwChk.getPassword()).length() == 0) {
				arpw.getJlChk().setText("");
			}
		}
		if (ke.getSource() == jpPw) {
			if (new String(jpPw.getPassword()).length() > 0) {
				if (new String(jpPwChk.getPassword()).equals(new String(jpPw.getPassword()))) {
					arpw.getJlChk().setForeground(Color.BLUE);
					arpw.getJlChk().setText("��й�ȣ�� ��ġ�մϴ�.");
				} else {
					arpw.getJlChk().setForeground(Color.RED);
					arpw.getJlChk().setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			}
			if (new String(jpPw.getPassword()).length() == 0) {
				arpw.getJlChk().setText("");
			}
		}
	}

}// class
