
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
 * ��ǰ���� View
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

		JLabel jlTitle = new JLabel(" ��ǰ����  ");
		jlTitle.setForeground(Color.white);
		// ��Ʈ
		Font font = new Font("���� ���", Font.BOLD, 30); // Ÿ��Ʋ��Ʈ
		Font fontHh = new Font("���� ���", Font.BOLD, 15); // ���̺����
		jlTitle.setFont(font);

		// ��ȸ
		String[] searchData = { "��ǰ��ü", "��ǰ�ڵ�", "�귣��", "��ǰ��", "����" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchData);
		jcbSearch = new JComboBox<String>(dcbmSearch);

		// ��ȣ�߰�
		String[] columnName = { "��ǰ��(��ǰ�ڵ�)", "�귣��", "��ǰŸ��", "��ǰ�ݾ�", "�Ǹż���", "����", "���", "�԰�¥" };

		dtmGoodsList = new DefaultTableModel(columnName, 20) {
			// ���ڴ� ���ڷ� �����ϱ� ���� ó�� 2019-09-12 15:35
			Class[] types = { String.class, String.class, String.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Date.class };

			@Override
			public Class getColumnClass(int columnIndex) { // ����
				return this.types[columnIndex];
			}
		};

		jtGoods = new JTable(dtmGoodsList) {
			@Override
			public Class<?> getColumnClass(int column) { // �̹���
				return getValueAt(0, column).getClass();
			}// getColumnClass

			@Override
			public boolean isCellEditable(int row, int column) {// �������ϰ� ����
				return false;
			}// isCellEditable
		};

		th = jtGoods.getTableHeader(); // header����
		th.setFont(fontHh); // header ��Ʈ ����
		th.setBackground(new Color(0x352A26));
		th.setForeground(Color.white);
		jtGoods.setBorder(new LineBorder(new Color(0x352A26)));
		jtGoods.setSelectionForeground(new Color(0x352A26));
		jtGoods.setBackground(Color.white);
		Font tableList = new Font("���� ���", Font.PLAIN, 15);
		jtGoods.setFont(tableList);

		// ��� ����
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // �� ��� ������ ����
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // �� ������ ������ ����
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtGoods.getColumnModel();// ������ �÷����� ������
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrRight);
		tcm.getColumn(4).setCellRenderer(dtcrRight);
		tcm.getColumn(5).setCellRenderer(dtcrRight);
		tcm.getColumn(6).setCellRenderer(dtcrRight);
		tcm.getColumn(7).setCellRenderer(dtcrCenter);

		jspGoods = new JScrollPane(jtGoods); // ��ũ��

		jbtnUpload = new JButton("��ǰ���");
		jbtnSearch = new JButton("��ǰ��ȸ");
		jbtnCate = new JButton("�귣�� �� ī�װ� ����");
		jtfSearch = new JTextField("��ȸ�Ͻ� ������ �Է����ּ���.");

		th.setPreferredSize(new Dimension(20, 30)); // header ���� ����
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
		

		setLayout(null); // ������ġ

		jbtnCate.setBounds(1000, 20, 200, 30);
		jbtnUpload.setBounds(880, 20, 100, 30);

		jspGoods.setBounds(80, 120, 1330, 600);
		jpmGoods = new JPopupMenu();
		jmDelete = new JMenuItem("��ǰ ����");

		jpmGoods.add(jmDelete);
		jtGoods.setComponentPopupMenu(jpmGoods); // �˾�������Ʈ �߰�
		add(jspGoods);
		add(jbtnUpload);
		add(jbtnCate);
		add(jpLabel);
		////////////////////////////////// ����
		////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
//		jtGoods.setRowSorter(new TableRowSorter(dtmGoodsList)); ////////// �� ���� (��������, ��������)/////////09-11�߰�
		jtGoods.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> table = new TableRowSorter<TableModel>(jtGoods.getModel());
		jtGoods.setRowSorter(table);

		AdGoodsManageEvt ple = new AdGoodsManageEvt(this);
		// action�̺�Ʈ ó��
		jbtnSearch.addActionListener(ple);
		jbtnUpload.addActionListener(ple);
		jmDelete.addActionListener(ple);
		jtfSearch.addActionListener(ple);
		jbtnCate.addActionListener(ple);

		// ���콺 �̺�Ʈ ó��
		jtGoods.addMouseListener(ple);
		jtfSearch.addMouseListener(ple);

		jtGoods.getTableHeader().setReorderingAllowed(false);// �÷��̵�����
		jtGoods.getTableHeader().setResizingAllowed(false);// ũ�������Ұ�

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
