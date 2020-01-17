package user.view.content;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import user.controller.content.ZipcodeSearchOrderEvt;

//
@SuppressWarnings("serial")
public class ZipcodeSearchOrderView extends JDialog {
	private JTextField jtfSearch;
	private JButton jbtnSearch;
	private DefaultTableModel dtmZipcode;
	private JTable jtZipcode;
	private JScrollPane jspTable;

	private DefaultComboBoxModel<String> dcmSido;
	private JComboBox<String> cbSido;

	private DefaultComboBoxModel<String> dcmGugun;
	private JComboBox<String> cbGugun;

	private PayView pv;

	public ZipcodeSearchOrderView(PayView pv) {
		super(pv, "�ּҰ˻�", true);
		this.pv = pv;

		String[] sido = { "��ü", "����", "���", "��õ", "����", "����", "�泲", "���", "����", "����", "����", "����", "�뱸", "���", "���",
				"�泲", "�λ�", "����" };

		dcmSido = new DefaultComboBoxModel<String>(sido);
		cbSido = new JComboBox<String>(dcmSido);

		String[] gugun = { "��ü" };
		dcmGugun = new DefaultComboBoxModel<String>(gugun);
		cbGugun = new JComboBox<String>(dcmGugun);

		JLabel jlZipcode = new JLabel("�ּҰ˻�");

		String[] zipcode = { "�����ȣ", "�ּ�" };
		jtfSearch = new JTextField("");
		jbtnSearch = new JButton("��ȸ");
		dtmZipcode = new DefaultTableModel(zipcode, 20);
		jtZipcode = new JTable(dtmZipcode) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtZipcode.setRowHeight(30);

		jtZipcode.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtZipcode.getColumnModel().getColumn(1).setPreferredWidth(500);

		jtZipcode.getTableHeader().setResizingAllowed(false);
		jtZipcode.getTableHeader().setReorderingAllowed(false);

		jspTable = new JScrollPane(jtZipcode);

		setLayout(null);

		cbGugun.setBounds(150, 20, 120, 30);
		cbSido.setBounds(30, 20, 100, 30);
		jlZipcode.setBounds(30, 60, 100, 30);
		jtfSearch.setBounds(120, 60, 200, 30);
		jbtnSearch.setBounds(330, 60, 80, 30);
		jspTable.setBounds(30, 100, 600, 300);

		ZipcodeSearchOrderEvt zsoe = new ZipcodeSearchOrderEvt(pv, this);
		cbSido.addActionListener(zsoe);
		jbtnSearch.addActionListener(zsoe);
		jtfSearch.addActionListener(zsoe);
		jtZipcode.addMouseListener(zsoe);
		jbtnSearch.setBackground(Color.white);
		jlZipcode.setForeground(Color.white);
		this.getContentPane().setBackground(new Color(0x352A26)); // JDialog ���� ����

		add(cbGugun);
		add(cbSido);
		add(jlZipcode);
		add(jtfSearch);
		add(jbtnSearch);
		add(jspTable);

		setBounds(100, 100, 700, 600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}// ZipcodeSearchView

	public JTextField getJtfSearch() {
		return jtfSearch;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}

	public DefaultTableModel getDtmZipcode() {
		return dtmZipcode;
	}

	public JTable getJtZipcode() {
		return jtZipcode;
	}

	public JScrollPane getJspTable() {
		return jspTable;
	}

	public DefaultComboBoxModel<String> getDcmSido() {
		return dcmSido;
	}

	public JComboBox<String> getCbSido() {
		return cbSido;
	}

	public DefaultComboBoxModel<String> getDcmGugun() {
		return dcmGugun;
	}

	public JComboBox<String> getCbGugun() {
		return cbGugun;
	}

}// class
