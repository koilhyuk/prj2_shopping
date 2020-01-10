package user.newtest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

@SuppressWarnings("serial")
public class UserMyOrderView extends JFrame {

	private DefaultTableModel dtmOrderList;
	private JTable jtOrder;
	private JScrollPane jspOrder;
	private JTableHeader th;
	private JButton jbtBack;

	private static String id;
	public UserMyOrderView(String id) {
		this.id=id;
		setBackground(Color.white);

		JLabel jlTitle = new JLabel("'"+id+"'������ �ֹ�����");
		jlTitle.setForeground(Color.white);
		jbtBack= new JButton("������������ ����");
		// ��Ʈ
		Font font = new Font("���� ���", Font.BOLD, 20); // Ÿ��Ʋ��Ʈ
		Font fontHh = new Font("���� ���", Font.BOLD, 15); // ���̺����
		jlTitle.setFont(font);
		// ��ȣ�߰�
		String[] columnName = { "��ǰ��", "�귣��", "��ۿ���", "����", "�ֹ���" };

		dtmOrderList = new DefaultTableModel(columnName, 20) ;
//			// ���ڴ� ���ڷ� �����ϱ� ���� ó�� 2019-09-12 15:35
//			Class[] types = { String.class, String.class, String.class, Integer.class, Integer.class, Integer.class,
//					Integer.class, Date.class };
//
//			@Override
//			public Class getColumnClass(int columnIndex) { // ����
//				return this.types[columnIndex];
//			}
//		};

		jtOrder = new JTable(dtmOrderList); //{
//			@Override
//			public Class<?> getColumnClass(int column) { // �̹���
//				return getValueAt(0, column).getClass();
//			}// getColumnClass
//
//			@Override
//			public boolean isCellEditable(int row, int column) {// �������ϰ� ����
//				return false;
//			}// isCellEditable
//		};

		th = jtOrder.getTableHeader(); // header����
		th.setFont(fontHh); // header ��Ʈ ����
		th.setBackground(new Color(0x3F4040));
		th.setForeground(Color.white);
		jtOrder.setBorder(new LineBorder(new Color(0x3F4040)));
		jtOrder.setSelectionForeground(new Color(0x3F4040));
		jtOrder.setBackground(Color.white);
		Font tableList = new Font("���� ���", Font.PLAIN, 15);
		jtOrder.setFont(tableList);

		// ��� ����
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // �� ��� ������ ����
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // �� ������ ������ ����
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtOrder.getColumnModel();// ������ �÷����� ������
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrRight);
		tcm.getColumn(4).setCellRenderer(dtcrRight);

		jspOrder = new JScrollPane(jtOrder); // ��ũ��
		th.setPreferredSize(new Dimension(20, 30)); // header ���� ����
		jtOrder.setRowHeight(60);
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(110);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(50);

		JPanel jpLabel = new JPanel();
		jpLabel.setLayout(null);
		add(jlTitle);
		add(jbtBack);
		jlTitle.setBounds(150, 20, 400, 50);
		jbtBack.setBounds(900, 30, 200, 30);
		jpLabel.setBackground(new Color(0x3F4040));
		jpLabel.setBorder(new LineBorder(Color.black));
		jpLabel.setBounds(-5, 0, 1700, 80);
		
		setLayout(null); // ������ġ

		jspOrder.setBounds(80, 120, 1000, 500);
		add(jspOrder);
		add(jpLabel);
//		jtOrder.setRowSorter(new TableRowSorter(dtmOrderList)); ////////// �� ���� (��������, ��������)/////////09-11�߰�
		jtOrder.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> table = new TableRowSorter<TableModel>(jtOrder.getModel());
		jtOrder.setRowSorter(table);

		UserMyOrderEvt umo= new UserMyOrderEvt(this,id);
//		// action�̺�Ʈ ó��
		jbtBack.addActionListener(umo);
//		// ���콺 �̺�Ʈ ó��
//		jtOrder.addMouseListener(ple);
//		jtfSearch.addMouseListener(ple);

		jtOrder.getTableHeader().setReorderingAllowed(false);// �÷��̵�����
		jtOrder.getTableHeader().setResizingAllowed(false);// ũ�������Ұ�

		setVisible(true);
		setBounds(100, 100, 1200, 700);

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


	public JButton getJbtBack() {
		return jbtBack;
	}


	public static String getId() {
		return id;
	}


	public static void main(String[] args) {
		new UserMyOrderView("hyebin");
	}

}// class
