package user.view.login;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import user.controller.login.JoinDetailEvt;

@SuppressWarnings("serial")
public class JoinDetailView extends JFrame {

	private JLabel jlTitle, jlId, jlPw, jlPwConfirm, jlName, jlBirth, jlBirth1, jlBirth2, jlPhoneNum1, jlPhoneNum2,
			jlGender, JlPhoneNum, jlAddr, jlEmail;
	private JTextField jtfId, jtfName, jtfBirthYear, jtfBirthMonth, jtfBirthday, jtfPhoneNum, jtfPhoneNum1,
			jtfPhoneNum2, jtfAddr, jtfEmail;
	private JComboBox<String> jcbPhoneNum;
	private ButtonGroup bgGender;
	private JRadioButton jrbM, jrbF;
	private JButton jbtIdConfirm, jbtConfirm, jbtClose;
	private JPasswordField jpfPw, jpfPwConfirm;
	private JTextField jtfZipcode, jtfAddress, jtfDetailAddr;
	private JButton jbtnSearchAddr;

	public JoinDetailView() {

		this.getContentPane().setBackground(new Color(0x3F4040));
		jpfPw = new JPasswordField();
		jpfPwConfirm = new JPasswordField();

		jlTitle = new JLabel("※회원가입");
		jlTitle.setForeground(Color.white);
		jlId = new JLabel("아이디");
		jlId.setForeground(Color.white);
		jlPw = new JLabel("비밀번호");
		jlPw.setForeground(Color.white);
		jlPwConfirm = new JLabel("비밀번호 재확인");
		jlPwConfirm.setForeground(Color.white);
		jlName = new JLabel("이름");
		jlName.setForeground(Color.white);
		jlBirth = new JLabel("생년월일");
		jlBirth.setForeground(Color.white);
		jlBirth1 = new JLabel("-");
		jlBirth1.setForeground(Color.white);
		jlBirth2 = new JLabel("-");
		jlBirth2.setForeground(Color.white);
		jlGender = new JLabel("성별");
		jlGender.setForeground(Color.white);
		JlPhoneNum = new JLabel("전화번호");
		JlPhoneNum.setForeground(Color.white);
		jlPhoneNum1 = new JLabel("-");
		jlPhoneNum1.setForeground(Color.white);
		jlPhoneNum2 = new JLabel("-");
		jlPhoneNum2.setForeground(Color.white);

		jbtnSearchAddr = new JButton("주소 검색");
		jbtnSearchAddr.setForeground(new Color(0x3F4040));
		jbtnSearchAddr.setBackground(Color.white);
		jtfZipcode = new JTextField();
		jtfAddress = new JTextField();

		jlAddr = new JLabel("주소");
		jlAddr.setForeground(Color.white);
		jlEmail = new JLabel("이메일");
		jlEmail.setForeground(Color.white);

		jtfId = new JTextField();
//		jtfPw = new JTextField();
//		jtfPwConfirm = new JTextField();
		jtfName = new JTextField();
		jtfBirthYear = new JTextField();
		jtfBirthMonth = new JTextField();
		jtfBirthday = new JTextField();
		jtfPhoneNum = new JTextField("010");
		jtfPhoneNum1 = new JTextField(4);
		jtfPhoneNum2 = new JTextField(4);
		jtfAddr = new JTextField();// 3
		jtfEmail = new JTextField();

		jbtIdConfirm = new JButton("중복");
		jbtIdConfirm.setForeground(new Color(0x3F4040));
		jbtIdConfirm.setBackground(Color.white);
		jbtConfirm = new JButton("확인");
		jbtConfirm.setForeground(new Color(0x3F4040));
		jbtConfirm.setBackground(Color.white);

		jbtClose = new JButton("닫기");
		jbtClose.setForeground(new Color(0x3F4040));
		jbtClose.setBackground(Color.white);

		jrbF = new JRadioButton("여자");
		jrbF.setBackground(new Color(0x3F4040));
		jrbF.setForeground(Color.white);
		jrbM = new JRadioButton("남자");
		jrbM.setBackground(new Color(0x3F4040));
		jrbM.setForeground(Color.white);

		bgGender = new ButtonGroup();
		bgGender.add(jrbM);
		bgGender.add(jrbF);

		jtfId.setBorder(null);
		jpfPwConfirm.setBorder(null);//
		jpfPw.setBorder(null);
		jtfName.setBorder(null);
		jtfBirthYear.setBorder(null);
		jtfBirthMonth.setBorder(null);
		jtfBirthday.setBorder(null);
		jtfPhoneNum1.setBorder(null);
		jtfPhoneNum2.setBorder(null);
		jtfAddr.setBorder(null);
		jtfEmail.setBorder(null);
		jtfAddress.setBorder(null);
		jtfEmail.setBorder(null);
		jtfZipcode.setBorder(null);

		setLayout(null);
		add(jtfZipcode);
		add(jbtnSearchAddr);
		add(jtfAddress);
		add(jlTitle);
		add(jlId);
		add(jlPw);
		add(jlPwConfirm);
		add(jlName);
		add(jlBirth);
		add(jlBirth1);
		add(jlBirth2);
		add(jlGender);
		add(JlPhoneNum);
		add(jlPhoneNum1);
		add(jlPhoneNum2);
		add(jlAddr);
		add(jlEmail);
		add(jtfId);
		add(jpfPw);
		add(jpfPwConfirm);
		add(jtfName);
		add(jtfBirthYear);
		add(jtfBirthMonth);
		add(jtfBirthday);
		add(jtfPhoneNum);
		add(jtfPhoneNum1);
		add(jtfPhoneNum2);
		add(jtfAddr);
		add(jtfEmail);
		add(jbtIdConfirm);
		add(jbtConfirm);
		add(jbtClose);
		add(jrbF);
		add(jrbM);

		jlTitle.setBounds(70, 10, 170, 30);
		jlId.setBounds(70, 50, 170, 30);
		jbtIdConfirm.setBounds(280, 50, 60, 30);
		jtfId.setBounds(170, 50, 100, 30);
		jlPw.setBounds(70, 100, 170, 30);
		jpfPw.setBounds(170, 100, 170, 30);
		jlPwConfirm.setBounds(70, 150, 170, 30);
		jpfPwConfirm.setBounds(170, 150, 170, 30);
		jlName.setBounds(70, 200, 170, 30);
		jtfName.setBounds(170, 200, 170, 30);
		jlBirth.setBounds(70, 250, 170, 30);
		jlBirth1.setBounds(230, 250, 30, 30);
		jlBirth2.setBounds(290, 250, 30, 30);
		jtfBirthYear.setBounds(170, 250, 40, 30);
		jtfBirthMonth.setBounds(230, 250, 40, 30);
		jtfBirthday.setBounds(290, 250, 40, 30);
		jlGender.setBounds(70, 300, 50, 30);
		jrbF.setBounds(170, 300, 70, 30);// 라디오버튼
		jrbM.setBounds(250, 300, 70, 30);
		JlPhoneNum.setBounds(70, 350, 170, 30);
		jtfPhoneNum.setBounds(170, 350, 40, 30);
		jlPhoneNum1.setBounds(220, 350, 50, 30);
		jlPhoneNum2.setBounds(280, 350, 50, 30);
		jtfPhoneNum1.setBounds(230, 350, 40, 30);
		jtfPhoneNum2.setBounds(300, 350, 40, 30);
		jlEmail.setBounds(70, 400, 170, 30);
		jtfEmail.setBounds(170, 400, 170, 30);

		jtfZipcode.setBounds(170, 450, 100, 30);
		jbtnSearchAddr.setBounds(280, 450, 90, 30);
		jtfAddress.setBounds(170, 500, 200, 30);
		jlAddr.setBounds(70, 450, 200, 30);
		jtfAddr.setBounds(170, 550, 200, 30);

		jbtConfirm.setBounds(130, 650, 60, 30);
		jbtClose.setBounds(250, 650, 60, 30);

		JoinDetailEvt jde = new JoinDetailEvt(this);
		jbtnSearchAddr.addActionListener(jde);
		jtfId.addActionListener(jde);
		jpfPw.addActionListener(jde);//
		jpfPwConfirm.addActionListener(jde);
		jtfName.addActionListener(jde);
		jtfBirthYear.addActionListener(jde);
		jtfBirthMonth.addActionListener(jde);
		jtfBirthday.addActionListener(jde);
		jtfPhoneNum1.addActionListener(jde);
		jtfPhoneNum2.addActionListener(jde);
		jtfAddr.addActionListener(jde);
		jtfEmail.addActionListener(jde);
		jbtIdConfirm.addActionListener(jde);
		jbtClose.addActionListener(jde);
		jbtConfirm.addActionListener(jde);
		jrbF.addActionListener(jde);
		jrbM.addActionListener(jde);
		jtfBirthYear.addKeyListener(jde);
		jtfBirthMonth.addKeyListener(jde);
		jtfBirthday.addKeyListener(jde);
		jtfPhoneNum.addKeyListener(jde);
		jtfPhoneNum1.addKeyListener(jde);
		jtfPhoneNum2.addKeyListener(jde);

		setBounds(100, 100, 450, 750);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// JoinDetail

	public JLabel getJlTitle() {
		return jlTitle;
	}

	public JLabel getJlId() {
		return jlId;
	}

	public JLabel getJlPw() {
		return jlPw;
	}

	public JLabel getJlPwConfirm() {
		return jlPwConfirm;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public JLabel getJlBirth() {
		return jlBirth;
	}

	public JLabel getJlBirth1() {
		return jlBirth1;
	}

	public JLabel getJlBirth2() {
		return jlBirth2;
	}

	public JLabel getJlPhoneNum1() {
		return jlPhoneNum1;
	}

	public JLabel getJlPhoneNum2() {
		return jlPhoneNum2;
	}

	public JLabel getJlGender() {
		return jlGender;
	}

	public JLabel getJlPhoneNum() {
		return JlPhoneNum;
	}

	public JLabel getJlAddr() {
		return jlAddr;
	}

	public JLabel getJlEmail() {
		return jlEmail;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfBirthYear() {
		return jtfBirthYear;
	}

	public JTextField getJtfBirthMonth() {
		return jtfBirthMonth;
	}

	public JTextField getJtfBirthday() {
		return jtfBirthday;
	}

	public JTextField getJtfPhoneNum() {
		return jtfPhoneNum;
	}

	public JTextField getJtfPhoneNum1() {
		return jtfPhoneNum1;
	}

	public JTextField getJtfPhoneNum2() {
		return jtfPhoneNum2;
	}

	public JTextField getJtfAddr() {
		return jtfAddr;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}

	public ButtonGroup getBgGender() {
		return bgGender;
	}

	public JRadioButton getJrbM() {
		return jrbM;
	}

	public JRadioButton getJrbF() {
		return jrbF;
	}

	public JButton getJbtIdConfirm() {
		return jbtIdConfirm;
	}

	public JButton getJbtConfirm() {
		return jbtConfirm;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public JPasswordField getJpfPw() {
		return jpfPw;
	}

	public JPasswordField getJpfPwConfirm() {
		return jpfPwConfirm;
	}

	public JTextField getJtfZipcode() {
		return jtfZipcode;
	}

	public JTextField getJtfAddress() {
		return jtfAddress;
	}

	public JTextField getJtfDetailAddr() {
		return jtfDetailAddr;
	}

	public JButton getJbtnSearchAddr() {
		return jbtnSearchAddr;
	}

}// class
