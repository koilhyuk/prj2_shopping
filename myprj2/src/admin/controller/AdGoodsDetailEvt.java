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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import admin.dao.AdminDAO;
import admin.run.StaticCla;
import admin.view.AdGoodsDetailView;
import admin.vo.UpdateGoodsVO;
import kr.co.sist.util.img.ImageResize;

/**
 * ������ ��ǰ�󼼺��� �̺�Ʈ����
 * 
 * @author hyebin
 *
 */
public class AdGoodsDetailEvt extends MouseAdapter implements ActionListener {
	private String originImg;// ���� �̹���
//	private String tempImage;

	private AdGoodsDetailView pdv;
	private AdGoodsManageEvt gle; // ����Ʈ ����
	private boolean imgFlag; //

	public AdGoodsDetailEvt(AdGoodsDetailView pdv, String originImg, AdGoodsManageEvt gle) {
		this.pdv = pdv;
		this.gle = gle;
		this.originImg = originImg;
	}// AdGoodsDetailEvt

	/**
	 * �ݱ��ư
	 */
	public void close() {
		pdv.dispose();
	}// close

	/**
	 * ������ ��ǰ ����
	 */
	public void modifyGoods() {
		String name = pdv.getJtfGoodsName().getText().trim();
		switch (JOptionPane.showConfirmDialog(pdv, name + "�� ��ǰ�� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			int price = 0;
			String code = name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(")"));
			String strong = pdv.getJtaStrongPoint().getText().trim();

			String img = new File(pdv.getJlImg().getIcon().toString()).getName();
			String newimg = img.replace("gd_", "");

			String addGoods = pdv.getJtfAddGoods().getText();
			String tempImage = "";
			int inventory = 0;
			try {
				if (Integer.parseInt(addGoods) < 0) {
					JOptionPane.showMessageDialog(pdv, "�߰������� 0���� �Է��� �Ұ����մϴ�.");
					return;
				} // end if
				price = Integer.parseInt(pdv.getJtfPrice().getText().trim());
				inventory = Integer.parseInt(pdv.getJtfInventory().getText()) + Integer.parseInt(addGoods);
				if (newimg.equals(originImg)) {
					tempImage = originImg;
				} else {
					tempImage = newimg;
				} // end if

				UpdateGoodsVO ugVO = new UpdateGoodsVO(code, tempImage, strong, price, inventory);
				AdminDAO aDAO = AdminDAO.getInstance();
				if (aDAO.updateDetailGoods(ugVO)) {
					try {
						if (!newimg.equals(originImg)) {
							modifyImg(); // �̹��� ����
						} // end if
						gle.searchGoods(); // �ٽ� ����
						JOptionPane.showMessageDialog(pdv, "��ǰ������ �Ϸ�Ǿ����ϴ�.");
						pdv.dispose();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(pdv, "�̹��� ���ε� �� ������ �߻��Ͽ����ϴ�. ");
//						e.printStackTrace();
						return;
					} // end catch
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(pdv, "�ݾװ� �߰������� ���ڸ� �Է� �����մϴ�.");
			} // end catch

		}// end switch
	}// modifyProduct

	/**
	 * �̹��� ����
	 */
	public void chgImg() throws IOException {
		FileDialog fdOpen = new FileDialog(pdv, "�̹��� ���� ����", FileDialog.LOAD);
		fdOpen.setVisible(true); // ����ȭ

		String path = fdOpen.getDirectory(); // ���
		String file = fdOpen.getFile(); // ����
		if (file != null) {// ������ ������ ����
			String ext = "jpg, png, bmp, gif";
			String fileName = file.substring(file.lastIndexOf(".") + 1);
			if (!ext.contains(fileName.toLowerCase())) { // ���� Ȯ���ڰ� ���ٸ�
				JOptionPane.showMessageDialog(pdv, fileName + "��(��) ����ϽǼ� ���� Ȯ�����Դϴ�.");
				return;
			} // end if;
			JLabel jlImg = pdv.getJlImg();
			jlImg.setText("");
			jlImg.setIcon(new ImageIcon(path + file)); // �̹��� �ֱ�
			imgFlag = true; // �̹����� ���õǾ����� �����Ѵ�.
		} else {// ������ ������ ���ٸ�
			imgFlag = false; // �̹����� ���õ��� �ʾҴ�.
		} // end if

	}// chgImg

	/**
	 * ������ �̹����� �о ����� �����Ŵ
	 * 
	 * @throws IOException
	 */
	private void modifyImg() throws IOException {

		File readFile = new File(pdv.getJlImg().getIcon().toString());
		byte[] readData = new byte[512];
		int length = 0;

		FileOutputStream fos = null;
		FileOutputStream fos2 = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(readFile); // ������ ����
			if (readFile.exists()) {// ������ �̹����� �����ϸ�

				File writeFile = new File(StaticCla.FILE_PATH+"/gd_" + readFile.getName());// (rsXXXX)
				File writeFile2 = new File(StaticCla.FILE_PATH+"/gds_" + readFile.getName());// (rsXXXX)
				fos = new FileOutputStream(writeFile); // ������ �̹��� ������ ����
				fos2 = new FileOutputStream(writeFile2); // ������ �̹��� ������ ����

				while ((length = fis.read(readData)) != -1) {// ������ 0
					fos.write(readData, 0, length);// �о���� ��ŭ ��½�Ʈ���� ���
					fos2.write(readData, 0, length);// �о���� ��ŭ ��½�Ʈ���� ���
				} // end while
				fos.flush();

				ImageResize.resizeImage(writeFile.getAbsolutePath(), 270, 350);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pdv.getJbtnClose()) {// �ݱ��ư
			switch (JOptionPane.showConfirmDialog(pdv, "�󼼺��� â�� �����ðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				close();
			}// end switch
		} // end if
		if (e.getSource() == pdv.getJbtnUpload()) {// ������ư
			modifyGoods();
		} // end if

		if (e.getSource() == pdv.getJbtnImg()) {// �̹�����ư
			try {
				chgImg();// �̹��� ����
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(pdv, "�̹��� �۾� �� ������ �߻��߽��ϴ�. ��� �� �ٽ� ���� ���ּ���");
				e1.printStackTrace();
			} // end catch
		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == pdv.getJtfAddGoods()) {// �߰������� ������ �ʱ�ȭ
			pdv.getJtfAddGoods().setText("");
		} // end if

	}// mouseClicked

}// class
