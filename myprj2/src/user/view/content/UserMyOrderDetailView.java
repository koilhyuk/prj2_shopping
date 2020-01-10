package user.view.content;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import admin.controller.AdCusDetailEvt;
import admin.run.StaticCla;
import admin.vo.SelectCusDetailDTO;

/**
 * ������- ��ǰ���� View
 * 
 * @author hyebin
 *
 */
@SuppressWarnings("serial")
public class UserMyOrderDetailView extends JDialog {

	private JLabel jtfCusNum, jtfCusIP, jtfID, jtfDate, jtfTotalPrice;
	private JTextField jtfCusName, jtfBirth, jtfGender, jtfPhone, jtfCusAddr, jtfEmail, jtfCusbunzi, jtfCusAddr2;
	private JButton jbtnClose, jbtnModify, jbtnStop, jbtnAddr;
	private JLabel jlImg, jtfStop, jlreason;

	private SelectCusDetailDTO scDTO;

	public UserMyOrderDetailView(SelectCusDetailDTO scDTO) {
		super(StaticCla.mv, "ȸ������-�󼼺���", true);
		this.scDTO = scDTO;
		// ȸ���̹���
		ImageIcon iiImage = new ImageIcon("C:/Users/hyebin/git/MyPrj2/myprj2/src/admin/img/model.png");
		Image oriimg = iiImage.getImage();
		Image chgimg = oriimg.getScaledInstance(250, 200, Image.SCALE_SMOOTH);// �̹��� �������
		ImageIcon newImg = new ImageIcon(chgimg);
		jlImg = new JLabel(newImg);
//		jlImg = new JLabel("C:/Users/hyebin/git/MyPrj2/myprj2/src/admin/img/model.png");
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border����
		Font titleFont = new Font("���� ����", Font.BOLD, 20);
		Font font = new Font("���� ����", Font.BOLD, 15);
		// ��
		JTextField jlTitle = new JTextField(" ȸ������  ");
		jlTitle.setFont(titleFont);
		jlTitle.setBackground(Color.black);
		jlTitle.setForeground(Color.white);
		jlTitle.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel jlCusNum = new JLabel("ȸ����ȣ :");//
		jlCusNum.setFont(font);
		JLabel jlCusIP = new JLabel("ȸ��IP:");//
		jlCusIP.setFont(font);
		JLabel jlOrderer = new JLabel("ȸ�����̵�:");//
		jlOrderer.setFont(font);
		JLabel jlCusName = new JLabel("ȸ���̸�:");//
		jlCusName.setFont(font);
		JLabel jlBirth = new JLabel("�������:");//
		jlBirth.setFont(font);
		JLabel jlGender = new JLabel("����:");//
		jlGender.setFont(font);
		JLabel jlPhone = new JLabel("����ó :");//
		jlPhone.setFont(font);
		JLabel jlCusbunzi = new JLabel("������ȣ :");//
		jlCusbunzi.setFont(font);
		JLabel jlCusAddr = new JLabel("�ּ� :");//
		jlCusAddr.setFont(font);
		JLabel jlCusAddr2 = new JLabel("���ּ� :");//
		jlCusAddr2.setFont(font);
		JLabel jlEmail = new JLabel("�̸���:");//
		jlEmail.setFont(font);
		JLabel jlInputDate = new JLabel("������:");//
		jlInputDate.setFont(font);
		JLabel jlTotalPrice = new JLabel("�ѻ��ݾ�:");//
		jlTotalPrice.setFont(font);
		JLabel jlStop = new JLabel("�������� :");//
		jlStop.setFont(font);
		jtfStop = new JLabel();//
		jlreason = new JLabel("");//

		// T.F
		jtfCusNum = new JLabel();
		jtfCusIP = new JLabel();
		jtfID = new JLabel();

		jtfDate = new JLabel();
		jtfTotalPrice = new JLabel();

		jtfCusName = new JTextField(10);
		jtfBirth = new JTextField(10);
		jtfGender = new JTextField(10);
		jtfPhone = new JTextField(10);
		jtfCusAddr = new JTextField(10);
		jtfEmail = new JTextField(10);
		jtfCusbunzi = new JTextField(10);
		jtfCusAddr2 = new JTextField(10);

//		jtfCusNum.setFont(font);
//		jtfCusIP.setFont(font);
//		jtfID.setFont(font);
//		jtfCusName.setFont(font);
//		jtfBirth.setFont(font);
//		jtfGender.setFont(font);
//		jtfPhone.setFont(font);
//		jtfCusAddr.setFont(font);
//		jtfEmail.setFont(font);
//		jtfDate.setFont(font);
//		jtfTotalPrice.setFont(font);
//		jtfCusbunzi.setFont(font);
//		jtfCusAddr2.setFont(font);

		jbtnClose = new JButton("�ݱ�");
		jbtnModify = new JButton("����");
		jbtnStop = new JButton("����");
		jbtnAddr = new JButton("�ּҰ˻�");

		setLayout(null);

		JPanel jpCus = new JPanel();
		jpCus.setBackground(Color.white);
		jpCus.setLayout(null);
		jlImg.setBounds(280, 20, 200, 250);
		jlCusNum.setBounds(10, 30, 100, 30);
		jlCusIP.setBounds(10, 70, 100, 30);
		jlOrderer.setBounds(10, 110, 100, 30);
		jlTotalPrice.setBounds(10, 150, 140, 30);
		jlInputDate.setBounds(10, 190, 140, 30);
		jtfCusNum.setBounds(100, 30, 220, 30);
		jtfCusIP.setBounds(100, 70, 220, 30);
		jtfID.setBounds(100, 110, 220, 30);
		jtfTotalPrice.setBounds(100, 150, 220, 30);
		jtfDate.setBounds(100, 190, 220, 30);
		jlStop.setBounds(10, 230, 100, 30);
		jtfStop.setBounds(100, 230, 50, 30);
		jlreason.setBounds(10, 255, 300, 30);
		jpCus.add(jlImg);
		jpCus.add(jlCusNum);
		jpCus.add(jlCusIP);
		jpCus.add(jlOrderer);
		jpCus.add(jtfCusNum);
		jpCus.add(jtfCusIP);
		jpCus.add(jtfID);
		jpCus.add(jlTotalPrice);
		jpCus.add(jlInputDate);
		jpCus.add(jtfDate);
		jpCus.add(jtfTotalPrice);
		jpCus.add(jlStop);
		jpCus.add(jtfStop);
		jpCus.add(jlreason);

		jpCus.setFont(font);
		
		JPanel jpCus2 = new JPanel(); // ȸ������
		jpCus2.setBackground(Color.white);
		jpCus2.setLayout(null);
		jlCusName.setBounds(10, 30, 100, 30);
		jlGender.setBounds(270, 30, 100, 30);
		jlBirth.setBounds(10, 70, 100, 30);
		jlPhone.setBounds(10, 110, 100, 30);
		jbtnAddr.setBounds(220, 150, 100, 30);
		jlCusbunzi.setBounds(10, 150, 100, 30);// ����
		jlCusAddr.setBounds(20, 190, 100, 30);
		jlCusAddr2.setBounds(10, 240, 100, 30);// ���ּ�
		jlEmail.setBounds(10, 290, 100, 30);

		jtfCusName.setBounds(100, 30, 100, 30);
		jtfBirth.setBounds(100, 70, 200, 30);
		jtfGender.setBounds(350, 30, 100, 30);
		jtfPhone.setBounds(100, 110, 200, 30);
		jtfCusbunzi.setBounds(100, 150, 100, 30);
		jtfCusAddr.setBounds(100, 190, 370, 30);
		jtfCusAddr2.setBounds(100, 240, 370, 30);
		jtfEmail.setBounds(100, 290, 200, 30);
		
		jpCus2.add(jlCusName);
		jpCus2.add(jlBirth);
		jpCus2.add(jlGender);
		jpCus2.add(jlPhone);
		jpCus2.add(jlCusAddr);
		jpCus2.add(jlEmail);
		jpCus2.add(jlCusAddr2);
		jpCus2.add(jlCusbunzi);
		jpCus2.add(jtfCusName);
		jpCus2.add(jtfBirth);
		jpCus2.add(jtfGender);
		jpCus2.add(jtfPhone);
		jpCus2.add(jtfCusAddr);
		jpCus2.add(jtfCusAddr2);
		jpCus2.add(jtfCusbunzi);
		jpCus2.add(jtfEmail);
		jpCus2.add(jbtnAddr);
		jbtnAddr.setBackground(Color.white);

		// setText
		jtfCusNum.setText(scDTO.getM_code());
		jtfCusIP.setText(scDTO.getM_ip());
		jtfID.setText(scDTO.getM_id());
		jtfCusName.setText(scDTO.getM_name());
		jtfBirth.setText(scDTO.getM_birth());
		jtfGender.setText(scDTO.getM_gender());
		jtfPhone.setText(scDTO.getM_phone());
		jtfCusAddr2.setText(scDTO.getM_detail_addr());
		jtfEmail.setText(scDTO.getM_email());
		jtfDate.setText(scDTO.getM_joindate());
		jtfTotalPrice.setText(String.valueOf(scDTO.getM_totalmoney()) + "��");
		jtfCusAddr.setText(scDTO.getZ_addr());
		jtfCusbunzi.setText(scDTO.getZ_zipcode());
		jtfStop.setText(scDTO.getM_stopflag());
		jlreason.setText(scDTO.getM_stop_reason());
		jlTitle.setBounds(10, 10, 200, 30);

		jlImg.setBorder(new LineBorder(Color.GRAY,0));
		// �������ϰ� ����
		jtfCusAddr.setEditable(false);
		jtfCusbunzi.setEditable(false);
		jlTitle.setEditable(false);

		
		
		
		jpCus.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border����
		jpCus2.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border����
		jpCus.setBounds(10, 50, 550, 290);
		jpCus2.setBounds(10, 350, 550, 330);

		jbtnClose.setBounds(450, 700, 100, 30);
		jbtnModify.setBounds(70, 700, 100, 30);
		jbtnStop.setBounds(250, 700, 100, 30);
		
		jbtnClose.setBackground(Color.white);
		jbtnModify.setBackground(Color.white);
		jbtnStop.setBackground(Color.white);
		add(jpCus);
		add(jpCus2);
		add(jlTitle);

		add(jbtnClose);
		add(jbtnModify);
		add(jbtnStop);
		this.getContentPane().setBackground(new Color(0x3F4040)); //JDialog ���� ���� 

		setBounds(100, 100, 600, 800);
//		AdCusDetailEvt cde = new AdCusDetailEvt(this);
//		jbtnClose.addActionListener(cde);
//		jbtnModify.addActionListener(cde);
//		jbtnStop.addActionListener(cde);
//		jbtnAddr.addActionListener(cde);
		setVisible(true);
		setResizable(false);

	}// AdCusDetailView

