package admin.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin;

	public LoginView() {
		super("관리자 로그인");
		jtfId = new JTextField();
		jpfPw = new JPasswordField();

		JLabel jlTitle = new JLabel("§ ST LOGIN §");
		Font fTitle = new Font("맑은 고딕", Font.BOLD, 30);
		jlTitle.setFont(fTitle);
		jlTitle.setForeground(Color.WHITE);
		JLabel jlId = new JLabel("아이디");
		jlId.setForeground(Color.white);
		JLabel jlPw = new JLabel("비밀번호");
		jlPw.setForeground(Color.white);

		jbtLogin = new JButton("로그인");
		jbtLogin.setBackground(Color.white);
		jbtLogin.setForeground(new Color(0x3F4040));
		jbtLogin.setBorder(new LineBorder(Color.white));		

		jlTitle.setBounds(150, 30, 270, 30);
		jtfId.setBounds(130, 85, 250, 30);
		jpfPw.setBounds(130, 130, 250, 30);
		jlId.setBounds(70, 80, 70, 50);
		jlPw.setBounds(70, 120, 70, 50);
		jbtLogin.setBounds(195, 190, 100, 30);

		this.getContentPane().setBackground(new Color(0x3F4040));

		setLayout(null);
		add(jlTitle);
		add(jtfId);
		add(jpfPw);
		add(jlId);
		add(jlPw);
		add(jbtLogin);

		LoginEvt le = new LoginEvt(this);

		jtfId.addActionListener(le);
		jpfPw.addActionListener(le);
		jbtLogin.addActionListener(le);

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

//	public static void main(String[] args) {
//		new LoginView();
//	}

}// class
