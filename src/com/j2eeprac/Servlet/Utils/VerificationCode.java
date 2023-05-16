/*
 * Õ¼Î»·û
 * */
package com.j2eeprac.Servlet.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/VerificationCode")
public class VerificationCode extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		try {
			response.setHeader("programa", "no-cache");
			response.setDateHeader("Expires", 0);
			BufferedImage image = new BufferedImage(50, 18, BufferedImage.TYPE_INT_RGB);
			Graphics graphic = image.getGraphics();
			graphic.setColor(Color.LIGHT_GRAY);
			graphic.drawRect(0, 0, 50, 18);
			String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String code = "";
			for (int i = 0; i < 4; i++) {
				int k = (int) (Math.random() * 36);
				char c = str.charAt(k);
				code += c;
			}
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			graphic.setColor(Color.BLACK);
			@SuppressWarnings("unused")
			Font font = new Font("DIALOG", Font.ITALIC, 15);
			ImageIO.write(image, "JEPG", out);
			out.flush();
			out.close();
		} finally {
			out.close();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
