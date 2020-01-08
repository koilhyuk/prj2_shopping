package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import admin.dao.AdminDAO;
import admin.view.AdGoodsCateAddView;
import admin.view.AdGoodsCateManageView;
import admin.view.AdGoodsCateModifyView;
import admin.vo.SelectBrandListVO;

public class AdGoodsCateManageEvt extends MouseAdapter implements ActionListener {
	private AdGoodsCateManageView gcmv;

	
	public AdGoodsCateManageEvt(AdGoodsCateManageView gcmv) {
		this.gcmv = gcmv;
		setBrandList();
		typeList();
	}// AdGoodsCateManageEvt
	/**
	 * jtable에 브랜드리스트 설정 
	 */
	public void setBrandList() {
		DefaultTableModel brand= gcmv.getDtmBrand();
		brand.setRowCount(0);
		AdminDAO aDAO = AdminDAO.getInstance();
		List<SelectBrandListVO> list = null;
		Object[] rowData = null; // jtable에 넣을 데이터
		SelectBrandListVO sbVO= null;
		try {
			list = aDAO.selectBrandList();
			for (int i = 0; i < list.size(); i++) {
				sbVO=list.get(i);
				rowData = new Object[8];
				rowData[0]=sbVO.getB_code();
				rowData[1]=sbVO.getB_name();
				brand.addRow(rowData);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// setBrandList

	/**
	 * 대분류 리스트 설정
	 */
	public void typeList() {
		DefaultListModel<String> type = gcmv.getDlmType();
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

	}// typeList

	/**
	 * 소분류 리스트 설정
	 * @param type
	 */
	public void detailTypeList(String type) {
		DefaultListModel<String> detailType = gcmv.getDlmDetailType();
		detailType.removeAllElements();
		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> list = null;
		type = (String) gcmv.getLstType().getSelectedValue();
		String typeList = "";
		try {
			list = aDAO.selectDetailType(type);
			for (int i = 0; i < list.size(); i++) {
				typeList = list.get(i);
				detailType.addElement("- "+typeList);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// detailTypeList

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==gcmv.getJbtnAdd()) {//추가
			new AdGoodsCateAddView( this);
			
		}//end if
		if(ae.getSource()==gcmv.getJbtnModify()) {//수정
			new AdGoodsCateModifyView(this);
		}//end if
		if(ae.getSource()==gcmv.getJbtnClose()) {//닫기
			gcmv.dispose();
		}//end if
		
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==gcmv.getLstType()) {
			String type=gcmv.getLstType().getSelectedValue();
			gcmv.getDlmDetailType().removeAllElements();
			detailTypeList(type);
			
		}//end if
		
	}//mouseClicked

	
}// class
