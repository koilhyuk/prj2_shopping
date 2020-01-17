package user.view.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import user.controller.content.UserGoodsMainEvt;
import user.run.MemLoginState;
import user.view.login.ClientLoginView;

@SuppressWarnings("serial")
public class UserGoodsMainView extends JFrame {

	private MemLoginState mls;
	private JButton btnOrderNe;
	/////////////////////////////// 20200112 찜하기 버튼 추가
	private JButton btnGoodsLike;

	//// 2019-09-24 종류 채널
	private JLabel jlChannel;

	/// 2019-09-23
	// 광고 배너 추가// 최근 본 상품
	private JPanel jpRecent;
	private JScrollPane jspRecent;
	private JButton btnLogout;

	// 2019-09-22
	private JButton btnSearchBrand;
	private JTextField jtfSearchBrand;
	private JTable jtBrandKind;
	private DefaultTableModel dtmBrandKind;
	private JPanel jpCloBtn;
	private JPanel jpBrandKind;
	private JScrollPane jspBrandKind;
	private JTabbedPane jttpCate;
//////////////////////////////

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

	public static String id;
	public static String ip;
	public static final String KEY = "9394959696959493";


	public UserGoodsMainView(String id, String ip) {// 비회원일 경우 로그인 버튼으로 변경
		super("\"" + id + "\" 님이 로그인하셨습니다.");
		this.id = id;
		this.ip = ip;

//		File file = new File("C:/dev/workspace/myprj2/src/user/img");
//		File[] temp = file.listFiles();
//		for (int i = 0; i < temp.length; i++) {
//			if (!temp[i].getName().startsWith("rs_")) {
//				ImageResize.resizeImage("C:/dev/workspace/myprj2/src/user/img/" + temp[i].getName(), 100, 100);
//			} // end if
//		} // end for

		setLayout(null);

		//// 2019-09-24
		jlChannel = new JLabel("ALL", SwingConstants.CENTER);
		jlChannel.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		jlChannel.setOpaque(true);
		jlChannel.setForeground(Color.white);
		jlChannel.setBackground(new Color(0x352A26));
		jlChannel.setBounds(722, 5, 240, 80);

		btnType = new JButton("BRAND 보기");
		btnType.setBackground(Color.white);
		btnType.setForeground(new Color(0x3F4040));
		btnType.setBorder(new LineBorder(Color.white));
		btnType.setBounds(1400, 20, 120, 50);


		JPanel jpChannel = new JPanel();
		jpChannel.setBackground(new Color(0x352A26));
		jpChannel.setLayout(null);

		jpChannel.add(btnType);
		jpChannel.add(jlChannel);

		////////// 2019-09-23
		jpRecent = new JPanel();
		jpRecent.setLayout(new FlowLayout());
		jpRecent.setPreferredSize(new Dimension(170, 600));
		jspRecent = new JScrollPane(jpRecent);

		jspRecent.setBorder(new TitledBorder("최근 본 상품"));

		btnLogout = new JButton();

		JLabel jlBest = new JLabel("판매 랭킹");
		///////////// full 화면
		jpContent = new JPanel();
		jpContent.setLayout(null);
		jspFull = new JScrollPane(jpContent);

		////////////// best
		jpBestGoods = new JPanel();
		jpBestGoods.setBorder(new LineBorder(Color.black));
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

		jtfSearch = new JTextField();
		btnSearch = new JButton("조회");

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
		btnSearchBrand = new JButton(new ImageIcon(ClientLoginView.USER_FILE_PATH + "/searchButton.jpg"));
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

		jttpCate.setBounds(10, 300, 200, btnAcc.getY() + 70);
		jspRecent.setBounds(10, jttpCate.getY() + jttpCate.getHeight() + 10, 200, 440);

		jpContent.add(jspRecent);
		jpContent.add(jttpCate);
		jpContent.add(btnLogout);
		jpContent.add(jpChannel);
///////////////////////////////////////////

		jpContent.add(jpBestGoods);
		jpContent.add(jlBest);

		ImageIcon mainLogoImg = new ImageIcon(ClientLoginView.USER_FILE_PATH+"/prj2_logo_back_white.PNG");
		JLabel jlMainLogo = new JLabel(mainLogoImg);
		
		jlMainLogo.setBounds(20, 10, 250, 54);
		jpContent.add(jlMainLogo);
		
		
		jlBest.setBounds(310, 230, 100, 40);
		jpBestGoods.setBounds(310, 260, 1070, 290);

		jspGoods.setBounds(310, 575, 1070, 1150);

		/////////////////////////// 이벤트 처리 ////////////////////
		UserGoodsMainEvt ugme = new UserGoodsMainEvt(this);

		btnBest.addActionListener(ugme);
		btnOuter.addActionListener(ugme);
		btnTop.addActionListener(ugme);
		btnBottom.addActionListener(ugme);
		btnDress.addActionListener(ugme);

		/////// 2019-09-22
		btnLogout.addActionListener(ugme);
		jtBrandKind.addMouseListener(ugme);
		btnAcc.addActionListener(ugme);
		btnBag.addActionListener(ugme);
		btnHeadWear.addActionListener(ugme);
		btnShoes.addActionListener(ugme);

		btnSearchBrand.addActionListener(ugme);
		jtfSearchBrand.addActionListener(ugme);
		/////////////////////////////

		btnType.addActionListener(ugme);
		btnSearch.addActionListener(ugme);
		jcbSearch.addActionListener(ugme);
		jtfSearch.addActionListener(ugme);

		btnBest.addMouseListener(ugme);
		btnOuter.addMouseListener(ugme);
		btnTop.addMouseListener(ugme);
		btnBottom.addMouseListener(ugme);
		btnDress.addMouseListener(ugme);

		/////////// 2019-09-22
		btnAcc.addMouseListener(ugme);
		btnBag.addMouseListener(ugme);
		btnHeadWear.addMouseListener(ugme);
		btnShoes.addMouseListener(ugme);
		jttpCate.addMouseListener(ugme);
		//////////////

		jtDetail.addMouseListener(ugme);

		jpContent.add(jlSelect);
		jpContent.add(jspGoods);
		jpContent.add(btnSearch);
		jpContent.add(jtfSearch);
		jpContent.add(jcbSearch);

		//////////////////////////////////////////////////////////////////////
		ImageIcon logoImg =new ImageIcon(ClientLoginView.USER_FILE_PATH+"/prj2_logo_back_gray.PNG");
		JLabel jlname = new JLabel(logoImg);
		Font f = new Font("맑은 고딕", Font.BOLD, 40);
		Font f1 = new Font("맑은 고딕", Font.PLAIN, 20);
		jlname.setFont(f);
		jlname.setForeground(Color.white);
		jlname.setBounds(15, 40, 300, 68);
		JLabel jladdr = new JLabel("서울 특별시 강남구  테헤란로 132(역삼동) 한독약품 빌딩 8층 ST쇼핑몰");
		jladdr.setBounds(500, 20, 650, 30);
		jladdr.setForeground(Color.white);
		jladdr.setFont(f1);

		JLabel jljob = new JLabel("사업자 번호 : 214-89-29543  |  대표 : 고일혁  |  개인정보처리관리책임자 : 김혜빈");
		jljob.setBounds(500, 60, 800, 30);
		jljob.setForeground(Color.white);
		jljob.setFont(f1);

		JLabel jlinfo = new JLabel("Copyright (C) 2012 ST Instituete of System Technolgy ., ALL Right Reserved");
		jlinfo.setBounds(500, 100, 800, 30);
		jlinfo.setForeground(Color.white);
		jlinfo.setFont(f1);

		JPanel jpbanner = new JPanel();
		jpbanner.setBackground(new Color(0x3F4040));
		jpbanner.setBounds(00, 50, 1600, 150);
		JPanel jpline = new JPanel();
		jpline.setBackground(Color.white);
		jpline.setBounds(330, 13, 3, 130);

		jpbanner.add(jlname);
		jpbanner.add(jpline);
		jpbanner.add(jladdr);
		jpbanner.add(jljob);
		jpbanner.add(jlinfo);
		jpbanner.setBounds(0, 1750, 1600, 300);
		jpbanner.setLayout(null);
		jpContent.add(jpbanner);
		setResizable(false);

		//////////////////////////////////////////////////////////////////////

		add(jspFull);
		if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원일때만
			mls = new MemLoginState(id);
			mls.start();

			btnOrderNe = new JButton("마이페이지");
			btnOrderNe.setBounds(115, 200, 90, 30);
			btnOrderNe.setBackground(new Color(0x043424));
			btnOrderNe.setForeground(Color.white);
			btnOrderNe.setBorder(new LineBorder(new Color(0x043424)));
			jpContent.add(btnOrderNe);
			btnOrderNe.addMouseListener(ugme);
			btnOrderNe.addActionListener(ugme);
			btnLogout.setText("로그아웃");
		} else {// 비회원
			btnLogout.setText("로그인");
		}

