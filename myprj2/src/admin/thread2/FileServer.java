package admin.thread2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileServer extends Thread {

	private List<FileHelper> list = new ArrayList<FileHelper>();

	@Override
	public void run() {

		ServerSocket server = null;

		try {
			server = new ServerSocket(5000);
			Socket client = null;
			FileHelper fh = null;

			while (true) {
				client = server.accept();
				fh = new FileHelper(client);
				list.add(fh);
				fh.start();
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
}// class
