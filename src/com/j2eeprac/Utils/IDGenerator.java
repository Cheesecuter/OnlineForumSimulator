package com.j2eeprac.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class IDGenerator {
	String emptyUidsPath = "idLists/emptyUidLists.txt";
	String emptyAidsPath = "idLists/emptyAidLists.txt";

//	String emptyUidsPath = "J2EE_PRAC/src/com/j2eeprac/Utils/emptyUidLists.txt";
//	String emptyAidsPath = "J2EE_PRAC/src/com/j2eeprac/Utils/emptyAidLists.txt";

	public static void main(String[] args) throws IOException {
		IDGenerator generator = new IDGenerator();

		// System.out.println(generator.getUID());
		// generator.addUID("10001");
		System.out.println(generator.getAID());
		// generator.addAID("10001");
	}

	public String getUID() throws IOException {
		@SuppressWarnings("resource")
		InputStream inputStream = new FileInputStream(emptyUidsPath);
		OutputStream outputStream;
		BufferedWriter bufferedWriter;
		int maxsize = inputStream.available();
		byte[] arr = new byte[maxsize];
		int len = inputStream.read(arr);
		String uidstr = new String(arr, 0, len);
		String uid = null;
		String[] uids = uidstr.split("\n");
		uid = uids[0];
		if (uids.length == 1) {
			uidstr = Integer.toString(Integer.parseInt(uid) + 1);
		} else {
			uidstr = uidstr.replace(uid + "\n", "");
		}
		outputStream = new FileOutputStream(new File(emptyUidsPath));
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
		bufferedWriter.write(uidstr);
		bufferedWriter.close();
		return uid;
	}

	public void addUID(String uid) throws IOException {
		@SuppressWarnings("resource")
		InputStream inputStream = new FileInputStream(emptyUidsPath);
		OutputStream outputStream;
		BufferedWriter bufferedWriter;
		int maxsize = inputStream.available();
		byte[] arr = new byte[maxsize];
		int len = inputStream.read(arr);
		String uidstr = new String(arr, 0, len);
		uidstr = uid + "\n" + uidstr;
		outputStream = new FileOutputStream(new File(emptyUidsPath));
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
		bufferedWriter.write(uidstr);
		bufferedWriter.close();
	}

	public String getAID() throws IOException {
		@SuppressWarnings("resource")
		InputStream inputStream = new FileInputStream(emptyAidsPath);
		OutputStream outputStream;
		BufferedWriter bufferedWriter;
		int maxsize = inputStream.available();
		byte[] arr = new byte[maxsize];
		int len = inputStream.read(arr);
		String aidstr = new String(arr, 0, len);
		String aid = null;
		String[] uids = aidstr.split("\n");
		aid = uids[0];
		if (uids.length == 1) {
			aidstr = Integer.toString(Integer.parseInt(aid) + 1);
		} else {
			aidstr = aidstr.replace(aid + "\n", "");
		}
		outputStream = new FileOutputStream(new File(emptyAidsPath));
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
		bufferedWriter.write(aidstr);
		bufferedWriter.close();
		return aid;
	}

	public void addAID(String aid) throws IOException {
		@SuppressWarnings("resource")
		InputStream inputStream = new FileInputStream(emptyUidsPath);
		OutputStream outputStream;
		BufferedWriter bufferedWriter;
		int maxsize = inputStream.available();
		byte[] arr = new byte[maxsize];
		int len = inputStream.read(arr);
		String aidstr = new String(arr, 0, len);
		aidstr = aid + "\n" + aidstr;
		outputStream = new FileOutputStream(new File(emptyUidsPath));
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
		bufferedWriter.write(aidstr);
		bufferedWriter.close();
	}
}
