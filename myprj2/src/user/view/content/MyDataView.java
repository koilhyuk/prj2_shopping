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
	//ī����, ���ϱ�, �ֹ�����, ���������� 
	
	private static String id;
	
	public MyDataView(String id) {
		super("����������");
		this.id=id;

		jlMyPage= new JLabel("����������");
		jtaName= new JTextArea("'"+id+"'ȸ���� ȯ���մϴ�. \n");
		jbtOrderList= new JButton("�ֹ�����");
		jbtMyData= new JButton("�� ���� ����");
		jbtWithdrawal= new JButton("ī����");
		jbtJJim= new JButton("���� ��ǰ");
		jbtOk= new JButton("Ȯ��");
		jbtLogOut= new JButton("�α׾ƿ�");
		
		//�̹��� // ���� 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\����.PNG");
		JLabel jtaImage= new JLabel(iImage);
		//����
		setLayout(null);
		jlMyPage.setBounds(10, 30, 100, 40);
		jtaName.setBounds(40, 70, 250, 50);
		jbtOrderList.setBounds(40, 140, 250, 60);//�ֹ�����
		jbtMyData.setBounds(40, 210, 250, 60);//����������
		jbtWithdrawal.setBounds(40, 260, 250, 60); //ī����
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
