package user.view.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import user.controller.content.UserGoodsMainEvt;
import user.controller.content.UserMyOrderEvt;

@SuppressWarnings("serial")
public class UserMyOrderView extends JDialog {

	private DefaultTableModel dtmOrderList;
	private JTable jtOrder;
	private JScrollPane jspOrder;
	private JTableHeader th;

	private static String id;
	public UserMyOrderView(String id) {
		super(UserGoodsMainEvt.ugmv,id+"���� �ֹ�����",false);
		this.id=id;

		JLabel jlTitle = new JLabel("��  '"+id+"'ȸ������ �ֹ�����  ��");
		jlTitle.setForeground(Color.white);
		// ��Ʈ
		Font font = new Font("���� ���", Font.BOLD, 20); // Ÿ��Ʋ��Ʈ
		Font fontHh = new Font("���� ���", Font.BOLD, 15); // ���̺����
		jlTitle.setFont(font);
		// ��ȣ�߰�
		String[] columnName = { "��ǰ��", "�귣��", "��ۿ���", "����", "�ֹ���" };

		dtmOrderList = new DefaultTableModel(columnName, 20) {
//			// ���ڴ� ���ڷ� �����ϱ� ���� ó�� 2019-09-12 15:35
			Class[] types = { String.class, String.class, String.class,Integer.class, Date.class };

			@Override
			public Class getColumnClass(int columnIndex) { // ����
				return this.types[columnIndex];
			}
		};

		jtOrder = new JTable(dtmOrderList){

			@Override
			public boolean isCellEditable(int row, int column) {// �������ϰ� ����
				return false;
			}// isCellEditable
		};

		th = jtOrder.getTableHeader(); // header����
		th.setFont(fontHh); // header ��Ʈ ����
		th.setBackground(new Color(0x3F4040));
		th.setForeground(Color.white);
		jtOrder.setBorder(new LineBorder(new Color(0x3F4040)));
		jtOrder.setSelectionForeground(new Color(0x3F4040));
		jtOrder.setBackground(Color.white);
		Font tableList = new Font("���� ���", Font.BOLD, 13);
		jtOrder.setFont(tableList);

		// ��� ����
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // �� ��� ������ ����
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // �� ������ ������ ����
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtOrder.getColumnModel();// ������ �÷����� ������
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(1).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrCenter);
		tcm.getColumn(4).setCellRenderer(dtcrCenter);

		jspOrder = new JScrollPane(jtOrder); // ��ũ��
		th.setPreferredSize(new Dimension(20, 30)); // header ���� ����
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
		jpLabel.setBackground(new Color(0x3F4040));
		jpLabel.setBorder(new LineBorder(Color.black));
		jpLabel.setBounds(-5, 0, 1000, 80);
		
		setLayout(null); // ������ġ

		jspOrder.setBounds(30, 120, 800, 380);
		add(jspOrder);
		add(jpLabel);
//		jtOrder.setRowSorter(new TableRowSorter(dtmOrderList)); ////////// �� ���� (��������, ��������)/////////09-11�߰�
		jtOrder.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> table = new TableRowSorter<TableModel>(jtOrder.getModel());
		jtOrder.setRowSorter(table);

		UserMyOrderEvt umo= new UserMyOrderEvt(this,id);
		// action�̺�Ʈ ó��
		
		// ���콺 �̺�Ʈ ó��
		jtOrder.addMouseListener(umo);

		jtOrder.getTableHeader().setReorderingAllowed(false);// �÷��̵�����
		jtOrder.getTableHeader().setResizingAllowed(false);// ũ�������Ұ�
		this.getContentPane().setBackground(Color.white);

		setVisible(true);
		setResizable(false);

		setBounds(100, 100, 900, 580);

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


	public static String getId() {
		return id;
	}

}// class
