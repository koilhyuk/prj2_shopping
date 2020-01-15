package user.view.login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.login.LoginPwResetEvt;

@SuppressWarnings("serial")
public class LoginPwReset extends JFrame{

	private JLabel jlTitle, jlNewPw, jlNewPwConfirm;
	private JTextField jtfNewPw, jtfNewPwConfirm;
	private JButton jbtConfirm, jbtCancle;
	
	public LoginPwReset(String id) {
		super("���̵�/��й�ȣ ã��");
		
		jtfNewPw=new JPasswordField();
		jtfNewPwConfirm=new JPasswordField();
		
		jtfNewPw.setBorder(null);
		jtfNewPwConfirm.setBorder(null);
		
		jlTitle=new JLabel("�ܺ�й�ȣ �缳��");
		jlNewPw=new JLabel("�ű� ��й�ȣ");
		jlNewPwConfirm=new JLabel("�ű� ��й�ȣ Ȯ��");
		
		jbtConfirm=new JButton("Ȯ��");
		jbtCancle=new JButton("���");
		
		JPanel jp=new JPanel();
		
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
		
		LoginPwResetEvt lpre=new LoginPwResetEvt(this,id);
		jtfNewPw.addActionListener(lpre);
		jtfNewPwConfirm.addActionListener(lpre);
		jbtConfirm.addActionListener(lpre);
		jbtCancle.addActionListener(lpre);
		
		setBounds(100, 100, 400, 290);
		setVisible(true);
	}//AdminLoginFound
	
	public JLabel getJlTitle() {
		return jlTitle;
	}

	public JLabel getJlNewPw() {
		return jlNewPw;
	}

	public JLabel getJlNewPwConfirm() {
		return jlNewPwConfirm;
	}

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

}//class
