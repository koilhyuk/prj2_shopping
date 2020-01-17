package admin.controller;


import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import admin.dao.AdminDAO;
import admin.run.StaticCla;
import admin.view.AdGoodsAddView;
import admin.vo.InsertGoodsAddVO;
import kr.co.sist.util.img.ImageResize;

/**
 * ������ ��ǰ���â �̺�Ʈ����
 * 
 * @author hyebin
 *
 */
public class AdGoodsAddEvt implements ActionListener {
	private AdGoodsAddView guv;
	private AdGoodsManageEvt gle;

	public AdGoodsAddEvt(AdGoodsAddView guv, AdGoodsManageEvt gle) {
		this.guv = guv;
		this.gle = gle;
		brandList();
		typeList();
		detailTypeList();
	}// AdGoodsAddEvt

	/**
	 * �ݱ��ư
	 */
	public void close() {
		guv.dispose();
	}// close

	/**
	 * �̹��� ��Ϲ�ư-> �̹��� �߰�����
	 * 
	 * @throws IOException
	 */
	public void addImg() throws IOException {
		// �̹��� ������ ���� ���ϴ��̾�α� ����
		FileDialog fdOpen = new FileDialog(guv, "�̹��� ���� ����", FileDialog.LOAD);
		fdOpen.setVisible(true);// ����ȭ

		String path = fdOpen.getDirectory(); // ���
		String file = fdOpen.getFile();// ����

		if (file != null) { // ������ �����Ѵٸ�
			// Ȯ���� üũ
			String fileExt = "jpg, png, gif, bmp"; // ���� Ȯ���� ����
			String ext = file.substring(file.lastIndexOf(".") + 1); // ���� Ȯ����
			if (!fileExt.contains(ext.toLowerCase())) {// jpg, png, gif, bmp�� �����ϴ� ������ �ƴϸ�
				JOptionPane.showMessageDialog(guv, file + "��(��) ����ϽǼ� ���� Ȯ���� �Դϴ�. ");
				return; // �귯�������� ���ϰ� ����
			} // end if

			JLabel img = guv.getJlImg();
			img.setText("");
			img.setIcon(new ImageIcon(path + file));

		} // end else
	}// addImg

