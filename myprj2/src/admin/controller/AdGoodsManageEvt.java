package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.dao.AdminDAO;
import admin.run.StaticCla;
import admin.view.AdGoodsAddView;
import admin.view.AdGoodsCateManageView;
import admin.view.AdGoodsDetailView;
import admin.view.AdGoodsManageView;
import admin.vo.SelectGoodsDetailDTO;
import admin.vo.SelectGoodsListVO;
import admin.vo.SelectListVO;

/**
 * ��ǰ���� Event - ������ ��ǰ�˻� - ������ ��ǰ ����
 * 
 * @author hyebin
 *
 */
public class AdGoodsManageEvt extends MouseAdapter implements ActionListener {
	public static final int DOUBLE_CLICK = 2;// ����Ŭ��
	// ��ȸ�� ���� ���
	public static final int ALL_GOODS = 0;// ��ü
	public static final int GOODS_CODE = 1;// ��ǰ�ڵ�
	public static final int BRAND = 2;// �귣��
	public static final int GOODS_NAME = 3;// ��ǰ��
	public static final int SCORE = 4;// ����Ŭ��

	private int index; // JCOMBOBOX�� ����Ʈ�� �ε���
	private String search; // T.F
	private AdGoodsManageView gv;

	public AdGoodsManageEvt(AdGoodsManageView gv) {
		this.gv = gv;
		searchGoods();

	}// AdGoodsManageEvt

	/**
	 * ������ ��ǰ�� �������� ����
	 */
	public void setChoiceGoods(JTable jtGoods) {
		// ��â���� ����� �� DTO�� �����Ѵ�!
		SelectGoodsDetailDTO dgDTO = new SelectGoodsDetailDTO();
		// ��ǰ����Ʈ���� ������ �ִ� ��, ������ ���� ��ǰ�� ���� ��´�
		String goodsName = (String) jtGoods.getValueAt(jtGoods.getSelectedRow(), 0); // ��ǰ��
		String goodsCode = goodsName.substring(goodsName.lastIndexOf("(") + 1, goodsName.lastIndexOf(")")); // ��ǰ�ڵ�
		String brand = jtGoods.getValueAt(jtGoods.getSelectedRow(), 1).toString(); // �귣��
		String detailType = (String) jtGoods.getValueAt(jtGoods.getSelectedRow(), 2);// �Һз�
		// ������ ��ǰ�ݾ��� ���� ,�� ������ �迭�� ����
		int price = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 3);// ����

