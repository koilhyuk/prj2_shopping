package admin.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdMainView extends JFrame implements ActionListener {

	/////////// 비회원일 경우 /////////////
	private JButton btnLogin;

///////////// 상단 버튼 /////////////////////
	private JButton btnMyPage;
	private JButton btnLogout;
	private JButton btnLogo;
///////////////// 카테고리 버튼 ////////////////
	private JButton btnBest;
	private JButton btnOuter;
	private JButton btnTop;
	private JButton btnBottom;
	private JButton btnDress;

	/////////////// 제품 보기 ///////////
	private JButton btnNoBrand;
	private JButton btnBrand;
	private JButton btnSearch;
	private DefaultComboBoxModel<String> dcbmSearch;
	private JComboBox<String> jcbSearch;
	private JTextField jtfSearch;

	private DefaultTableModel dtmProduct;
	private JTable jtProduct;

	private JTextArea jtaInform;

	public AdMainView(String name) {// 비회원일 경우 로그인 버튼으로 변경
		super("User Main");
		setLayout(null);

		btnLogo = new JButton("로고 버튼");
		btnMyPage = new JButton("마이페이지");
		btnLogout = new JButton("로그아웃");

		btnBrand = new JButton("BRAND");
		btnNoBrand = new JButton("SOHO");
		String[] searchCombo = new String[] { "상품명", "브랜드명" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchCombo);
		jcbSearch = new JComboBox<String>(dcbmSearch);

		jtfSearch = new JTextField();
		btnSearch = new JButton("조회");

		JPanel jp = new JPanel();
		JButton jb = new JButton("gkgk");

		jb.setBounds(0, 0, 40, 40);
		jp.add(jb);

		JPanel[] jpTable = { jp };
		dtmProduct = new DefaultTableModel(jpTable, 0);
		jtProduct = new JTable(dtmProduct) {
			@Override
			public java.lang.Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		dtmProduct.addColumn(jp);

		JScrollPane jspProdcut = new JScrollPane(jtProduct);
		
		jtaInform= new JTextArea() {
			
		};

		//////// 카테고리는 패널로 묶음
		JPanel catePanel = new JPanel();
		catePanel.setLayout(null);

		btnBest = new JButton("Best");
		btnOuter = new JButton("Outer");
		btnTop = new JButton("Top");
		btnBottom = new JButton("Bottom");
		btnDress = new JButton("Dress");

		btnLogo.setBounds(10, 10, 100, 40);
		btnMyPage.setBounds(1150, 10, 100, 40);
		btnLogout.setBounds(1270, 10, 100, 40);
		btnBrand.setBounds(500, 120, 120, 50);
		btnNoBrand.setBounds(750, 120, 120, 50);
		jcbSearch.setBounds(350, 200, 120, 30);
		jtfSearch.setBounds(500, 200, 300, 30);
		btnSearch.setBounds(830, 200, 100, 30);
		jspProdcut.setBounds(350, 250, 930, 400);

		btnBest.setBounds(0, 0, 120, 40);
		btnOuter.setBounds(0, 40, 120, 40);
		btnTop.setBounds(0, 80, 120, 40);
		btnBottom.setBounds(0, 120, 120, 40);
		btnDress.setBounds(0, 160, 120, 40);

		catePanel.add(btnBest);
		catePanel.add(btnOuter);
		catePanel.add(btnTop);
		catePanel.add(btnBottom);
		catePanel.add(btnDress);

		catePanel.setBounds(10, 200, 120, 200);

		add(jspProdcut);
		add(btnSearch);
		add(jtfSearch);
		add(jcbSearch);
		add(btnBrand);
		add(btnNoBrand);
		add(catePanel);
		add(btnLogo);
		add(btnMyPage);
		add(btnLogout);

		setResizable(false);
		setBounds(100, 50, 1400, 800);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}// UserMainView
