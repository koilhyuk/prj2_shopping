package admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import admin.controller.AdGoodsDetailEvt;
import admin.controller.AdGoodsManageEvt;
import admin.run.StaticCla;
import admin.vo.SelectGoodsDetailDTO;

/**
 * ��ǰ����- �󼼺���
 * 
 * @author hyebin
 *
 */
@SuppressWarnings("serial")
public class AdGoodsDetailView extends JDialog {
	private JTextArea jtaStrongPoint;
	private JTextField jtfBrand, jtfGoodsName, jtfType, jtfDetailType, jtfPrice, jtfInventory, jtfSaleNum, jtfScore,
			jtfInputDate, jtfAddGoods;
	private JButton jbtnClose, jbtnUpload, jbtnImg;
	private JLabel jlImg;

	private AdGoodsManageEvt gle;
	private SelectGoodsDetailDTO dgDTO; // ��â�� ������ �����ؾ��ϹǷ� �ʿ�

	public AdGoodsDetailView(AdGoodsManageEvt gle, SelectGoodsDetailDTO dgDTO) {
		super(StaticCla.mv, "��ǰ����", true);
		this.gle = gle;
		// �̹���
		ImageIcon iiImage = new ImageIcon(StaticCla.FILE_PATH + "/gd_" + dgDTO.getG_img());// �̹��� ���� (rs_)
		jlImg = new JLabel(iiImage, JLabel.CENTER);
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		Font fontLabel = new Font("���� ���", Font.BOLD, 15);
		Font font = new Font("���� ���", Font.BOLD, 20);
		// ��
		JTextField jlTitle = new JTextField(" ��ǰ���� ");
		jlTitle.setBackground(new Color(0x352A26));
		jlTitle.setHorizontalAlignment(JTextField.CENTER);
		jlTitle.setForeground(Color.white);

		jlTitle.setFont(font);
		JLabel jlBrand = new JLabel("�귣��");// N
		jlBrand.setForeground(Color.white);
		jlBrand.setFont(fontLabel);

		JLabel jlGoodsName = new JLabel("��ǰ��");// N
		jlGoodsName.setForeground(Color.white);
		jlGoodsName.setFont(fontLabel);

		JLabel jlType = new JLabel("��з�");// N
		jlType.setForeground(Color.white);
		jlType.setFont(fontLabel);

		JLabel jlDetailType = new JLabel("�Һз�");// N
		jlDetailType.setForeground(Color.white);
		jlDetailType.setFont(fontLabel);

		JLabel jlPrice = new JLabel("�ݾ�");// Y
		jlPrice.setForeground(Color.white);
		jlPrice.setFont(fontLabel);

		JLabel jlAddGoods = new JLabel("�߰�����");// N
		jlAddGoods.setForeground(Color.white);
		jlAddGoods.setFont(fontLabel);

		JLabel jlInventory = new JLabel("���");// N
		jlInventory.setForeground(Color.white);
		jlInventory.setFont(fontLabel);

		JLabel jlSaleNum = new JLabel("�Ǹŷ�");// N
		jlSaleNum.setForeground(Color.white);
		jlSaleNum.setFont(fontLabel);

		JLabel jlScore = new JLabel("����");// N
		jlScore.setForeground(Color.white);
		jlScore.setFont(fontLabel);

		JLabel jlinputDate = new JLabel("�԰���");// N
		jlinputDate.setForeground(Color.white);
		jlinputDate.setFont(fontLabel);

		JLabel jlStrongPoint = new JLabel("- Ư����");// Y
		jlStrongPoint.setForeground(Color.white);
		jlStrongPoint.setFont(fontLabel);

		// T.F
		jtfBrand = new JTextField(10);
		jtfGoodsName = new JTextField(10);
		jtfPrice = new JTextField(10);
		jtfType = new JTextField(10);
		jtfAddGoods = new JTextField("0");
		jtfInventory = new JTextField(10);
		jtfSaleNum = new JTextField(10);
		jtfScore = new JTextField(10);
		jtfInputDate = new JTextField(10);
		jtfDetailType = new JTextField(10);

		jtaStrongPoint = new JTextArea(30, 30);
		JScrollPane jspStrong = new JScrollPane(jtaStrongPoint);

		jbtnClose = new JButton("�ݱ�");
		jbtnUpload = new JButton("��ǰ����");
		jbtnImg = new JButton("�̹��� ����");

		// setText
		jtfBrand.setText(dgDTO.getB_name());// �귣��
		jtfGoodsName.setText(dgDTO.getG_name());
		jtfPrice.setText(String.valueOf(dgDTO.getG_price()));
		jtfType.setText(dgDTO.getC_type());// Ÿ��
		jtfInventory.setText(String.valueOf(dgDTO.getG_inventory()));
		jtfSaleNum.setText(String.valueOf(dgDTO.getG_salenum()));
		jtfScore.setText(String.valueOf(dgDTO.getG_score()));
		jtfInputDate.setText(dgDTO.getG_inputdate());
		jtfDetailType.setText(dgDTO.getD_type());
		jtaStrongPoint.setText(dgDTO.getG_strong());// Ư����
		jtaStrongPoint.setLineWrap(true);// �ڵ� �ٹٲ�

		// �������ϰ� ����
		jtfBrand.setEditable(false);
		jtfGoodsName.setEditable(false);
		jtfInventory.setEditable(false);
		jtfSaleNum.setEditable(false);
		jtfType.setEditable(false);
		jtfScore.setEditable(false);
		jtfDetailType.setEditable(false);
		jtfInputDate.setEditable(false);
		jlTitle.setEditable(false);

		setLayout(null); // ����
		jlTitle.setBounds(50, 10, 200, 30);
		jlImg.setBounds(10, 50, 270, 360);
		jlBrand.setBounds(300, 50, 100, 30);
		jlGoodsName.setBounds(300, 90, 100, 30);
		jlType.setBounds(300, 130, 100, 30);
		jlDetailType.setBounds(480, 130, 100, 30);
		jlPrice.setBounds(300, 170, 100, 30);
		jlInventory.setBounds(300, 210, 90, 30);
		jlAddGoods.setBounds(480, 210, 90, 30);
		jlSaleNum.setBounds(300, 250, 90, 30);
		jlScore.setBounds(490, 250, 90, 30);
		jlinputDate.setBounds(300, 290, 100, 30);
		jlStrongPoint.setBounds(300, 330, 100, 30);

		jtfBrand.setBounds(370, 50, 280, 30);
		jtfGoodsName.setBounds(370, 90, 200, 30);
		jtfType.setBounds(370, 130, 100, 30);
		jtfDetailType.setBounds(530, 130, 120, 30);
		jtfPrice.setBounds(370, 170, 200, 30);
		jtfInventory.setBounds(370, 210, 100, 30);
		jtfAddGoods.setBounds(550, 210, 100, 30);
		jtfSaleNum.setBounds(370, 250, 100, 30);
		jtfScore.setBounds(550, 250, 100, 30);
		jtfInputDate.setBounds(370, 290, 280, 30);
		jspStrong.setBounds(300, 370, 360, 200);

		jbtnImg.setBounds(10, 420, 270, 20);
		jbtnImg.setBackground(Color.white);
		jbtnImg.setBorder(new LineBorder(Color.white));		
		jbtnUpload.setBounds(380, 580, 120, 30);
		jbtnUpload.setBackground(Color.white);
		jbtnUpload.setBorder(new LineBorder(Color.white));		
		jbtnClose.setBounds(530, 580, 120, 30);
		jbtnClose.setBackground(Color.white);
		jbtnClose.setBorder(new LineBorder(Color.white));		

		add(jlTitle);
		add(jlImg);
		add(jlPrice);
		add(jlBrand);
		add(jlGoodsName);
		add(jlType);
		add(jlDetailType);
		add(jlScore);
		add(jlInventory);
		add(jlSaleNum);
		add(jlinputDate);
		add(jlStrongPoint);
		add(jlAddGoods);
		add(jtfAddGoods);
		add(jtfBrand);
		add(jtfGoodsName);
		add(jtfPrice);
		add(jtfType);
		add(jtfInventory);
		add(jtfSaleNum);
		add(jtfScore);
		add(jtfInputDate);
		add(jtfDetailType);
		add(jspStrong);
		add(jbtnClose);
		add(jbtnUpload);
		add(jbtnImg);
		this.getContentPane().setBackground(new Color(0x352A26)); // JDialog ���� ����

		AdGoodsDetailEvt pde = new AdGoodsDetailEvt(this, dgDTO.getG_img(), gle);
		jbtnClose.addActionListener(pde);
		jbtnUpload.addActionListener(pde);
		jbtnImg.addActionListener(pde);

		setBounds(100, 100, 720, 680);

		setVisible(true);
		setResizable(false);

	}// AdGoodsDetailView

	public JTextArea getJtaStrongPoint() {
		return jtaStrongPoint;
	}

	public JTextField getJtfBrand() {
		return jtfBrand;
	}

	public JTextField getJtfGoodsName() {
		return jtfGoodsName;
	}

	public JTextField getJtfType() {
		return jtfType;
	}

	public JTextField getJtfDetailType() {
		return jtfDetailType;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextField getJtfInventory() {
		return jtfInventory;
	}

	public JTextField getJtfSaleNum() {
		return jtfSaleNum;
	}

	public JTextField getJtfScore() {
		return jtfScore;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public JTextField getJtfAddGoods() {
		return jtfAddGoods;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public JButton getJbtnUpload() {
		return jbtnUpload;
	}

	public JButton getJbtnImg() {
		return jbtnImg;
	}

	public JLabel getJlImg() {
		return jlImg;
	}

}// class
