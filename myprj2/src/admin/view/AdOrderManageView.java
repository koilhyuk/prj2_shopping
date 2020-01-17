
package admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import admin.controller.AdOrderManageEvt;

@SuppressWarnings("serial")
public class AdOrderManageView extends JPanel {

	private DefaultComboBoxModel<String> dbcmSearch;
	private JComboBox<String> jcbSearch;
	private JTextField jtfSearch;
	private JButton jbtnSearch, jbtnCash;
	private DefaultTableModel dtmOrder;
	private JTable jtOrder;
	private JTableHeader th;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

	private JPopupMenu jpm;
	private JMenuItem jmDelete, jmDelivery;

	public AdOrderManageView() {
		setBackground(Color.white);
		// ��Ʈ
		Font font = new Font("���� ���", Font.BOLD, 30);
		JLabel jlTitle = new JLabel(" �ֹ�����  ");
		jlTitle.setFont(font);
		jlTitle.setForeground(Color.white);

		String[] searchArr = { "��ü�ֹ�", "�ֹ� ��ȣ", "ȸ�����̵�", "�޴���", "��ǰ��", "�ֹ�����" };
		dbcmSearch = new DefaultComboBoxModel<String>(searchArr);
		jcbSearch = new JComboBox<String>(dbcmSearch);

		jtfSearch = new JTextField("��ȸ�Ͻ� ������ �Է����ּ���.");
		jbtnSearch = new JButton("�ֹ���ȸ");
		jbtnCash = new JButton("����");

		String[] orderArr = { "�ֹ���ȣ", "ȸ�����̵�(�޴���)", "��ȭ��ȣ", "��ǰ��/�ɼ�", "��ǰ���űݾ�", "�Ǹż���", "��ۿ���", "�ֹ�����" };
		dtmOrder = new DefaultTableModel(orderArr, 0) {
			Class[] type = { String.class, String.class, String.class, String.class, Integer.class, Integer.class,
					String.class, Date.class };

			@Override
			public Class<?> getColumnClass(int column) {
				return this.type[column];
			}

		};
		jtOrder = new JTable(dtmOrder) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane jspOrder = new JScrollPane(jtOrder);

		jspOrder.getVerticalScrollBar().setBackground(Color.white);

//		jspOrder.getVerticalScrollBar().setUI(new BasicScrollBarUI()
//        {
//            @Override
//            protected void configureScrollBarColors()
//            {
//                this.thumbColor = new Color(0x352A26);
//            }
//            
//        });
		Font tableList = new Font("���� ���", Font.PLAIN, 15);
		jtOrder.setFont(tableList);

		Font fontHh = new Font("���� ���", Font.BOLD, 15); // ���̺����
		th = jtOrder.getTableHeader(); // header����
		th.setFont(fontHh); // header ��Ʈ ����
		th.setBackground(new Color(0x352A26));
		th.setForeground(Color.white);
		jtOrder.setBorder(new LineBorder(new Color(0x352A26)));
		jtOrder.setSelectionForeground(new Color(0x352A26));
		jtOrder.setBackground(Color.white);

		th.setPreferredSize(new Dimension(30, 35)); // header ���� ����
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // �� ��� ������ ����
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // �� ������ ������ ����
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtOrder.getColumnModel();// ������ �÷����� ������
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(1).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrCenter);
		tcm.getColumn(4).setCellRenderer(dtcrRight);
		tcm.getColumn(5).setCellRenderer(dtcrRight);
		tcm.getColumn(6).setCellRenderer(dtcrRight);
		tcm.getColumn(7).setCellRenderer(dtcrCenter);

		jtOrder.setRowHeight(35);
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(5).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(6).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(7).setPreferredWidth(100);

		// JPopMenu
		jpm = new JPopupMenu();
		// �˾��޴��� �� �޴� ������ ����
		jmDelivery = new JMenuItem("��ۻ��� ����");
		jmDelete = new JMenuItem("�ֹ����� ����");

		jpm.add(jmDelivery);
		jpm.add(jmDelete);

		jtOrder.setComponentPopupMenu(jpm);

		JPanel jpLabel = new JPanel();
		jpLabel.setLayout(null);
		add(jlTitle);
		add(jcbSearch);
		add(jtfSearch);
		add(jbtnSearch);
		add(jbtnCash);
		jlTitle.setBounds(150, 20, 200, 30);
		jcbSearch.setBounds(350, 20, 120, 30);
		jtfSearch.setBounds(490, 20, 250, 30);
		jbtnSearch.setBounds(750, 20, 100, 30);
		jbtnCash.setBounds(1000, 20, 100, 30);
		jpLabel.setBackground(new Color(0x352A26));
		jpLabel.setBorder(new LineBorder(Color.black));
		jpLabel.setBounds(-5, 0, 1700, 80);

		jbtnSearch.setForeground(Color.white);
		jbtnSearch.setBackground(new Color(0x352A26));
		jbtnCash.setForeground(Color.white);
		jbtnCash.setBackground(new Color(0x352A26));

		setLayout(null);
		jspOrder.setBounds(80, 120, 1330, 600);

		add(jspOrder);
		add(jpLabel);

		jtOrder.setRowSorter(new TableRowSorter(dtmOrder));
		jtOrder.getTableHeader().setReorderingAllowed(false);// �÷��̵�����
		jtOrder.getTableHeader().setResizingAllowed(false);// ũ�������Ұ�

		AdOrderManageEvt ole = new AdOrderManageEvt(this);
		jbtnSearch.addActionListener(ole);
		jcbSearch.addActionListener(ole);
		jmDelivery.addActionListener(ole);
		jmDelete.addActionListener(ole);
		jbtnCash.addActionListener(ole);
		jtfSearch.addActionListener(ole);

		jtfSearch.addMouseListener(ole);
		jtfSearch.addMouseListener(ole);
		jtOrder.addMouseListener(ole);
		setVisible(true);

	}// OrderView

	public DefaultComboBoxModel<String> getDbcmSearch() {
		return dbcmSearch;
	}

	public JComboBox<String> getJcbSearch() {
		return jcbSearch;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}

	public DefaultTableModel getDtmOrder() {
		return dtmOrder;
	}

	public JTable getJtOrder() {
		return jtOrder;
	}

	public JTableHeader getTh() {
		return th;
	}

	public JPopupMenu getJpm() {
		return jpm;
	}

	public JMenuItem getJmDelete() {
		return jmDelete;
	}

	public JMenuItem getJmDelivery() {
		return jmDelivery;
	}

	public JButton getJbtnCash() {
		return jbtnCash;
	}

}// class
