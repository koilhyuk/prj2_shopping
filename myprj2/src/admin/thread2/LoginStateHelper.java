package admin.thread2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import admin.run.StaticCla;
import admin.view.AdCusManageView;

public class LoginStateHelper extends Thread {

	private boolean threadFlag;
	private Socket client;
	private DataInputStream readStream;
	private String id;
	private AdCusManageView acmv;
	private LoginStateServer lss;

	public LoginStateHelper(Socket client, AdCusManageView acmv, LoginStateServer lss) {
		this.client = client;
		this.acmv = acmv;
		this.lss = lss;
	}// LoginStateHelperl

	public void setStop(boolean threadFlag) {
		this.threadFlag = threadFlag;
	}

	@Override
	public void run() {
		JList<String> jl = acmv.getCusList();
		JTable jt = acmv.getJtCus();
		try {

			readStream = new DataInputStream(client.getInputStream());
			id = readStream.readUTF();
			StaticCla.mv.getIdList().add(id);

			jt.setVisible(false);
			jl.setVisible(false);
			jt.setVisible(true);
			jl.setVisible(true);

			while (!threadFlag) {
				readStream.readUTF();
			} // end while
		} catch (IOException e) {
			try {
				JScrollPane jsp = acmv.getJspPro();
				jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());

				StaticCla.mv.getIdList().remove(id);
				jt.setVisible(false);
				jl.setVisible(false);
				jt.setVisible(true);
				jl.setVisible(true);
				if (readStream != null) {
					readStream.close();
				} // end if
				if (client != null) {
					client.close();
				} // end if
				lss.getListLogin().remove(this);
			} catch (IOException ie) {
				e.printStackTrace();
			} // end catch
			e.printStackTrace();
		} // end catch
	}// end run
}// classs
