package admin.thread2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestSync {
	private static TestSync ts;
	private FileHelper fh;
	private FileInputStream fis;

	private TestSync() {
	}

	public static TestSync getInstance() {
		if (ts == null) {
			ts = new TestSync();
		}
		return ts;
	}

	public synchronized void syn(FileHelper fh) {
		this.fh = fh;

		DataInputStream dis = null;
		DataOutputStream dos = null;

		byte[] readData = new byte[300];
		int sendCnt = 0;
		int readSize = 0;

		try {
			dis = fh.getDis();
			dos = fh.getDos();

			for (int i = 0; i < fh.getListFiles().size(); i++) {
				sendCnt = 0;
				fis = new FileInputStream(new File(fh.getAdFile().getAbsoluteFile() + "/" + fh.getListFiles().get(i)));

				while ((readSize = fis.read(readData)) != -1) {
					sendCnt++;
				} // end while
				fis.close();
				fis = new FileInputStream(new File(fh.getAdFile().getAbsoluteFile() + "/" + fh.getListFiles().get(i)));

				dis.readUTF();

				dos.writeInt(sendCnt);// 파일 회전 수 보냄
				dos.flush();

				dos.writeUTF(fh.getListFiles().get(i));// 파일명 보냄
				dos.flush();

				while (sendCnt > 0) {
					readSize = fis.read(readData);
					dos.write(readData, 0, readSize);
					sendCnt--;
				} // end while
				dos.flush();
			} // end for
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// syn
}// class