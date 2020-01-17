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
 * 관리자 상품등록창 이벤트관리
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
	 * 닫기버튼
	 */
	public void close() {
		guv.dispose();
	}// close

	/**
	 * 이미지 등록버튼-> 이미지 추가가능
	 * 
	 * @throws IOException
	 */
	public void addImg() throws IOException {
		// 이미지 선택을 위한 파일다이얼로그 열기
		FileDialog fdOpen = new FileDialog(guv, "이미지 파일 열기", FileDialog.LOAD);
		fdOpen.setVisible(true);// 가시화

		String path = fdOpen.getDirectory(); // 경로
		String file = fdOpen.getFile();// 파일

		if (file != null) { // 파일이 존재한다면
			// 확장자 체크
			String fileExt = "jpg, png, gif, bmp"; // 사진 확장자 저장
			String ext = file.substring(file.lastIndexOf(".") + 1); // 파일 확장자
			if (!fileExt.contains(ext.toLowerCase())) {// jpg, png, gif, bmp를 포함하는 파일이 아니면
				JOptionPane.showMessageDialog(guv, file + "는(은) 사용하실수 없는 확장자 입니다. ");
				return; // 흘러내러가지 못하게 막음
			} // end if

			JLabel img = guv.getJlImg();
			img.setText("");
			img.setIcon(new ImageIcon(path + file));

		} // end else
	}// addImg

	/**
	 * 사이즈 변경된 이미지 폴더에 저장
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
	 * 제품 등록 method
	 */
	public void GoodsAdd() {
		String img = new File(guv.getJlImg().getIcon().toString()).getName(); // 이미지
		String brand = (String) guv.getJcbBrand().getSelectedItem(); // 브랜드
		String detailType = (String) guv.getJcbDetailType().getSelectedItem();
		String name = guv.getJtfGoodsName().getText().trim();// 상품명
		String strong = guv.getJtaStrongPoint().getText().trim();// 특장점
		int price = 0;// 가격
		int inventory = 0;// 입고량
		String tempPrice = guv.getJtfPrice().getText().trim();
		String tempInven = guv.getJtfInventory().getText().trim();
		try {
			price = Integer.parseInt(tempPrice);
			inventory = Integer.parseInt(tempInven);

			InsertGoodsAddVO dpVO = new InsertGoodsAddVO(img, brand, detailType, name, price, inventory, strong);
			if (img.isEmpty()||"".equals(img)||guv.getJlImg().getIcon().toString().isEmpty()||!new File(guv.getJlImg().getIcon().toString()).exists()) {
				JOptionPane.showMessageDialog(guv, "상품 이미지를 등록해주세요.");
				return;
			} // end if
			if (name.isEmpty()) {// 상품명T.F 공백
				JOptionPane.showMessageDialog(guv, "상품명을 입력해주세요.");
				guv.getJtfGoodsName().requestFocus();
				return;
			} // end if
			if (tempPrice.isEmpty() || price <= 0) {
				JOptionPane.showMessageDialog(guv, "상품금액은 정수형태로 입력해주세요");
				guv.getJtfPrice().requestFocus();
				return;
			} // end if
			guv.getJtfPrice().requestFocus();
			if (tempInven.isEmpty() || inventory <= 0) {// 재고량T.F 공백
				JOptionPane.showMessageDialog(guv, "입고량을 입력해주세요.");
				guv.getJtfInventory().requestFocus();
				return;
			} // end if
			if (strong.isEmpty()) {// 특장점T.F 공백
				JOptionPane.showMessageDialog(guv, "특장점을 입력해주세요.");
				guv.getJtaStrongPoint().requestFocus();
				return;
			} // end if
			if(guv.getJcbBrand().getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(guv, "브랜드를 선택해주세요.");
				return;
			}//end if 
			modifyImg();
			AdminDAO svDAO = AdminDAO.getInstance();
			// DBMS 추가
			svDAO.insertGoods(dpVO);
			JOptionPane.showMessageDialog(guv, "상품이 추가되었습니다.");
			gle.searchGoods();
			guv.dispose();
			// 제품추가후 리스트가 갱신되게 작성

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(guv, "가격과 입고량은 숫자만 입력해주세요.");
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
	 * 브랜드를 jcombobox에 추가
	 */
	public void brandList() {
		DefaultComboBoxModel<String> brand = guv.getDcbmBrand();
		brand.removeAllElements();
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
	 * 대분류 리스트 jcombobox에 추가
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
	 * 소분류 리스트 jcombobox에 추가
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
		if (e.getSource() == guv.getJbtnClose()) {// 닫기버튼
			close();
		} // end if

		if (e.getSource() == guv.getJbtnGoodsAdd()) {// 등록버튼
			// 제품을 등록해야할 method 생성
			switch (JOptionPane.showConfirmDialog(guv, "상품을 등록하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				GoodsAdd();
			}// end switch
		} // end if

		if (e.getSource() == guv.getJbtnImg()) {// 이미지버튼
			try {
				addImg();// 이미지 추가
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(guv, "이미지 작업 중 문제가 발생했습니다. 잠시 후 다시 실행 해주세요");
				e1.printStackTrace();
			} catch (NullPointerException ne) {
				JOptionPane.showMessageDialog(guv, "이미지를 등록해주세요");
			} // end catch
		} // end if

		if (e.getSource() == guv.getJcbType()) {// 대분류가 정해지면 소분류가 바꿔짐
			// 대분류가 선택되면 소분류의 리스트가 초기화 되어야한다.
			JComboBox<String> typeList = guv.getJcbType();
			int index = guv.getJcbType().getSelectedIndex();
			int count = typeList.getItemCount(); // 대분류의 갯수
			for (int i = 0; i < count; i++) {
				if (index == i) { // 선택한 인덱스 값이 동일하다면
					guv.getDcbmDetailType().removeAllElements();
					detailTypeList();
				} // end if
			} // end for

		} // end if
	}// actionPerformed

}// class
