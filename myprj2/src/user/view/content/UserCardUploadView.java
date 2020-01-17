package user.view.content;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import user.controller.content.UserCardUploadEvt;

@SuppressWarnings("serial")
public class UserCardUploadView extends JDialog {

	private JButton jbtUpload, jbtnClose;
	private JTextField jtfCard1, jtfCard2, jtfCard3, jtfCard4, jtfCVC;
	private JComboBox<String> jcbCardName;
	private DefaultComboBoxModel<String> dcbmCard;

	public UserCardUploadView(UserMyPageView ugmv) {
		super(ugmv, "카드등록", true);

		JLabel jlCard = new JLabel("카드선택");
		JLabel jlCVC = new JLabel("CVC(3자리)");
		JLabel jlCard1 = new JLabel("-");
		JLabel jlCard2 = new JLabel("-");
		JLabel jlCard3 = new JLabel("-");

		jbtUpload = new JButton("카드등록");
		jbtnClose = new JButton("닫기");
		jtfCard1 = new JTextField(4);
		jtfCard2 = new JTextField(4);
		jtfCard3 = new JTextField(4);
		jtfCard4 = new JTextField(4);
		jtfCVC = new JTextField(3);

		String[] card = { "신한", "롯데", "국민", "새마을", "기업", "우리", "농협", "하나", "카카오" };
		dcbmCard = new DefaultComboBoxModel<String>(card);
		jcbCardName = new JComboBox<String>(dcbmCard);

		setLayout(null);
		jbtUpload.setBounds(120, 170, 100, 30);
		jbtnClose.setBounds(250, 170, 100, 30);
		jtfCard1.setBounds(50, 70, 80, 30);
		jtfCard2.setBounds(150, 70, 80, 30);
		jtfCard3.setBounds(250, 70, 80, 30);
		jtfCard4.setBounds(350, 70, 80, 30);
		jtfCVC.setBounds(170, 120, 100, 30);
		jcbCardName.setBounds(180, 30, 100, 30);
		jlCard.setBounds(100, 30, 100, 30);
		jlCVC.setBounds(90, 120, 100, 30);
		jlCard1.setBounds(135, 70, 30, 30);
		jlCard2.setBounds(235, 70, 30, 30);
		jlCard3.setBounds(335, 70, 30, 30);

		//////////// 이벤트 처리
		UserCardUploadEvt ucue = new UserCardUploadEvt(this);
		jbtUpload.addActionListener(ucue);
		jbtnClose.addActionListener(ucue);
		jcbCardName.addActionListener(ucue);
		jtfCVC.addActionListener(ucue);// cvc는 액션

		jtfCVC.addKeyListener(ucue);
		jtfCard1.addKeyListener(ucue);
		jtfCard2.addKeyListener(ucue);
		jtfCard3.addKeyListener(ucue);
		jtfCard4.addKeyListener(ucue);

		add(jlCard1);
		add(jlCard2);
		add(jlCard3);
		add(jlCard);
		add(jlCVC);
		add(jbtUpload);
		add(jbtnClose);
		add(jtfCard1);
		add(jtfCard2);
		add(jtfCard3);
		add(jtfCard4);
		add(jtfCVC);
		add(jcbCardName);

		jbtUpload.setBackground(Color.white);
		jbtUpload.setForeground(new Color(0x3F4040));
		jbtnClose.setBackground(Color.white);
		jbtnClose.setForeground(new Color(0x3F4040));

		jlCard.setForeground(Color.white);
		jlCard1.setForeground(Color.white);
		jlCard2.setForeground(Color.white);
		jlCard3.setForeground(Color.white);
		jlCVC.setForeground(Color.white);

		this.getContentPane().setBackground(new Color(0x352A26));

		setBounds(400, 300, 500, 270);
		setResizable(false);
		setVisible(true);

	}// UserCardUploadView

	public JButton getJbtUpload() {
		return jbtUpload;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public JTextField getJtfCard1() {
		return jtfCard1;
	}

	public JTextField getJtfCard2() {
		return jtfCard2;
	}

	public JTextField getJtfCard3() {
		return jtfCard3;
	}

	public JTextField getJtfCard4() {
		return jtfCard4;
	}

	public JTextField getJtfCVC() {
		return jtfCVC;
	}

	public JComboBox<String> getJcbCardName() {
		return jcbCardName;
	}

	public DefaultComboBoxModel<String> getDcbmCard() {
		return dcbmCard;
	}

}// class
