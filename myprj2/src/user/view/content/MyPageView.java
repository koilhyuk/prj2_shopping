package user.view.content;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import user.controller.content.MyPageEvt;

@SuppressWarnings("serial")
public class MyPageView extends JFrame {
	private JLabel jlMyPage, jlId, jlPassword, jlChangePass,jlPhone, jlAddr, jlBirth, jlEmail, jlPhoneH1, jlPhoneH2;
	private JTextField jtfId, jtfPassword, jtfChangePass,jtfPhoneFront,jtfPhoneBehind, jtfAddr, jtfBirth, jtfEmail;
	private JButton jbtChange, jbtClose; 
	
	private DefaultComboBoxModel<String> dcbmPhone;
	private JComboBox<String> jcbPhoneNum;
	
	public MyPageView() {
		super("내 정보변경 창");
		//핸드폰 
		String[] phoneData= {"010","011","019","017"};
		dcbmPhone= new DefaultComboBoxModel<String>(phoneData);
		jcbPhoneNum= new JComboBox<String>(dcbmPhone);
		
		//라벨
		jlMyPage= new JLabel("정보 변경");
		jlId= new JLabel("아이디");
		jlPassword= new JLabel("현재 비밀번호");
		jlChangePass =  new JLabel("변경할 비밀번호");
		jlPhone= new JLabel("전화번호");
		jlBirth = new JLabel("생년월일");
		jlAddr= new JLabel("주소지");
		jlEmail= new JLabel("이메일");
		jlPhoneH1= new JLabel("-");
		jlPhoneH2= new JLabel("-");
		
		//T.F
		jtfId= new JTextField();
		jtfPassword= new JTextField(10);
		jtfChangePass= new JTextField(10);
		jtfPhoneFront= new JTextField(4);
		jtfPhoneBehind= new JTextField(4);
		jtfAddr= new JTextField(15);
		jtfBirth= new JTextField(10);
		jtfEmail= new JTextField(20);
		
		jbtChange= new JButton("수정");
		jbtClose= new JButton("닫기");
		
		//수동
		setLayout(null);
		//라벨
		jtfId.setEditable(false); //수정불가
		jlMyPage.setBounds(220, 30, 100, 40);
		jlId.setBounds(50, 90, 120, 50);
		jlPassword.setBounds(50, 170, 120, 50);
		jlChangePass.setBounds(50, 250, 120, 50);
		jlPhone.setBounds(50, 330, 120, 50);		
		jlBirth.setBounds(50, 420, 120, 50);
		jlAddr.setBounds(50, 500, 120, 50);
		jlEmail.setBounds(50, 580, 120, 50);
		
		//버튼
		jbtChange.setBounds(100, 680, 120, 40);
		jbtClose.setBounds(250, 680, 120, 40);
		
		//T.F
		jtfId.setBounds(200, 90, 250, 40); 
		jtfPassword.setBounds(200, 170, 250, 40);
		jtfChangePass.setBounds(200, 250, 250, 40);
		jcbPhoneNum.setBounds(200, 330, 70, 40);
		jtfPhoneFront.setBounds(290, 330, 70, 40); 
		jtfPhoneBehind.setBounds(380, 330, 70, 40);
		jtfBirth.setBounds(200, 420, 250, 40);
		jtfAddr.setBounds(200, 500, 250, 40);
		jtfEmail.setBounds(200, 580, 250, 40);
		jlPhoneH1.setBounds(280, 330, 30, 40);
		jlPhoneH2.setBounds(365, 330, 30, 40);
		
		//배치
		add(jlMyPage);
		add(jlId);
		add(jlPassword);
		add(jlChangePass);
		add(jlPhone);
		add(jlBirth);
		add(jlAddr);
		add(jlEmail);
		add(jlPhoneH1);
		add(jlPhoneH2);
	
		add(jbtChange);
		add(jbtClose);
		
		add(jtfId);
		add(jtfPassword);
		add(jtfChangePass);
		add(jcbPhoneNum);
		add(jtfPhoneFront);
		add(jtfPhoneBehind);
		add(jtfBirth);
		add(jtfAddr);
		add(jtfEmail);
		
		MyPageEvt mpe=new MyPageEvt(this);
		jbtChange.addActionListener(mpe);
		jbtClose.addActionListener(mpe);
		
		setBounds(100, 100, 500, 800);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//MyDataView

	public JLabel getJlMyPage() {
		return jlMyPage;
	}

	public JLabel getJlId() {
		return jlId;
	}

	public JLabel getJlPassword() {
		return jlPassword;
	}

	public JLabel getJlChangePass() {
		return jlChangePass;
	}

	public JLabel getJlPhone() {
		return jlPhone;
	}

	public JLabel getJlAddr() {
		return jlAddr;
	}

	public JLabel getJlBirth() {
		return jlBirth;
	}

	public JLabel getJlEmail() {
		return jlEmail;
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

	public JTextField getJtfPassword() {
		return jtfPassword;
	}

	public JTextField getJtfChangePass() {
		return jtfChangePass;
	}

	public JTextField getJtfPhoneFront() {
		return jtfPhoneFront;
	}

	public JTextField getJtfPhoneBehind() {
		return jtfPhoneBehind;
	}

	public JTextField getJtfAddr() {
		return jtfAddr;
	}

	public JTextField getJtfBirth() {
		return jtfBirth;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JButton getJbtChange() {
		return jbtChange;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public DefaultComboBoxModel<String> getDcbmPhone() {
		return dcbmPhone;
	}

	public JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}
	
}//class
