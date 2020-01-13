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
	//ī����, ���ϱ�, �ֹ�����, ���������� 
	
	private static String id;
	private UserMyOrderView umo;
	public UserMyPageView(String id) {
		super("����������");
		this.id=id;
		
		Font mFont= new Font("�������", Font.BOLD, 15);
		Font jtFont= new Font("�������", Font.BOLD, 13);
		jlMyPage= new JLabel("��  ����������  ��");
		jlMyPage.setFont(mFont);
		jtaName= new JTextArea("\n\t����'"+id+"'ȸ���� ȯ���մϴ�. ����\n");
		jtaName.setFont(jtFont);
		jtaName.setEditable(false);
		
		jbtOrderList= new JButton("�ֹ�����");
		jbtOrderList.setFont(mFont);
		jbtOrderList.setForeground(Color.white);
		jbtOrderList.setBackground(new Color(0x3F4040));
		
		jbtMyData= new JButton("�� ���� ����");
		jbtMyData.setFont(mFont);
		jbtMyData.setForeground(Color.white);
		jbtMyData.setBackground(new Color(0x3F4040));
		
		jbtWithdrawal= new JButton("ī����");
		jbtWithdrawal.setFont(mFont);
		jbtWithdrawal.setForeground(Color.white);
		jbtWithdrawal.setBackground(new Color(0x3F4040));
		
		jbtJJim= new JButton("���� ��ǰ");
		jbtJJim.setFont(mFont);
		jbtJJim.setForeground(Color.white);
		jbtJJim.setBackground(new Color(0x3F4040));
		
		jbtOk= new JButton("�ݱ�");
		jbtOk.setForeground(Color.white);
		jbtOk.setBackground(new Color(0x3F4040));
		
		jbtLogOut= new JButton("�α׾ƿ�");
		jbtLogOut.setForeground(Color.white);
		jbtLogOut.setBackground(new Color(0x3F4040));
		//�̹��� // ���� 
		ImageIcon iImage= new ImageIcon("C:\\dev\\workspace\\myproject2\\src\\clientView\\����.PNG");
		JLabel jtaImage= new JLabel("�̹���"+iImage);
		jtaImage.setBorder(new TitledBorder("�̹���"));
		//����
		setLayout(null);
		jlMyPage.setBounds(30, 15, 200, 40);
		jtaName.setBounds(40, 70, 400, 50);
		jbtOrderList.setBounds(40, 140, 250, 50);//�ֹ�����
		jbtMyData.setBounds(40, 200, 250, 50);//����������
		jbtWithdrawal.setBounds(40, 260, 250, 50); //ī����
		jbtJJim.setBounds(40,320,250,50);//���ϱ�
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


}//class
