package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import user.dao.ClientDAO;
import user.view.content.MyDataView;
import user.view.content.UserMyOrderDetailView;
import user.view.content.UserMyOrderView;
import user.vo.content.SelectMyOrderDetailDTO;
import user.vo.content.SelectMyOrderVO;

public class UserMyOrderEvt extends MouseAdapter implements ActionListener{

	private UserMyOrderView umo;
	private static String id;
	public UserMyOrderEvt(UserMyOrderView umo,String id) {
		this.umo=umo;
		this.id=id;
		searchOrderList();
	}//UserMyOrderEvt

	/**
	 * 고객의 주문내역 조회
	 */
	public void searchOrderList() {
		DefaultTableModel dtmOrder= umo.getDtmOrderList();
		dtmOrder.setRowCount(0);//초기화
		
		ClientDAO cDAO= ClientDAO.getInstance();
		Object[] rowData=null; //jtable에 넣을 데이터 
		List<SelectMyOrderVO> list=null;
		SelectMyOrderVO smVO=null;
		
		try {
			list= cDAO.selectAllOrderList(id);
			if(list.isEmpty()) {
				rowData= new Object[5];
				rowData[0]="주문내역이 없습니다.";
				dtmOrder.addRow(rowData);
			}//end if
			for(int i=0; i<list.size(); i++) {
				smVO=list.get(i);
				rowData= new Object[5];
				rowData[0]=smVO.getG_name();
				rowData[1]=smVO.getB_name();
				rowData[2]=smVO.getO_delivery();
				rowData[3]=smVO.getG_price();
				rowData[4]=smVO.getO_date();
				dtmOrder.addRow(rowData);
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//searchOrderList
	
	/**
	 * 상세 주문내역 
	 */
	public void selectDetailOrder(JTable jtOrder) {
		SelectMyOrderDetailDTO moDTO = new SelectMyOrderDetailDTO();
		String goodsName =(String)umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(),0);
		String brandName=(String)umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 1);
		String delivery=(String)umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 2);
		int price=(int)umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 3);
		String orderDate=(String)umo.getJtOrder().getValueAt(jtOrder.getSelectedRow(), 4);
		
		moDTO.setG_name(goodsName);
		moDTO.setB_name(brandName);
		moDTO.setO_delivery(delivery);
		moDTO.setO_buypay(price);
		moDTO.setO_date(orderDate);
		
		ClientDAO cDAO=ClientDAO.getInstance();
		try {
			cDAO.selectDetailMyOrder(moDTO);
			new UserMyOrderDetailView(moDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//selectDetailOrder
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==umo.getJbtBack()) {//마이페이지
//			new MyDataView(id);
		}//end if
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount()==2) {
			if(me.getSource()==umo.getJtOrder()) {
				JTable jtOrder=umo.getJtOrder();
				selectDetailOrder(jtOrder);
			}//end if
		}//end if
	}//mouseClicked
	
}//class
