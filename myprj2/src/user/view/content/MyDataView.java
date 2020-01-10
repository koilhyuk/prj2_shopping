package user.view.content;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import user.controller.content.MyDataEvt;

@SuppressWarnings("serial")
public class MyDataView extends JFrame{
	
	private JLabel jlMyPage;
	private JTextArea jtaName;
	private JButton jbtOrderList, jbtMyData, jbtWithdrawal, jbtOk, jbtLogOut, jbtJJim;
	//ī����, ���ϱ�, �ֹ�����, ���������� 
	
	private static String id;
	private UserMyOrderView umo;
	public MyDataView(String id) {
		super("����������");
		this.id=id;

		Font mFont= new Font("�������", Font.BOLD, 15);
		Font jtFont= new Font("�������", Font.BOLD, 13);
		jlMyPage= new JLabel("����������");
		jtaName= new JTextArea("\n\t'"+id+"'ȸ���� ȯ���մϴ�. \n");
		jtaName.setFont(jtFont);
		jbtOrderList= new JButton("�ֹ�����");
		jbtOrderList.setFont(mFont);
		jbtMyData= new JButton("�� ���� ����");
		jbtMyData.setFont(mFont);
		jbtWithdrawal= new JButton("ī����");
		jbtWithdrawal.setFont(mFont);
		jbtJJim= new JButton("���� ��ǰ");
		jbtJJim.setFont(mFont);
		jbtOk= new JButton("�ݱ�");
		jbtLogOut= new JButton("�α׾ƿ�");
		
		//�̹��� // ���� 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\����.PNG");
		JLabel jtaImage= new JLabel("�̹���"+iImage);
		jtaImage.setBorder(new TitledBorder("�̹���"));
		//����
		
		
		setLayout(null);
		jlMyPage.setBounds(10, 10, 100, 40);
		jtaName.setBounds(40, 70, 400, 50);
		jbtOrderList.setBounds(40, 140, 250, 50);//�ֹ�����
		jbtMyData.setBounds(40, 200, 250, 50);//����������
		jbtWithdrawal.setBounds(40, 260, 250, 50); //ī����
		jbtJJim.setBounds(40,320,250,50);//���ϱ�
		jbtOk.setBounds(50,380,100,40);
		jbtLogOut.setBounds(180,380,100,40);
		jtaImage.setBounds(315, 130, 160, 280);
		
		add(jlMyPage);
		add(jtaName);
		add(jbtOrderList);
		add(jbtMyData);
		add(jbtWithdrawal);
		add(jbtJJim);
		add(jbtOk);
		add(jbtLogOut);
		add(jtaImage);
		
		MyDataEvt mde=new MyDataEvt(this,id);
		jbtOrderList.addActionListener(mde);
		jbtMyData.addActionListener(mde);
		jbtWithdrawal.addActionListener(mde);
		jbtOk.addActionListener(mde);
		jbtLogOut.addActionListener(mde);
		
		setBounds(100, 100, 500, 500);
		//����ȭ
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

	
	public static void main(String[] args) {
		new MyDataView("test����");
	}//main

}//class
