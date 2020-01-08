package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import user.newtest.UserMyOrderView;
import user.view.content.MyDataView;
import user.view.content.MyPageView;
import user.view.content.UserCardUploadView;

public class MyDataEvt implements ActionListener{
	private MyDataView mdv ;
	
	public MyDataEvt(MyDataView mdv) {
		this.mdv=mdv;
	}//myDataEvt

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==mdv.getJbtOrderList()) {//주문내역
				new UserMyOrderView("hyebin");
			}//end if
			
			if(ae.getSource()==mdv.getJbtMyData()) {//내정보변경
					new MyPageView();
			}//end if
			
			if(ae.getSource()==mdv.getJbtWithdrawal()) {//카드등록
//				new UserCardUploadView();
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



























