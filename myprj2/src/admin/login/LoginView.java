package admin.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin;
	public LoginView() {
		super("관리자 로그인");
		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		
		ImageIcon logoImg= new ImageIcon(StaticCla.FILE_PATH+"/prj2_logo_back_brown.png");
//		ImageIcon logoImg= new ImageIcon("C:/Users/hyebin/git/prj2_shopping/myprj2/src/admin/img"+"/prj2_logo_back_brown.png");
		JLabel jlTitle = new JLabel(logoImg);
		JLabel jlId = new JLabel("아이디");
		jlId.setForeground(Color.white);
		JLabel jlPw = new JLabel("비밀번호");
		jlPw.setForeground(Color.white);

		jbtLogin = new JButton("로그인");
		jbtLogin.setBackground(Color.white);
		jbtLogin.setForeground(new Color(0x352A26));
		jbtLogin.setBorder(new LineBorder(Color.white));		

		jlTitle.setBounds(80, 20, 300, 60);
		jtfId.setBounds(130, 115, 250, 30);
		jpfPw.setBounds(130, 160, 250, 30);
		jlId.setBounds(70, 110, 70, 50);
		jlPw.setBounds(60, 150, 70, 50);
		jbtLogin.setBounds(195, 220, 100, 30);

		this.getContentPane().setBackground(new Color(0x352A26));

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

		setBounds(100, 100, 500, 330);
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