		int saleNum = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 4);// �Ǹż���
		int score = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 5);// ����
		int inventory = (int) jtGoods.getValueAt(jtGoods.getSelectedRow(), 6);// ���
		String inputDate = (String) jtGoods.getValueAt(jtGoods.getSelectedRow(), 7);// �԰�¥

		// ���� �ִ´�.
		dgDTO.setG_code(goodsCode.trim()); // �ڵ�
		dgDTO.setG_name(goodsName.trim());// ��ǰ��
		dgDTO.setB_name(brand.trim());// ��ص�
		dgDTO.setD_type(detailType.trim());// ��ǰ�з�

		dgDTO.setG_price(price);// ����
		dgDTO.setG_salenum(saleNum);// �Ǹż���
	
		dgDTO.setG_score(score);// ����
		dgDTO.setG_inventory(inventory);// ���
		dgDTO.setG_inputdate(inputDate.trim());// �԰���

		// DB���� ���� �� �ִ� ��
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.selectDetailGoods(dgDTO);// Ư����, �̹���, �귣��, Ÿ��
			// �󼼺��� â�� ���� �־���
			new AdGoodsDetailView(this, dgDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// setChoiceGoods

	/**
	 * JcomboBox���� ������ �׸����� ��ȸ DBMS ���̺��� ��ȸ�� ��ǰ����Ʈ�� JTable�� ����
	 */
	public void searchGoods() {
		DefaultTableModel dtmPro = gv.getDtmGoodsList(); // Jtable�� ������
//		dtmPro.removeRow(0);
		dtmPro.setRowCount(0); // �ʱ�ȭ

		AdminDAO aDAO = AdminDAO.getInstance(); // ��ü �ϳ��� �����ϰ� ����

		Object[] rowData = null; // jtable�� ���� ������
		search = gv.getJtfSearch().getText().trim(); // T.F�� �ִ� ����

		List<SelectGoodsListVO> list = null;
		SelectListVO slVO = null;// ��ȸ �ε���, T.F�� ���� VO
		SelectGoodsListVO gvO = null; // JTable�� ���� ��ȸ�ϱ� ���� VO

		try {
			slVO = new SelectListVO(search, index);
			index = gv.getJcbSearch().getSelectedIndex(); // ������ �׸��� �ε���
			list = aDAO.selectAllGoods(slVO);

			if (list.isEmpty()) {// ��ȸ�� ����Ʈ�� ���ٸ� (��ȸ�� ��ǰ�� ���ٸ� )
				JOptionPane.showMessageDialog(gv, "��ȸ������ ��ǰ�� �����ϴ�.");
				gv.getJtfSearch().setText("");// �ʱ�ȭ
				gv.getJtfSearch().requestFocus(); // Ŀ����ġ
				// ��ȸ������ ��ǰ�� ������ ����â������ ��ü ����Ʈ�� ���´�.
				searchGoods();
			} // end if
			for (int i = 0; i < list.size(); i++) { // �����͸� ����
				gvO = list.get(i);
				rowData = new Object[8];
				rowData[0] = gvO.getG_name() + "(" + gvO.getG_code() + ")";
				if (gvO.getB_img() != null) {// �̹����� �ִٸ�
					rowData[1] = new ImageIcon(StaticCla.FILE_PATH+"/rs_br_"+gvO.getB_img().trim());
				} else {// �̹����� ���ٸ� �̸��� �־�
					rowData[1] = gvO.getB_name();
				} // end else
				rowData[2] = gvO.getD_type();
				rowData[3] = gvO.getG_price();
				rowData[4] = gvO.getG_salenum();
				rowData[5] = gvO.getG_score();
				rowData[6] = gvO.getG_inventory();
				rowData[7] = gvO.getG_inputdate();
				dtmPro.addRow(rowData);
			} // end for

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(gv, "�����ڴ� ���񽺰� ��Ȱ���� �ʽ��ϴ�. �˼��մϴ�.");
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(gv, "��ȸ������ ��ǰ�� �����ϴ�.");
		} // end catch
	}// searchGoods

	/**
	 * �ֹ��� �ȵ��� ��ǰ�� ��������
	 */
	public void removeGoods() {
		JTable jtGoods = gv.getJtGoods();

		int selectRow = jtGoods.getSelectedRow(); // ������ ��ǰ�� ��
		if (selectRow == -1) {// ������ ��ǰ�� ���� ���õ��� �ʾҴٸ�
			JOptionPane.showMessageDialog(gv, "�����Ͻ� ��ǰ�� �������ּ���.");
			return;
		} // end if

		String name = (String) jtGoods.getValueAt(selectRow, 0);// ��ǰ��
		String goodsCode = name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(")")); // ��ǰ�ڵ�
		switch (JOptionPane.showConfirmDialog(gv, goodsCode + "���� ��ǰ�� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			AdminDAO aDAO = AdminDAO.getInstance();
			try {
				if (aDAO.deleteGoods(goodsCode)) {
					gv.getDtmGoodsList().removeRow(selectRow);
					JOptionPane.showMessageDialog(gv, goodsCode + "���� ��ǰ�� �����Ǿ����ϴ�.");
				} // end if;
				searchGoods();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(gv, "���� �ֹ��� �ִ� ��ǰ�̱⶧���� ��ǰ������ �Ұ����մϴ�.");
//				e.printStackTrace();
			} // end if
		}// end switch
	}// removeGoods

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == gv.getJbtnSearch()) { // ��ǰ��ȸ
			// ������ ����Ʈ�� ��ȸ�ǰ� �ۼ�
			index = gv.getJcbSearch().getSelectedIndex();// jcombobox �ε���
			search = gv.getJtfSearch().getText().trim(); // T.F�� ����
			switch (index) {
			case ALL_GOODS: // ��ü
//				JOptionPane.showMessageDialog(gv, "��ü��ǰ�� ��ȸ�Ǿ����ϴ�.");
				gv.getJtfSearch().setText("");// �ʱ�ȭ
				gv.getJtfSearch().requestFocus();
				searchGoods();
				break;

			case GOODS_CODE: // ��ǰ�ڵ�
				if (search.isEmpty()) {// �����̶��
					JOptionPane.showMessageDialog(gv, "��ȸ�Ͻ� ��ǰ �ڵ带 �Է����ּ���");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				searchGoods();
				break;

			case BRAND: // �귣��
				if (search.isEmpty()) {// �����̶��
					JOptionPane.showMessageDialog(gv, "��ȸ�Ͻ� �귣�带 �Է����ּ���");
					gv.getJtfSearch().setText("");// �����̶��
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				searchGoods();
				break;

			case GOODS_NAME: // ��ǰ��
				if (search.isEmpty()) {// �����̶��
					JOptionPane.showMessageDialog(gv, "��ȸ�Ͻ� ��ǰ���� �Է����ּ���");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				searchGoods();
				break;

			case SCORE: // ����
				if (search.isEmpty()) {// �����̶��
					JOptionPane.showMessageDialog(gv, "��ȸ�Ͻ� ������ �Է����ּ���");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
					return;
				} // end if
				try {
					if (Integer.parseInt(search) == 0 || Integer.parseInt(search) > 5) { // �˻�â�� 1~5�� ���̰� �ƴ϶��
						JOptionPane.showMessageDialog(gv, "������ 1~5������ �Է����ּ���.");
						gv.getJtfSearch().setText("");
						gv.getJtfSearch().requestFocus();
					} // end if
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(gv, "������ ���ڸ� �Է°����մϴ�.");
					gv.getJtfSearch().setText("");
					gv.getJtfSearch().requestFocus();
				} // end catch
				searchGoods();
				break;

			}// end switch
		} // end if
		if (ae.getSource() == gv.getJtfSearch()) {// ����
			searchGoods();
		} // end if
		if (ae.getSource() == gv.getJbtnUpload()) { // ��ǰ���
			new AdGoodsAddView(this);
		} // end if
		if (ae.getSource() == gv.getJmDelete()) {// �˾� ����
			removeGoods();
		} // end if
		if (ae.getSource() == gv.getJbtnCate()) {// �귣��� ī�װ�����
			new AdGoodsCateManageView(this);
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == DOUBLE_CLICK) {// ����Ŭ��
			if (me.getSource() == gv.getJtGoods()) {
				JTable jtGoods = gv.getJtGoods();
				setChoiceGoods(jtGoods); // ������ ��ǰ �󼼺���â�� ����
			} // end if
		} // end if
		if (me.getSource() == gv.getJtfSearch()) { // ��ȸ T.F�� ������ �ʱ�ȭ
			gv.getJtfSearch().setText("");
		} // end if
	}// mouseClicked

}// class