		btnLogout.setBounds(10, 200, 90, 30);

		btnLogout.setBackground(new Color(0x043424));
		btnLogout.setForeground(Color.white);
		btnLogout.setBorder(new LineBorder(new Color(0x043424)));
		jpBrandKind.setBackground(new Color(0x043424));

		jpRecent.setBackground(Color.white);
		btnSearch.setBackground(new Color(0x043424));
		btnSearch.setForeground(Color.white);
		jcbSearch.setBackground(new Color(0x043424));
		jcbSearch.setForeground(Color.white);

		jpContent.setBackground(Color.white);

		btnLogout.addMouseListener(ugme);

		btnType.addMouseListener(ugme);
		btnSearch.addMouseListener(ugme);

		jpBestGoods.setBackground(Color.white);
		jpGoods.setBackground(Color.white);

		setBounds(10, 0, 1600, 800);
		jspFull.setBounds(0, 0, getWidth() - 20, getHeight() - 37);// 고정 값
		jpChannel.setBounds(0, 70, jspFull.getWidth() - 15, 100);
		jpContent.setPreferredSize(new Dimension(jspFull.getWidth() - 20, jspFull.getHeight() + 1150));// 고정 값
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// HomeMainView

	public JLabel getJlChannel() {
		return jlChannel;
	}

	public JPanel getJpRecent() {
		return jpRecent;
	}

	public JScrollPane getJspRecent() {
		return jspRecent;
	}

	public JButton getBtnLogout() {
		return btnLogout;
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

	public MemLoginState getMls() {
		return mls;
	}

	public JButton getBtnOrderNe() {
		return btnOrderNe;
	}

	/////////////////////////////// 20200112 찜하기 버튼 추가
	public JButton getBtnGoodsLike() {
		return btnGoodsLike;
	}

}// UserMainView
