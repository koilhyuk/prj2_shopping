package admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import admin.controller.AdGoodsPanelDetailEvt;
import admin.run.StaticCla;
import admin.vo.SelectClickGoodsDetailDTO;

@SuppressWarnings("serial")
public class AdGoodsPanelDetailView extends JDialog {
	private JButton jbtnClose;

	public AdGoodsPanelDetailView(SelectClickGoodsDetailDTO scgdDTO) {
		super(StaticCla.mv, "��ǰ��", true);

		jbtnClose = new JButton("Ȯ��");
		JLabel jlImg = new JLabel(new ImageIcon(StaticCla.FILE_PATH + "/rs_gd_" + scgdDTO.getgImg()));
		JLabel jlDetail = new JLabel("��ǰ�󼼺���");
		JLabel jlBrandTag = new JLabel("-�귣��");
		JLabel jlBrand = new JLabel("NO".equals(scgdDTO.getbName()) ? "SOHO" : scgdDTO.getbName(), JLabel.LEFT);
		JLabel jlPriceTag = new JLabel("-����");

		JLabel jlSellTag = new JLabel("-���� �Ǹ�");
		JLabel jlSell = new JLabel(String.valueOf(scgdDTO.getgSaleNum()) + " ��", JLabel.LEFT);
		JLabel jlLikeTag = new JLabel("-����");
		
		JLabel jlLike = new JLabel(scgdDTO.getgLikeNum() + " ��");

		jlDetail.setForeground(Color.white);
		JLabel jlGName = new JLabel(scgdDTO.getgName(), JLabel.LEFT);
		JLabel jlGPrice = new JLabel(String.valueOf(scgdDTO.getgPrice()) + "��", JLabel.LEFT);

		JLabel jlGoodsStarTag = new JLabel("-����");
		JLabel jlGoodsStar = null;
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

		jlGoodsStar.setForeground(Color.red);
		Font f = new Font("���� ���)", Font.BOLD, 30);
		jlDetail.setFont(f);

		JTextArea jtaStrong = new JTextArea(scgdDTO.getgStrong()) {
			@Override
			public boolean isEditable() {
				return false;
			}
		};

		jlGoodsStarTag.setFont(new Font("���� ���", Font.PLAIN, 15));
		jlPriceTag.setFont(new Font("���� ���", Font.PLAIN, 15));
		jlBrandTag.setFont(new Font("���� ���", Font.PLAIN, 15));
		jlGName.setFont(new Font("���� ���", Font.BOLD, 25));
		jlGPrice.setFont(new Font("���� ���", Font.BOLD, 15));
		jlBrand.setFont(new Font("���� ���", Font.BOLD, 15));
		jlSell.setFont(new Font("���� ���", Font.BOLD, 15));
		jlGoodsStar.setFont(new Font("���� ���", Font.BOLD, 15));
		jlSellTag.setFont(new Font("���� ���", Font.PLAIN, 15));
		jlLikeTag.setFont(new Font("���� ���", Font.PLAIN, 15));
		jlLike.setFont(new Font("���� ���", Font.BOLD, 15));

		JPanel jpDetail = new JPanel();
		JScrollPane jsp = new JScrollPane();
		jsp.setLayout(null);

		jlImg.setBounds(50, 25, 270, 350);
		jlImg.setBorder(new LineBorder(Color.BLACK));
		jlDetail.setBounds(350, 30, 200, 50);

		jlGName.setBounds(360, 20, 370, 50);
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

		jtaStrong.setBounds(30, 400, 720, 260);
		jtaStrong.setBorder(new LineBorder(Color.black));

		jbtnClose.setBounds(670, 340, 80, 30);
		jbtnClose.setBackground(new Color(0x352A26));
		jbtnClose.setForeground(Color.white);
		jbtnClose.setBorder(new LineBorder(new Color(0x352A26)));
		

		jpDetail.setBounds(20, 5, 800, 80);
		jpDetail.setBackground(new Color(0x352A26));

		jsp.add(jbtnClose);
		jsp.add(jlSell);
		jsp.add(jlBrand);
		jsp.add(jlGoodsStar);
		jsp.add(jlSellTag);
		jsp.add(jlLikeTag);
		jsp.add(jlLike);
		jsp.add(jlGoodsStarTag);
		jsp.add(jlBrandTag);
		jsp.add(jlImg);
		jsp.add(jlGName);
		jsp.add(jlGPrice);
		jsp.add(jlPriceTag);
		jsp.add(jtaStrong);
		jsp.setBounds(20, 80, 800, 680);

		jpDetail.add(jlDetail);
		add(jsp);
		add(jpDetail);
		jsp.setBackground(Color.white);

		setLayout(null);
		setResizable(false);
		AdGoodsPanelDetailEvt agpdEvt = new AdGoodsPanelDetailEvt(this);
		jbtnClose.addActionListener(agpdEvt);
		this.getContentPane().setBackground(Color.white); //JDialog ���� ���� 
		setBounds(200, 20, 850, 800);
		setVisible(true);

	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

}// class
