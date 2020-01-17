package admin.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import admin.controller.AdGoodsAddEvt;
import admin.controller.AdGoodsManageEvt;
import admin.run.StaticCla;

/**
 * ��ǰ��� View
 * 
 * @author hyebin
 *
 */
@SuppressWarnings("serial")
public class AdGoodsAddView extends JDialog {
	private JTextArea jtaStrongPoint;
	private JTextField jtfGoodsName, jtfPrice, jtftype, jtfInventory;
	
	private JButton jbtnClose, jbtnGoodsAdd, jbtnImg;
	private JLabel jlImg;
	private ImageIcon img;
	private DefaultComboBoxModel<String> dcbmType, dcbmDetailType, dcbmBrand;
	private JComboBox<String> jcbType, jcbDetailType, jcbBrand;
	private JScrollPane jsp;

	private AdGoodsManageEvt gle;
	
	public AdGoodsAddView(AdGoodsManageEvt gle) {
		super(StaticCla.mv, "��ǰ���â", true);
		this.gle = gle;

		// �̹���
		img= new ImageIcon("");
		Image orimg= img.getImage();
		Image chgimg = orimg.getScaledInstance(250, 350, Image.SCALE_SMOOTH);// �̹��� �������
		ImageIcon newImg=new ImageIcon(chgimg);
		jlImg = new JLabel("��ǰ �̹����� ������ּ���.",newImg,JLabel.CENTER);
		jlImg.setForeground(Color.white);
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	

		dcbmBrand = new DefaultComboBoxModel<String>();
		jcbBrand = new JComboBox<String>(dcbmBrand);
		//
		dcbmType = new DefaultComboBoxModel<String>();
		jcbType = new JComboBox<String>(dcbmType);
		//
		dcbmDetailType= new DefaultComboBoxModel<String>();
		jcbDetailType = new JComboBox<String>(dcbmDetailType);
		Font titleFont = new Font("���� ���", Font.BOLD, 20);
		// ��
		JTextField jlPro = new JTextField("��ǰ���");
		jlPro.setFont(titleFont);
		jlPro.setBackground(new Color(0x352A26));
		jlPro.setForeground(Color.white);
		jlPro.setHorizontalAlignment(JTextField.CENTER);
		JLabel jlBrand = new JLabel("�귣��");
		jlBrand.setForeground(Color.white);
		JLabel jlProName = new JLabel("��ǰ��");
		jlProName.setForeground(Color.white);
		
		JLabel jltype = new JLabel("��з�");
		jltype.setForeground(Color.white);
		
		JLabel jlDetailtype = new JLabel("�Һз�");
		jlDetailtype.setForeground(Color.white);
		
		JLabel jlPrice = new JLabel("����");
		jlPrice.setForeground(Color.white);
		
		JLabel jlCount = new JLabel("�԰�");
		jlCount.setForeground(Color.white);
		
		JLabel jlStrongPoint = new JLabel("Ư����");
		jlStrongPoint.setForeground(Color.white);

		// T.F
		jtfGoodsName = new JTextField(10);
		jtfPrice = new JTextField(10);
		jtfInventory = new JTextField(10);

		jtaStrongPoint = new JTextArea(30, 30);

		jbtnClose = new JButton("�ݱ�");
		jbtnGoodsAdd = new JButton("��ǰ ���");
		jbtnImg = new JButton("�̹��� ���");
		
		jsp= new JScrollPane(jtaStrongPoint);
		setLayout(null); // ����
		jlPro.setBounds(50, 10, 200, 30);
		jlImg.setBounds(10, 50, 270, 360);
		jlBrand.setBounds(300, 50, 100, 30);
		jltype.setBounds(300, 100, 80, 30);
		jlDetailtype.setBounds(490, 100, 80, 30);
		jlProName.setBounds(300, 150, 100, 30);
		jlPrice.setBounds(300, 200, 100, 30);
		jlCount.setBounds(300, 250, 100, 30);
		jlStrongPoint.setBounds(300, 300, 100, 30);

		jcbBrand.setBounds(370, 50, 150, 30);
		jcbType.setBounds(370, 100, 110, 30);
		jcbDetailType.setBounds(550, 100, 110, 30);
		jtfGoodsName.setBounds(370,150, 290, 30);
		jtfPrice.setBounds(370, 200, 290, 30);
		jtfInventory.setBounds(370, 250, 290, 30);
		jsp.setBounds(370, 300, 290, 270);

		jbtnGoodsAdd.setBounds(380, 580, 120, 30);
		jbtnClose.setBounds(530, 580, 120, 30);
		jbtnImg.setBounds(10, 420, 270, 20);
		
		jcbBrand.setBorder(null);
		jtfGoodsName.setBorder(null);
		jtfPrice.setBorder(null);
		jtfInventory.setBorder(null);
		jlPro.setEditable(false);

		add(jlPro);
		add(jlImg);
		add(jlPrice);
		add(jlBrand);
		add(jlProName);
		add(jlCount);
		add(jlStrongPoint);
		add(jltype);
		add(jcbBrand);
		add(jcbType);
		add(jtfGoodsName);
		add(jtfPrice);
		add(jtfInventory);
		add(jsp);
		add(jbtnClose);
		add(jbtnImg);
		add(jbtnGoodsAdd);
		add(jcbDetailType);
		add(jlDetailtype);
		
		jbtnClose.setBackground(Color.white);
		jbtnClose.setBorder(new LineBorder(Color.white));	
		jbtnImg.setBackground(Color.white);
		jbtnImg.setBorder(new LineBorder(Color.white));	
		jbtnGoodsAdd.setBackground(Color.white);
		jbtnGoodsAdd.setBorder(new LineBorder(Color.white));	
		
		this.getContentPane().setBackground(new Color(0x352A26)); //JDialog ���� ���� 
		AdGoodsAddEvt pue = new AdGoodsAddEvt(this,gle);
		jbtnClose.addActionListener(pue);
		jbtnGoodsAdd.addActionListener(pue);
		jbtnImg.addActionListener(pue);
		jcbType.addActionListener(pue);
		jcbDetailType.addActionListener(pue);
		jcbBrand.addActionListener(pue);

		setBounds(100, 100, 730, 700);

		setVisible(true);
		setResizable(false);


	}// AdGoodsAddView

	public JTextArea getJtaStrongPoint() {
		return jtaStrongPoint;
	}

	public JTextField getJtfGoodsName() {
		return jtfGoodsName;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextField getJtftype() {
		return jtftype;
	}

	public JTextField getJtfInventory() {
		return jtfInventory;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public JButton getJbtnGoodsAdd() {
		return jbtnGoodsAdd;
	}

	public JButton getJbtnImg() {
		return jbtnImg;
	}

	public JLabel getJlImg() {
		return jlImg;
	}

	public DefaultComboBoxModel<String> getDcbmType() {
		return dcbmType;
	}

	public DefaultComboBoxModel<String> getDcbmDetailType() {
		return dcbmDetailType;
	}

	public DefaultComboBoxModel<String> getDcbmBrand() {
		return dcbmBrand;
	}

	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	public JComboBox<String> getJcbDetailType() {
		return jcbDetailType;
	}

	public JComboBox<String> getJcbBrand() {
		return jcbBrand;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public ImageIcon getImg() {
		return img;
	}

	public AdGoodsManageEvt getGle() {
		return gle;
	}



}// class
