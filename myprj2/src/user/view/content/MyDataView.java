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
	//ī����, ���ϱ�, �ֹ�����, ���������� 
	
	public MyDataView() {
		super("����������");
		
		jlMyPage= new JLabel("����������");
		jtaName= new JTextArea("+ȸ���� ȯ���մϴ�. \n");
		jbtOrderList= new JButton("�ֹ�����");
		jbtMyData= new JButton("�� ���� ����");
		jbtWithdrawal= new JButton("Ż��");
		jbtOk= new JButton("Ȯ��");
		jbtLogOut= new JButton("�α׾ƿ�");
		
		
		//�̹��� 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\����.PNG");
		JLabel jtaImage= new JLabel(iImage);
		//����
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
		//����ȭ
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
