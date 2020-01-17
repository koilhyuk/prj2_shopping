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
	/////////////////////////////// 20200112 ���ϱ� ��ư �߰�
	private JButton btnGoodsLike;

	//// 2019-09-24 ���� ä��
	private JLabel jlChannel;

	/// 2019-09-23
	// ���� ��� �߰�// �ֱ� �� ��ǰ
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

//////////// ȭ�� ��ü 
	private JPanel jpContent;
	private JScrollPane jspFull;

///////////////// ī�װ� ��ư ////////////////
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
	///////////////// ��ǰ ���� ///////////
	private JButton btnType;
	private JButton btnSearch;
	private DefaultComboBoxModel<String> dcbmSearch;
	private JComboBox<String> jcbSearch;
	private JTextField jtfSearch;

	////////////// ��ǰ �󼼺��� test
	private DefaultTableModel dtmDetail;
	private JTable jtDetail;
	///// ���� �׸� ��
	private JLabel jlSelect;

	private JPanel jpBestGoods;
	private JPanel jpGoods;
	private JScrollPane jspGoods;

	public static String id;
	public static String ip;
	public static final String KEY = "9394959696959493";


	public UserGoodsMainView(String id, String ip) {// ��ȸ���� ��� �α��� ��ư���� ����
		super("\"" + id + "\" ���� �α����ϼ̽��ϴ�.");
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
		jlChannel.setFont(new Font("���� ���", Font.BOLD, 60));
		jlChannel.setOpaque(true);
		jlChannel.setForeground(Color.white);
		jlChannel.setBackground(new Color(0x352A26));
		jlChannel.setBounds(722, 5, 240, 80);

		btnType = new JButton("BRAND ����");
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

		jspRecent.setBorder(new TitledBorder("�ֱ� �� ��ǰ"));

		btnLogout = new JButton();

		JLabel jlBest = new JLabel("�Ǹ� ��ŷ");
		///////////// full ȭ��
		jpContent = new JPanel();
		jpContent.setLayout(null);
		jspFull = new JScrollPane(jpContent);

		////////////// best
		jpBestGoods = new JPanel();
		jpBestGoods.setBorder(new LineBorder(Color.black));
		jpBestGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpBestGoods.setBackground(Color.white);

		///////////// ��ǰ ����
		jpGoods = new JPanel();
		jpGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpGoods.setBackground(Color.white);
		jspGoods = new JScrollPane(jpGoods);

		jlSelect = new JLabel("");// ���� �׸� ��

		String[] searchCombo = new String[] { "��ǰ��", "�귣���" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchCombo);
		jcbSearch = new JComboBox<String>(dcbmSearch);

		jtfSearch = new JTextField();
		btnSearch = new JButton("��ȸ");

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
		jttpCate.addTab("ǰ��", jpCloBtn);
		jttpCate.addTab("�귣��", jpBrandKind);

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

		/////////////////////////// �̺�Ʈ ó�� ////////////////////
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
		Font f = new Font("���� ���", Font.BOLD, 40);
		Font f1 = new Font("���� ���", Font.PLAIN, 20);
		jlname.setFont(f);
		jlname.setForeground(Color.white);
		jlname.setBounds(15, 40, 300, 68);
		JLabel jladdr = new JLabel("���� Ư���� ������  ������� 132(���ﵿ) �ѵ���ǰ ���� 8�� ST���θ�");
		jladdr.setBounds(500, 20, 650, 30);
		jladdr.setForeground(Color.white);
		jladdr.setFont(f1);

		JLabel jljob = new JLabel("����� ��ȣ : 214-89-29543  |  ��ǥ : ������  |  ��������ó������å���� : ������");
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
		if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// ȸ���϶���
			mls = new MemLoginState(id);
			mls.start();

			btnOrderNe = new JButton("����������");
			btnOrderNe.setBounds(115, 200, 90, 30);
			btnOrderNe.setBackground(new Color(0x043424));
			btnOrderNe.setForeground(Color.white);
			btnOrderNe.setBorder(new LineBorder(new Color(0x043424)));
			jpContent.add(btnOrderNe);
			btnOrderNe.addMouseListener(ugme);
			btnOrderNe.addActionListener(ugme);
			btnLogout.setText("�α׾ƿ�");
		} else {// ��ȸ��
			btnLogout.setText("�α���");
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
		jspFull.setBounds(0, 0, getWidth() - 20, getHeight() - 37);// ���� ��
		jpChannel.setBounds(0, 70, jspFull.getWidth() - 15, 100);
		jpContent.setPreferredSize(new Dimension(jspFull.getWidth() - 20, jspFull.getHeight() + 1150));// ���� ��
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

	/////////////////////////////// 20200112 ���ϱ� ��ư �߰�
	public JButton getBtnGoodsLike() {
		return btnGoodsLike;
	}

}// UserMainView
