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
 * ī�װ�,�귣�� �߰�
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
	 * �ݱ�
	 */
	public void close() {
		acv.dispose();
	}// close

	/**
	 * ī�װ�
	 * ��з����� �����ϰ�, �Һз��� �Է��Ͽ� �߰���
	 */
	public void insertDetailType() {
		AdminDAO aDAO = AdminDAO.getInstance();
		InsertGoodsTypeVO idVO = null;
		String d_type = acv.getJtfCategory().getText().trim();
		String c_type = acv.getCgCategory().getSelectedCheckbox().getLabel(); // ������ ��з� Ÿ��
		try {
			idVO = new InsertGoodsTypeVO(d_type, c_type);
			aDAO.insertDetailType(idVO);
			JOptionPane.showMessageDialog(acv, d_type + " (��)��� ī�װ��� �߰��Ǿ����ϴ�.");
			close();
			//���� 
			acv.getGcme().typeList();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// insertDetailType

	/**
	 * �귣���߰� 
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
			JOptionPane.showMessageDialog(acv, b_name + " (��)��� �귣�尡 �߰��Ǿ����ϴ�.");
			modifyImg(); //����� �̹����� �̹��������� ���� 
			close();
			//���� 
			acv.getGcme().setBrandList();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// insertBrand
	

	/**
	 * �귣�� �̹��� �߰� 
	 */
	public void brandImg() {
		FileDialog fdOpen = new FileDialog(acv, "�귣�� �̹��� ����", FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String path = fdOpen.getDirectory();
		String file = ""+fdOpen.getFile();

		if (file != null) {// ������ �����ϸ�
			String fileExt = "bmp, jpg, png, gif";
			String ext = file.substring(file.lastIndexOf(".") + 1);
			if (!fileExt.contains(ext.toLowerCase())) {// Ȯ���ڰ� �������� �ʴ´ٸ�
				JOptionPane.showMessageDialog(acv, ext + "��(��) ����Ҽ� ���� Ȯ���� �Դϴ�.");
				return;
			} // end if
			JLabel jlImg = acv.getJlImg();
			jlImg.setText("");
			jlImg.setIcon(new ImageIcon(path +file));
		} // end if
	}// brandImg
	
	/**
	 * ����� �̹��� ������ ����.
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
				File writeFile= new File(StaticCla.FILE_PATH+"/br_"+readFile.getName()); //�귣��
				fos= new FileOutputStream(writeFile);
				while((length=fis.read(readData) )!=-1) {
					fos.write(readData,0,length);
				}//end while
				fos.flush();
				ImageResize.resizeImage(writeFile.getAbsolutePath(), 150, 150);//�귣�� �̹��� 
			}//end if
		}finally {
			if(fos !=null) {fos.close();}
			if(fis !=null) {fis.close();}
		}//end finally
		
	}//modifyImg

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == acv.getJbtnCAdd()) {// ī�װ��߰�
			insertDetailType();
		} // end if
		if (e.getSource() == acv.getJbtnCClose()) {// ī�װ��ݱ�
			close();
		} // end if
		/////////////////////////////////////////////////////////////
		if (e.getSource() == acv.getJbtnBAdd()) {// �귣���߰�
			insertBrand();
		} // end if
		if (e.getSource() == acv.getJbtnBClose()) {// �귣��ݱ�
			close();
		} // end if
		if (e.getSource() == acv.getJbtnBImg()) {// �귣�� �̹��� �ֱ�
			brandImg();
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getSource()==acv.getJtfBrand()) { //�귣�� T.F
			acv.getJtfBrand().setText("");
		}//end if
		if(me.getSource()==acv.getJtfCategory()) { //ī�װ�T.F
			acv.getJtfCategory().setText("");
		}//end if
	}// mouseClicked

}// class