	public JTextField getJtfCusName() {
		return jtfCusName;
	}

	public JTextField getJtfBirth() {
		return jtfBirth;
	}

	public JTextField getJtfGender() {
		return jtfGender;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JTextField getJtfCusAddr() {
		return jtfCusAddr;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfCusbunzi() {
		return jtfCusbunzi;
	}

	public JTextField getJtfCusAddr2() {
		return jtfCusAddr2;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public JButton getJbtnStop() {
		return jbtnStop;
	}

	public JButton getJbtnAddr() {
		return jbtnAddr;
	}


	public JLabel getJtfStop() {
		return jtfStop;
	}

	public JLabel getJlreason() {
		return jlreason;
	}

	public JLabel getJtfTotalPrice() {
		return jtfTotalPrice;
	}

	public void setJtfTotalPrice(JLabel jtfTotalPrice) {
		this.jtfTotalPrice = jtfTotalPrice;
	}

	public JLabel getJtfCusNum() {
		return jtfCusNum;
	}

	public JLabel getJtfCusIP() {
		return jtfCusIP;
	}

	public JLabel getJtfID() {
		return jtfID;
	}

	public JLabel getJtfDate() {
		return jtfDate;
	}

	public JLabel getJlImg() {
		return jlImg;
	}

	public void setJlImg(JLabel jlImg) {
		this.jlImg = jlImg;
	}
	

}// class