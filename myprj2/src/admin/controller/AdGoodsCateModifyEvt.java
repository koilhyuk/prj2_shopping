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
 * 브랜드 및 카테고리 수정 브랜드 수정시 JCOMBOBOX 초기화
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
	 * 브랜드DB를 jcombobox에 넣음
	 */
	public void setBrandName() {
		DefaultComboBoxModel<String> brand = gcmv.getDcbmBrand();
		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> list = null;
		String brandName = "";
		try {
			list = aDAO.selectBrand();
			brand.addElement("---브랜드선택---");
			for (int i = 0; i < list.size(); i++) {
				brandName = list.get(i);
				brand.addElement(brandName);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// brandList

	/**
	 * 대분류를 jcombobox에 넣음
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
	 * 소분류를 jcombobox에 넣음
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
	 * 선택한 이미지로 변경(브랜드 이미지 수정 )
	 */
	public void updateImg() {
		FileDialog fdOpen = new FileDialog(gcmv, "브랜드이미지열기");
		fdOpen.setVisible(true);
		String path = fdOpen.getDirectory();
		String file = fdOpen.getFile();
		if (file != null) {
			String fileExt = "jpg, bmp, png, gif";
			String ext = file.substring(file.lastIndexOf(".") + 1);
			if (!fileExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(gcmv, "는(은) 사용하실수 없는 확장자 입니다.");
				return;
			} // end if
		} // end if
		JLabel img = gcmv.getJlImg();
		img.setIcon(new ImageIcon(path + file));
	}// modfyImg

	/**
	 * 브랜드 삭제
	 */
	public void deleteBrand(String type) {
		type = (String) gcmv.getJtfBrand().getText().trim();
		SelectListVO slVO = new SelectListVO(type, 2);
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			if (aDAO.updateTypeDEL(slVO)) { // 제품테이블의 코드를 삭제컬럼으로 변경
				aDAO.deleteBrand(type);
				JOptionPane.showMessageDialog(gcmv, type + "의 브랜드를 삭제하였습니다.");
				close();
				gcmv.getGcme().setBrandList(); // 갱신
			} else {
				aDAO.deleteBrand(type);
				JOptionPane.showMessageDialog(gcmv, type + "의 브랜드를 삭제하였습니다.");
			} // end else

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(gcmv, "현재 선택하신 브랜드로 등록된 상품이 존재하여 삭제 불가합니다.");
			e.printStackTrace();
		} // end catch

	}// deleteBrand

	/**
	 * 브랜드이름이 선택이 되면 이미지랑, 브랜드 이름이 조회되게 변경 //////
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
	 * 브랜드 수정
	 */
	public void modifyBrand() {
		switch (JOptionPane.showConfirmDialog(gcmv, "브랜드를 수정하시겠습니까?")) {
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
							fis = new FileInputStream(readFile); // 파일을 읽음
							if (readFile.exists()) {// 이미지가 존재하면
								File writeFile = new File(StaticCla.FILE_PATH + "/br_" + readFile.getName());
								String newimg = writeFile.getName().replace("br_", "");

								if (newimg.equals(originimg)) {
									tempImage = originimg;
								} else {
									tempImage = newimg;
									fos = new FileOutputStream(writeFile); // 관리자 이미지 폴더에 복사
									while ((length = fis.read(readData)) != -1) {// 시작이 0
										fos.write(readData, 0, length);// 읽어들인 만큼 출력스트림에 기록
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
						JOptionPane.showMessageDialog(gcmv, "브랜드수정이 완료되었습니다. ");
						close();
						gcmv.getGcme().setBrandList(); // 갱신

					} catch (IOException e) {
						JOptionPane.showMessageDialog(gcmv, "이미지 업로드 중 문제가 발생하였습니다.");
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
			// 제품테이블에 있는 소분류코드의 이름을 DEL로 변경 !
			// 삭제할 소분류코드를 소분류테이블에서 삭제!
			if (aDAO.updateTypeDEL(slVO)) { // 제품테이블의 코드를 삭제컬럼으로 변경
				aDAO.deleteType(type);
				JOptionPane.showMessageDialog(gcmv, type + "의 카테고리를 삭제하였습니다.");
				close();
				gcmv.getGcme().setBrandList(); // 갱신
			} else {
				aDAO.deleteType(type);
				JOptionPane.showMessageDialog(gcmv, type + "의 카테고리를 삭제하였습니다.");
			} // end else
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(gcmv, "현재 선택하신 카테고리로 등록된 상품이 존재하여 삭제 불가합니다.");
//			e.printStackTrace();
		} // end catch

	}// deleteType

	/**
	 * 카테고리 수정
	 */
	public void modifyType() {
		switch (JOptionPane.showConfirmDialog(gcmv, "카테고리를 수정하시겠습니까?")) {
		case JOptionPane.OK_OPTION:

			String newType = gcmv.getJtfCategory().getText().trim();
			String detailType = (String) gcmv.getJcbDetailType().getSelectedItem();

			AdminDAO aDAO = AdminDAO.getInstance();
			UpdateTypeVO utVO = null;
			try {
				utVO = new UpdateTypeVO(newType, detailType);

				if (aDAO.updateType(utVO)) {
					JOptionPane.showMessageDialog(gcmv, "카테고리수정이 완료되었습니다. ");
					close();
					gcmv.getGcme().typeList(); // 갱신
				} else {
					JOptionPane.showMessageDialog(gcmv, "카테고리수정을 실패하셨습니다. ");
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch
		}// switch
	}// modifyType

	@Override
	public void actionPerformed(ActionEvent e) {
		String index = (String) gcmv.getJcbBrand().getSelectedItem();
		if (e.getSource() == gcmv.getJcbBrand()) {// 브랜드 JComBOBox V
//			gcmv.getDcbmBrand().removeAllElements(); 
			setBrandName();// Jcombobox에 브랜드 List를 설정
			JTextField brand = gcmv.getJtfBrand(); // 선택한 인덱스의 브랜드를 T.F에 설정
			brand.setText(index.trim());
			// 선택한 인덱스의 브랜드의 이미지와 코드를 조회
			selectBrand(index);
		} // end
		if (e.getSource() == gcmv.getJbtnBImg()) {// 브랜드 이미지 수정 V
			updateImg();
		} // end if
		if (e.getSource() == gcmv.getJbtnBDelete()) {// 브랜드 삭제 V
			deleteBrand(index);
		} // end if
		if (e.getSource() == gcmv.getJbtnBModufy()) {// 브랜드수정 V
			modifyBrand();
		} // end if
		if (e.getSource() == gcmv.getJbtnBClose()) {// 브랜드닫기 V
			close();
		} // end if
////////////////////////////////////////////////////////////////////////////////////////////
		if (e.getSource() == gcmv.getJcbType()) {//
			JComboBox<String> typeList = gcmv.getJcbType();
			int indexType = gcmv.getJcbType().getSelectedIndex();
			int count = typeList.getItemCount(); // 대분류의 갯수
			for (int i = 0; i < count; i++) {
				if (indexType == i) { // 선택한 인덱스 값이 동일하다면
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

		if (e.getSource() == gcmv.getJbtnCClose()) {// 닫기
			close();
		} // end if
		if (e.getSource() == gcmv.getJbtnCAdd()) {// 수정
			modifyType();
		} // end if
		if (e.getSource() == gcmv.getJbtnCDelete()) {// 삭제 v
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
