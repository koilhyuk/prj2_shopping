package user.view.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.login.LoginDialogEvt;
import user.helper.RecentThread;
import user.view.content.UserGoodsMainView;

@SuppressWarnings("serial")
public class LoginDialogView extends JDialog {

	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin, jbtClose;

	public LoginDialogView(UserGoodsMainView ugmv, RecentThread rt) {
		super(ugmv, "로그인", true);

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

		jbtClose = new JButton("닫기");
		jbtClose.setBackground(Color.white);

		jbtLogin = new JButton("로그인");
		jbtLogin.setBackground(Color.white);

		jlTitle.setBounds(100, 15, 300, 67);
		jtfId.setBounds(130, 105, 250, 30);
		jpfPw.setBounds(130, 150, 250, 30);
		jlId.setBounds(70, 80, 70, 50);
		jlPw.setBounds(70, 120, 70, 50);
		jbtLogin.setBounds(140, 200, 100, 30);
		jbtClose.setBounds(260, 200, 100, 30);

		setLayout(null);
		add(jlTitle);
		add(jtfId);
		add(jpfPw);
		add(jlId);
		add(jlPw);
		add(jbtLogin);
		add(jbtClose);

		LoginDialogEvt lde = new LoginDialogEvt(this, ugmv, rt);

		jtfId.addActionListener(lde);
		jpfPw.addActionListener(lde);
		jbtLogin.addActionListener(lde);
		jbtClose.addActionListener(lde);

		this.getContentPane().setBackground(new Color(0x352A26));

		setBounds(200, 200, 500, 300);
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

	public JButton getJbtClose() {
		return jbtClose;
	}

//	public static void main(String[] args) {
//		new LoginView();
//	}

}// class
