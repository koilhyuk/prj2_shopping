package user.view.content;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import user.controller.content.MyDataEvt;

@SuppressWarnings("serial")
public class MyDataView extends JFrame{
	
	private JLabel jlMyPage;
	private JTextArea jtaName;
	private JButton jbtOrderList, jbtMyData, jbtWithdrawal, jbtOk, jbtLogOut;
	//카드등록, 찜하기, 주문내역, 내정보변경 
	
	public MyDataView() {
		super("마이페이지");
		
		jlMyPage= new JLabel("마이페이지");
		jtaName= new JTextArea("+회원님 환영합니다. \n");
		jbtOrderList= new JButton("주문내역");
		jbtMyData= new JButton("내 정보 변경");
		jbtWithdrawal= new JButton("탈퇴");
		jbtOk= new JButton("확인");
		jbtLogOut= new JButton("로그아웃");
		
		
		//이미지 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\예시.PNG");
		JLabel jtaImage= new JLabel(iImage);
		//수동
		setLayout(null);
		jlMyPage.setBounds(10, 30, 100, 40);
		jtaName.setBounds(40, 100, 250, 80);
		jbtOrderList.setBounds(40, 180, 125, 80);
		jbtMyData.setBounds(165, 180, 125, 80);
		jbtWithdrawal.setBounds(40, 260, 250, 60);
		jbtOk.setBounds(50,380,100,40);
		jbtLogOut.setBounds(180,380,100,40);
		jtaImage.setBounds(315, 100, 160, 300);
		
		add(jlMyPage);
		add(jtaName);
		add(jbtOrderList);
		add(jbtMyData);
		add(jbtWithdrawal);
		add(jbtOk);
		add(jbtLogOut);
		add(jtaImage);
		
		MyDataEvt mde=new MyDataEvt(this);
		jbtMyData.addActionListener(mde);
		jbtWithdrawal.addActionListener(mde);
		jbtOk.addActionListener(mde);
		jbtLogOut.addActionListener(mde);
		
		setBounds(100, 100, 500, 500);
		//가시화
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//mypageView

	
	public static void main(String[] args) {
		new MyDataView();
	}//main


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

}//class
