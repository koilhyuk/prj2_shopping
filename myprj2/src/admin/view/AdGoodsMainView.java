package admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import admin.controller.AdGoodsMainEvt;
import admin.run.StaticCla;

@SuppressWarnings("serial")
public class AdGoodsMainView extends JPanel {

	//// 2019-09-24 종류 채널
	private JLabel jlChannel;

	// 2019-09-22
	private JButton btnSearchBrand;
	private JTextField jtfSearchBrand;
	private JTable jtBrandKind;
	private DefaultTableModel dtmBrandKind;
	private JPanel jpCloBtn;
	private JPanel jpBrandKind;
	private JScrollPane jspBrandKind;
	private JTabbedPane jttpCate;

//////////// 화면 전체 
	private JPanel jpContent;
	private JScrollPane jspFull;

///////////////// 카테고리 버튼 ////////////////
	private JButton btnBest;
	private JButton btnOuter;
	private JButton btnTop;
	private JButton btnBottom;
///2019-09-22/////////////////////
	private JButton btnBag;
	private JButton btnShoes;
	private JButton btnHeadWear;
	private JButton btnAcc;
////////////////////////////

	private JButton btnDress;
	///////////////// 제품 보기 ///////////
	private JButton btnType;
	private JButton btnSearch;
	private DefaultComboBoxModel<String> dcbmSearch;
	private JComboBox<String> jcbSearch;
	private JTextField jtfSearch;

	////////////// 제품 상세보기 test
	private DefaultTableModel dtmDetail;
	private JTable jtDetail;
	///// 선택 항목 라벨
	private JLabel jlSelect;

	private JPanel jpBestGoods;
	private JPanel jpGoods;
	private JScrollPane jspGoods;

