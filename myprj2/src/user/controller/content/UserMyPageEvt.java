package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import user.dao.ClientDAO;
import user.view.content.UserMyPageView;
import user.view.content.UserCusDataView;
import user.view.content.UserGoodsLikeView;
import user.view.content.UserCardUploadView;
import user.view.content.UserMyOrderView;
import user.vo.content.SelectCusDataVO;

public class UserMyPageEvt implements ActionListener{
	static UserMyPageView mdv ;
	private static String id;
	public UserMyPageEvt(UserMyPageView mdv, String id) {
		this.mdv=mdv;
		this.id=id;
	}//myDataEvt
	
	public void searchCusData() {
		SelectCusDataVO cdVO = new SelectCusDataVO();
		cdVO.setM_id(id);
		ClientDAO cDAO= ClientDAO.getInstance();
		try {
			cDAO.selectCusData(cdVO);
			new UserCusDataView(id,  cdVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//searchCusData
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==mdv.getJbtOrderList()) {//주문내역
				new UserMyOrderView(id);
			}//end if
			 SelectCusDataVO cdVO= new SelectCusDataVO();
			if(ae.getSource()==mdv.getJbtMyData()) {//내정보변경
					searchCusData();
			}//end if
			
			if(ae.getSource()==mdv.getJbtWithdrawal()) {//카드등록
				new UserCardUploadView(mdv);
			}//end if
			if(ae.getSource()==mdv.getJbtJJim()) {//카드등록
				new UserGoodsLikeView();
			}//end if
			
			
			if(ae.getSource()==mdv.getJbtOk()) {//확인
				switch (JOptionPane.showConfirmDialog(mdv, "마이페이지 창을 종료하시겠습니까?")) {
				case JOptionPane.OK_OPTION:
				mdv.dispose();
				}//end if
			}
			if(ae.getSource()==mdv.getJbtLogOut()) {//로그아웃
				switch (JOptionPane.showConfirmDialog(mdv, "로그아웃 하시겠습니까?")) {
				case JOptionPane.OK_OPTION:
				mdv.dispose();
				}//end switch
			}//end if
		}//actionPerformed
}//class