	/**
	 * ������ ����� �̹��� ������ ����
	 * 
	 * @throws IOException
	 */
	public void modifyImg() throws IOException {
		File readFile = new File(guv.getJlImg().getIcon().toString());
		byte[] readData = new byte[512];
		int length = 0;

		FileOutputStream fos = null;
		FileOutputStream fos2 = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(readFile);
			if (readFile.exists()) {
				File writeFile = new File(StaticCla.FILE_PATH+"/gd_" + readFile.getName());
				File writeFile2 = new File(StaticCla.FILE_PATH+"/gds_" + readFile.getName());
				fos = new FileOutputStream(writeFile);
				fos2 = new FileOutputStream(writeFile2);
				while ((length = fis.read(readData)) != -1) {
					fos.write(readData, 0, length);
					fos2.write(readData, 0, length);
				} // end while
				fos.flush();

				ImageResize.resizeImage(writeFile.getAbsolutePath(), 270, 350);//gd
				ImageResize.resizeImage(writeFile2.getAbsolutePath(), 130, 140);
			} // end if

		} finally {
			if (fos != null) {
				fos.close();
			}
			if (fos2 != null) {
				fos2.close();
			}
			if (fis != null) {
				fis.close();
			}
		} // end finally
	}// modifyImg

	/**
	 * ��ǰ ��� method
	 */
	public void GoodsAdd() {
		String img = new File(guv.getJlImg().getIcon().toString()).getName(); // �̹���
		String brand = (String) guv.getJcbBrand().getSelectedItem(); // �귣��
		String detailType = (String) guv.getJcbDetailType().getSelectedItem();
		String name = guv.getJtfGoodsName().getText().trim();// ��ǰ��
		String strong = guv.getJtaStrongPoint().getText().trim();// Ư����
		int price = 0;// ����
		int inventory = 0;// �԰�
		String tempPrice = guv.getJtfPrice().getText().trim();
		String tempInven = guv.getJtfInventory().getText().trim();
		try {
			price = Integer.parseInt(tempPrice);
			inventory = Integer.parseInt(tempInven);

			InsertGoodsAddVO dpVO = new InsertGoodsAddVO(img, brand, detailType, name, price, inventory, strong);
			if (img.isEmpty()||"".equals(img)||guv.getJlImg().getIcon().toString().isEmpty()||!new File(guv.getJlImg().getIcon().toString()).exists()) {
				JOptionPane.showMessageDialog(guv, "��ǰ �̹����� ������ּ���.");
				return;
			} // end if
			if (name.isEmpty()) {// ��ǰ��T.F ����
				JOptionPane.showMessageDialog(guv, "��ǰ���� �Է����ּ���.");
				guv.getJtfGoodsName().requestFocus();
				return;
			} // end if
			if (tempPrice.isEmpty() || price <= 0) {
				JOptionPane.showMessageDialog(guv, "��ǰ�ݾ��� �������·� �Է����ּ���");
				guv.getJtfPrice().requestFocus();
				return;
			} // end if
			guv.getJtfPrice().requestFocus();
			if (tempInven.isEmpty() || inventory <= 0) {// ���T.F ����
				JOptionPane.showMessageDialog(guv, "�԰��� �Է����ּ���.");
				guv.getJtfInventory().requestFocus();
				return;
			} // end if
			if (strong.isEmpty()) {// Ư����T.F ����
				JOptionPane.showMessageDialog(guv, "Ư������ �Է����ּ���.");
				guv.getJtaStrongPoint().requestFocus();
				return;
			} // end if
			if(guv.getJcbBrand().getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(guv, "�귣�带 �������ּ���.");
				return;
			}//end if 
			modifyImg();
			AdminDAO svDAO = AdminDAO.getInstance();
			// DBMS �߰�
			svDAO.insertGoods(dpVO);
			JOptionPane.showMessageDialog(guv, "��ǰ�� �߰��Ǿ����ϴ�.");
			gle.searchGoods();
			guv.dispose();
			// ��ǰ�߰��� ����Ʈ�� ���ŵǰ� �ۼ�

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(guv, "���ݰ� �԰��� ���ڸ� �Է����ּ���.");
			guv.getJtfPrice().requestFocus();
			return;
//			nfe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

	}// GoodsAdd

	/**
	 * �귣�带 jcombobox�� �߰�
	 */
	public void brandList() {
		DefaultComboBoxModel<String> brand = guv.getDcbmBrand();
		brand.removeAllElements();
		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> list = null;
		String brandName = "";
		try {
			list = aDAO.selectBrand();
			brand.addElement("---�귣�弱��---");
			for (int i = 0; i < list.size(); i++) {
				brandName = list.get(i);
				brand.addElement(brandName);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// brandList

	/**
	 * ��з� ����Ʈ jcombobox�� �߰�
	 */
	public void typeList() {
		DefaultComboBoxModel<String> type = guv.getDcbmType();

		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> list = null;
		String Clotype = "";
		try {
			list = aDAO.selectType();
			for (int i = 0; i < list.size(); i++) {
				Clotype = list.get(i);
				type.addElement(Clotype);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// typeList

	/**
	 * �Һз� ����Ʈ jcombobox�� �߰�
	 */
	public void detailTypeList() {
		DefaultComboBoxModel<String> detailType = guv.getDcbmDetailType();

		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> list = null;
		String type = (String) guv.getDcbmType().getSelectedItem();
		String typeList = "";
		try {
			list = aDAO.selectDetailType(type);
			for (int i = 0; i < list.size(); i++) {
				typeList = list.get(i);
				detailType.addElement(typeList);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// detailTypeList

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == guv.getJbtnClose()) {// �ݱ��ư
			close();
		} // end if

		if (e.getSource() == guv.getJbtnGoodsAdd()) {// ��Ϲ�ư
			// ��ǰ�� ����ؾ��� method ����
			switch (JOptionPane.showConfirmDialog(guv, "��ǰ�� ����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				GoodsAdd();
			}// end switch
		} // end if

		if (e.getSource() == guv.getJbtnImg()) {// �̹�����ư
			try {
				addImg();// �̹��� �߰�
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(guv, "�̹��� �۾� �� ������ �߻��߽��ϴ�. ��� �� �ٽ� ���� ���ּ���");
				e1.printStackTrace();
			} catch (NullPointerException ne) {
				JOptionPane.showMessageDialog(guv, "�̹����� ������ּ���");
			} // end catch
		} // end if

		if (e.getSource() == guv.getJcbType()) {// ��з��� �������� �Һз��� �ٲ���
			// ��з��� ���õǸ� �Һз��� ����Ʈ�� �ʱ�ȭ �Ǿ���Ѵ�.
			JComboBox<String> typeList = guv.getJcbType();
			int index = guv.getJcbType().getSelectedIndex();
			int count = typeList.getItemCount(); // ��з��� ����
			for (int i = 0; i < count; i++) {
				if (index == i) { // ������ �ε��� ���� �����ϴٸ�
					guv.getDcbmDetailType().removeAllElements();
					detailTypeList();
				} // end if
			} // end for

		} // end if
	}// actionPerformed

}// class
