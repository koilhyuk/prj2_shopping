package user.view.content;

import java.awt.Font;

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
	private JButton jbtOrderList, jbtMyData, jbtWithdrawal, jbtOk, jbtLogOut, jbtJJim;
	//카드등록, 찜하기, 주문내역, 내정보변경 
	
	private static String id;
	
	public MyDataView(String id) {
		super("마이페이지");
		this.id=id;

		jlMyPage= new JLabel("마이페이지");
		jtaName= new JTextArea("'"+id+"'회원님 환영합니다. \n");
		jbtOrderList= new JButton("주문내역");
		jbtMyData= new JButton("내 정보 변경");
		jbtWithdrawal= new JButton("카드등록");
		jbtJJim= new JButton("찜한 상품");
		jbtOk= new JButton("확인");
		jbtLogOut= new JButton("로그아웃");
		
		//이미지 // 광고 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\예시.PNG");
		JLabel jtaImage= new JLabel(iImage);
		//수동
		setLayout(null);
		jlMyPage.setBounds(10, 30, 100, 40);
		jtaName.setBounds(40, 70, 250, 50);
		jbtOrderList.setBounds(40, 140, 250, 60);//주문내역
		jbtMyData.setBounds(40, 210, 250, 60);//내정보변경
		jbtWithdrawal.setBounds(40, 260, 250, 60); //카드등록
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
		new MyDataView("hyebin");
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