	public AdGoodsMainView() {

		setLayout(null);

		//// 2019-09-24
		ImageIcon logoImg= new ImageIcon(StaticCla.FILE_PATH+"/prj2_logo_back_white_main.png");
//		ImageIcon logoImg= new ImageIcon("C:/Users/hyebin/git/prj2_shopping/myprj2/src/admin/img"+"/prj2_logo_back_white_main.png");
		JLabel jlTitle = new JLabel(logoImg);
		jlChannel = new JLabel("ALL", SwingConstants.CENTER);
		jlChannel.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		jlChannel.setOpaque(true);
		jlChannel.setForeground(Color.white);
		jlChannel.setBackground(new Color(0x352A26));
		jlChannel.setBounds(722, 5, 240, 80);

		btnType = new JButton("BRAND 보기");
		btnType.setBounds(1400, 20, 120, 50);
		btnType.setBackground(Color.white);
		btnType.setForeground(new Color(0x043424));
		btnType.setBorder(new LineBorder(Color.white));

		JPanel jpChannel = new JPanel();
		jpChannel.setBackground(new Color(0x352A26));
		jpChannel.setLayout(null);

		jpChannel.add(btnType);
		jpChannel.add(jlChannel);


		JLabel jlBest = new JLabel("판매 랭킹");
		///////////// full 화면
		jpContent = new JPanel();
		jpContent.setLayout(null);
		jspFull = new JScrollPane(jpContent);

		////////////// best
		jpBestGoods = new JPanel();
		jpBestGoods.setBorder(new LineBorder(new Color(0x043424)));
		jpBestGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpBestGoods.setBackground(Color.white);

		///////////// 상품 정렬
		jpGoods = new JPanel();
		jpGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpGoods.setBackground(Color.white);
		jspGoods = new JScrollPane(jpGoods);

		jlSelect = new JLabel("");// 선택 항목 라벨

		String[] searchCombo = new String[] { "상품명", "브랜드명" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchCombo);
		jcbSearch = new JComboBox<String>(dcbmSearch);
		jcbSearch.setBackground(new Color(0x043424));
		jcbSearch.setForeground(Color.white);

		jtfSearch = new JTextField();
		btnSearch = new JButton("조회");
		btnSearch.setForeground(Color.white);
		btnSearch.setBackground(new Color(0x043424));

		dtmDetail = new DefaultTableModel(1, 2);
		jtDetail = new JTable(dtmDetail) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtDetail.setShowGrid(false);
		jtDetail.setTableHeader(null);
		jtDetail.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtDetail.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtDetail.setRowHeight(30);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		jtDetail.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtDetail.getColumnModel().getColumn(1).setCellRenderer(dtcr);


		btnBest = new JButton("Best");
		btnOuter = new JButton("Outer");
		btnTop = new JButton("Top");
		btnBottom = new JButton("Bottom");
		btnDress = new JButton("Dress");

		//////////////// 2019 - 09-22
		btnBag = new JButton("Bag");
		btnShoes = new JButton("Shoes");
		btnHeadWear = new JButton("HeadWear");
		btnAcc = new JButton("Accessory");

		///////////////////////////////

		btnBest.setBackground(new Color(0x043424));
		btnBest.setForeground(Color.white);

		btnOuter.setBackground(new Color(0x043424));
		btnOuter.setForeground(Color.white);

		btnTop.setBackground(new Color(0x043424));
		btnTop.setForeground(Color.white);

		btnBottom.setBackground(new Color(0x043424));
		btnBottom.setForeground(Color.white);
		btnDress.setBackground(new Color(0x043424));
		btnDress.setForeground(Color.white);

		//////////////// 2019 - 09-22
		btnBag.setBackground(new Color(0x043424));
		btnBag.setForeground(Color.white);

		btnShoes.setBackground(new Color(0x043424));
		btnShoes.setForeground(Color.white);

		btnHeadWear.setBackground(new Color(0x043424));
		btnHeadWear.setForeground(Color.white);
		btnAcc.setBackground(new Color(0x043424));
		btnAcc.setForeground(Color.white);

		///////////////////////////////

		jcbSearch.setBounds(410, 200, 80, 30);
		jtfSearch.setBounds(500, 200, 300, 30);
		btnSearch.setBounds(830, 200, 100, 30);

		jlSelect.setBounds(310, 540, 500, 50);

		//////////////// 2019 - 09-22
		btnBest.setBounds(0, 0, 200, 40);
		btnOuter.setBounds(0, 40, 200, 40);
		btnTop.setBounds(0, 80, 200, 40);
		btnBottom.setBounds(0, 120, 200, 40);
		btnDress.setBounds(0, 160, 200, 40);

		btnBag.setBounds(0, 200, 200, 40);
		btnShoes.setBounds(0, 240, 200, 40);
		btnHeadWear.setBounds(0, 280, 200, 40);
		btnAcc.setBounds(0, 320, 200, 40);

		jpCloBtn = new JPanel();
		jpCloBtn.setLayout(null);

		jpCloBtn.add(btnBest);
		jpCloBtn.add(btnOuter);
		jpCloBtn.add(btnTop);
		jpCloBtn.add(btnBottom);
		jpCloBtn.add(btnDress);

		jpCloBtn.add(btnBag);
		jpCloBtn.add(btnShoes);
		jpCloBtn.add(btnHeadWear);
		jpCloBtn.add(btnAcc);

		jtfSearchBrand = new JTextField();
		jtfSearchBrand.setBounds(10, 10, 140, 30);
		btnSearchBrand = new JButton(new ImageIcon(StaticCla.FILE_PATH + "/searchButton.jpg"));
		btnSearchBrand.setBounds(155, 10, 30, 30);

		dtmBrandKind = new DefaultTableModel(1, 1);
		jtBrandKind = new JTable(dtmBrandKind) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		jtBrandKind.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtBrandKind.setTableHeader(null);
		jtBrandKind.setRowHeight(30);

		jspBrandKind = new JScrollPane(jtBrandKind);
		jspBrandKind.setBounds(10, 60, 180, 290);

		jpBrandKind = new JPanel();
		jpBrandKind.setLayout(null);

		jpBrandKind.add(jspBrandKind);
		jpBrandKind.add(btnSearchBrand);
		jpBrandKind.add(jtfSearchBrand);

		jttpCate = new JTabbedPane();
		jttpCate.addTab("품목", jpCloBtn);
		jttpCate.addTab("브랜드", jpBrandKind);

		jpCloBtn.setBackground(new Color(0x043424));
		jpBrandKind.setBackground(new Color(0x043424));

		jttpCate.setBounds(10, 300, 200, btnAcc.getY() + 70);
		jpContent.add(jttpCate);
		jpContent.add(jpChannel);
///////////////////////////////////////////

		jpContent.add(jpBestGoods);
		jpContent.add(jlBest);

		jlBest.setBounds(310, 230, 100, 40);
		jpBestGoods.setBounds(310, 260, 1070, 290);

		jspGoods.setBounds(310, 575, 1070, 1150);
		/////////////////////////// 이벤트 처리 ////////////////////
		AdGoodsMainEvt agme = new AdGoodsMainEvt(this);

		btnBest.addActionListener(agme);
		btnOuter.addActionListener(agme);
		btnTop.addActionListener(agme);
		btnBottom.addActionListener(agme);
		btnDress.addActionListener(agme);

		/////// 2019-09-22
		jtBrandKind.addMouseListener(agme);
		btnAcc.addActionListener(agme);
		btnBag.addActionListener(agme);
		btnHeadWear.addActionListener(agme);
		btnShoes.addActionListener(agme);

		btnSearchBrand.addActionListener(agme);
		jtfSearchBrand.addActionListener(agme);
		/////////////////////////////

		btnType.addActionListener(agme);
		btnSearch.addActionListener(agme);
		jcbSearch.addActionListener(agme);
		jtfSearch.addActionListener(agme);

		btnBest.addMouseListener(agme);
		btnOuter.addMouseListener(agme);
		btnTop.addMouseListener(agme);
		btnBottom.addMouseListener(agme);
		btnDress.addMouseListener(agme);

		btnType.addMouseListener(agme);
		btnSearch.addMouseListener(agme);

		/////////// 2019-09-22
		btnAcc.addMouseListener(agme);
		btnBag.addMouseListener(agme);
		btnHeadWear.addMouseListener(agme);
		btnShoes.addMouseListener(agme);
		jttpCate.addMouseListener(agme);
		//////////////
		jttpCate.setBackground(Color.white);

		jtDetail.addMouseListener(agme);

		jpContent.add(jlSelect);
		jpContent.add(jspGoods);
		jpContent.add(btnSearch);
		jpContent.add(jtfSearch);
		jpContent.add(jcbSearch);
		jlTitle.setBounds(20, 10, 270,60 );
		jpContent.add(jlTitle);
		add(jspFull);
//		add(jlTitle);

		jpChannel.setBackground(new Color(0x352A26));
		jpContent.setBackground(Color.white);

		setBackground(Color.white);
		
		setBounds(0, 0, 1600, 900);
		jspFull.setBounds(0, 0, getWidth() - 20, getHeight() - 20);// 고정 값
		jpChannel.setBounds(0, 70, jspFull.getWidth() - 15, 100);
		jpContent.setPreferredSize(new Dimension(jspFull.getWidth() - 20, jspFull.getHeight() + 1050));// 고정 값
		setVisible(true);

	}// HomeMainView

