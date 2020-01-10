package user.view.content;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;
import admin.vo.SelectOrderDetailDTO;

@SuppressWarnings("serial")
public class UserMyOrderDetailView extends JDialog implements ActionListener {
//	private JTextField jtfOrderer, jtfReceive, jtfRecAddr, jtfPhone, jtfGoodsName, jtfbuyNum, jtfbuyMethod,

	private JLabel jtfOrderer, jtfReceive, jtfRecAddr, jtfPhone, jtfGoodsName, jtfbuyNum, jtfbuyMethod, jtfTotalPrice,
			jtfOrderDate, jtfDeliveryFlag, jtfDeliverMsg, jtfScore;

	private JButton jbtnClose;
	private JLabel jlImg;

	private SelectOrderDetailDTO odDTO;

	public UserMyOrderDetailView(SelectOrderDetailDTO odDTO) {
		super(StaticCla.mv, "��ǰ����-�󼼺���", true);
		this.odDTO = odDTO;
		// �̹���
		ImageIcon iiImage = new ImageIcon(StaticCla.FILE_PATH+"/gd_" + odDTO.getG_img());
		Image img = iiImage.getImage();
		Image chgimg = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);// �̹��� �������
		ImageIcon newImg = new ImageIcon(chgimg);
		jlImg = new JLabel(newImg);
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border����
		Font font = new Font("���� ���", Font.BOLD, 20);
		Font fontCon = new Font("���� ���", Font.BOLD, 14);
		// ��
		JTextField jlTitle = new JTextField(" �ֹ�����  ");
		Font titleFont = new Font("���� ���", Font.BOLD, 20);
		jlTitle.setFont(titleFont);
		jlTitle.setBackground(Color.black);
		jlTitle.setForeground(Color.white);
		jlTitle.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel jlCusTitle = new JLabel("��ȸ������ ��");
		jlCusTitle.setForeground(Color.white);
		JLabel jlPayTitle = new JLabel("�ػ�ǰ �� �������� �� ");
		jlPayTitle.setForeground(Color.white);
		JLabel jlDeliTitle = new JLabel("�ع������ �� ");
		jlDeliTitle.setForeground(Color.white);
		jlTitle.setFont(font);
		
		
		JLabel jlOrderer = new JLabel("ȸ�����̵�:");//
		jlTitle.setFont(fontCon);
		
		JLabel jlReceiver = new JLabel("�޴��� :");//
		jlReceiver.setFont(fontCon);
		JLabel jlRecAddr = new JLabel("����� :");//
		jlRecAddr.setFont(fontCon);
		JLabel jlPhone = new JLabel("����ó :");//
		jlPhone.setFont(fontCon);

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

		// T.F
//		jtfOrderer = new JTextField(10);
//		jtfReceive = new JTextField(10);
//		jtfRecAddr = new JTextField(10);
//		jtfPhone = new JTextField(10);
//		jtfGoodsName = new JTextField(10);
//		jtfbuyNum = new JTextField(10);
//		jtfbuyMethod = new JTextField(10);
//		jtfTotalPrice = new JTextField(10);
//		jtfOrderDate = new JTextField(10);
//		jtfDeliveryFlag = new JTextField(10);
//		jtfDeliverMsg = new JTextField(10);
//		jtfScore=new JTextField(10);

		jtfOrderer = new JLabel();
		jtfReceive = new JLabel();
		jtfRecAddr = new JLabel();
		jtfPhone = new JLabel();
		jtfGoodsName = new JLabel();
		jtfbuyNum = new JLabel();
		jtfbuyMethod = new JLabel();
		jtfTotalPrice = new JLabel();
		jtfOrderDate = new JLabel();
		jtfDeliveryFlag = new JLabel();
		jtfDeliverMsg = new JLabel();
		jtfScore = new JLabel();

		jbtnClose = new JButton("Ȯ��");
		// setText
		jtfScore.setText(String.valueOf(odDTO.getO_score()));
		jtfOrderer.setText(odDTO.getM_id());// �ֹ���
		jtfReceive.setText(odDTO.getO_person());// �޴���
		jtfRecAddr.setText(odDTO.getO_addr());
		jtfPhone.setText(odDTO.getO_phone());
		jtfGoodsName.setText(odDTO.getG_name() + "(" + odDTO.getO_code() + ")");
		jtfbuyNum.setText(String.valueOf(odDTO.getO_quantity()));
		jtfbuyMethod.setText(odDTO.getP_method());
		jtfTotalPrice.setText(String.valueOf(odDTO.getO_buypay()));
		jtfOrderDate.setText(odDTO.getO_date());
		jtfDeliveryFlag.setText(odDTO.getO_delivery());
		jtfDeliverMsg.setText(odDTO.getO_delmsg());

