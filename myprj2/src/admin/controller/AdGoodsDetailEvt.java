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
 * 관리자 제품상세보기 이벤트관리
 * 
 * @author hyebin
 *
 */
public class AdGoodsDetailEvt extends MouseAdapter implements ActionListener {
	private String originImg;// 원래 이미지
//	private String tempImage;

	private AdGoodsDetailView pdv;
	private AdGoodsManageEvt gle; // 리스트 갱신
	private boolean imgFlag; //

	public AdGoodsDetailEvt(AdGoodsDetailView pdv, String originImg, AdGoodsManageEvt gle) {
		this.pdv = pdv;
		this.gle = gle;
		this.originImg = originImg;
	}// AdGoodsDetailEvt

	/**
	 * 닫기버튼
	 */
	public void close() {
		pdv.dispose();
	}// close

	/**
	 * 선택한 상품 수정
	 */
	public void modifyGoods() {
		String name = pdv.getJtfGoodsName().getText().trim();
		switch (JOptionPane.showConfirmDialog(pdv, name + "의 상품을 수정하시겠습니까?")) {
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
					JOptionPane.showMessageDialog(pdv, "추가수량은 0이하 입력은 불가능합니다.");
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
							modifyImg(); // 이미지 수정
						} // end if
						gle.searchGoods(); // 다시 갱신
						JOptionPane.showMessageDialog(pdv, "상품수정이 완료되었습니다.");
						pdv.dispose();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(pdv, "이미지 업로드 중 문제가 발생하였습니다. ");
//						e.printStackTrace();
						return;
					} // end catch
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(pdv, "금액과 추가수량은 숫자만 입력 가능합니다.");
			} // end catch

		}// end switch
	}// modifyProduct

	/**
	 * 이미지 변경
	 */
	public void chgImg() throws IOException {
		FileDialog fdOpen = new FileDialog(pdv, "이미지 파일 열기", FileDialog.LOAD);
		fdOpen.setVisible(true); // 가시화

		String path = fdOpen.getDirectory(); // 경로
		String file = fdOpen.getFile(); // 파일
		if (file != null) {// 변경할 파일을 선택
			String ext = "jpg, png, bmp, gif";
			String fileName = file.substring(file.lastIndexOf(".") + 1);
			if (!ext.contains(fileName.toLowerCase())) { // 사진 확장자가 없다면
				JOptionPane.showMessageDialog(pdv, fileName + "는(은) 사용하실수 없는 확장자입니다.");
				return;
			} // end if;
			JLabel jlImg = pdv.getJlImg();
			jlImg.setText("");
			jlImg.setIcon(new ImageIcon(path + file)); // 이미지 넣기
			imgFlag = true; // 이미지가 선택되었음을 설정한다.
		} else {// 변경할 파일이 없다면
			imgFlag = false; // 이미지가 선택되지 않았다.
		} // end if

	}// chgImg

	/**
	 * 선택한 이미지를 읽어서 사이즈를 변경시킴
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
			fis = new FileInputStream(readFile); // 파일을 읽음
			if (readFile.exists()) {// 선택한 이미지가 존재하면

				File writeFile = new File(StaticCla.FILE_PATH+"/gd_" + readFile.getName());// (rsXXXX)
				File writeFile2 = new File(StaticCla.FILE_PATH+"/gds_" + readFile.getName());// (rsXXXX)
				fos = new FileOutputStream(writeFile); // 관리자 이미지 폴더에 복사
				fos2 = new FileOutputStream(writeFile2); // 관리자 이미지 폴더에 복사

				while ((length = fis.read(readData)) != -1) {// 시작이 0
					fos.write(readData, 0, length);// 읽어들인 만큼 출력스트림에 기록
					fos2.write(readData, 0, length);// 읽어들인 만큼 출력스트림에 기록
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
		if (e.getSource() == pdv.getJbtnClose()) {// 닫기버튼
			switch (JOptionPane.showConfirmDialog(pdv, "상세보기 창을 닫으시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				close();
			}// end switch
		} // end if
		if (e.getSource() == pdv.getJbtnUpload()) {// 수정버튼
			modifyGoods();
		} // end if

		if (e.getSource() == pdv.getJbtnImg()) {// 이미지버튼
			try {
				chgImg();// 이미지 변경
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(pdv, "이미지 작업 중 문제가 발생했습니다. 잠시 후 다시 실행 해주세요");
				e1.printStackTrace();
			} // end catch
		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == pdv.getJtfAddGoods()) {// 추가수량이 눌리면 초기화
			pdv.getJtfAddGoods().setText("");
		} // end if

	}// mouseClicked

}// class
