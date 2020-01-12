package user.view.content;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.content.UserCusPwResetEvt;

@SuppressWarnings("serial")
public class UserCusPwResetView extends JDialog{

	private JLabel jlTitle, jlNewPw, jlNewPwConfirm;
	private JTextField jtfNewPw, jtfNewPwConfirm;
	private JButton jbtConfirm, jbtCancle;
	
	private UserCusDataView ucd;
	private static String id;
	public UserCusPwResetView(UserCusDataView ucd,String id) {
		super(ucd, "비밀번호 변경", true);
		this.ucd=ucd;
		this.id=id;
		
		jtfNewPw=new JPasswordField();
		jtfNewPwConfirm=new JPasswordField();
		
		jlTitle=new JLabel("비밀번호 재설정");
		jlNewPw=new JLabel("신규 비밀번호");
		jlNewPwConfirm=new JLabel("신규 비밀번호 확인");
		
		jbtConfirm=new JButton("확인");
		jbtCancle=new JButton("취소");
		
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
		
		UserCusPwResetEvt lpre=new UserCusPwResetEvt(this,id);
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
