package admin.thread2;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import admin.run.StaticCla;
import admin.view.AdCusManageView;

public class MemLoginThread extends Thread {

	private boolean threadFlag;
	private AdCusManageView acmv;

	public MemLoginThread(AdCusManageView acmv) {
		this.acmv = acmv;
	}// MemLoginThread

	public void setStop(boolean threadFlag) {
		this.threadFlag = threadFlag;
	}// setStop

	@Override
	public void run() {
		JTextField jtf = acmv.getCus();
		DefaultListModel<String> dlm = acmv.getDlmCusList();
		JList<String> jl = acmv.getCusList();
		JTable jt = acmv.getJtCus();
		List<String> list = StaticCla.mv.getIdList();

		jtf.getText();
		jtf.setText("");
		while (!threadFlag) {
			dlm.removeAllElements();
			for (int i = 0; i < list.size(); i++) {
				dlm.addElement(list.get(i));
			}
			jtf.setText("현재 접속한 회원 : " + list.size() + "명");

			jl.setVisible(false);
			jt.setVisible(false);
			jl.setVisible(true);
			jt.setVisible(true);

			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(StaticCla.mv, "DBMS에 문제발생");
				e.printStackTrace();
			} // end catch
		} // emd while

	}// run

}// class
