package user.view.content;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;

@SuppressWarnings("serial")
public class UserMyOrderDetailView extends JDialog implements ActionListener {
//	private JTextField jtfOrderer, jtfReceive, jtfRecAddr, jtfPhone, jtfGoodsName, jtfbuyNum, jtfbuyMethod,

	private JLabel jtfGoodsName, jtfbuyNum, jtfbuyMethod, jtfTotalPrice,jtfOrderDate, jtfDeliveryFlag, jtfDeliverMsg;
	private DefaultComboBoxModel<Integer> dcbmScore;
	private JComboBox<Integer> jcbScore;
	private JButton jbtnClose, jbtnScore;
	private JLabel jlImg;

//	private SelectOrderDetailDTO odDTO;

	public UserMyOrderDetailView(/* SelectOrderDetailDTO odDTO */) {
		super(StaticCla.mv, "��ǰ����-�󼼺���", true);
//		this.odDTO = odDTO;
		// �̹���
//		ImageIcon iiImage = new ImageIcon(StaticCla.FILE_PATH+"/gd_" + odDTO.getG_img());
//		Image img = iiImage.getImage();
//		Image chgimg = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);// �̹��� �������
//		ImageIcon newImg = new ImageIcon(chgimg);
//		jlImg = new JLabel(newImg);
//		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border����
		Font font = new Font("���� ���", Font.BOLD, 20);
		Font fontCon = new Font("���� ���", Font.BOLD, 14);
		// ��
		JTextField jlTitle = new JTextField(" �ֹ�����  ");
		Font titleFont = new Font("���� ���", Font.BOLD, 20);
		jlTitle.setFont(titleFont);
		jlTitle.setBackground(Color.black);
		jlTitle.setForeground(Color.white);
		jlTitle.setHorizontalAlignment(JTextField.CENTER);
		
		//����
		Integer[] score= {1,2,3,4,5};
		dcbmScore= new DefaultComboBoxModel<Integer>(score);
		jcbScore= new JComboBox<Integer>(dcbmScore);
		
		JLabel jlPayTitle = new JLabel("�ػ�ǰ �� �������� �� ");
		jlPayTitle.setForeground(Color.white);
		JLabel jlDeliTitle = new JLabel("�ع������ �� ");
		jlDeliTitle.setForeground(Color.white);
		jlTitle.setFont(font);

		JLabel jlGoodsName = new JLabel("��ǰ�� :");//
		jlGoodsName.setFont(fontCon);
		
		JLabel jlbuyNum = new JLabel("���ż��� :");//
		jlbuyNum.setFont(fontCon);
		
		JLabel jlscore = new JLabel("��ǰ���� :");//
		jlscore.setFont(fontCon);
		
		JLabel jlbuyMethod = new JLabel("������� :");//
		jlbuyMethod.setFont(fontCon);
		
		JLabel jlTotalPrice = new JLabel("�� �����ݾ� :");//
		jlTotalPrice.setFont(fontCon);
		
		JLabel jlOrderDate = new JLabel("�ֹ���¥ :");//
		jlOrderDate.setFont(fontCon);
		
		JLabel jlDeliveryFlag = new JLabel("��ۿ��� :");//
		jlDeliveryFlag.setFont(fontCon);
		
		JLabel jlDeliverMsg = new JLabel("��ۿ�û���� :");//
		jlDeliverMsg.setFont(fontCon);

		jtfGoodsName = new JLabel();
		jtfbuyNum = new JLabel();
		jtfbuyMethod = new JLabel();
		jtfTotalPrice = new JLabel();
		jtfOrderDate = new JLabel();
		jtfDeliveryFlag = new JLabel();
		jtfDeliverMsg = new JLabel();

		jbtnScore = new JButton("�����ֱ�");
		jbtnClose = new JButton("Ȯ��");
		// setText
//		jtfScore.setText(String.valueOf(odDTO.getO_score()));
//		jtfOrderer.setText(odDTO.getM_id());// �ֹ���
//		jtfReceive.setText(odDTO.getO_person());// �޴���
//		jtfRecAddr.setText(odDTO.getO_addr());
//		jtfPhone.setText(odDTO.getO_phone());
//		jtfGoodsName.setText(odDTO.getG_name() + "(" + odDTO.getO_code() + ")");
//		jtfbuyNum.setText(String.valueOf(odDTO.getO_quantity()));
//		jtfbuyMethod.setText(odDTO.getP_method());
//		jtfTotalPrice.setText(String.valueOf(odDTO.getO_buypay()));
//		jtfOrderDate.setText(odDTO.getO_date());
//		jtfDeliveryFlag.setText(odDTO.getO_delivery());
//		jtfDeliverMsg.setText(odDTO.getO_delmsg());

		JPanel jpGoods = new JPanel();
		jpGoods.setLayout(null);
		jlGoodsName.setBounds(15, 10, 60, 30);
		jpGoods.add(jlGoodsName);
		jlbuyNum.setBounds(15, 60, 100, 30);//
		jpGoods.add(jlbuyNum);//
		jlbuyMethod.setBounds(15, 110, 100, 30);
		jpGoods.add(jlbuyMethod);
		jlTotalPrice.setBounds(15, 160, 110, 30);
		jpGoods.add(jlTotalPrice);
		jlOrderDate.setBounds(15, 210, 80, 30);
		jpGoods.add(jlOrderDate);
		jtfGoodsName.setBounds(90, 10, 230, 30);
		jpGoods.add(jtfGoodsName);
		jtfbuyNum.setBounds(100, 60, 80, 30);//
		jpGoods.add(jtfbuyNum);//
		jtfbuyMethod.setBounds(100, 110, 230, 30);
		jpGoods.add(jtfbuyMethod);
		jtfTotalPrice.setBounds(140, 160, 230, 30);
		jpGoods.add(jtfTotalPrice);
		jtfOrderDate.setBounds(90, 210, 230, 30);
		jpGoods.add(jtfOrderDate);

		//����
		jlscore.setBounds(15, 260, 70, 30);
		jpGoods.add(jlscore);

		jcbScore.setBounds(90, 260, 50, 30);
		jpGoods.add(jcbScore);
	
		jbtnScore.setBounds(160, 260, 100, 30);
		jpGoods.add(jbtnScore);

		JPanel jpDelivery = new JPanel();
		jpDelivery.setLayout(null);
		jlDeliveryFlag.setBounds(10, 10, 150, 30);
		jpDelivery.add(jlDeliveryFlag);
		jlDeliverMsg.setBounds(10, 50, 150, 30);
		jpDelivery.add(jlDeliverMsg);
		jtfDeliveryFlag.setBounds(120, 10, 180, 30);
		jpDelivery.add(jtfDeliveryFlag);
		jtfDeliverMsg.setBounds(120, 50, 450, 30);
		jpDelivery.add(jtfDeliverMsg);

		// �������ϰ� ����
		jlTitle.setEditable(false);

		setLayout(null); // ����
		jlTitle.setBounds(50, 10, 200, 30);
//		jlImg.setBounds(10, 60, 270, 350);
		Font fontLabel = new Font("���� ���", Font.BOLD, 17);
		jlPayTitle.setFont(fontLabel);
		jlDeliTitle.setFont(fontLabel);
		jlPayTitle.setBounds(300, 30, 200, 40);
		jlDeliTitle.setBounds(40, 410, 200, 40);

		jpGoods.setBounds(290, 70, 360, 320);
		jpGoods.setBorder(new LineBorder(Color.lightGray));
		jpDelivery.setBounds(15, 450, 630, 100);
		jpDelivery.setBorder(new LineBorder(Color.lightGray));
		jbtnClose.setBounds(280, 570, 120, 30);
		
		add(jlTitle);
//		add(jlImg);
		add(jpGoods);
		add(jpDelivery);
		add(jbtnClose);
		add(jlPayTitle);
		add(jlDeliTitle);

		this.getContentPane().setBackground(new Color(0x3F4040)); //JDialog ���� ���� 
		setBounds(100, 100, 700, 680);
		jbtnClose.addActionListener(this);

		setVisible(true);
		setResizable(false);

	}// AdOrderDetailView
	
	public static void main(String[] args) {
		new UserMyOrderDetailView();
	}//main

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtnClose) {
			switch (JOptionPane.showConfirmDialog(this, "�ֹ�����â�� �����ðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				dispose();
			}// end switch
		} // end if
	}// actionPerformed

}// class
