package user.run;

import java.io.IOException;
import java.net.UnknownHostException;

import user.view.login.ClientLoginView;

public class UserRun {

	public static void main(String[] args) {
		PhotoUploading pu = new PhotoUploading();
		try {
			pu.sendFiles();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new ClientLoginView();
	}// main
}// class
