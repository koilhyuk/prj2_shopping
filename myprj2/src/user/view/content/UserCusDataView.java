package user.view.content;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import user.controller.content.UserCusDataEvt;
import user.vo.content.SelectCusDataVO;

@SuppressWarnings("serial")
public class UserCusDataView extends JFrame {

	private JLabel jlTitle, jlId, jlPw, jlName, jlBirth, jlPhoneNum1, jlPhoneNum2, JlPhoneNum, jlAddr,
			jlEmail;
	private JTextField jtfId, jtfName, jtfBirth,jtfPhoneNum1, jtfPhoneNum2, jtfAddr,
			jtfEmail;
	private DefaultComboBoxModel<String> dcbPhone;
	private JComboBox<String> jcbPhoneNum;
	private JButton jbtWithdrawal, jbtConfirm, jbtBack, jbtnSearchAddr, jbtnPass;
	private JTextField jtfZipcode, jtfAddress, jtfDetailAddr;

	private static String id;
	private SelectCusDataVO cdVO;
	public UserCusDataView(String id, SelectCusDataVO cdVO) {
		super(id+"님의 회원정보");
		this.id=id;
		this.cdVO=cdVO;
//		jpfPw = new JPasswordField();
//		jpfPwConfirm = new JPasswordField();
		Font mFont= new Font("맑은고딕", Font.BOLD, 20);
		jlTitle = new JLabel("♥회원정보수정♥");
		jlTitle.setFont(mFont);
		jlId = new JLabel("아이디");
		jlPw = new JLabel("비밀번호");
		jlName = new JLabel("이름");
		jlBirth = new JLabel("생년월일");
		JlPhoneNum = new JLabel("전화번호");
		jlPhoneNum1 = new JLabel("-");
		jlPhoneNum2 = new JLabel("-");

		jbtnSearchAddr = new JButton("주소 검색");
		jtfZipcode = new JTextField();
		jtfAddress = new JTextField();

		jlAddr = new JLabel("주소");
		jlEmail = new JLabel("이메일");

		jtfId = new JTextField();
		jtfName = new JTextField();
		jtfBirth = new JTextField();
		String[] phone = { "010", "011", "017", "018", "019" };
		dcbPhone = new DefaultComboBoxModel<String>(phone);
		jcbPhoneNum = new JComboBox<String>(dcbPhone);
		jtfPhoneNum1 = new JTextField(4);
		jtfPhoneNum2 = new JTextField(4);
		jtfAddr = new JTextField();// 3
		jtfEmail = new JTextField();

		jbtWithdrawal = new JButton("회원 탈퇴");
		jbtWithdrawal.setForeground(new Color(0x3F4040));
		jbtConfirm = new JButton("수정");
		jbtConfirm.setForeground(new Color(0x3F4040));

		jbtBack = new JButton("닫기");
		jbtBack.setForeground(new Color(0x3F4040));

		jbtnPass = new JButton("비밀번호 변경");
		
		jtfId.setEditable(false);
		jtfName.setEditable(false);
		jtfBirth.setEditable(false);
		jtfAddress.setEditable(false);
		jtfZipcode.setEditable(false);

		// setText
		jtfId.setText(id);//
		jtfEmail.setText(cdVO.getM_email());
		jtfName.setText(cdVO.getM_name());
		jtfBirth.setText(cdVO.getM_birth());
		jtfZipcode.setText(cdVO.getZ_zipcode());
		jtfAddress.setText(cdVO.getZ_addr());
		jtfAddr.setText(cdVO.getM_detail_addr());
		
		String phone1=cdVO.getM_phone().substring(4,cdVO.getM_phone().lastIndexOf("-"));
		String phone2=cdVO.getM_phone().substring(cdVO.getM_phone().lastIndexOf("-")+1);
		jtfPhoneNum1.setText(phone1);
		jtfPhoneNum2.setText(phone2);

		setLayout(null);
		add(jtfZipcode);
		add(jbtnSearchAddr);
		add(jtfAddress);
		add(jlTitle);
		add(jlId);
		add(jlPw);
		add(jlName);
		add(jlBirth);
		add(JlPhoneNum);
		add(jlPhoneNum1);
		add(jlPhoneNum2);
		add(jlAddr);
		add(jlEmail);
		add(jtfId);
		add(jtfName);
		add(jtfBirth);
		add(jcbPhoneNum);
		add(jtfPhoneNum1);
		add(jtfPhoneNum2);
		add(jtfAddr);
		add(jtfEmail);
		add(jbtWithdrawal);
		add(jbtConfirm);
		add(jbtBack);
		add(jbtnPass);

		jlTitle.setBounds(70, 40, 170, 30);
		jlId.setBounds(70, 100, 60, 30);
		jtfId.setBounds(170, 100, 180, 30);

		jbtWithdrawal.setBounds(370, 100, 100, 30);
		jlPw.setBounds(70, 150, 100, 30);
		jbtnPass.setBounds(170, 150, 170, 35);

		jlName.setBounds(70, 200, 60, 30);
		jtfName.setBounds(170, 200, 200, 30);

		jlBirth.setBounds(70, 250, 60, 30);
		jtfBirth.setBounds(170, 250, 200, 30);
		JlPhoneNum.setBounds(70, 300, 60, 30);
		jcbPhoneNum.setBounds(170, 300, 55, 30);
		jlPhoneNum1.setBounds(235, 300, 60, 30);
		jlPhoneNum2.setBounds(300, 300, 60, 30);
		jtfPhoneNum1.setBounds(250, 300, 40, 30);
		jtfPhoneNum2.setBounds(320, 300, 40, 30);

		jlEmail.setBounds(70, 350, 60, 30);
		jtfEmail.setBounds(170, 350, 200, 30);

		jlAddr.setBounds(70, 400, 60, 30);
		jtfZipcode.setBounds(170, 400, 120, 30);
		jbtnSearchAddr.setBounds(300, 400, 90, 30);
		jtfAddress.setBounds(170, 450, 300, 30);
		jtfAddr.setBounds(170, 500, 300, 30);
		jbtConfirm.setBounds(130, 560, 70, 30);
		jbtBack.setBounds(250, 560, 70, 30);
		
		jbtBack.setForeground(Color.white);
		jbtBack.setBackground(new Color(0x043424));
		jbtConfirm.setForeground(Color.white);
		jbtConfirm.setBackground(new Color(0x043424));
		
		jbtnSearchAddr.setForeground(Color.white);
		jbtnSearchAddr.setBackground(new Color(0x043424));
		jbtnPass.setForeground(Color.white);
		jbtnPass.setBackground(new Color(0x043424));
		jbtWithdrawal.setForeground(Color.white);
		jbtWithdrawal.setBackground(new Color(0x043424));

		UserCusDataEvt ucd = new UserCusDataEvt(this, id);
		jbtConfirm.addActionListener(ucd);
		jbtBack.addActionListener(ucd);
		jbtnPass.addActionListener(ucd);
		jbtnSearchAddr.addActionListener(ucd);
		jbtWithdrawal.addActionListener(ucd);
		this.getContentPane().setBackground(Color.white);
		setBounds(100, 100, 520, 680);
		setResizable(false);
		setVisible(true);
	}// MyPageView
	
	public JLabel getJlTitle() {
		return jlTitle;
	}
	public JLabel getJlId() {
		return jlId;
	}
	public JLabel getJlPw() {
		return jlPw;
	}
	public JLabel getJlName() {
		return jlName;
	}
	public JLabel getJlBirth() {
		return jlBirth;
	}
	public JLabel getJlPhoneNum1() {
		return jlPhoneNum1;
	}
	public JLabel getJlPhoneNum2() {
		return jlPhoneNum2;
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
	public JTextField getJtfBirth() {
		return jtfBirth;
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
	public DefaultComboBoxModel<String> getDcbPhone() {
		return dcbPhone;
	}
	public JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}
	public JButton getJbtWithdrawal() {
		return jbtWithdrawal;
	}
	public JButton getJbtConfirm() {
		return jbtConfirm;
	}
	public JButton getJbtBack() {
		return jbtBack;
	}
	public JButton getJbtnSearchAddr() {
		return jbtnSearchAddr;
	}
	public JButton getJbtnPass() {
		return jbtnPass;
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
	public static String getId() {
		return id;
	}
	public SelectCusDataVO getCdVO() {
		return cdVO;
	}

}// class
