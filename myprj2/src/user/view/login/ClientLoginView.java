package user.view.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.login.ClientLoginEvt;

@SuppressWarnings("serial")
public class ClientLoginView extends JFrame {

	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JLabel jlTitle, jlId, jlPw;
	private JButton jbtLogin, jbtBLogin, jbtJoin, jbtfound;

	public ClientLoginView() {
		super("로그인");

		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		jlTitle = new JLabel("§ ST LOGIN §");
		Font fTitle = new Font("맑은 고딕", Font.BOLD, 30);
		jlTitle.setFont(fTitle);
		jlTitle.setForeground(Color.WHITE);
		jlId = new JLabel("아이디");
		jlId.setForeground(Color.WHITE);
		jlPw = new JLabel("비밀번호");
		jlPw.setForeground(Color.WHITE);

		jbtLogin = new JButton("로그인");
		jbtLogin.setBackground(Color.white);
		jbtBLogin = new JButton("둘러보기");
		jbtBLogin.setBackground(Color.white);
		jbtJoin = new JButton("회원가입");
		jbtJoin.setBackground(Color.white);
		jbtfound = new JButton("아이디/비밀번호 찾기");
		jbtfound.setBackground(Color.white);

		JPanel jp = new JPanel();

		jp.add(jbtLogin);
		jp.add(jbtBLogin);

		jlTitle.setBounds(150, 30, 400, 30);
		jtfId.setBounds(130, 85, 250, 30);
		jpfPw.setBounds(130, 130, 250, 30);
		jlId.setBounds(70, 80, 70, 50);
		jlPw.setBounds(70, 120, 70, 50);
		jbtLogin.setBounds(100, 180, 100, 30);
		jbtBLogin.setBounds(210, 180, 170, 30);
		jbtJoin.setBounds(100, 240, 100, 30);
		jbtfound.setBounds(210, 240, 170, 30);

		this.getContentPane().setBackground(new Color(0x3F4040));

		setLayout(null);
		add(jlTitle);
		add(jtfId);
		add(jpfPw);
		add(jlId);
		add(jlPw);
		add(jp);
		add(jbtLogin);
		add(jbtBLogin);
		add(jbtJoin);
		add(jbtfound);

		ClientLoginEvt cle = new ClientLoginEvt(this);

		jtfId.addActionListener(cle);
		jpfPw.addActionListener(cle);
		jbtLogin.addActionListener(cle);
		jbtBLogin.addActionListener(cle);
		jbtJoin.addActionListener(cle);
		jbtfound.addActionListener(cle);

		setBounds(100, 100, 500, 350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// AdminLoginView

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPw() {
		return jpfPw;
	}

	public JButton getJbtLogin() {
		return jbtLogin;
	}

	public JButton getJbtBLogin() {
		return jbtBLogin;
	}

	public JLabel getJlTitle() {
		return jlTitle;
	}

	public JLabel getJlId() {
		return jlId;
	}

	public JLabel getJlPw() {
		return jlPw;
	}

	public JButton getJbtJoin() {
		return jbtJoin;
	}

	public JButton getJbtfound() {
		return jbtfound;
	}

}// class
