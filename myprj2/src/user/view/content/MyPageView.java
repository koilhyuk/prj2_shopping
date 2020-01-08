package user.view.content;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MyPageView extends JFrame {

	private JLabel jlTitle, jlId, jlPw, jlPwConfirm, jlName, jlBirth, jlBirth1, jlBirth2, jlPhoneNum1, jlPhoneNum2,
			jlGender, JlPhoneNum, jlAddr, jlEmail;
	private JTextField jtfId, jtfName, jtfBirthYear, jtfBirthMonth, jtfBirthday, jtfPhoneNum, jtfPhoneNum1,
			jtfPhoneNum2, jtfAddr, jtfEmail;
	private JComboBox<String> jcbPhoneNum;
	private ButtonGroup bgGender;
	private JRadioButton jrbM, jrbF;
	private JButton jbtWithdrawal , jbtConfirm, jbtBack;
	private JPasswordField jpfPw, jpfPwConfirm;
	private JTextField jtfZipcode, jtfAddress, jtfDetailAddr;
	private JButton jbtnSearchAddr;

	public MyPageView() {
		jpfPw = new JPasswordField();
		jpfPwConfirm = new JPasswordField();

		jlTitle = new JLabel("회원정보수정");
		jlId = new JLabel("아이디");
		jlPw = new JLabel("비밀번호");
		jlPwConfirm = new JLabel("비밀번호 변경");
		jlName = new JLabel("이름");
		jlBirth = new JLabel("생년월일");
		jlBirth1 = new JLabel("-");
		jlBirth2 = new JLabel("-");
		jlGender = new JLabel("성별");
		JlPhoneNum = new JLabel("전화번호");
		jlPhoneNum1 = new JLabel("-");
		jlPhoneNum2 = new JLabel("-");

		jbtnSearchAddr = new JButton("주소 검색");
		jtfZipcode = new JTextField();
		jtfAddress = new JTextField();

		jlAddr = new JLabel("주소");
		jlEmail = new JLabel("이메일");

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

		jbtWithdrawal  = new JButton("회원 탈퇴");
		jbtWithdrawal.setForeground(new Color(0x3F4040));
		jbtConfirm = new JButton("수정");
		jbtConfirm.setForeground(new Color(0x3F4040));

		jbtBack = new JButton("닫기");
		jbtBack.setForeground(new Color(0x3F4040));

		jrbF = new JRadioButton("여자");
		jrbM = new JRadioButton("남자");

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
		add(jbtWithdrawal );
		add(jbtConfirm);
		add(jbtBack);
		add(jrbF);
		add(jrbM);

		jlTitle.setBounds(70, 10, 170, 30);
		jlId.setBounds(70, 50, 170, 30);
		jbtWithdrawal .setBounds(280, 50, 60, 30);
		jtfId.setBounds(170, 50, 170, 30);
		jlPw.setBounds(70, 100, 170, 30);
		jpfPw.setBounds(170, 100, 170, 30);
		jlPwConfirm.setBounds(70, 150, 170, 30);
		jpfPwConfirm.setBounds(170, 150, 170, 30);
		jlName.setBounds(70, 200, 170, 30);
		jtfName.setBounds(170, 200, 170, 30);
		jlBirth.setBounds(70, 250, 170, 30);
		jlBirth1.setBounds(230, 250, 40, 30);
		jlBirth2.setBounds(290, 250, 40, 30);
		jtfBirthYear.setBounds(170, 250, 50, 30);
		jtfBirthMonth.setBounds(230, 250, 50, 30);
		jtfBirthday.setBounds(290, 250, 50, 30);
		jlGender.setBounds(70, 300, 50, 30);
		jrbF.setBounds(170, 300, 70, 30);// 라디오버튼
		jrbM.setBounds(250, 300, 70, 30);
		JlPhoneNum.setBounds(70, 350, 170, 30);
		jtfPhoneNum.setBounds(170, 350, 50, 30);
		jlPhoneNum1.setBounds(220, 350, 60, 30);
		jlPhoneNum2.setBounds(280, 350, 60, 30);
		jtfPhoneNum1.setBounds(230, 350, 40, 30);
		jtfPhoneNum2.setBounds(300, 350, 40, 30);
		jlEmail.setBounds(70, 400, 120, 30);
		jtfEmail.setBounds(170, 400, 190, 30);
		jtfZipcode.setBounds(170, 450, 120, 30);
		jbtnSearchAddr.setBounds(300, 450, 90, 30);
		jtfAddress.setBounds(170, 500, 230, 30);
		jlAddr.setBounds(70, 450, 200, 30);
		jtfAddr.setBounds(170, 550, 230, 30);

		jbtConfirm.setBounds(130, 650, 60, 30);
		jbtBack.setBounds(250, 650, 60, 30);

		setBounds(100, 100, 450, 750);
		setVisible(true);
	}

	
}//class
