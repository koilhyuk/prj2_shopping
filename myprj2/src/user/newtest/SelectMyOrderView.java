package user.newtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class SelectMyOrderView extends JFrame{

	private JLabel jlorder, jlimg, jlg_name, jlg_getsu, jlg_price, jlb_name, jldel, jlo_date;
	private DefaultTableModel dtm;
	private JTable jtable;
	private JButton jbtnok;
	private JComboBox<String> jbcscore;
	private DefaultComboBoxModel<String> dcbm;

	public SelectMyOrderView() {

		// 라벨생성
		jlorder = new JLabel("주문내역");
		jlorder.setForeground(Color.white);

		// 디자인패널
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x3F4040));
		jp.setBounds(100, 0, 800, 100);
		jp.setLayout(null);

		jlimg = new JLabel((new ImageIcon("C:/dev/workspace/prj2/src/images/dhh.jpg")));
		jlg_name=new JLabel();
		jlg_getsu=new JLabel();
		jlg_price=new JLabel();
		jlb_name=new JLabel();
		jldel=new JLabel();
		jlo_date=new JLabel();
		jbtnok = new JButton("확인");
		jbtnok.setBackground(new Color(0x3F4040));
		jbtnok.setForeground(Color.white);

		// 콤보박스
		String[] data = { "1", "2", "3", "4", "5" };
		dcbm = new DefaultComboBoxModel<String>(data);
		jbcscore = new JComboBox<String>(dcbm);

		String[] st1 = { "이미지", "상품명", "수량", "브랜드", "주문일", "배송여부", "평점", "가격" };
		Object[][] rowdata = { { "", "", "" } };

		dtm = new DefaultTableModel(rowdata, st1);
		dtm = new DefaultTableModel(new Object[][] {}, st1);
//		dtm =
//		jtable = new JTable(dtm) {
//			public Class<?> getColumnClass(int column) {
//				return getValueAt(0, column).getClass();
//			}
//
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		};

		jtable = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.getTableHeader().setResizingAllowed(false);
		jtable.getColumnModel().getColumn(0).setPreferredWidth(300);
		jtable.getColumnModel().getColumn(1).setPreferredWidth(300);
		jtable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jtable.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		jtable.getColumnModel().getColumn(3).setPreferredWidth(180);
		jtable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		jtable.getColumnModel().getColumn(4).setPreferredWidth(300);
		jtable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		jtable.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		jtable.getColumnModel().getColumn(6).setPreferredWidth(150);
		jtable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		jtable.getColumnModel().getColumn(7).setPreferredWidth(150);
		jtable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		jtable.setRowHeight(200);

		jtable.getTableHeader().setBackground(new Color(0x3F4040));
		jtable.getTableHeader().setForeground(Color.white);

//		UserDAO uDAO = UserDAO.getInstance();
//		try {
//			SelectMyOrderVO smVO = null;
//
//			List<SelectMyOrderVO> list = uDAO.selectMyOrder();
//
//			for (int i = 0; i < list.size(); i++) {
//				smVO = list.get(i);
//
//			} // end for
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		JScrollPane jsp = new JScrollPane(jtable);
//		jsp.getViewport ().add (jtable);
//		jsp.setLayout(new GridLayout());

//		// 폰트설정
//		Font f = new Font("맑은 고딕)", Font.BOLD, 30);
//		jlorder.setFont(f);
//
//		jlorder.setBorder(new LineBorder(Color.magenta));
//		jsp.getBackground().
//		jsp.setBorder(new LineBorder(Color.white));
//		jtable.setBorder(new LineBorder(Color.black));
//		jtable.getTableHeader();
//
//		// 패널생성
//		jpContent=new JPanel();
//		jpContent.setBorder(new LineBorder(Color.black));
//		jpContent.setLayout(new FlowLayout(FlowLayout.LEFT));
//		jpContent.setBackground(Color.white);
//		jspFull=new JScrollPane(jpContent);
//		
//		jpBestGoods = new JPanel();
//		jpBestGoods.setBorder(new LineBorder(Color.black));
//		jpBestGoods.setLayout(new FlowLayout(FlowLayout.LEFT));
//		jpBestGoods.setBackground(Color.white);
//
//		// 배치
		jlorder.setBounds(360, 30, 135, 40);
//		jpContent.setBounds(100,100,650,1000);
		jlimg.setBounds(50,50,200,200);
		jbtnok.setBounds(450, 720, 100, 30);
//		// 패널세팅
//		jpBestGoods.setBounds(0,0, 828, 1000);
//		jpContent.add(jpBestGoods);
		jp.add(jlorder);
//		add(jspFull);
//		jpBestGoods.add(jlimg);
//		jpBestGoods.add(jtable);//test
		add(jtable);
//		jsp.add(jtable);
//		setLayout(null);
//		add(jsp);
		add(jbtnok);
//
		add(jp);
//
//		jsp.setBounds(100, 100, 800, 600);
		setBounds(50, 50, 1000, 820);
//		jpContent.setPreferredSize(new Dimension(getWidth() ,  5000));// 고정 값
//		jspFull.setBounds(0, 0, getWidth() - 15, getHeight() - 37);
//		jspFull.setBounds(100,100,850,500);
		setVisible(true);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
//
//		jbtnok.addActionListener(this);
//
	}// SelectMyOrderView


	@SuppressWarnings("rawtypes")
	public void JTableEvt(JButton jb) {
	}

	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JButton jb;
		JComboBox<String> jc;
		JPanel jp;

		public TableCell() {
			jb = new JButton("평점주기");
			jc = new JComboBox<String>();
			jc.addItem("1점");
			jc.addItem("2점");
			jc.addItem("3점");
			jc.addItem("4점");
			jc.addItem("5점");

			jp = new JPanel();
			jp.setLayout(null);
			jp.add(jb);
			jp.add(jc);

			jc.setBounds(0, 60, 75, 30);
			jb.setBounds(-13, 90, 100, 30);

	            jp.setLayout(new BorderLayout());
	            jp.add(jb);
	            jp.add(jc);
	            
	            jp.add("North",jc);
	            
	            jp.add("South",jb);

			jb.addActionListener(e -> {
				JTableEvt(jb);
			});
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return jp;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return jp;
		}
	}


	public JLabel getJlorder() {
		return jlorder;
	}

	public JLabel getJlimg() {
		return jlimg;
	}

	public JLabel getJlg_name() {
		return jlg_name;
	}

	public JLabel getJlg_getsu() {
		return jlg_getsu;
	}

	public JLabel getJlg_price() {
		return jlg_price;
	}

	public JLabel getJlb_name() {
		return jlb_name;
	}

	public JLabel getJldel() {
		return jldel;
	}

	public JLabel getJlo_date() {
		return jlo_date;
	}

	public DefaultTableModel getDtm() {
		return dtm;
	}
	public static void main(String[] args) {
		new SelectMyOrderView();
}

}//class
