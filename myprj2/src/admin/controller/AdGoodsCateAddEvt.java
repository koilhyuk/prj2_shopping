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
import admin.view.AdGoodsCateAddView;
import admin.view.AdGoodsCateManageView;
import admin.vo.InsertBrandVO;
import admin.vo.InsertGoodsTypeVO;
import kr.co.sist.util.img.ImageResize;

/**
 * 카테고리,브랜드 추가
 * @author hyebin
 *
 */
public class AdGoodsCateAddEvt extends MouseAdapter implements ActionListener {
	private AdGoodsCateAddView acv;
	private AdGoodsCateManageView gcmv;
	public AdGoodsCateAddEvt(AdGoodsCateAddView acv) {
		this.acv = acv;
	}// AddCategoryEvt

	/**
	 * 닫기
	 */
	public void close() {
		acv.dispose();
	}// close

	/**
	 * 카테고리
	 * 대분류에서 선택하고, 소분류를 입력하여 추가함
	 */
	public void insertDetailType() {
		AdminDAO aDAO = AdminDAO.getInstance();
		InsertGoodsTypeVO idVO = null;
		String d_type = acv.getJtfCategory().getText().trim();
		String c_type = acv.getCgCategory().getSelectedCheckbox().getLabel(); // 선택한 대분류 타입
		try {
			idVO = new InsertGoodsTypeVO(d_type, c_type);
			aDAO.insertDetailType(idVO);
			JOptionPane.showMessageDialog(acv, d_type + " (이)라는 카테고리가 추가되었습니다.");
			close();
			//갱신 
			acv.getGcme().typeList();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// insertDetailType

	/**
	 * 브랜드추가 
	 */
	public void insertBrand() {
		AdminDAO aDAO = AdminDAO.getInstance();
		InsertBrandVO ibVO = null;
		String b_name = acv.getJtfBrand().getText().trim();
		String b_img = new File(acv.getJlImg().getIcon().toString()).getName();
		try {
			if(b_img==null) {
				b_img=" ";
			}//end if
			ibVO = new InsertBrandVO(b_name, b_img);
			aDAO.insertBrand(ibVO);
			JOptionPane.showMessageDialog(acv, b_name + " (이)라는 브랜드가 추가되었습니다.");
			modifyImg(); //변경된 이미지가 이미지폴더에 저장 
			close();
			//갱신 
			acv.getGcme().setBrandList();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// insertBrand
	

	/**
	 * 브랜드 이미지 추가 
	 */
	public void brandImg() {
		FileDialog fdOpen = new FileDialog(acv, "브랜드 이미지 열기", FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String path = fdOpen.getDirectory();
		String file = ""+fdOpen.getFile();

		if (file != null) {// 파일이 존재하면
			String fileExt = "bmp, jpg, png, gif";
			String ext = file.substring(file.lastIndexOf(".") + 1);
			if (!fileExt.contains(ext.toLowerCase())) {// 확장자가 존재하지 않는다면
				JOptionPane.showMessageDialog(acv, ext + "는(은) 사용할수 없는 확장자 입니다.");
				return;
			} // end if
			JLabel jlImg = acv.getJlImg();
			jlImg.setText("");
			jlImg.setIcon(new ImageIcon(path +file));
		} // end if
	}// brandImg
	
	/**
	 * 변경된 이미지 폴더에 저장.
	 * @throws IOException
	 */
	public void modifyImg() throws IOException{
		File readFile =new File(acv.getJlImg().getIcon().toString());
		byte[] readData= new byte[512];
		int length=0;
		
		FileOutputStream fos=null; 
		FileInputStream fis=null;
		try {
			fis= new FileInputStream(readFile);
			if(readFile.exists()) {
				File writeFile= new File(StaticCla.FILE_PATH+"/br_"+readFile.getName()); //브랜드
				fos= new FileOutputStream(writeFile);
				while((length=fis.read(readData) )!=-1) {
					fos.write(readData,0,length);
				}//end while
				fos.flush();
				ImageResize.resizeImage(writeFile.getAbsolutePath(), 150, 150);//브랜드 이미지 
			}//end if
		}finally {
			if(fos !=null) {fos.close();}
			if(fis !=null) {fis.close();}
		}//end finally
		
	}//modifyImg

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == acv.getJbtnCAdd()) {// 카테고리추가
			insertDetailType();
		} // end if
		if (e.getSource() == acv.getJbtnCClose()) {// 카테고리닫기
			close();
		} // end if
		/////////////////////////////////////////////////////////////
		if (e.getSource() == acv.getJbtnBAdd()) {// 브랜드추가
			insertBrand();
		} // end if
		if (e.getSource() == acv.getJbtnBClose()) {// 브랜드닫기
			close();
		} // end if
		if (e.getSource() == acv.getJbtnBImg()) {// 브랜드 이미지 넣기
			brandImg();
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getSource()==acv.getJtfBrand()) { //브랜드 T.F
			acv.getJtfBrand().setText("");
		}//end if
		if(me.getSource()==acv.getJtfCategory()) { //카테고리T.F
			acv.getJtfCategory().setText("");
		}//end if
	}// mouseClicked

}// class
