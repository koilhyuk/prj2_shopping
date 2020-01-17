
package admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import admin.controller.AdGoodsManageEvt;
import admin.run.StaticCla;

/**
 * 상품관리 View
 * 
 * @author hyebin
 */
@SuppressWarnings("serial")
public class AdGoodsManageView extends JPanel {
	private DefaultComboBoxModel<String> dcbmSearch;
	private JComboBox<String> jcbSearch;

	private DefaultTableModel dtmGoodsList;
	private JTable jtGoods;
	private JButton jbtnUpload, jbtnSearch, jbtnCate;
	private JTextField jtfSearch;
	private JScrollPane jspGoods;
	private JTableHeader th;

	private JPopupMenu jpmGoods;
	private JMenuItem jmDelete;

	public AdGoodsManageView() {
		setBackground(Color.white);
		JTabbedPane jtap = StaticCla.mv.getJtpMain();
		jtap.setBackground(Color.WHITE);

		JLabel jlTitle = new JLabel(" 상품관리  ");
		jlTitle.setForeground(Color.white);
		// 폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 30); // 타이틀폰트
		Font fontHh = new Font("맑은 고딕", Font.BOLD, 15); // 테이블헤더
		jlTitle.setFont(font);

		// 조회
		String[] searchData = { "상품전체", "상품코드", "브랜드", "상품명", "평점" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchData);
		jcbSearch = new JComboBox<String>(dcbmSearch);

		// 번호추가
		String[] columnName = { "상품명(상품코드)", "브랜드", "상품타입", "상품금액", "판매수량", "평점", "재고량", "입고날짜" };

		dtmGoodsList = new DefaultTableModel(columnName, 20) {
			// 숫자는 숫자로 정렬하기 위한 처리 2019-09-12 15:35
			Class[] types = { String.class, String.class, String.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Date.class };

			@Override
			public Class getColumnClass(int columnIndex) { // 정렬
				return this.types[columnIndex];
			}
		};

		jtGoods = new JTable(dtmGoodsList) {
			@Override
			public Class<?> getColumnClass(int column) { // 이미지
				return getValueAt(0, column).getClass();
			}// getColumnClass

			@Override
			public boolean isCellEditable(int row, int column) {// 편집못하게 막음
				return false;
			}// isCellEditable
		};

		th = jtGoods.getTableHeader(); // header설정
		th.setFont(fontHh); // header 폰트 변경
		th.setBackground(new Color(0x352A26));
		th.setForeground(Color.white);
		jtGoods.setBorder(new LineBorder(new Color(0x352A26)));
		jtGoods.setSelectionForeground(new Color(0x352A26));
		jtGoods.setBackground(Color.white);
		Font tableList = new Font("맑은 고딕", Font.PLAIN, 15);
		jtGoods.setFont(tableList);

		// 헤더 정렬
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // 셀 가운데 정렬을 위해
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // 셀 오른쪽 정렬을 위해
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtGoods.getColumnModel();// 정렬할 컬럼모델을 가져옴
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrRight);
		tcm.getColumn(4).setCellRenderer(dtcrRight);
		tcm.getColumn(5).setCellRenderer(dtcrRight);
		tcm.getColumn(6).setCellRenderer(dtcrRight);
		tcm.getColumn(7).setCellRenderer(dtcrCenter);

		jspGoods = new JScrollPane(jtGoods); // 스크롤

		jbtnUpload = new JButton("상품등록");
		jbtnSearch = new JButton("상품조회");
		jbtnCate = new JButton("브랜드 및 카테고리 관리");
		jtfSearch = new JTextField("조회하실 내용을 입력해주세요.");

		th.setPreferredSize(new Dimension(20, 30)); // header 높이 변경
		jtGoods.setRowHeight(150);
		jtGoods.getColumnModel().getColumn(0).setPreferredWidth(110);
		jtGoods.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtGoods.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtGoods.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtGoods.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtGoods.getColumnModel().getColumn(5).setPreferredWidth(50);
		jtGoods.getColumnModel().getColumn(6).setPreferredWidth(50);
		jtGoods.getColumnModel().getColumn(7).setPreferredWidth(100);

		JPanel jpLabel = new JPanel();
		jpLabel.setLayout(null);
		add(jlTitle);
		add(jcbSearch);
		add(jtfSearch);
		add(jbtnSearch);
		jlTitle.setBounds(150, 20, 200, 30);
		jcbSearch.setBounds(350, 20, 120, 30);
		jtfSearch.setBounds(490, 20, 250, 30);
		jbtnSearch.setBounds(750, 20, 100, 30);
		jpLabel.setBackground(new Color(0x352A26));
		jpLabel.setBorder(new LineBorder(Color.black));
		jpLabel.setBounds(-5, 0, 1700, 80);
		
		jbtnSearch.setForeground(Color.white);
		jbtnSearch.setBackground(new Color(0x352A26));
		jbtnCate.setForeground(Color.white);
		jbtnCate.setBackground(new Color(0x352A26));
		jbtnUpload.setForeground(Color.white);
		jbtnUpload.setBackground(new Color(0x352A26));
		

		setLayout(null); // 수동배치

		jbtnCate.setBounds(1000, 20, 200, 30);
		jbtnUpload.setBounds(880, 20, 100, 30);

		jspGoods.setBounds(80, 120, 1330, 600);
		jpmGoods = new JPopupMenu();
		jmDelete = new JMenuItem("상품 삭제");

		jpmGoods.add(jmDelete);
		jtGoods.setComponentPopupMenu(jpmGoods); // 팝업컴포넌트 추가
		add(jspGoods);
		add(jbtnUpload);
		add(jbtnCate);
		add(jpLabel);
		////////////////////////////////// 정렬
		////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
//		jtGoods.setRowSorter(new TableRowSorter(dtmGoodsList)); ////////// 열 정렬 (오름차순, 내림차순)/////////09-11추가
		jtGoods.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> table = new TableRowSorter<TableModel>(jtGoods.getModel());
		jtGoods.setRowSorter(table);

		AdGoodsManageEvt ple = new AdGoodsManageEvt(this);
		// action이벤트 처리
		jbtnSearch.addActionListener(ple);
		jbtnUpload.addActionListener(ple);
		jmDelete.addActionListener(ple);
		jtfSearch.addActionListener(ple);
		jbtnCate.addActionListener(ple);

		// 마우스 이벤트 처리
		jtGoods.addMouseListener(ple);
		jtfSearch.addMouseListener(ple);

		jtGoods.getTableHeader().setReorderingAllowed(false);// 컬럼이동방지
		jtGoods.getTableHeader().setResizingAllowed(false);// 크기조절불가

		setVisible(true);

	}// AdGoodsManageView

	public DefaultComboBoxModel<String> getDcbmSearch() {
		return dcbmSearch;
	}

	public JComboBox<String> getJcbSearch() {
		return jcbSearch;
	}

	public DefaultTableModel getDtmGoodsList() {
		return dtmGoodsList;
	}

	public JTable getJtGoods() {
		return jtGoods;
	}

	public JButton getJbtnUpload() {
		return jbtnUpload;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}

	public JButton getJbtnCate() {
		return jbtnCate;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}

	public JScrollPane getJspGoods() {
		return jspGoods;
	}

	public JTableHeader getTh() {
		return th;
	}

	public JPopupMenu getJpmGoods() {
		return jpmGoods;
	}

	public JMenuItem getJmDelete() {
		return jmDelete;
	}

}// class