	public JLabel getJlChannel() {
		return jlChannel;
	}

	public JButton getBtnSearchBrand() {
		return btnSearchBrand;
	}

	public JTextField getJtfSearchBrand() {
		return jtfSearchBrand;
	}

	public JTable getJtBrandKind() {
		return jtBrandKind;
	}

	public DefaultTableModel getDtmBrandKind() {
		return dtmBrandKind;
	}

	public JPanel getJpCloBtn() {
		return jpCloBtn;
	}

	public JPanel getJpBrandKind() {
		return jpBrandKind;
	}

	public JScrollPane getJspBrandKind() {
		return jspBrandKind;
	}

	public JTabbedPane getJttpCate() {
		return jttpCate;
	}

	public JPanel getJpContent() {
		return jpContent;
	}

	public JScrollPane getJspFull() {
		return jspFull;
	}

	public JButton getBtnBest() {
		return btnBest;
	}

	public JButton getBtnOuter() {
		return btnOuter;
	}

	public JButton getBtnTop() {
		return btnTop;
	}

	public JButton getBtnBottom() {
		return btnBottom;
	}

	public JButton getBtnBag() {
		return btnBag;
	}

	public JButton getBtnShoes() {
		return btnShoes;
	}

	public JButton getBtnHeadWear() {
		return btnHeadWear;
	}

	public JButton getBtnAcc() {
		return btnAcc;
	}

	public JButton getBtnDress() {
		return btnDress;
	}

	public JButton getBtnType() {
		return btnType;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public DefaultComboBoxModel<String> getDcbmSearch() {
		return dcbmSearch;
	}

	public JComboBox<String> getJcbSearch() {
		return jcbSearch;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}

	public DefaultTableModel getDtmDetail() {
		return dtmDetail;
	}

	public JTable getJtDetail() {
		return jtDetail;
	}

	public JLabel getJlSelect() {
		return jlSelect;
	}

	public JPanel getJpBestGoods() {
		return jpBestGoods;
	}

	public JPanel getJpGoods() {
		return jpGoods;
	}

	public JScrollPane getJspGoods() {
		return jspGoods;
	}

}// UserMainView
