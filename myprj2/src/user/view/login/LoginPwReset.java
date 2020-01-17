package user.view.login;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.login.LoginPwResetEvt;

@SuppressWarnings("serial")
public class LoginPwReset extends JDialog {

	private JTextField jtfNewPw, jtfNewPwConfirm;
	private JButton jbtConfirm, jbtCancle;

	public LoginPwReset(String id, LoginFound lf) {
		super(lf, "아이디/비밀번호 찾기", false);

		jtfNewPw = new JPasswordField();
		jtfNewPwConfirm = new JPasswordField();

		jtfNewPw.setBorder(null);
		jtfNewPwConfirm.setBorder(null);

		JLabel jlTitle = new JLabel("●비밀번호 재설정");
		jlTitle.setForeground(Color.white);
		JLabel jlNewPw = new JLabel("신규 비밀번호");
		jlNewPw.setForeground(Color.white);
		JLabel jlNewPwConfirm = new JLabel("신규 비밀번호 확인");
		jlNewPwConfirm.setForeground(Color.white);

		jbtConfirm = new JButton("확인");
		jbtConfirm.setForeground(new Color(0x3F4040));
		jbtConfirm.setBackground(Color.white);

		jbtCancle = new JButton("취소");
		jbtCancle.setForeground(new Color(0x3F4040));
		jbtCancle.setBackground(Color.white);

		JPanel jp = new JPanel();

		jlTitle.setBounds(50, 30, 150, 20);
		jlNewPw.setBounds(50, 70, 270, 30);
		jtfNewPw.setBounds(170, 70, 150, 30);
		jlNewPwConfirm.setBounds(50, 125, 270, 30);
		jtfNewPwConfirm.setBounds(170, 125, 150, 30);
		jbtConfirm.setBounds(110, 190, 60, 30);
		jbtCancle.setBounds(200, 190, 60, 30);

		setLayout(null);
		add(jtfNewPw);
		add(jlTitle);
		add(jtfNewPwConfirm);
		add(jlNewPw);
		add(jlNewPwConfirm);
		add(jbtConfirm);
		add(jbtCancle);
		add(jp);

		LoginPwResetEvt lpre = new LoginPwResetEvt(this, id, lf);
		jtfNewPw.addActionListener(lpre);
		jtfNewPwConfirm.addActionListener(lpre);
		jbtConfirm.addActionListener(lpre);
		jbtCancle.addActionListener(lpre);
		this.getContentPane().setBackground(new Color(0x352A26));

		setResizable(false);
		setBounds(100, 100, 400, 290);
		setVisible(true);
	}// AdminLoginFound

	public JTextField getJtfNewPw() {
		return jtfNewPw;
	}

	public JTextField getJtfNewPwConfirm() {
		return jtfNewPwConfirm;
	}

	public JButton getJbtConfirm() {
		return jbtConfirm;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

}// class
