package user.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import user.view.login.ClientLoginView;

public class PhotoUploading {

	public PhotoUploading() {

	}

	public void sendFiles() throws UnknownHostException, IOException {
		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		int fileCnt = 0;

		try {
			client = new Socket("localhost", 5000);

			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());

			File file = new File(ClientLoginView.USER_FILE_PATH);
			File[] temp = file.listFiles();
			StringBuilder csvFile = new StringBuilder();

			for (int i = 0; i < temp.length; i++) {
				if (temp[i].getName().startsWith("rs_")) {
					if (i != 0) {
						csvFile.append(",");
					} // end if
					csvFile.append(temp[i].getName());
				} // end if
			} // end for
			dos.writeUTF(csvFile.toString());
			dos.flush();

			fileCnt = dis.readInt();// 파일 갯수 받음
			int readCnt = 0;
			String revFileName = "";
			byte[] readData = new byte[300];
			int readSize = 0;

			for (int i = 0; i < fileCnt; i++) {

				dos.writeUTF("Y");
				readCnt = dis.readInt();// 파파일 회전 수 받음
				revFileName = dis.readUTF();// 파일명 받음
				fos = new FileOutputStream(ClientLoginView.USER_FILE_PATH + "/" + revFileName);

				while (readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0, readSize);
					readCnt--;
				} // end while
				fos.flush();
//				ImageResize.resizeImage("C:/dev/workspace/myprj2/src/user/img/" + revFileName, 100, 100);
			} // end for

		} finally {
			if (fis != null) {
				fis.close();
			}
			if (dis != null) {
				dis.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (client != null) {
				client.close();
			}
		} // end finally
	}// sendFiles
}
