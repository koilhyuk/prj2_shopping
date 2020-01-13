package user.view.content;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import user.controller.content.UserMyPageEvt;

@SuppressWarnings("serial")
public class UserMyPageView extends JFrame{
	
	private JLabel jlMyPage;
	private JTextArea jtaName;
	private JButton jbtOrderList, jbtMyData, jbtWithdrawal, jbtOk, jbtLogOut, jbtJJim;
	//카드등록, 찜하기, 주문내역, 내정보변경 
	
	private static String id;
	private UserMyOrderView umo;
	public UserMyPageView(String id) {
		super("마이페이지");
		this.id=id;
		
		Font mFont= new Font("맑은고딕", Font.BOLD, 15);
		Font jtFont= new Font("맑은고딕", Font.BOLD, 13);
		jlMyPage= new JLabel("▒  마이페이지  ▒");
		jlMyPage.setFont(mFont);
		jtaName= new JTextArea("\n\t♡♡'"+id+"'회원님 환영합니다. ♡♡\n");
		jtaName.setFont(jtFont);
		jtaName.setEditable(false);
		
		jbtOrderList= new JButton("주문내역");
		jbtOrderList.setFont(mFont);
		jbtOrderList.setForeground(Color.white);
		jbtOrderList.setBackground(new Color(0x3F4040));
		
		jbtMyData= new JButton("내 정보 변경");
		jbtMyData.setFont(mFont);
		jbtMyData.setForeground(Color.white);
		jbtMyData.setBackground(new Color(0x3F4040));
		
		jbtWithdrawal= new JButton("카드등록");
		jbtWithdrawal.setFont(mFont);
		jbtWithdrawal.setForeground(Color.white);
		jbtWithdrawal.setBackground(new Color(0x3F4040));
		
		jbtJJim= new JButton("찜한 상품");
		jbtJJim.setFont(mFont);
		jbtJJim.setForeground(Color.white);
		jbtJJim.setBackground(new Color(0x3F4040));
		
		jbtOk= new JButton("닫기");
		jbtOk.setForeground(Color.white);
		jbtOk.setBackground(new Color(0x3F4040));
		
		jbtLogOut= new JButton("로그아웃");
		jbtLogOut.setForeground(Color.white);
		jbtLogOut.setBackground(new Color(0x3F4040));
		//이미지 // 광고 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\예시.PNG");
		JLabel jtaImage= new JLabel("이미지"+iImage);
		jtaImage.setBorder(new TitledBorder("이미지"));
		//수동
		setLayout(null);
		jlMyPage.setBounds(30, 15, 200, 40);
		jtaName.setBounds(40, 70, 400, 50);
		jbtOrderList.setBounds(40, 140, 250, 50);//주문내역
		jbtMyData.setBounds(40, 200, 250, 50);//내정보변경
		jbtWithdrawal.setBounds(40, 260, 250, 50); //카드등록
		jbtJJim.setBounds(40,320,250,50);//찜하기
		jbtOk.setBounds(100,390,100,30);
		jtaImage.setBounds(315, 130, 160, 280);
		
		add(jlMyPage);
		add(jtaName);
		add(jbtOrderList);
		add(jbtMyData);
		add(jbtWithdrawal);
		add(jbtJJim);
		add(jbtOk);
		add(jtaImage);
		
		UserMyPageEvt mde=new UserMyPageEvt(this,id);
		jbtOrderList.addActionListener(mde);
		jbtMyData.addActionListener(mde);
		jbtWithdrawal.addActionListener(mde);
		jbtOk.addActionListener(mde);
		jbtLogOut.addActionListener(mde);
		jbtJJim.addActionListener(mde);
		
		this.getContentPane().setBackground(Color.white);
		setBounds(100, 100, 500, 500);
		//가시화
		setVisible(true);
		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//mypageView

	public JLabel getJlMyPage() {
		return jlMyPage;
	}


	public JTextArea getJtaName() {
		return jtaName;
	}


	public JButton getJbtOrderList() {
		return jbtOrderList;
	}


	public JButton getJbtMyData() {
		return jbtMyData;
	}


	public JButton getJbtWithdrawal() {
		return jbtWithdrawal;
	}


	public JButton getJbtOk() {
		return jbtOk;
	}


	public JButton getJbtLogOut() {
		return jbtLogOut;
	}


	public JButton getJbtJJim() {
		return jbtJJim;
	}


	public static String getId() {
		return id;
	}


}//class
