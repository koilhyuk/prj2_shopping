package user.view.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;
import user.controller.content.UserMyOrderDetailEvt;
import user.vo.content.SelectMyOrderDetailDTO;

@SuppressWarnings("serial")
public class UserMyOrderDetailView extends JDialog {
	private JLabel jtfGoodsName, jtfbuyNum, jtfbuyMethod, jtfTotalPrice, jtfOrderDate, jtfDeliveryFlag, jtfDeliverMsg,
			jtfOrderCode, jtfGoodsCode, jlScore;
	private DefaultComboBoxModel<Integer> dcbmScore;
	private JComboBox<Integer> jcbScore;
	private JButton jbtnClose, jbtnScore;
	private JLabel jlImg;

	private SelectMyOrderDetailDTO moDTO;

	public UserMyOrderDetailView(SelectMyOrderDetailDTO moDTO) {
		super(StaticCla.mv, "��ǰ����-�󼼺���", true);
		this.moDTO = moDTO;
		// �̹���
		ImageIcon iiImage = new ImageIcon(StaticCla.FILE_PATH + "/gd_" + moDTO.getG_img());
		Image img = iiImage.getImage();
		Image chgimg = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);// �̹��� �������
		ImageIcon newImg = new ImageIcon(chgimg);
		jlImg = new JLabel(newImg);
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border����
		Font font = new Font("���� ���", Font.BOLD, 20);
		Font fontCon = new Font("���� ���", Font.BOLD, 15);
		// ��
		JTextField jlTitle = new JTextField(" �ֹ�����  ");
		Font titleFont = new Font("���� ���", Font.BOLD, 20);
		jlTitle.setFont(titleFont);
		jlTitle.setBackground(new Color(0x352A26));
		jlTitle.setForeground(Color.white);
		jlTitle.setHorizontalAlignment(JTextField.CENTER);

		// ����
		Integer[] score = { 5, 4, 3, 2, 1 };
		dcbmScore = new DefaultComboBoxModel<Integer>(score);
		jcbScore = new JComboBox<Integer>(dcbmScore);

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
		jtfOrderCode = new JLabel();
		jtfGoodsCode = new JLabel();

		jbtnScore = new JButton("�����ֱ�");

		jbtnClose = new JButton("Ȯ��");
		// setText
		jtfGoodsCode.setText(moDTO.getG_code());
		jtfOrderCode.setText(moDTO.getO_code());
		jtfGoodsName.setText(moDTO.getG_name());
		jtfbuyNum.setText(String.valueOf(moDTO.getO_quantity()) + " ��");
		jtfbuyMethod.setText(moDTO.getP_method() + "ī��");
		jtfTotalPrice.setText(String.valueOf(moDTO.getO_buypay()) + " ��");
		jtfOrderDate.setText(moDTO.getO_date());
		jtfDeliveryFlag.setText(moDTO.getO_delivery());
		jtfDeliverMsg.setText(moDTO.getO_delmsg());
		if (moDTO.getO_delmsg() == null || "".equals(moDTO.getO_delmsg())) {
			jtfDeliverMsg.setText("��� ��û������ �����ϴ�.");
		} // end if

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
		jtfTotalPrice.setBounds(120, 160, 230, 30);
		jpGoods.add(jtfTotalPrice);
		jtfOrderDate.setBounds(90, 210, 230, 30);
		jpGoods.add(jtfOrderDate);

		// ����
		jlscore.setBounds(15, 260, 70, 30);
		jpGoods.add(jlscore);

//		jlScore.setText(String.valueOf(moDTO.getO_score()));
		if (moDTO.getO_score() == 0) {
			jcbScore.setBounds(90, 260, 50, 30);
			jbtnScore.setBounds(160, 260, 100, 30);
			jpGoods.add(jcbScore);
			jpGoods.add(jbtnScore);
		} else {
			switch (moDTO.getO_score()) {
			case 5:
				jlScore = new JLabel("�ڡڡڡڡ�");
				jlScore.setBounds(90, 260, 200, 30);
				jpGoods.add(jlScore);
				break;
			case 4:
				jlScore = new JLabel("�ڡڡڡڡ�");
				jlScore.setBounds(90, 260, 200, 30);
				jpGoods.add(jlScore);
				break;
			case 3:
				jlScore = new JLabel("�ڡڡڡ١�");
				jlScore.setBounds(90, 260, 200, 30);
				jpGoods.add(jlScore);
				break;
			case 2:
				jlScore = new JLabel("�ڡڡ١١�");
				jlScore.setBounds(90, 260, 200, 30);
				jpGoods.add(jlScore);
				break;
			case 1:
				jlScore = new JLabel("�ڡ١١١�");
				jlScore.setBounds(90, 260, 200, 30);
				jpGoods.add(jlScore);
				break;
			default:
				jlScore = new JLabel("");
				break;
			}// switch
			jlScore.setForeground(Color.red);
		} // end else

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
//		jlTitle.setBorder(new LineBorder(new Color(0x352A26)));
		jlTitle.setEditable(false);

		setLayout(null); // ����
		jlTitle.setBounds(50, 10, 200, 30);
		jlImg.setBounds(10, 60, 270, 340);
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

		jbtnScore.setForeground(Color.white);
		jbtnScore.setBackground(new Color(0x043424));
		jbtnClose.setForeground(Color.black);
		jbtnClose.setBackground(Color.white);

		add(jlTitle);
		add(jlImg);
		add(jpGoods);
		add(jpDelivery);
		add(jbtnClose);
		add(jlPayTitle);
		add(jlDeliTitle);

		this.getContentPane().setBackground(new Color(0x352A26)); // JDialog ���� ����
		setBounds(100, 100, 700, 680);

		UserMyOrderDetailEvt ume = new UserMyOrderDetailEvt(this);
		jbtnClose.addActionListener(ume);
		jbtnScore.addActionListener(ume);

		setVisible(true);
		setResizable(false);

	}// AdOrderDetailView

	public JLabel getJtfGoodsName() {
		return jtfGoodsName;
	}

	public JLabel getJtfbuyNum() {
		return jtfbuyNum;
	}

	public JLabel getJtfbuyMethod() {
		return jtfbuyMethod;
	}

	public JLabel getJtfTotalPrice() {
		return jtfTotalPrice;
	}

	public JLabel getJtfOrderDate() {
		return jtfOrderDate;
	}

	public JLabel getJtfDeliveryFlag() {
		return jtfDeliveryFlag;
	}

	public JLabel getJtfDeliverMsg() {
		return jtfDeliverMsg;
	}

	public JLabel getJtfOrderCode() {
		return jtfOrderCode;
	}

	public DefaultComboBoxModel<Integer> getDcbmScore() {
		return dcbmScore;
	}

	public JComboBox<Integer> getJcbScore() {
		return jcbScore;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public JButton getJbtnScore() {
		return jbtnScore;
	}

	public JLabel getJlImg() {
		return jlImg;
	}

	public SelectMyOrderDetailDTO getMoDTO() {
		return moDTO;
	}

	public JLabel getJtfGoodsCode() {
		return jtfGoodsCode;
	}

}// class
