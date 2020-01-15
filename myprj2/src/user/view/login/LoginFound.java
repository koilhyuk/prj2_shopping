package user.view.login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import user.controller.login.LoginFoundEvt;
import user.controller.login.LoginFoundEvt2;

@SuppressWarnings("serial")
public class LoginFound extends JFrame {
	private JLabel jlTitle, jlTitle2, jlId2, jlName, jlName2, jlPhoneNum, jlPhoneNum2, jlPhoneH1, jlPhoneH2, jlPhoneH3, jlPhoneH4;
	private JTextField  jtfId2, jtfName, jtfName2, jtfPhoneH1, jtfPhoneH2, jtfPhoneH3, jtfPhoneH4, jtfPhoneF1, jtfPhoneF2;
	private JButton jbtConfirm, jbtConfirm2, jbtCancle, jbtCancle2;
//	private JComboBox<String> jcbPhoneNum, jcbPhoneNum2;
	private JTabbedPane jtpFound;
	
	public LoginFound() {
		super("아이디/비밀번호 찾기");

		jlTitle=new JLabel("●아이디찾기");
		jlTitle2=new JLabel("●비밀번호찾기");
		jlId2=new JLabel("아이디");
		jlName=new JLabel("이름");
		jlName2=new JLabel("이름");
		jlPhoneNum=new JLabel("전화번호");
		jlPhoneNum2=new JLabel("전화번호");
		jlPhoneH1=new JLabel("-");
		jlPhoneH2=new JLabel("-");
		jlPhoneH3=new JLabel("-");
		jlPhoneH4=new JLabel("-");
		
		jtfId2=new JTextField(15); 
		jtfName=new JTextField(15);
		jtfName2=new JTextField(15);
		jtfPhoneF1=new JTextField(4);//
		jtfPhoneF2=new JTextField(4);//
		jtfPhoneH1=new JTextField(4);
		jtfPhoneH2=new JTextField(4);
		jtfPhoneH3=new JTextField(4);
		jtfPhoneH4=new JTextField(4);
		
		jbtConfirm=new JButton("확인");
		jbtConfirm2=new JButton("확인");
		jbtCancle=new JButton("취소");
		jbtCancle2=new JButton("취소");
		
//		jcbPhoneNum=new JComboBox<String>();
//		jcbPhoneNum2=new JComboBox<String>();
		jtpFound=new JTabbedPane();
		
		///////////////////////////아이디찾기/////////////////////////////
		JPanel jpIdFoundTitle=new JPanel(); //id title
		jpIdFoundTitle.add(jlTitle);
		jpIdFoundTitle.setBounds(60, 5, 100, 20);
		
		JPanel jpIdFoundName = new JPanel(); //id 이름
		jpIdFoundName.add(jlName);
		jpIdFoundName.add(jtfName);
		jpIdFoundName.setBounds(60, 55, 230, 30);

		JPanel jpIdFoundPhone= new JPanel(); //id 전화번호
		jpIdFoundPhone.add(jlPhoneNum);
//		jpIdFoundPhone.add(jcbPhoneNum);
		jpIdFoundPhone.add(jtfPhoneF1);//
		jpIdFoundPhone.add(jlPhoneH1);
		jpIdFoundPhone.add(jtfPhoneH1);
		jpIdFoundPhone.add(jlPhoneH2);
		jpIdFoundPhone.add(jtfPhoneH2);
		jpIdFoundPhone.setBounds(30, 105, 270, 30);
		
		JPanel jpIdFoundBtn=new JPanel(); //id 버튼
		jpIdFoundBtn.add(jbtConfirm);
		jpIdFoundBtn.add(jbtCancle);
		jpIdFoundBtn.setBounds(95, 165, 180, 30);
		
		JPanel jpIdFound=new JPanel(); //id큰 패널
		jpIdFound.setLayout(null);
		jpIdFound.add(jpIdFoundTitle);
		jpIdFound.add(jpIdFoundName);
		jpIdFound.add(jpIdFoundPhone);
		jpIdFound.add(jpIdFoundBtn);
		//////////////////////비밀번호찾기////////////////////////////
		JPanel jpPwFoundTitle=new JPanel(); //pw title
		jpPwFoundTitle.add(jlTitle2);
		jpPwFoundTitle.setBounds(60, 5, 100, 20);
		
		JPanel jpPwFoundId = new JPanel(); //pw id
		jpPwFoundId.add(jlId2);
		jpPwFoundId.add(jtfId2);
		jpPwFoundId.setBounds(60, 35, 230, 30);
		
		JPanel jpPwFoundName=new JPanel(); //pw 이름
		jpPwFoundName.add(jlName2);
		jpPwFoundName.add(jtfName2);
		jpPwFoundName.setBounds(65, 75, 230, 30);
		
		JPanel jpPwFoundPhone=new JPanel(); //전화번호
		jpPwFoundPhone.add(jlPhoneNum2);
//		jpPwFoundPhone.add(jcbPhoneNum2);
		jpPwFoundPhone.add(jtfPhoneF2);//
		
		jpPwFoundPhone.add(jlPhoneH3);
		jpPwFoundPhone.add(jtfPhoneH3);
		jpPwFoundPhone.add(jlPhoneH4);
		jpPwFoundPhone.add(jtfPhoneH4);
		jpPwFoundPhone.setBounds(50, 115, 250, 50);
		
		JPanel jpPwFoundBtn=new JPanel();
		jpPwFoundBtn.add(jbtConfirm2);
		jpPwFoundBtn.add(jbtCancle2);
		jpPwFoundBtn.setBounds(95, 165, 180, 30);
		
		JPanel jpPwFound=new JPanel(); //pw큰 패널
		jpPwFound.setLayout(null);
		jpPwFound.add(jpPwFoundTitle);
		jpPwFound.add(jpPwFoundId);
		jpPwFound.add(jpPwFoundName);
		jpPwFound.add(jpPwFoundPhone);
		jpPwFound.add(jpPwFoundBtn);
		
		JTabbedPane jtpFound = new JTabbedPane();
		jtpFound.addTab("아이디찾기", jpIdFound);
		jtpFound.addTab("비밀번호찾기", jpPwFound);
		
		
		LoginFoundEvt lfe=new LoginFoundEvt(this);
		jbtConfirm.addActionListener(lfe);
		jbtCancle.addActionListener(lfe);
		
		LoginFoundEvt2 lfe2=new LoginFoundEvt2(this);
		jbtConfirm2.addActionListener(lfe2);
		jbtCancle2.addActionListener(lfe2);
		jtfName2.addKeyListener(lfe2);
		jtfId2.addKeyListener(lfe2);
		jtfPhoneF2.addKeyListener(lfe2);
		jtfPhoneH3.addKeyListener(lfe2);
		jtfPhoneH4.addKeyListener(lfe2);
		add("Center", jtpFound);

		setBounds(100, 100, 400, 290);
		setVisible(true);

	}//LoginFound1
	public JLabel getJlTitle() {
		return jlTitle;
	}

