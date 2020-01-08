package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.util.cipher.DataEncrypt;
import user.dao.UserDAO;
import user.view.content.UserCardUploadView;
import user.view.content.UserGoodsMainView;
import user.vo.content.InsertNewCardVO;

public class UserCardUploadEvt extends KeyAdapter implements ActionListener {

	private UserCardUploadView ucuv;
	private JTextField jtCard1;
	private JTextField jtCard2;
	private JTextField jtCard3;
	private JTextField jtCard4;
	private JTextField jtCvc;

	public UserCardUploadEvt(UserCardUploadView ucuv) {
		this.ucuv = ucuv;
		jtCard1 = ucuv.getJtfCard1();
		jtCard2 = ucuv.getJtfCard2();
		jtCard3 = ucuv.getJtfCard3();
		jtCard4 = ucuv.getJtfCard4();
		jtCvc = ucuv.getJtfCVC();
	}// UserCardUploadEvt

	private void inserNewCard() {
		String cardCom = ucuv.getJcbCardName().getSelectedItem().toString().trim();
		String totalCardNum = jtCard1.getText().trim() + "-" + jtCard2.getText().trim() + "-" + jtCard3.getText().trim()
				+ "-" + jtCard4.getText().trim();

		String transCvc = "";
		String transCardNum = "";

		DataEncrypt de = null;
		try {
			de = new DataEncrypt(UserGoodsMainView.KEY);
			transCvc = de.encryption(ucuv.getJtfCVC().getText().trim());
			transCardNum = de.encryption(totalCardNum);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} // end catch

		UserDAO uDAO = UserDAO.getInstance();

		try {
			InsertNewCardVO inc = new InsertNewCardVO(transCardNum, cardCom, transCvc);
			uDAO.insertMemNewCard(inc);
			JOptionPane.showMessageDialog(ucuv, "카드가 등록되었습니다.");
			ucuv.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// inserNewCard

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ucuv.getJbtnClose()) {
			ucuv.dispose();
		} // end if

		if (ae.getSource() == ucuv.getJbtUpload() || ae.getSource() == ucuv.getJtfCVC()) {
			if (jtCard1.getText().trim().length() < 4) {
				JOptionPane.showMessageDialog(ucuv, "카드번호를 확인해주세요.");
				jtCard1.requestFocus();
				return;
			} // end if
			if (jtCard2.getText().trim().length() < 4) {
				JOptionPane.showMessageDialog(ucuv, "카드번호를 확인해주세요.");
				jtCard2.requestFocus();
				return;
			} // end if
			if (jtCard3.getText().trim().length() < 4) {
				JOptionPane.showMessageDialog(ucuv, "카드번호를 확인해주세요.");
				jtCard3.requestFocus();
				return;
			} // end if
			if (jtCard4.getText().trim().length() < 4) {
				JOptionPane.showMessageDialog(ucuv, "카드번호를 확인해주세요.");
				jtCard4.requestFocus();
				return;
			} // end if
			if (jtCvc.getText().trim().length() < 3) {
				JOptionPane.showMessageDialog(ucuv, "CVC를 확인해주세요.");
				jtCvc.requestFocus();
				return;
			} // end if
			inserNewCard();
			return;
		} // end if

		if (ae.getSource() == ucuv.getJcbCardName()) {
			ucuv.getJtfCard1().requestFocus();
		} // end if
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent ke) {

		if (ke.getSource() == jtCard1) {
			if (jtCard1.getText().trim().length() >= 4) {
				ke.consume();
				jtCard2.requestFocus();
			}
		} // end if
		if (ke.getSource() == jtCard2) {
			if (jtCard2.getText().trim().length() >= 4) {
				ke.consume();
				jtCard3.requestFocus();
			}
		} // end if
		if (ke.getSource() == jtCard3) {
			if (jtCard3.getText().trim().length() >= 4) {
				ke.consume();
				jtCard4.requestFocus();
			}
		} // end if
		if (ke.getSource() == jtCard4) {
			if (jtCard4.getText().trim().length() >= 4) {
				ke.consume();
				jtCvc.requestFocus();
			}
		} // end if
		if (ke.getSource() == jtCvc) {
			if (jtCvc.getText().trim().length() >= 3) {
				ke.consume();
			}
		} // end if

	}// keyTyped

}// class
