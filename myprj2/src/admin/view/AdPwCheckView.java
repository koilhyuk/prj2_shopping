package admin.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import admin.controller.AdPwCheckEvt;
import admin.run.StaticCla;

@SuppressWarnings("serial")
public class AdPwCheckView extends JDialog {
	private JPasswordField jtpPw;
	private JButton btnConfirm;
	private JButton btnClose;

	public AdPwCheckView() {
		super(StaticCla.mv, "비밀번호 확인", true);
		setLayout(null);
		JLabel jl = new JLabel("비밀번호를 입력해주세요.");
		jl.setForeground(Color.white);
		jtpPw = new JPasswordField();
		btnConfirm = new JButton("확인");
		btnConfirm.setBackground(Color.white);
		btnConfirm.setForeground(new Color(0x352A26));
		btnConfirm.setBorder(new LineBorder(Color.white));

		btnClose = new JButton("닫기");
		btnClose.setBackground(Color.white);
		btnClose.setForeground(new Color(0x352A26));
		btnClose.setBorder(new LineBorder(Color.white));

		jl.setBounds(35, 10, 150, 30);
		jtpPw.setBounds(25, 50, 185, 30);
		btnConfirm.setBounds(50, 90, 60, 30);
		btnClose.setBounds(120, 90, 60, 30);
		AdPwCheckEvt apce = new AdPwCheckEvt(this);
		jtpPw.addActionListener(apce);
		btnClose.addActionListener(apce);
		btnConfirm.addActionListener(apce);
		this.getContentPane().setBackground(new Color(0x352A26));
		add(jl);
		add(jtpPw);
		add(btnClose);
		add(btnConfirm);
		setBounds(600, 300, 250, 170);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}// AdPwCheckView

	public JPasswordField getJtpPw() {
		return jtpPw;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

}// AdPwCheckView
