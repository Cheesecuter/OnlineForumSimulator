/*
 * Õ¼Î»·û
 * */
package com.j2eeprac.Servlet.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RepeatedlyRequestWrapper extends HttpServletRequestWrapper {
	private byte[] buffer;

	public RepeatedlyRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		InputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buff[] = new byte[1024];
		int read;
		while ((read = is.read(buff)) > 0) {
			baos.write(buff, 0, read);
		}
		this.buffer = baos.toByteArray();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new IDGenerator(this.buffer);
	}
}
