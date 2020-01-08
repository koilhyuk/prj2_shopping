package admin.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;

import admin.dao.AdminDAO;
import admin.run.StaticCla;
import admin.view.AdGoodsCateModifyView;
import admin.vo.SelectListVO;
import admin.vo.UpdateBrandVO;
import admin.vo.UpdateTypeVO;
import kr.co.sist.util.img.ImageResize;

/**
 * �귣�� �� ī�װ� ���� �귣�� ������ JCOMBOBOX �ʱ�ȭ
 * 
 * @author owner
 */
public class AdGoodsCateModifyEvt extends MouseAdapter implements ActionListener {
	private AdGoodsCateModifyView gcmv;

	public AdGoodsCateModifyEvt(AdGoodsCateModifyView gcmv) {
		this.gcmv = gcmv;

		setBrandName();
		typeName();
		detailtypeName();
	}// AdGoodsCateModifyEvt

	public void close() {
		gcmv.dispose();
	}// close

	/**
	 * �귣��DB�� jcombobox�� ����
	 */
	public void setBrandName() {
		DefaultComboBoxModel<String> brand = gcmv.getDcbmBrand();
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
	 * ��з��� jcombobox�� ����
	 */
	public void typeName() {
		DefaultComboBoxModel<String> type = gcmv.getDcbmType();
		type.removeAllElements();
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

	}// typeName

	/**
	 * �Һз��� jcombobox�� ����
	 */
	public void detailtypeName() {
		DefaultComboBoxModel<String> detailType = gcmv.getDcbmDetailType();
		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> list = null;
		String type = (String) gcmv.getDcbmType().getSelectedItem();
		String typeName = "";
		try {
			list = aDAO.selectDetailType(type);
			for (int i = 0; i < list.size(); i++) {
				typeName = list.get(i);
				detailType.addElement(typeName);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// detailtypeName

	/**
	 * ������ �̹����� ����(�귣�� �̹��� ���� )
	 */
	public void updateImg() {
		FileDialog fdOpen = new FileDialog(gcmv, "�귣���̹�������");
		fdOpen.setVisible(true);
		String path = fdOpen.getDirectory();
		String file = fdOpen.getFile();
		if (file != null) {
			String fileExt = "jpg, bmp, png, gif";
			String ext = file.substring(file.lastIndexOf(".") + 1);
			if (!fileExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(gcmv, "��(��) ����ϽǼ� ���� Ȯ���� �Դϴ�.");
				return;
			} // end if
		} // end if
		JLabel img = gcmv.getJlImg();
		img.setIcon(new ImageIcon(path + file));
	}// modfyImg

	/**
	 * �귣�� ����
	 */
	public void deleteBrand(String type) {
		type = (String) gcmv.getJtfBrand().getText().trim();
		SelectListVO slVO = new SelectListVO(type, 2);
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			if (aDAO.updateTypeDEL(slVO)) { // ��ǰ���̺��� �ڵ带 �����÷����� ����
				aDAO.deleteBrand(type);
				JOptionPane.showMessageDialog(gcmv, type + "�� �귣�带 �����Ͽ����ϴ�.");
				close();
				gcmv.getGcme().setBrandList(); // ����
			} else {
				aDAO.deleteBrand(type);
				JOptionPane.showMessageDialog(gcmv, type + "�� �귣�带 �����Ͽ����ϴ�.");
			} // end else

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(gcmv, "���� �����Ͻ� �귣��� ��ϵ� ��ǰ�� �����Ͽ� ���� �Ұ��մϴ�.");
			e.printStackTrace();
		} // end catch

	}// deleteBrand

	/**
	 * �귣���̸��� ������ �Ǹ� �̹�����, �귣�� �̸��� ��ȸ�ǰ� ���� //////
	 * 
	 * @param name
	 */
	public void selectBrand(String name) {
		name = (String) gcmv.getJcbBrand().getSelectedItem();
		AdminDAO aDAO = AdminDAO.getInstance();
		String img = "";
		JLabel setImg = gcmv.getJlImg();
		try {
			img = aDAO.selectBrandImg(name);
			setImg.setIcon(new ImageIcon(StaticCla.FILE_PATH + "/br_" + img));

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// selectBrand

	/**
	 * �귣�� ����
	 */
	public void modifyBrand() {
		switch (JOptionPane.showConfirmDialog(gcmv, "�귣�带 �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			String img = new File(gcmv.getJlImg().getIcon().toString()).getName();
			String originimg = img.replace("br_", "");
			String name = (String) gcmv.getJtfBrand().getText();
			String code = (String) gcmv.getJcbBrand().getSelectedItem();
			String tempImage = "";
			AdminDAO aDAO = AdminDAO.getInstance();
			UpdateBrandVO ubVO = null;
			try {
				ubVO = new UpdateBrandVO(originimg, name, code);
				if (aDAO.updateBrand(ubVO)) {

					try {

						File readFile = new File(gcmv.getJlImg().getIcon().toString());
						byte[] readData = new byte[512];
						int length = 0;

						FileOutputStream fos = null;
						FileInputStream fis = null;
						try {
							fis = new FileInputStream(readFile); // ������ ����
							if (readFile.exists()) {// �̹����� �����ϸ�
								File writeFile = new File(StaticCla.FILE_PATH + "/br_" + readFile.getName());
								String newimg = writeFile.getName().replace("br_", "");

								if (newimg.equals(originimg)) {
									tempImage = originimg;
								} else {
									tempImage = newimg;
									fos = new FileOutputStream(writeFile); // ������ �̹��� ������ ����
									while ((length = fis.read(readData)) != -1) {// ������ 0
										fos.write(readData, 0, length);// �о���� ��ŭ ��½�Ʈ���� ���
									} // end while
									fos.flush();
									ImageResize.resizeImage(writeFile.getAbsolutePath(), 150, 150);
								} // end if

							} // end if
						} finally {
							if (fos != null) {
								fos.close();
							}
							if (fis != null) {
								fis.close();
							}
						} // end finally
						JOptionPane.showMessageDialog(gcmv, "�귣������� �Ϸ�Ǿ����ϴ�. ");
						close();
						gcmv.getGcme().setBrandList(); // ����

					} catch (IOException e) {
						JOptionPane.showMessageDialog(gcmv, "�̹��� ���ε� �� ������ �߻��Ͽ����ϴ�.");
						e.printStackTrace();
						return;
					} // end catch
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
			break;
		}// switch
	}// modifyBrand

	public void deleteType(String type) {
		type = (String) gcmv.getJcbDetailType().getSelectedItem();
		SelectListVO slVO = new SelectListVO(type, 1);
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			// ��ǰ���̺� �ִ� �Һз��ڵ��� �̸��� DEL�� ���� !
			// ������ �Һз��ڵ带 �Һз����̺��� ����!
			if (aDAO.updateTypeDEL(slVO)) { // ��ǰ���̺��� �ڵ带 �����÷����� ����
				aDAO.deleteType(type);
				JOptionPane.showMessageDialog(gcmv, type + "�� ī�װ��� �����Ͽ����ϴ�.");
				close();
				gcmv.getGcme().setBrandList(); // ����
			} else {
				aDAO.deleteType(type);
				JOptionPane.showMessageDialog(gcmv, type + "�� ī�װ��� �����Ͽ����ϴ�.");
			} // end else
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(gcmv, "���� �����Ͻ� ī�װ��� ��ϵ� ��ǰ�� �����Ͽ� ���� �Ұ��մϴ�.");
//			e.printStackTrace();
		} // end catch

	}// deleteType

	/**
	 * ī�װ� ����
	 */
	public void modifyType() {
		switch (JOptionPane.showConfirmDialog(gcmv, "ī�װ��� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:

			String newType = gcmv.getJtfCategory().getText().trim();
			String detailType = (String) gcmv.getJcbDetailType().getSelectedItem();

			AdminDAO aDAO = AdminDAO.getInstance();
			UpdateTypeVO utVO = null;
			try {
				utVO = new UpdateTypeVO(newType, detailType);

				if (aDAO.updateType(utVO)) {
					JOptionPane.showMessageDialog(gcmv, "ī�װ������� �Ϸ�Ǿ����ϴ�. ");
					close();
					gcmv.getGcme().typeList(); // ����
				} else {
					JOptionPane.showMessageDialog(gcmv, "ī�װ������� �����ϼ̽��ϴ�. ");
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
		}// switch
	}// modifyType

	@Override
	public void actionPerformed(ActionEvent e) {
		String index = (String) gcmv.getJcbBrand().getSelectedItem();
		if (e.getSource() == gcmv.getJcbBrand()) {// �귣�� JComBOBox V
//			gcmv.getDcbmBrand().removeAllElements(); 
			setBrandName();// Jcombobox�� �귣�� List�� ����
			JTextField brand = gcmv.getJtfBrand(); // ������ �ε����� �귣�带 T.F�� ����
			brand.setText(index.trim());
			// ������ �ε����� �귣���� �̹����� �ڵ带 ��ȸ
			selectBrand(index);
		} // end
		if (e.getSource() == gcmv.getJbtnBImg()) {// �귣�� �̹��� ���� V
			updateImg();
		} // end if
		if (e.getSource() == gcmv.getJbtnBDelete()) {// �귣�� ���� V
			deleteBrand(index);
		} // end if
		if (e.getSource() == gcmv.getJbtnBModufy()) {// �귣����� V
			modifyBrand();
		} // end if
		if (e.getSource() == gcmv.getJbtnBClose()) {// �귣��ݱ� V
			close();
		} // end if
////////////////////////////////////////////////////////////////////////////////////////////
		if (e.getSource() == gcmv.getJcbType()) {//
			JComboBox<String> typeList = gcmv.getJcbType();
			int indexType = gcmv.getJcbType().getSelectedIndex();
			int count = typeList.getItemCount(); // ��з��� ����
			for (int i = 0; i < count; i++) {
				if (indexType == i) { // ������ �ε��� ���� �����ϴٸ�
					gcmv.getDcbmDetailType().removeAllElements();
					detailtypeName();
				} // end if
			} // end switch

		} // end if
		if (e.getSource() == gcmv.getJcbDetailType()) {
			String type = (String) gcmv.getJcbDetailType().getSelectedItem();
			JTextField category = gcmv.getJtfCategory();
			category.setText(type);
		} // end if

		if (e.getSource() == gcmv.getJbtnCClose()) {// �ݱ�
			close();
		} // end if
		if (e.getSource() == gcmv.getJbtnCAdd()) {// ����
			modifyType();
		} // end if
		if (e.getSource() == gcmv.getJbtnCDelete()) {// ���� v
			String typeIndex = (String) gcmv.getJcbDetailType().getSelectedItem();
			deleteType(typeIndex);
		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == gcmv.getJtfCategory()) {
			JTextField category = gcmv.getJtfCategory();
			category.setText("");
		} // end if
	}// mouseClicked

}// class
