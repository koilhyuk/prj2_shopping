package admin.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import admin.controller.AdResetPwEvt;

@SuppressWarnings("serial")
public class AdResetPwView extends JFrame {

	private JPasswordField jpResetPw;
	private JPasswordField jpResetPwChk;
	private JButton btnReset;
	private JButton btnClose;
	private JLabel jlChk;

	public AdResetPwView() {

		setLayout(null);
		JLabel jlPw = new JLabel("변경할 비밀번호 :");
		jlPw.setForeground(Color.white);
		JLabel jlPwChk = new JLabel("비밀번호 확인 :");
		jlPwChk.setForeground(Color.white);
		jlChk = new JLabel();

		jpResetPw = new JPasswordField();
		jpResetPwChk = new JPasswordField();
		btnReset = new JButton("재설정");
		btnReset.setBackground(Color.white);
		btnReset.setForeground(new Color(0x352A26));
		btnReset.setBorder(new LineBorder(Color.white));

		btnClose = new JButton("닫기");
		btnClose.setBackground(Color.white);
		btnClose.setForeground(new Color(0x352A26));
		btnClose.setBorder(new LineBorder(Color.white));

		jlPw.setBounds(10, 30, 100, 30);
		jpResetPw.setBounds(120, 30, 140, 30);
		jlPwChk.setBounds(15, 70, 90, 30);
		jpResetPwChk.setBounds(120, 70, 140, 30);
		jlChk.setBounds(55, 115, 200, 30);
//		jlChk.setBorder(new LineBorder(Color.black));

		btnReset.setBounds(40, 160, 80, 30);
		btnClose.setBounds(150, 160, 80, 30);

		AdResetPwEvt arpe = new AdResetPwEvt(this);
		jpResetPw.addKeyListener(arpe);
		jpResetPwChk.addKeyListener(arpe);
		jpResetPwChk.addActionListener(arpe);
		btnReset.addActionListener(arpe);
		btnClose.addActionListener(arpe);
		this.getContentPane().setBackground(new Color(0x352A26));

		add(jlChk);
		add(jlPw);
		add(jlPwChk);
		add(jpResetPw);
		add(jpResetPwChk);
		add(btnReset);
		add(btnClose);

		setVisible(true);
		setBounds(100, 100, 300, 260);

	}// AdPwCheckView

	public JPasswordField getJpResetPw() {
		return jpResetPw;
	}

	public JPasswordField getJpResetPwChk() {
		return jpResetPwChk;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public JLabel getJlChk() {
		return jlChk;
	}

}// AdPwCheckView
