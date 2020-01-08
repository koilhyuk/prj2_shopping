package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import user.view.content.MyDataView;
import user.view.content.MyPageView;

public class MyDataEvt implements ActionListener{
	private MyDataView mdv ;
	
	public MyDataEvt(MyDataView mdv) {
		this.mdv=mdv;
	}//myDataEvt

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==mdv.getJbtOrderList()) {//�ֹ�����
				
			}//end if
			
			if(ae.getSource()==mdv.getJbtMyData()) {//����������
				switch (JOptionPane.showConfirmDialog(mdv, "�������� �����Ͻðڽ��ϱ�")) {
				case JOptionPane.OK_OPTION:
					new MyPageView();
				}//end switch
			}//end if
			
			if(ae.getSource()==mdv.getJbtWithdrawal()) {//Ż��
				switch (JOptionPane.showConfirmDialog(mdv, "���� Ż���Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
				}
				//�ڵ��߰��Ұ�
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



























