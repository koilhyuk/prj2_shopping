package user.view.login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPwFound extends JFrame{

	private JLabel jlTitle, jlId, jlName, jlPhoneNum, jlPhoneH1, jlPhoneH2;
	private JTextField jtfId, jtfName, jtfPhoneH1, jtfPhoneH2, jtfPhone;
	private JButton jbtConfirm, jbtCancle;
//	private JComboBox<String> jcbPhoneNum;
//	private JTabbedPane jtp;
	
	public LoginPwFound() {
		super("아이디/비밀번호 찾기");
		
		jtfId=new JTextField();
		jtfName=new JTextField();
		jtfPhone=new JTextField();
		jtfPhoneH1=new JTextField();
		jtfPhoneH2=new JTextField();
		
		jtfId.setBorder(null);
		jtfName.setBorder(null);
		jtfPhone.setBorder(null);//
		jtfPhoneH1.setBorder(null);
		jtfPhoneH2.setBorder(null);
		
		jlTitle=new JLabel("●비밀번호찾기");
		jlId=new JLabel("아이디");
		jlName=new JLabel("이름");
		jlPhoneNum=new JLabel("전화번호");
		jlPhoneH1=new JLabel("-");
		jlPhoneH2=new JLabel("-");
		
		jbtConfirm=new JButton("확인");
		jbtCancle=new JButton("취소");
		
//		jcbPhoneNum=new JComboBox<String>();
		
		JPanel jp=new JPanel();
		
//		jtp=new JTabbedPane();
//		jtp.addTab("아이디찾기", jp);
//		jtp.addTab("비밀번호찾기", jp);
		
		jtfId.setBounds(130, 85, 250, 30);
		jtfName.setBounds(130, 125, 250, 30);
		jtfPhone.setBounds(130, 165, 70, 30);//
		jtfPhoneH1.setBounds(220, 165, 70, 30);
		jtfPhoneH2.setBounds(310, 165, 70, 30);
//		jcbPhoneNum.setBounds(130, 125, 50, 30);
		jlId.setBounds(70, 85, 70, 30);
		jlTitle.setBounds(190, 30, 170, 30);
		jlName.setBounds(70, 125, 70, 30);
		jlPhoneNum.setBounds(70, 165, 70, 30);
		jlPhoneH1.setBounds(210, 165, 30, 30);
		jlPhoneH2.setBounds(300, 165, 30, 30);
		jbtConfirm.setBounds(130, 240, 70, 30);
		jbtCancle.setBounds(270, 240, 70, 30);
//		jcbPhoneNum.setBounds(130, 165, 70, 30);
		
		setLayout(null);
		add(jtfId);
		add(jlTitle);
		add(jtfName);
		add(jtfPhone);//
		add(jtfPhoneH1);
		add(jtfPhoneH2);
		add(jlId);
		add(jlName);
		add(jlPhoneNum);
		add(jlPhoneH1);
		add(jlPhoneH2);
		add(jbtConfirm);
		add(jbtCancle);
		add(jp);
//		add(jcbPhoneNum);
//		add("Center", jtp);
		
		setBounds(100, 100, 500, 350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//AdminLoginFound
	
	public JLabel getJlId() {
		return jlId;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public JLabel getJlPhoneNum() {
		return jlPhoneNum;
	}

	public JLabel getJlPhoneH1() {
		return jlPhoneH1;
	}

	public JLabel getJlPhoneH2() {
		return jlPhoneH2;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfPw() {
		return jtfName;
	}

	public JTextField getJtfPhoneH1() {
		return jtfPhoneH1;
	}

	public JTextField getJtfPhoneH2() {
		return jtfPhoneH2;
	}

	public JButton getJbtConfirm() {
		return jbtConfirm;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

//	public JComboBox<String> getJcbPhoneNum() {
//		return jcbPhoneNum;
//	}

//	public JTabbedPane getJtp() {
//		return jtp;
//	}

	public static void main(String[] args) {
		new LoginPwFound();
	}//main

	public JLabel getJlTitle() {
		return jlTitle;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}
}//class
