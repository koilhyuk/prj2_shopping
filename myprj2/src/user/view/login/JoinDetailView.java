package user.view.login;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import user.controller.login.JoinDetailEvt;

@SuppressWarnings("serial")
public class JoinDetailView extends JDialog {

	private JTextField jtfId, jtfName,jtfPhoneNum1,jtfPhoneNum2, jtfAddr, jtfEmail;
	private JComboBox<String> jcbPhoneNum,  jcbBirthMonth, jcbBirthDay,jcbBirthYear;
	private DefaultComboBoxModel<String> dcbmYear,dcbmMonth, dcbmDay, dcmbPhone;
	
	private ButtonGroup bgGender;
	private JRadioButton jrbM, jrbF;
	private JButton jbtIdConfirm, jbtConfirm, jbtClose;
	private JPasswordField jpfPw, jpfPwConfirm;
	private JTextField jtfZipcode, jtfAddress;
	private JButton jbtnSearchAddr;

	public JoinDetailView(ClientLoginView clv) {
		super(clv, "회원가입", false);
		this.getContentPane().setBackground(new Color(0x352A26));
		jpfPw = new JPasswordField();
		jpfPwConfirm = new JPasswordField();

		Font f = new Font("고딕", Font.BOLD, 15);
		JLabel jlTitle = new JLabel("♡ 회 원 가 입 ♡");
		jlTitle.setFont(f);
		jlTitle.setForeground(Color.white);
		JLabel jlId = new JLabel("아이디");
		jlId.setForeground(Color.white);
		JLabel jlPw = new JLabel("비밀번호");
		jlPw.setForeground(Color.white);
		JLabel jlPwConfirm = new JLabel("비밀번호 확인");
		jlPwConfirm.setForeground(Color.white);
		JLabel jlName = new JLabel("이름");
		jlName.setForeground(Color.white);
		JLabel jlBirth = new JLabel("생년월일");
		jlBirth.setForeground(Color.white);
		JLabel jlGender = new JLabel("성별");
		jlGender.setForeground(Color.white);
		JLabel JlPhoneNum = new JLabel("전화번호");
		JlPhoneNum.setForeground(Color.white);
		JLabel jlPhoneNum1 = new JLabel("-");
		jlPhoneNum1.setForeground(Color.white);
		JLabel jlPhoneNum2 = new JLabel("-");
		jlPhoneNum2.setForeground(Color.white);

		jbtnSearchAddr = new JButton("주소 검색");
		jbtnSearchAddr.setForeground(new Color(0x3F4040));
		jbtnSearchAddr.setBackground(Color.white);
		jtfZipcode = new JTextField();
		jtfAddress = new JTextField();
		jtfAddr = new JTextField();// 상세
		
		JLabel jlAddr = new JLabel("주소");
		jlAddr.setForeground(Color.white);
		JLabel jlEmail = new JLabel("이메일");
		jlEmail.setForeground(Color.white);

		jtfId = new JTextField();
		jtfName = new JTextField();
		
		//생년
		Calendar cal = new GregorianCalendar();
		int fYear=1940;
		int nowYear=cal.get(Calendar.YEAR);
		List<String> tempArr=new ArrayList<String>();
		for(int i=(nowYear-fYear); i>=0; i--) {
			tempArr.add(String.valueOf(fYear+i));
		}//end for
		String[] temp=new String[tempArr.size()];
		for(int j=0; j<temp.length; j++) {
			temp[j]=tempArr.get(j);
		}//end for
		dcbmYear= new DefaultComboBoxModel<String>(temp);
		jcbBirthYear= new JComboBox<String>(dcbmYear);
		
		//월
		String month[]= new String[12];
		for(int i=0;i<12; i++) {
			month[i]=String.valueOf(i+1);
				if(month[i].length()==1) {
					month[i]="0"+String.valueOf(i+1);
				}//end if 
		}//end for
		dcbmMonth= new DefaultComboBoxModel<String>(month);
		jcbBirthMonth= new JComboBox<String>(dcbmMonth);
		
		//일 
		String day[]=new String[31];
		for(int i=0; i<31; i++) {
			day[i]=String.valueOf(i+1);
			if(day[i].length()==1) {
				day[i]="0"+String.valueOf(i+1);
			}//end if 
		}//end for
		dcbmDay = new DefaultComboBoxModel<String>(day);
		jcbBirthDay= new JComboBox<String>(dcbmDay);
		
		//핸드폰
		String[] phone= {"010","011","017","018","019"};
		dcmbPhone= new DefaultComboBoxModel<String>(phone);
		jcbPhoneNum = new JComboBox<String>(dcmbPhone);
		
		jtfPhoneNum1 = new JTextField(4);
		jtfPhoneNum2 = new JTextField(4);
		jtfEmail = new JTextField();

		jbtIdConfirm = new JButton("중복");
		jbtIdConfirm.setForeground(new Color(0x3F4040));
		jbtIdConfirm.setBackground(Color.white);
		jbtConfirm = new JButton("가입");
		jbtConfirm.setForeground(new Color(0x3F4040));
		jbtConfirm.setBackground(Color.white);

		jbtClose = new JButton("닫기");
		jbtClose.setForeground(new Color(0x3F4040));
		jbtClose.setBackground(Color.white);

		jrbF = new JRadioButton("여자");
		jrbF.setBackground(new Color(0x352A26));
		jrbF.setForeground(Color.white);
		jrbM = new JRadioButton("남자");
		jrbM.setBackground(new Color(0x352A26));
		jrbM.setForeground(Color.white);

		bgGender = new ButtonGroup();
		bgGender.add(jrbM);
		bgGender.add(jrbF);

		jtfId.setBorder(null);
		jpfPwConfirm.setBorder(null);//
		jpfPw.setBorder(null);
		jtfName.setBorder(null);
		jtfPhoneNum1.setBorder(null);
		jtfPhoneNum2.setBorder(null);
		jtfAddr.setBorder(null);
		jtfEmail.setBorder(null);
		jtfAddress.setBorder(null);
		jtfEmail.setBorder(null);
		jtfZipcode.setBorder(null);

		setLayout(null);
		add(jtfZipcode);//
		add(jbtnSearchAddr);
		add(jtfAddress);//
		add(jlTitle);
		add(jlId);
		add(jlPw);
		add(jlPwConfirm);
		add(jlName);
		add(jlBirth);
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
		add(jcbBirthYear);
		add(jcbBirthMonth);
		add(jcbBirthDay);
		add(jcbPhoneNum);
		add(jtfPhoneNum1);
		add(jtfPhoneNum2);
		add(jtfAddr);//
		add(jtfEmail);
		add(jbtIdConfirm);
		add(jbtConfirm);
		add(jbtClose);
		add(jrbF);
		add(jrbM);

		jlTitle.setBounds(70, 10, 170, 30);
		jlId.setBounds(70, 50, 100, 30);
		jtfId.setBounds(170, 50, 170, 30);
		jbtIdConfirm.setBounds(360, 50, 60, 30);
		
		jlPw.setBounds(70, 100, 100, 30);
		jpfPw.setBounds(170, 100, 170, 30);
		jlPwConfirm.setBounds(70, 150, 150, 30);
		jpfPwConfirm.setBounds(170, 150, 170, 30);
		
		jlName.setBounds(70, 200, 100, 30);
		jtfName.setBounds(170, 200, 170, 30);
		
		jlBirth.setBounds(70, 250, 100, 30);
		jcbBirthYear.setBounds(170, 250, 80, 30);
		jcbBirthMonth.setBounds(260, 250, 50, 30);
		jcbBirthDay.setBounds(320, 250, 50, 30);
		
		jlGender.setBounds(70, 300, 80, 30);
		jrbF.setBounds(170, 300, 70, 30);// 라디오버튼
		jrbM.setBounds(250, 300, 70, 30);
		
		JlPhoneNum.setBounds(70, 350, 100, 30);
		jlPhoneNum1.setBounds(245, 350, 30, 30);
		jlPhoneNum2.setBounds(315, 350, 30, 30);
		jcbPhoneNum.setBounds(170, 350, 60, 30);
		jtfPhoneNum1.setBounds(260, 350, 40, 30);
		jtfPhoneNum2.setBounds(330, 350, 40, 30);
		
		jlEmail.setBounds(70, 400, 100, 30);
		jtfEmail.setBounds(170, 400, 200, 30);

		jlAddr.setBounds(70, 450, 200, 30);
		jbtnSearchAddr.setBounds(320, 450, 100, 30);
		jtfZipcode.setBounds(170, 450, 130, 30);
		jtfAddress.setBounds(170, 500, 250, 30);
		jtfAddr.setBounds(170, 550, 250, 30);//상세 

		jbtConfirm.setBounds(250, 620, 80, 30);
		jbtClose.setBounds(350, 620, 80, 30);

		JoinDetailEvt jde = new JoinDetailEvt(this);
		jbtConfirm.addActionListener(jde);
		jbtIdConfirm.addActionListener(jde);
		jbtClose.addActionListener(jde);
		jbtnSearchAddr.addActionListener(jde);
		
		jtfPhoneNum1.addKeyListener(jde);
		jtfPhoneNum2.addKeyListener(jde);

		setBounds(100, 100, 500, 740);
		setVisible(true);
		setResizable(false);
	}// JoinDetail

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfName() {
		return jtfName;
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

	public JComboBox<String> getJcbBirthMonth() {
		return jcbBirthMonth;
	}

	public JComboBox<String> getJcbBirthDay() {
		return jcbBirthDay;
	}

	public JComboBox<String> getJcbBirthYear() {
		return jcbBirthYear;
	}

	public DefaultComboBoxModel<String> getDcbmYear() {
		return dcbmYear;
	}

	public DefaultComboBoxModel<String> getDcbmMonth() {
		return dcbmMonth;
	}

	public DefaultComboBoxModel<String> getDcbmDay() {
		return dcbmDay;
	}

	public DefaultComboBoxModel<String> getDcmbPhone() {
		return dcmbPhone;
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

	public JButton getJbtnSearchAddr() {
		return jbtnSearchAddr;
	}

}// class
