package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import user.view.content.MyDataView;
import user.view.content.MyPageView;
import user.view.content.UserCardUploadView;
import user.view.content.UserMyOrderView;

public class MyDataEvt implements ActionListener{
	private MyDataView mdv ;
	private static String id;
	public MyDataEvt(MyDataView mdv, String id) {
		this.mdv=mdv;
		this.id=id;
	}//myDataEvt

	
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==mdv.getJbtOrderList()) {//�ֹ�����
				new UserMyOrderView(id);
			}//end if
			
			if(ae.getSource()==mdv.getJbtMyData()) {//����������
					new MyPageView();
			}//end if
			
			if(ae.getSource()==mdv.getJbtWithdrawal()) {//ī����
//				new UserCardUploadView();
			}//end if
			
			if(ae.getSource()==mdv.getJbtOk()) {//Ȯ��
				switch (JOptionPane.showConfirmDialog(mdv, "���������� â�� �����Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
				mdv.dispose();
				}//end if
			}
			if(ae.getSource()==mdv.getJbtLogOut()) {//�α׾ƿ�
				switch (JOptionPane.showConfirmDialog(mdv, "�α׾ƿ� �Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
				mdv.dispose();
				}//end switch
			}//end if
		}//actionPerformed
}//class



























