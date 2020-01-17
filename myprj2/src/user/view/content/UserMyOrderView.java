package user.view.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import user.controller.content.UserGoodsMainEvt;
import user.controller.content.UserMyOrderEvt;

@SuppressWarnings("serial")
public class UserMyOrderView extends JDialog {

	private DefaultTableModel dtmOrderList;
	private JTable jtOrder;
	private JScrollPane jspOrder;
	private JTableHeader th;
	private JButton jbtClose;

	public UserMyOrderView(String id) {
		super(UserGoodsMainEvt.ugmv, id + "님의 주문내역", false);

		JLabel jlTitle = new JLabel("▒  '" + id + "'회원님의 주문내역  ▒");
		jlTitle.setForeground(Color.white);
		// 폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 20); // 타이틀폰트
		Font fontHh = new Font("맑은 고딕", Font.BOLD, 15); // 테이블헤더
		jlTitle.setFont(font);

		jbtClose = new JButton("닫기");
		// 번호추가	
		String[] columnName = { "상품명", "브랜드", "배송여부", "가격", "주문일" };

		dtmOrderList = new DefaultTableModel(columnName, 20) {
//			// 숫자는 숫자로 정렬하기 위한 처리 2019-09-12 15:35
			Class[] types = { String.class, String.class, String.class, Integer.class, Date.class };

			@Override
			public Class getColumnClass(int columnIndex) { // 정렬
				return this.types[columnIndex];
			}
		};

		jtOrder = new JTable(dtmOrderList) {

			@Override
			public boolean isCellEditable(int row, int column) {// 편집못하게 막음
				return false;
			}// isCellEditable
		};

		th = jtOrder.getTableHeader(); // header설정
		th.setFont(fontHh); // header 폰트 변경
		th.setBackground(new Color(0x352A26));
		th.setForeground(Color.white);
		jtOrder.setBorder(new LineBorder(Color.black));
		jtOrder.setSelectionForeground(Color.black);
		jtOrder.setBackground(Color.white);
		Font tableList = new Font("맑은 고딕", Font.BOLD, 13);
		jtOrder.setFont(tableList);

		// 헤더 정렬
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // 셀 가운데 정렬을 위해
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // 셀 오른쪽 정렬을 위해
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtOrder.getColumnModel();// 정렬할 컬럼모델을 가져옴
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(1).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrCenter);
		tcm.getColumn(4).setCellRenderer(dtcrCenter);

		jspOrder = new JScrollPane(jtOrder); // 스크롤
		th.setPreferredSize(new Dimension(20, 30)); // header 높이 변경
		jtOrder.setRowHeight(60);
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(170);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(70);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(70);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(50);

		JPanel jpLabel = new JPanel();
		jpLabel.setLayout(null);
		add(jlTitle);
		jlTitle.setBounds(80, 20, 400, 50);
		jpLabel.setBackground(new Color(0x352A26));
		jpLabel.setBorder(new LineBorder(Color.black));
		jpLabel.setBounds(-5, 0, 1000, 80);

		setLayout(null); // 수동배치

		jspOrder.setBounds(30, 120, 800, 380);
		jbtClose.setBounds(360, 510, 100, 30);

		jbtClose.setForeground(Color.white);
		jbtClose.setBackground(new Color(0x043424));

		add(jbtClose);
		add(jspOrder);
		add(jpLabel);
//		jtOrder.setRowSorter(new TableRowSorter(dtmOrderList)); ////////// 열 정렬 (오름차순, 내림차순)/////////09-11추가
		jtOrder.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> table = new TableRowSorter<TableModel>(jtOrder.getModel());
		jtOrder.setRowSorter(table);

		UserMyOrderEvt umo = new UserMyOrderEvt(this, id);
		// action이벤트 처리

		// 마우스 이벤트 처리
		jtOrder.addMouseListener(umo);
		jbtClose.addActionListener(umo);

		jtOrder.getTableHeader().setReorderingAllowed(false);// 컬럼이동방지
		jtOrder.getTableHeader().setResizingAllowed(false);// 크기조절불가
		this.getContentPane().setBackground(Color.white);

		setVisible(true);
		setResizable(false);

		setBounds(100, 100, 900, 590);

	}// AdGoodsManageView

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}

	public JTable getJtOrder() {
		return jtOrder;
	}

	public JScrollPane getJspOrder() {
		return jspOrder;
	}

	public JTableHeader getTh() {
		return th;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

}// class
