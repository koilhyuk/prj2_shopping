package user.view.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.login.ClientLoginEvt;

@SuppressWarnings("serial")
public class ClientLoginView extends JFrame {

	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin, jbtBLogin, jbtJoin, jbtfound;

	public static final String USER_FILE_PATH = "C:/Users/SAMSUNG/AppData/Roaming/SPB_Data/git/prj2_shopping/myprj2/src/user/img";

	public ClientLoginView() {
		super("로그인");

		jtfId = new JTextField();
		jpfPw = new JPasswordField();

		ImageIcon logoImg = new ImageIcon(ClientLoginView.USER_FILE_PATH + "/prj2_logo_back_brown.PNG");
		JLabel jlTitle = new JLabel(logoImg);
		Font fTitle = new Font("맑은 고딕", Font.BOLD, 30);
		jlTitle.setFont(fTitle);
		jlTitle.setForeground(Color.WHITE);
		JLabel jlId = new JLabel("아이디");
		jlId.setForeground(Color.WHITE);
		JLabel jlPw = new JLabel("비밀번호");
		jlPw.setForeground(Color.WHITE);

		jbtLogin = new JButton("로그인");
		jbtLogin.setBackground(Color.white);
		jbtBLogin = new JButton("둘러보기");
		jbtBLogin.setBackground(Color.white);
		jbtJoin = new JButton("회원가입");
		jbtJoin.setBackground(Color.white);
		jbtfound = new JButton("아이디/비밀번호 찾기");
		jbtfound.setBackground(Color.white);

		jlTitle.setBounds(100, 15, 300, 67);
		jtfId.setBounds(130, 105, 250, 30);
		jpfPw.setBounds(130, 150, 250, 30);
		jlId.setBounds(70, 100, 70, 50);
		jlPw.setBounds(70, 140, 70, 50);
		jbtLogin.setBounds(100, 200, 100, 30);
		jbtBLogin.setBounds(210, 200, 170, 30);
		jbtJoin.setBounds(100, 240, 100, 30);
		jbtfound.setBounds(210, 240, 170, 30);

		this.getContentPane().setBackground(new Color(0x352A26));

		setLayout(null);
		add(jlTitle);
		add(jtfId);
		add(jpfPw);
		add(jlId);
		add(jlPw);
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

	public JButton getJbtJoin() {
		return jbtJoin;
	}

	public JButton getJbtfound() {
		return jbtfound;
	}

}// class