	public JLabel getJlTitle2() {
		return jlTitle2;
	}

	public JLabel getJlId2() {
		return jlId2;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public JLabel getJlName2() {
		return jlName2;
	}

	public JLabel getJlPhoneNum() {
		return jlPhoneNum;
	}

	public JLabel getJlPhoneNum2() {
		return jlPhoneNum2;
	}

	public JLabel getJlPhoneH1() {
		return jlPhoneH1;
	}

	public JLabel getJlPhoneH2() {
		return jlPhoneH2;
	}

	public JLabel getJlPhoneH3() {
		return jlPhoneH3;
	}

	public JLabel getJlPhoneH4() {
		return jlPhoneH4;
	}

	public JTextField getJtfId2() {
		return jtfId2;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfName2() {
		return jtfName2;
	}

	public JTextField getJtfPhoneH1() {
		return jtfPhoneH1;
	}

	public JTextField getJtfPhoneH2() {
		return jtfPhoneH2;
	}

	public JTextField getJtfPhoneH3() {
		return jtfPhoneH3;
	}

	public JTextField getJtfPhoneH4() {
		return jtfPhoneH4;
	}

	public JButton getJbtConfirm() {
		return jbtConfirm;
	}

	public JButton getJbtConfirm2() {
		return jbtConfirm2;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public JButton getJbtCancle2() {
		return jbtCancle2;
	}

//	public JComboBox<String> getJcbPhoneNum() {
//		return jcbPhoneNum;
//	}
//
//	public JComboBox<String> getJcbPhoneNum2() {
//		return jcbPhoneNum2;
//	}

	public JTabbedPane getJtpFound() {
		return jtpFound;
	}
	
	public JTextField getJtfPhoneF1() {
		return jtfPhoneF1;
	}
	public JTextField getJtfPhoneF2() {
		return jtfPhoneF2;
	}
//	public static void main(String[] args) {
//		new LoginFound();
//	}// main
//	
}// class

