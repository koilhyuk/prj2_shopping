package admin.run;

import admin.login.LoginView;
import admin.thread2.FileServer;

public class AdminRun {
	public static void main(String[] args) {
		FileServer fs = new FileServer();
		fs.start();// 파일 업로드
		new LoginView();
	}// main
}// class
