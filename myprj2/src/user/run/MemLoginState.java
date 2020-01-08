package user.run;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MemLoginState extends Thread {

	private boolean threadFlag;
	private String id;
	Socket client;
	DataOutputStream dos;

	public MemLoginState(String id) {
		this.id = id;
	}

	public void setStop(boolean threadFlag) {
		this.threadFlag = threadFlag;
	}

	@Override
	public void run() {

		try {

			client = new Socket("localhost", 6000);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(id);
			while (!threadFlag) {
				dos = new DataOutputStream(client.getOutputStream());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// sendId

	public Socket getClient() {
		return client;
	}

	public DataOutputStream getDos() {
		return dos;
	}

}
