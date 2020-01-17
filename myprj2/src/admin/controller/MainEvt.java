package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import admin.login.LoginView;
import admin.run.StaticCla;
import admin.thread2.LoginStateServer;
import admin.thread2.MemLoginThread;
import admin.view.AdPwCheckView;
import admin.view.MainView;

public class MainEvt extends MouseAdapter implements ActionListener {

//	private admin.thread.RecentThread rt;
	public final static int CALDAY = 0;
	public final static int CALMON = 1;
	public final static int CALYEAR = 2;
	public final static int CALTOTAL = 3;
	protected static final int ABORT = 128;

	private LoginStateServer lss;
	private MemLoginThread mlt;

	public MainEvt(LoginStateServer lss, MemLoginThread mlt) {

		this.lss = lss;
		this.mlt = mlt;

		StaticCla.mv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				StaticCla.mv.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				mlt.setStop(true);
				lss.setStop(true);
				try {
					lss.getServer().close();
				} catch (IOException ie) {
					ie.printStackTrace();
				} finally {
					System.exit(ABORT);// JVM이 종료 될 때 생성 된 모든 인스턴스가 종료
				} // end finally
			}// windowClosed
		});
	}// MainViewEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		MainView mv = StaticCla.mv;
		if (ae.getSource() == mv.getJbtnSett()) {
			new AdPwCheckView();

		} // end if
		if (ae.getSource() == mv.getJbtnLogout()) {
			new LoginView();
			mlt.setStop(true);
			lss.setStop(true);
			try {
				lss.getServer().close();
			} catch (IOException ie) {
				ie.printStackTrace();
			} finally {
				System.exit(ABORT);// JVM이 종료 될 때 생성 된 모든 인스턴스가 종료
			} // end finally
			mv.dispose();
		} // end if
	}// actionPerformed
}// class
