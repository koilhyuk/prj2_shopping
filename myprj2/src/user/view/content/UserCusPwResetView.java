package user.view.content;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.content.UserCusPwResetEvt;

@SuppressWarnings("serial")
public class UserCusPwResetView extends JDialog {

	private JTextField jtfNewPw, jtfNewPwConfirm;
	private JButton jbtConfirm, jbtCancle;

	public UserCusPwResetView(UserCusDataView ucd, String id) {
		super(ucd, "비밀번호 변경", true);

		jtfNewPw = new JPasswordField();
		jtfNewPwConfirm = new JPasswordField();

		JLabel jlTitle = new JLabel("비밀번호 재설정");
		JLabel jlNewPw = new JLabel("신규 비밀번호");
		JLabel jlNewPwConfirm = new JLabel("신규 비밀번호 확인");

		jbtConfirm = new JButton("확인");
		jbtCancle = new JButton("취소");

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

		jbtConfirm.setForeground(Color.white);
		jbtConfirm.setBackground(new Color(0x043424));
		jbtCancle.setForeground(Color.white);
		jbtCancle.setBackground(new Color(0x043424));
		this.getContentPane().setBackground(Color.white);

		UserCusPwResetEvt lpre = new UserCusPwResetEvt(this, id);
		jbtConfirm.addActionListener(lpre);
		jbtCancle.addActionListener(lpre);

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
