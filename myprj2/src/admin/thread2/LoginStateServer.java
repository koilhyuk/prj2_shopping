package admin.thread2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import admin.view.AdCusManageView;

public class LoginStateServer extends Thread {
	private List<LoginStateHelper> listLogin = new ArrayList<LoginStateHelper>();
	private AdCusManageView acmv;
	private boolean threadFlag;
	ServerSocket server;

	public LoginStateServer(AdCusManageView acmv) {
		this.acmv = acmv;
	}// LoginStateServer

	public void setStop(boolean threadFlag) {
		this.threadFlag = threadFlag;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(6000);
			Socket client = null;
			LoginStateHelper lsh = null;

			while (!threadFlag) {
				client = server.accept();
				lsh = new LoginStateHelper(client, acmv, this);
				listLogin.add(lsh);
				lsh.start();
			} // end while
		} catch (IOException ie) {
			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // end catch
		} // end catch
	}// end run

	public List<LoginStateHelper> getListLogin() {
		return listLogin;
	}// List

	public ServerSocket getServer() {
		return server;
	}

}// LoginStateServer
