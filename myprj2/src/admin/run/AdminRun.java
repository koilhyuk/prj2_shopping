package admin.run;

import admin.login.LoginView;
import admin.thread2.FileServer;

public class AdminRun {
	public static void main(String[] args) {
		FileServer fs = new FileServer();
		fs.start();//
		new LoginView();
	}// main
}// class
