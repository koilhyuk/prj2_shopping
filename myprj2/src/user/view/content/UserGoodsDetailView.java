package user.view.content;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.controller.content.UserGoodsDetailEvt;
import user.controller.content.UserGoodsMainEvt;
import user.vo.content.SelectClickGoodsDetailDTO;

@SuppressWarnings("serial")
public class UserGoodsDetailView extends JDialog {

	private JButton jbtnBuy, jbtnPlus, jbtnMinus, jbtnGoodsLike;
	private JTable jtProduct;
	private JLabel jlGName, jlGPrice, jlGTotalPrice;
	private JTextArea jtaStrong;
	private JTextField jtfSelectNum;

	public UserGoodsDetailView(SelectClickGoodsDetailDTO scgdDTO) {
		super(UserGoodsMainEvt.ugmv, "ī����", true);

		JLabel jlImg = new JLabel(new ImageIcon(UserGoodsMainView.USER_FILE_PATH + "/rs_gd_" + scgdDTO.getgImg()));
		JLabel jlDetail = new JLabel("��ǰ�󼼺���");
		JLabel jlBrandTag = new JLabel("-�귣��");
		JLabel jlBrand = new JLabel("NO".equals(scgdDTO.getbName()) ? "SOHO" : scgdDTO.getbName(), JLabel.LEFT);
		JLabel jlPriceTag = new JLabel("-����");

		JLabel jlSellTag = new JLabel("-���� �Ǹ�");
		JLabel jlSell = new JLabel(String.valueOf(scgdDTO.getgSaleNum()) + " ��", JLabel.LEFT);
		JLabel jlLikeTag = new JLabel("-����");
		JLabel jlLike = new JLabel(scgdDTO.getgLikeNum() + " ��");
		JLabel jlTotalMoneyTag = new JLabel("�� ��ǰ �ݾ�");

		jlDetail.setForeground(Color.white);
		jlGName = new JLabel(scgdDTO.getgName(), JLabel.LEFT);
		jlGPrice = new JLabel(String.valueOf(scgdDTO.getgPrice()) + "��", JLabel.LEFT);
		DecimalFormat priceFormat = new DecimalFormat("###,###");
		jlGTotalPrice = new JLabel(priceFormat.format(scgdDTO.getgPrice()) + "��", JLabel.RIGHT);

		JLabel jlGoodsStarTag = new JLabel("-����");
		JLabel jlGoodsStar = new JLabel("-����");
		switch (scgdDTO.getgScore()) {
		case 5:
			jlGoodsStar = new JLabel("�ڡڡڡڡ�", JLabel.LEFT);
			break;
		case 4:
			jlGoodsStar = new JLabel("�ڡڡڡڡ�", JLabel.LEFT);
			break;
		case 3:
			jlGoodsStar = new JLabel("�ڡڡڡ١�", JLabel.LEFT);
			break;
		case 2:
			jlGoodsStar = new JLabel("�ڡڡ١١�", JLabel.LEFT);
			break;
		case 1:
			jlGoodsStar = new JLabel("�ڡ١١١�", JLabel.LEFT);
			break;
		case 0:
			jlGoodsStar = new JLabel("�١١١١�", JLabel.LEFT);
			break;
		default:
			jlGoodsStar = new JLabel("");
			break;
		}// switch

		jtfSelectNum = new JTextField("1");
		jtfSelectNum.setHorizontalAlignment(JTextField.CENTER);

		Font f = new Font("���� ����)", Font.BOLD, 30);
		jlDetail.setFont(f);

		jtaStrong = new JTextArea(scgdDTO.getgStrong()) {
			@Override
			public boolean isEditable() {
				return false;
			}
		};

		jlGoodsStarTag.setFont(new Font("���� ����", Font.PLAIN, 15));
		jlPriceTag.setFont(new Font("���� ����", Font.PLAIN, 15));
		jlBrandTag.setFont(new Font("���� ����", Font.PLAIN, 15));
		jlGName.setFont(new Font("���� ����", Font.BOLD, 25));
		jlGPrice.setFont(new Font("���� ����", Font.BOLD, 15));
		jlBrand.setFont(new Font("���� ����", Font.BOLD, 15));
		jlSell.setFont(new Font("���� ����", Font.BOLD, 15));
		jlLike.setFont(new Font("���� ����", Font.BOLD, 15));
		jlGoodsStar.setFont(new Font("���� ����", Font.BOLD, 15));
		jlSellTag.setFont(new Font("���� ����", Font.PLAIN, 15));
		jlLikeTag.setFont(new Font("���� ����", Font.PLAIN, 15));
		jlTotalMoneyTag.setFont(new Font("���� ����", Font.BOLD, 20));
		jlGTotalPrice.setFont(new Font("���� ����", Font.BOLD, 20));

		jbtnBuy = new JButton("����");
		jbtnBuy.setForeground(Color.white);
		jbtnBuy.setBackground(new Color(0x3F4040));
		jbtnMinus = new JButton("-");
		jbtnMinus.setForeground(Color.white);
		jbtnMinus.setBackground(new Color(0x3F4040));
		jbtnPlus = new JButton("+");
		jbtnPlus.setForeground(Color.white);
		jbtnPlus.setBackground(new Color(0x3F4040));

		ImageIcon likeImg = null;
		//// ���ϱ�
		if (scgdDTO.isgLikeStatus()) {// ���ϱ� �Ǿ�����
			likeImg = new ImageIcon(UserGoodsMainView.USER_FILE_PATH + "/like_heart.PNG");
		} else {// ���ϱ� �Ǿ� X
			likeImg = new ImageIcon(UserGoodsMainView.USER_FILE_PATH + "/unlike_heart.png");
		}
		
		jbtnGoodsLike = new JButton(likeImg);

		JPanel jpDetail = new JPanel();
		JScrollPane jsp = new JScrollPane();
		jsp.setLayout(null);

		jlImg.setBounds(50, 25, 270, 350);
		jlImg.setBorder(new LineBorder(Color.BLACK));
		jlDetail.setBounds(350, 30, 200, 50);

		jlGName.setBounds(360, 20, 370, 50);
		jlGName.setBorder(new TitledBorder("f"));
		jbtnGoodsLike.setBounds(730, 30, 38, 40);
		
		
		jlBrandTag.setBounds(360, 70, 90, 50);
		jlBrand.setBounds(470, 70, 300, 50);
		jlPriceTag.setBounds(360, 110, 90, 50);
		jlGPrice.setBounds(470, 110, 300, 50);

		jlGoodsStarTag.setBounds(360, 150, 90, 50);
		jlGoodsStar.setBounds(470, 150, 300, 50);

		jlSellTag.setBounds(360, 190, 90, 50);
		jlSell.setBounds(470, 190, 300, 50);

		jlLikeTag.setBounds(360, 230, 90, 50);
		jlLike.setBounds(470, 230, 300, 50);

		jbtnMinus.setBounds(512, 280, 40, 28);
		jtfSelectNum.setBounds(552, 280, 60, 29);
		jbtnPlus.setBounds(610, 280, 42, 28);
		jbtnBuy.setBounds(660, 280, 80, 28);

		jtaStrong.setBounds(30, 400, 720, 250);
		Border borderColor = BorderFactory.createLineBorder(Color.black);
		JPanel jpTotalMoney = new JPanel();

		jpTotalMoney.setBorder(borderColor);
		jpTotalMoney.setBounds(360, 320, 380, 60);
		jpTotalMoney.setLayout(null);

		jlTotalMoneyTag.setBounds(15, 0, 140, 60);
		jlGTotalPrice.setBounds(150, 0, 220, 60);

		jpTotalMoney.add(jlGTotalPrice);
		jpTotalMoney.add(jlTotalMoneyTag);

		jpDetail.setBounds(20, 0, 800, 80);
		jpDetail.setBackground(new Color(0x3F4040));

		jsp.add(jbtnGoodsLike);
		jsp.add(jpTotalMoney);
		jsp.add(jlSell);
		jsp.add(jlBrand);
		jsp.add(jlGoodsStar);
		jsp.add(jlSellTag);
		jsp.add(jlLikeTag);
		jsp.add(jlLike);
		jsp.add(jlGoodsStarTag);
		jsp.add(jlBrandTag);
		jsp.add(jbtnPlus);
		jsp.add(jbtnMinus);
		jsp.add(jtfSelectNum);
		jsp.add(jbtnBuy);
		jsp.add(jlImg);
		jsp.add(jlGName);
		jsp.add(jlGPrice);
		jsp.add(jlPriceTag);
		jsp.add(jtaStrong);
		jsp.setBounds(20, 80, 800, 800);

		jpDetail.add(jlDetail);
		add(jsp);
		add(jpDetail);

		setLayout(null);
		setResizable(false);
		UserGoodsDetailEvt uEvt = new UserGoodsDetailEvt(this, scgdDTO.getgCode());
		jbtnBuy.addActionListener(uEvt);
		jbtnMinus.addActionListener(uEvt);
		jbtnPlus.addActionListener(uEvt);

		setBounds(200, 20, 850, 800);
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

	public JLabel getJlGTotalPrice() {
		return jlGTotalPrice;
	}

}// class
