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

	/////////// ��ȸ���� ��� /////////////
	private JButton btnLogin;

///////////// ��� ��ư /////////////////////
	private JButton btnMyPage;
	private JButton btnLogout;
	private JButton btnLogo;
///////////////// ī�װ� ��ư ////////////////
	private JButton btnBest;
	private JButton btnOuter;
	private JButton btnTop;
	private JButton btnBottom;
	private JButton btnDress;

	/////////////// ��ǰ ���� ///////////
	private JButton btnNoBrand;
	private JButton btnBrand;
	private JButton btnSearch;
	private DefaultComboBoxModel<String> dcbmSearch;
	private JComboBox<String> jcbSearch;
	private JTextField jtfSearch;

	private DefaultTableModel dtmProduct;
	private JTable jtProduct;

	private JTextArea jtaInform;

	public AdMainView(String name) {// ��ȸ���� ��� �α��� ��ư���� ����
		super("User Main");
		setLayout(null);

		btnLogo = new JButton("�ΰ� ��ư");
		btnMyPage = new JButton("����������");
		btnLogout = new JButton("�α׾ƿ�");

		btnBrand = new JButton("BRAND");
		btnNoBrand = new JButton("SOHO");
		String[] searchCombo = new String[] { "��ǰ��", "�귣���" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchCombo);
		jcbSearch = new JComboBox<String>(dcbmSearch);

		jtfSearch = new JTextField();
		btnSearch = new JButton("��ȸ");

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

		//////// ī�װ��� �гη� ����
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
