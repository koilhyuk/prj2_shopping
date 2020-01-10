package user.view.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import user.controller.content.UserGoodsDetailEvt;
import user.controller.content.UserGoodsMainEvt;
import user.vo.content.SelectClickGoodsDetailDTO;

@SuppressWarnings("serial")
public class UserGoodsDetailView extends JDialog {

	private JButton jbtnBuy, jbtnPlus, jbtnMinus;
	private JTable jtProduct;
	private JPanel jp;
	private JLabel jlGName, jlGPrice;
	private JTextArea jtaStrong;
	private JTextField jtfSelectNum, jtfBrand;

	public UserGoodsDetailView(SelectClickGoodsDetailDTO scgdDTO) {
		super(UserGoodsMainEvt.ugmv, "Ä«µåµî·Ï", true);

		JLabel jlimg = new JLabel(new ImageIcon(UserGoodsMainView.USER_FILE_PATH + "/rs_gd_" + scgdDTO.getgImg()));
		JLabel jldetail = new JLabel("»óÇ°»ó¼¼º¸±â");
		jldetail.setForeground(Color.white);
		jlGName = new JLabel(scgdDTO.getgName(), JLabel.LEFT);
		jlGPrice = new JLabel(String.valueOf(scgdDTO.getgPrice()) + "¿ø", JLabel.LEFT);
		JLabel jl_su = new JLabel("¼ö·®");
		jtfSelectNum = new JTextField("1");
		jtfSelectNum.setHorizontalAlignment(JTextField.CENTER);
		jtfBrand = new JTextField(scgdDTO.getbName());

		Font f = new Font("¸¼Àº °íµñ)", Font.BOLD, 30);
		jldetail.setFont(f);

		jtaStrong = new JTextArea(scgdDTO.getgStrong());

		jlGName.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		jlGPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		jl_su.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));

		jbtnBuy = new JButton("±¸¸ÅÇÏ±â");
		jbtnBuy.setForeground(Color.white);
		jbtnBuy.setBackground(new Color(0x3F4040));
		jbtnMinus = new JButton("-");
		jbtnMinus.setForeground(Color.white);
		jbtnMinus.setBackground(new Color(0x3F4040));
		jbtnPlus = new JButton("+");
		jbtnPlus.setForeground(Color.white);
		jbtnPlus.setBackground(new Color(0x3F4040));

		jp = new JPanel();
		JPanel jp1 = new JPanel();
		jp.setPreferredSize(new Dimension(800, 500));
		JScrollPane jsp = new JScrollPane(jp);
		jsp.setLayout(null);

		jtaStrong.setBounds(30, 400, 720, 300);
		jl_su.setBounds(400, 200, 100, 50);
		jlGPrice.setBounds(400, 130, 380, 50);
		jlGName.setBounds(400, 50, 380, 50);
		jlimg.setBounds(50, 25, 270, 350);
		jlimg.setBorder(new LineBorder(Color.BLACK));
		jbtnBuy.setBounds(600, 290, 100, 30);
		jldetail.setBounds(350, 30, 200, 50);
		jtfSelectNum.setBounds(522, 215, 60, 26);
		jbtnMinus.setBounds(482, 215, 40, 25);
		jbtnPlus.setBounds(580, 215, 42, 25);

		jp1.setBounds(20, 0, 800, 80);
		jp1.setBackground(new Color(0x3F4040));

		jsp.add(jbtnPlus);
		jsp.add(jbtnMinus);
		jsp.add(jtfSelectNum);
		jsp.setBounds(20, 80, 800, 800);
		jsp.add(jbtnBuy);
		jsp.add(jlimg);
		jsp.add(jlGName);
		jsp.add(jlGPrice);
		jsp.add(jl_su);
		jsp.add(jtaStrong);

		jp1.add(jldetail);
		add(jsp);
		add(jp1);

		setLayout(null);
		UserGoodsDetailEvt u_evt = new UserGoodsDetailEvt(this, scgdDTO.getgCode());
		jbtnBuy.addActionListener(u_evt);
		jbtnMinus.addActionListener(u_evt);
		jbtnPlus.addActionListener(u_evt);

		setBounds(100, 50, 850, 800);
		setVisible(true);

	}

	public JButton getJbtnBuy() {
		return jbtnBuy;
	}

	public JButton getJbtnPlus() {
		return jbtnPlus;
	}

	public JButton getJbtnMinus() {
		return jbtnMinus;
	}

	public JTable getJtProduct() {
		return jtProduct;
	}

	public JLabel getJlGName() {
		return jlGName;
	}

	public JLabel getJlGPrice() {
		return jlGPrice;
	}

	public JTextArea getJtaStrong() {
		return jtaStrong;
	}

	public JTextField getJtfSelectNum() {
		return jtfSelectNum;
	}

	public JTextField getJtfBrand() {
		return jtfBrand;
	}
}// class
