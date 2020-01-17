package admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import admin.controller.AdCusManageEvt;

@SuppressWarnings("serial")
public class AdCusManageView extends JPanel {
	private DefaultComboBoxModel<String> dcbmSearch;
	private JComboBox<String> jcbSearch;

	private DefaultTableModel dtmCusList;
	private JTable jtCus;
	private DefaultListModel<String> dlmCusList;
	private JList<String> cusList;
	private JButton jbtnSearch;
	private JTextField jtfSearch;
	private JScrollPane jspPro;
	private JTextField cus;

	public AdCusManageView() {
		setBackground(Color.white);
		Font titleFont = new Font("���� ���", Font.BOLD, 30);
		JLabel jlTitle = new JLabel(" ȸ������ ");
		jlTitle.setFont(titleFont);
		jlTitle.setForeground(Color.white);
		// JCOMBOBOX
		String[] searchData = { "��üȸ��", "ȸ����ȣ", "���̵�", "�̸�", "��ȭ��ȣ" };
		dcbmSearch = new DefaultComboBoxModel<String>(searchData);
		jcbSearch = new JComboBox<String>(dcbmSearch);
		// JTABLE
		String[] columnName = { "ȸ����ȣ", "���̵�", "ȸ���̸�", "��ȭ��ȣ", "���ݾ�", "������", "����ȸ������" };
		dtmCusList = new DefaultTableModel(columnName, 20) { // ����
			Class[] type = { String.class, String.class, String.class, String.class, Integer.class, Date.class,
					String.class };
		};
		jtCus = new JTable(dtmCusList) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jspPro = new JScrollPane(jtCus);
		jtCus.setRowSorter(new TableRowSorter(dtmCusList));// ����
		// JTABLE FONT
		Font tableList = new Font("���� ���", Font.PLAIN, 15);
		jtCus.setFont(tableList);

		// �� ��ġ ����
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer(); // �� ��� ������ ����
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer(); // �� ������ ������ ����
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jtCus.getColumnModel();// ������ �÷����� ������
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(1).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrCenter);
		tcm.getColumn(4).setCellRenderer(dtcrRight);
		tcm.getColumn(5).setCellRenderer(dtcrCenter);
		tcm.getColumn(6).setCellRenderer(dtcrCenter);

		// �� ũ�� ����
		jtCus.setRowHeight(35);
		jtCus.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtCus.getColumnModel().getColumn(1).setPreferredWidth(60);
		jtCus.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtCus.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtCus.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtCus.getColumnModel().getColumn(5).setPreferredWidth(140);
		jtCus.getColumnModel().getColumn(6).setPreferredWidth(50);

		 JTableHeader th = jtCus.getTableHeader(); //header���� 
		th.setPreferredSize(new Dimension(30,35)); //header ���� ���� 
		Font tfFont= new Font("���� ���", Font.BOLD, 15);
		th.setFont(tfFont);
		th.setBackground(new Color(0x352A26));
		th.setForeground(Color.white);
		jtCus.setBorder(new LineBorder( new Color(0x352A26)));
		jtCus.setSelectionBackground(new Color(0xA3A1A1));
		jtCus.setSelectionForeground(new Color(0x352A26));
		jtCus.setBackground(Color.white);

		// �������� ȸ��
		dlmCusList = new DefaultListModel<String>();
		cusList = new JList<String>(dlmCusList);
		cus = new JTextField("");
		cus.setHorizontalAlignment(JTextField.CENTER);
		cus.setFont(tfFont);
		JScrollPane jsp = new JScrollPane(cusList);

		JPanel jpCusList = new JPanel();
		jpCusList.setLayout(null);
		cus.setBounds(10, 30, 275, 50);
		jsp.setBounds(10, 90, 275, 400);
		jpCusList.add(cus);
		jpCusList.add(jsp);

		jpCusList.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		jpCusList.setBackground(new Color(0x352A26));

		jbtnSearch = new JButton("ȸ����ȸ");
		jbtnSearch.setForeground(Color.white);
		jbtnSearch.setBackground(new Color(0x352A26));
		jtfSearch = new JTextField("��ȸ�Ͻ� ������ �Է����ּ���.");

		JPanel jpLabel = new JPanel();
		jpLabel.setLayout(null);
		jlTitle.setBounds(150, 20, 200, 30);
		jcbSearch.setBounds(350, 20, 120, 30);
		jtfSearch.setBounds(490, 20, 250, 30);
		jbtnSearch.setBounds(750, 20, 100, 30);

		jpLabel.add(jlTitle);
		jpLabel.add(jcbSearch);
		jpLabel.add(jtfSearch);
		jpLabel.add(jbtnSearch);

		jpLabel.setBackground(new Color(0x352A26));
		setLayout(null);

		jspPro.setBounds(80, 120, 1100, 600);
		jpCusList.setBounds(1220, 160, 300, 500);
		jpLabel.setBorder(new LineBorder(Color.black));
		jpLabel.setBounds(-5, 0, 1700, 80);
		add(jpLabel);

		add(jspPro);
		add(jpCusList);

		// �̺�Ʈ
		AdCusManageEvt cme = new AdCusManageEvt(this);
		// �׼��̺�Ʈ
		jbtnSearch.addActionListener(cme);
		jtfSearch.addActionListener(cme);
		// ���콺�̺�Ʈ
		jtCus.addMouseListener(cme);
		jtfSearch.addMouseListener(cme);

		jtCus.getTableHeader().setReorderingAllowed(false);// �÷��̵�����
		jtCus.getTableHeader().setResizingAllowed(false);// ũ�������Ұ�
		setVisible(true);
	}//

	public DefaultComboBoxModel<String> getDcbmSearch() {
		return dcbmSearch;
	}

	public JComboBox<String> getJcbSearch() {
		return jcbSearch;
	}

	public DefaultTableModel getDtmCusList() {
		return dtmCusList;
	}

	public JTable getJtCus() {
		return jtCus;
	}

	public DefaultListModel<String> getDlmCusList() {
		return dlmCusList;
	}

	public JList<String> getCusList() {
		return cusList;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}

	public JScrollPane getJspPro() {
		return jspPro;
	}

	public JTextField getCus() {
		return cus;
	}

	
}// class