		JPanel jpCuOrder = new JPanel();
		jpCuOrder.setLayout(null);

		jlOrderer.setBounds(15, 10, 80, 30);
		jpCuOrder.add(jlOrderer);

		jlReceiver.setBounds(200, 10, 60, 30);
		jpCuOrder.add(jlReceiver);

		jlRecAddr.setBounds(15, 40, 60, 30);
		jpCuOrder.add(jlRecAddr);

		jlPhone.setBounds(15, 70, 60, 30);
		jpCuOrder.add(jlPhone);

		jtfOrderer.setBounds(100, 10, 100, 30);
		jpCuOrder.add(jtfOrderer);

		jtfReceive.setBounds(260, 10, 100, 30);
		jpCuOrder.add(jtfReceive);

		jtfRecAddr.setBounds(80, 40, 250, 30);
		jpCuOrder.add(jtfRecAddr);

		jtfPhone.setBounds(80, 70, 230, 30);
		jpCuOrder.add(jtfPhone);

		JPanel jpGoods = new JPanel();
		jpGoods.setLayout(null);
		jlGoodsName.setBounds(15, 10, 60, 30);
		jpGoods.add(jlGoodsName);
		jlbuyNum.setBounds(15, 40, 100, 30);//
		jpGoods.add(jlbuyNum);//
		jlbuyMethod.setBounds(15, 70, 100, 30);
		jpGoods.add(jlbuyMethod);
		jlTotalPrice.setBounds(15, 100, 110, 30);
		jpGoods.add(jlTotalPrice);
		jlOrderDate.setBounds(15, 130, 80, 30);
		jpGoods.add(jlOrderDate);
		jtfGoodsName.setBounds(90, 10, 230, 30);
		jpGoods.add(jtfGoodsName);
		jtfbuyNum.setBounds(100, 40, 80, 30);//
		jpGoods.add(jtfbuyNum);//
		jtfbuyMethod.setBounds(100, 70, 230, 30);
		jpGoods.add(jtfbuyMethod);
		jtfTotalPrice.setBounds(140, 100, 230, 30);
		jpGoods.add(jtfTotalPrice);
		jtfOrderDate.setBounds(90, 130, 230, 30);
		jpGoods.add(jtfOrderDate);

		jlscore.setBounds(180, 40, 70, 30);
		jpGoods.add(jlscore);

		jtfScore.setBounds(250, 40, 100, 30);
		jpGoods.add(jtfScore);

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

//		// �������ϰ� ����
//		jtfOrderer.setEditable(false);
//		jtfReceive.setEditable(false);
//		jtfRecAddr.setEditable(false);
//		jtfPhone.setEditable(false);
//		jtfGoodsName.setEditable(false);
//		jtfbuyNum.setEditable(false);
//		jtfbuyMethod.setEditable(false);
//		jtfTotalPrice.setEditable(false);
//		jtfOrderDate.setEditable(false);
//		jtfbuyMethod.setEditable(false);
//		jtfDeliveryFlag.setEditable(false);
//		jtfDeliverMsg.setEditable(false);
		jlTitle.setEditable(false);

		setLayout(null); // ����
		jlTitle.setBounds(50, 10, 200, 30);
		jlImg.setBounds(10, 60, 270, 350);
		Font fontLabel = new Font("���� ���", Font.BOLD, 17);
		jlCusTitle.setFont(fontLabel);
		jlPayTitle.setFont(fontLabel);
		jlDeliTitle.setFont(fontLabel);
		jlCusTitle.setBounds(300, 40, 200, 40);
		jlPayTitle.setBounds(300, 190, 200, 40);
		jlDeliTitle.setBounds(40, 410, 200, 40);

		jpCuOrder.setBounds(290, 80, 360, 110);
		jpCuOrder.setBorder(new LineBorder(Color.lightGray));
		jpGoods.setBounds(290, 240, 360, 170);
		jpGoods.setBorder(new LineBorder(Color.lightGray));
		jpDelivery.setBounds(15, 450, 630, 100);
		jpDelivery.setBorder(new LineBorder(Color.lightGray));
		jbtnClose.setBounds(280, 570, 120, 30);
		
		add(jlTitle);
		add(jlImg);
		add(jpCuOrder);
		add(jpGoods);
		add(jpDelivery);
		add(jbtnClose);
		add(jlCusTitle);
		add(jlPayTitle);
		add(jlDeliTitle);

		this.getContentPane().setBackground(new Color(0x3F4040)); //JDialog ���� ���� 
		setBounds(100, 100, 700, 680);
		jbtnClose.addActionListener(this);

		setVisible(true);
		setResizable(false);

	}// AdOrderDetailView

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
