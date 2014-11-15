package com.medical.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class FtpUpload {

	/*
	 * <property name="username">upload</property> <property
	 * name="userpwd">upload20091224</property> <property
	 * name="remoteip">10.10.10.2</property> <property
	 * name="remoteport">21</property> <property
	 * name="remotedir">medicalupload</property>
	 */

	private static String filepath = "com/medical/common/ftp-cfg.xml";

	private String hostName;

	private String userName;

	private String password;

	private String remoteDir;

	private String port;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public FtpUpload() {
		if (remoteDir == null || remoteDir.equalsIgnoreCase("")) {
			remoteDir = null;
		}
		hostName = this.getProperty("remoteip");
		userName = this.getProperty("username");
		password = this.getProperty("userpwd");
		remoteDir = this.getProperty("remotedir");
		port = this.getProperty("remoteport");
	}

	public boolean UploadFile(InputStream localfileins, String remotefilename) {

		FTPClient ftp = new FTPClient();
		int reply;
		try {
			ftp.connect(hostName, Integer.parseInt(port));
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
		} catch (IOException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					f.printStackTrace();
					return false;
				}
			}
			e.printStackTrace();
		}

		try {
			if (!ftp.login(userName, password)) {
				ftp.logout();
				return false;
			}

			ftp.setFileType(FTP.BINARY_FILE_TYPE);

			ftp.enterLocalPassiveMode();

			ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);

			InputStream input = localfileins;

			String[] dirs = remotefilename.split("/");

			ftp.changeWorkingDirectory("/");

			for (int i = 1; i < dirs.length - 1; i++) {
				if (!ftp.changeWorkingDirectory(dirs[i])) {
					ftp.makeDirectory(dirs[i]);
				}
				ftp.changeWorkingDirectory(dirs[i]);
			}

			ftp.storeFile(dirs[dirs.length - 1], input);

			input.close();

			ftp.logout();

		} catch (FTPConnectionClosedException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("unused")
	public boolean UploadFile(String localfilename, String remotefilename) {
		remotefilename = "/"+ remotefilename;
		FTPClient ftp = new FTPClient();
		int reply;
		try {
			ftp.connect(hostName, Integer.parseInt(port));
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
		} catch (IOException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					f.printStackTrace();
					return false;
				}
			}
			e.printStackTrace();
		}

		try {
			if (!ftp.login(userName, password)) {
				ftp.logout();
				return false;
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			InputStream input = new FileInputStream(localfilename);
			if (input == null) {
				System.out.println("本地文件不存在");
			}
			String[] dirs = remotefilename.split("/");

			for (String a : dirs) {
				System.out.println(a);
			}
			System.out.println(dirs.length);

			ftp.changeWorkingDirectory("/");

			for (int i = 1; i < dirs.length - 1; i++) {
				if (!ftp.changeWorkingDirectory(dirs[i])) {
					ftp.makeDirectory(dirs[i]);
				}
				ftp.changeWorkingDirectory(dirs[i]);
			}

			ftp.storeFile(remotefilename, input);
			input.close();
			ftp.logout();
		} catch (FTPConnectionClosedException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					return false;
				}
			}
		}
		return true;
	}

	private Document readCfgXml() {
		String path = this.getClass().getClassLoader().getResource("")
				.getPath()
				+ filepath;
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	private String getProperty(String propertyname) {
		Document doc = this.readCfgXml();
		Element root = doc.getRootElement();
		Node node = root.selectSingleNode("/ftp/property[@name='"
				+ propertyname + "']");
		return node.getText();
	}

	public static void main(String args[]) throws Exception {
		System.out.println("sssssssssss");
		FtpUpload ftpup = new FtpUpload();
		ftpup.UploadFile("c:/mywebapp-debug.log", "/html/aa/a/a/debuglog.log");
		System.out.println("sssssssssss");
	}

}
