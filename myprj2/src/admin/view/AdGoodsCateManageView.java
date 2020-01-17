package admin.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import admin.controller.AdGoodsCateManageEvt;
import admin.controller.AdGoodsManageEvt;
import admin.run.StaticCla;

@SuppressWarnings("serial")
public class AdGoodsCateManageView extends JDialog {
	private DefaultListModel<String> dlmType, dlmDetailType;

	private JList<String> lstType, lstDetailType;
	private JButton jbtnAdd, jbtnModify, jbtnClose;

	private DefaultTableModel dtmBrand;
	private JTable jtBrand;

	private AdGoodsManageEvt gme;
	private JTableHeader th;

	public AdGoodsCateManageView(AdGoodsManageEvt gme) {
		super(StaticCla.mv, "브랜드 및 카테고리", true);
		this.gme = gme;
		// 브랜드
		String[] data = { "브랜드코드", "브랜드명" };
		
		dtmBrand = new DefaultTableModel(data, 10);
		jtBrand = new JTable(dtmBrand) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}

		};
		jtBrand.getTableHeader().setBackground(new Color(0x352A26));
		jtBrand.getTableHeader().setForeground(Color.white);
		// 대분류
		dlmType = new DefaultListModel<String>();
		lstType = new JList<String>(dlmType);
		// 소분류
		dlmDetailType = new DefaultListModel<String>();
		lstDetailType = new JList<String>(dlmDetailType);
		jbtnAdd = new JButton("추가");
		jbtnModify = new JButton("수정");
		jbtnClose = new JButton("닫기");

		jtBrand.setRowHeight(25);
		jtBrand.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtBrand.getColumnModel().getColumn(1).setPreferredWidth(100);

		JScrollPane jspBrand = new JScrollPane(jtBrand);
		JScrollPane jspType = new JScrollPane(lstType);
		JScrollPane jspDetailType = new JScrollPane(lstDetailType);
		JPanel jpType = new JPanel();
		jpType.setLayout(null);
		jspType.setBounds(10, 20, 160, 250);
		jspDetailType.setBounds(180, 20, 160, 250);
		jpType.add(jspType);
		jpType.add(jspDetailType);
		th = jtBrand.getTableHeader();
		th.setPreferredSize(new Dimension(30, 35)); // header 높이 변경
		// 수동배치
		setLayout(null);
		jspBrand.setBorder(new TitledBorder("브랜드목록"));
		jpType.setBorder(new TitledBorder("카테고리를 클릭해주세요."));
		jspType.setBorder(new TitledBorder("대분류목록"));
		jspType.setBackground(Color.white);
		
		jspDetailType.setBorder(new TitledBorder("소분류목록"));
		jspDetailType.setBackground(Color.white);

		
		jspBrand.setBounds(10, 20, 270, 300);
		jpType.setBounds(300, 20, 360, 300);
		jbtnAdd.setBounds(200, 350, 100, 30);
		jbtnModify.setBounds(320, 350, 100, 30);
		jbtnClose.setBounds(450, 350, 100, 30);
		jspBrand.setBackground(Color.white);
		jpType.setBackground(Color.white);
		
		jbtnAdd.setBackground(new Color(0x352A26));
		jbtnAdd.setForeground(Color.white);
		jbtnModify.setBackground(new Color(0x352A26));
		jbtnModify.setForeground(Color.white);
		jbtnClose.setBackground(new Color(0x352A26));
		jbtnClose.setForeground(Color.white);
		add(jspBrand);
		add(jpType);
		add(jbtnAdd);
		add(jbtnModify);
		add(jbtnClose);
		this.getContentPane().setBackground(Color.white); //JDialog 배경색 변경 
		// 이벤트 등록
		AdGoodsCateManageEvt gcme = new AdGoodsCateManageEvt(this);
		jbtnAdd.addActionListener(gcme);
		jbtnModify.addActionListener(gcme);
		jbtnClose.addActionListener(gcme);
		lstType.addMouseListener(gcme);
		jtBrand.addMouseListener(gcme);
		
		jtBrand.getTableHeader().setReorderingAllowed(false);// 컬럼이동방지
		jtBrand.getTableHeader().setResizingAllowed(false);// 크기조절불가

		setBounds(100, 100, 700, 480);
		setVisible(true);
		setResizable(false);
	}// AdGoodsCateManageView

	public DefaultListModel<String> getDlmType() {
		return dlmType;
	}

	public DefaultListModel<String> getDlmDetailType() {
		return dlmDetailType;
	}

	public JList<String> getLstType() {
		return lstType;
	}

	public JList<String> getLstDetailType() {
		return lstDetailType;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public JButton getJbtnClose() {
		return jbtnClose;
	}

	public DefaultTableModel getDtmBrand() {
		return dtmBrand;
	}

	public JTable getJtBrand() {
		return jtBrand;
	}

	public AdGoodsManageEvt getGme() {
		return gme;
	}

}// class
