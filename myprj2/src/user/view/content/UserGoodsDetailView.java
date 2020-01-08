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

import user.controller.content.UserGoodsDetailEvt;
import user.controller.content.UserGoodsMainEvt;
import user.vo.content.SelectClickGoodsDetailDTO;

@SuppressWarnings("serial")
public class UserGoodsDetailView extends JDialog {

	private JButton jbtbuy, jbtplus, jbtminus;
	private JLabel jldetail, jlimg, jlG_name, jlg_price, jl_su, jl_won;
	private JTable jtProduct;
	private JPanel jp;
	private JTextArea jta_strong;
	private JTextField jtf, jtfbrand;

	public UserGoodsDetailView(SelectClickGoodsDetailDTO scgdDTO ) {
		super(UserGoodsMainEvt.ugmv, "Ä«µåµî·Ï", true);
		
		jlimg = new JLabel(new ImageIcon(UserGoodsMainView.USER_FILE_PATH + "/rs_gds_"+scgdDTO.getgImg()));
		jldetail = new JLabel("»óÇ°»ó¼¼º¸±â");
		jldetail.setForeground(Color.white);
		jlG_name = new JLabel(scgdDTO.getgName());
		jlg_price = new JLabel(String.valueOf(scgdDTO.getgPrice()));
		jl_su = new JLabel("¼ö·®");
		jtf = new JTextField("1");
		jtf.setHorizontalAlignment(JTextField.CENTER);
		jl_won = new JLabel("¿ø");
		jtfbrand = new JTextField(scgdDTO.getbName());

		Font f = new Font("¸¼Àº °íµñ)", Font.BOLD, 30);
		jldetail.setFont(f);

		jta_strong = new JTextArea(scgdDTO.getgStrong());

		jlG_name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		jlg_price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		jl_su.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		jl_won.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));

		jbtbuy = new JButton("±¸¸ÅÇÏ±â");
		jbtbuy.setForeground(Color.white);
		jbtbuy.setBackground(new Color(0x3F4040));
		jbtminus = new JButton("-");
		jbtminus.setForeground(Color.white);
		jbtminus.setBackground(new Color(0x3F4040));
		jbtplus = new JButton("+");
		jbtplus.setForeground(Color.white);
		jbtplus.setBackground(new Color(0x3F4040));

		jp = new JPanel();
		JPanel jp1 = new JPanel();
		jp.setPreferredSize(new Dimension(800, 500));
		JScrollPane jsp = new JScrollPane(jp);
		jsp.setLayout(null);

		jta_strong.setBounds(30, 350, 720, 300);
		jl_su.setBounds(400, 200, 100, 50);
		jlg_price.setBounds(400, 130, 100, 50);
		jl_won.setBounds(470, 130, 50, 50);
		jlG_name.setBounds(400, 50, 100, 50);
		jlimg.setBounds(50, 50, 300, 200);
		jbtbuy.setBounds(600, 290, 100, 30);
		jldetail.setBounds(350, 30, 200, 50);
		jtf.setBounds(522, 215, 60, 26);
		jbtminus.setBounds(482, 215, 40, 25);
		jbtplus.setBounds(580, 215, 42, 25);

		jp1.setBounds(20, 0, 800, 80);
		jp1.setBackground(new Color(0x3F4040));

		jsp.add(jl_won);
		jsp.add(jbtplus);
		jsp.add(jbtminus);
		jsp.add(jtf);
		jsp.setBounds(20, 80, 800, 800);
		jsp.add(jbtbuy);
		jsp.add(jlimg);
		jsp.add(jlG_name);
		jsp.add(jlg_price);
		jsp.add(jl_su);
		jsp.add(jta_strong);

		jp1.add(jldetail);
		add(jsp);
		add(jp1);

		setLayout(null);
		UserGoodsDetailEvt u_evt = new UserGoodsDetailEvt(this,scgdDTO.getgCode());
		jbtbuy.addActionListener(u_evt);	
		jbtminus.addActionListener(u_evt);
		jbtplus.addActionListener(u_evt);

		setBounds(100, 100, 850, 800);
		setVisible(true);


	}// UserProductDetailView

	public JButton getJbtbuy() {
		return jbtbuy;
	}

	public JButton getJbtplus() {
		return jbtplus;
	}

	public JButton getJbtminus() {
		return jbtminus;
	}

	public JLabel getJldetail() {
		return jldetail;
	}

	public JLabel getJlimg() {
		return jlimg;
	}

	public JLabel getJlG_name() {
		return jlG_name;
	}

	public JLabel getJlg_price() {
		return jlg_price;
	}

	public JLabel getJl_su() {
		return jl_su;
	}

	public JLabel getJl_won() {
		return jl_won;
	}

	public JTable getJtProduct() {
		return jtProduct;
	}

	public JPanel getJp() {
		return jp;
	}

	public JTextArea getJta_strong() {
		return jta_strong;
	}

	public JTextField getJtf() {
		return jtf;
	}

	public JTextField getJtfbrand() {
		return jtfbrand;
	}


}// class
