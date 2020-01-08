package admin.thread2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import admin.run.StaticCla;

public class FileHelper extends Thread {

	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private FileInputStream fis;
	private List<String> listFiles;
	private File adFile;

	public FileHelper(Socket client) throws IOException {

		this.client = client;

		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());

		String temp = "";
		File[] serverFile = null;

		temp = dis.readUTF();
		adFile = new File(StaticCla.FILE_PATH);
		serverFile = adFile.listFiles();
		listFiles = new ArrayList<String>();

		for (int i = 0; i < serverFile.length; i++) {
			if (!temp.contains(serverFile[i].getName()) && serverFile[i].getName().startsWith("rs_")) {
				listFiles.add(serverFile[i].getName());
			} // end if
		} // end for
		dos.writeInt(listFiles.size());
		dos.flush();
	}// FileHelper

	@Override
	public void run() {
		TestSync ts= null;
		ts=TestSync.getInstance();
		ts.syn(this);

//		byte[] readData = new byte[300];
//		int sendCnt = 0;
//		int readSize = 0;
//
//		try {
//			for (int i = 0; i < listFiles.size(); i++) {
//				sendCnt = 0;
//				fis = new FileInputStream(new File(adFile.getAbsoluteFile() + "/" + listFiles.get(i)));
//
//				while ((readSize = fis.read(readData)) != -1) {
//					sendCnt++;
//				} // end while
//				fis.close();
//				fis = new FileInputStream(new File(adFile.getAbsoluteFile() + "/" + listFiles.get(i)));
//
//				dis.readUTF();
//
//				dos.writeInt(sendCnt);// 파일 회전 수 보냄
//				dos.flush();
//
//				dos.writeUTF(listFiles.get(i));// 파일명 보냄
//				dos.flush();
//
//				while (sendCnt > 0) {
//					readSize = fis.read(readData);
//					dos.write(readData, 0, readSize);
//					sendCnt--;
//				} // end while
//				dos.flush();
//
//			} // end for
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}// run

	public Socket getClient() {
		return client;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public FileInputStream getFis() {
		return fis;
	}

	public List<String> getListFiles() {
		return listFiles;
	}

	public File getAdFile() {
		return adFile;
	}

}// class
