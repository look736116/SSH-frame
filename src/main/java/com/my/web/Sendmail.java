package com.my.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Sendmail
 */
@WebServlet("/Sendmail")
public class Sendmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sendmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// �ռ��˵ĵ����ʼ� ID
	      String to = "xxx@qq.com";
	 
	      // �����˵ĵ����ʼ� ID
	      String from = "xxx@qq.com";
	 
	      // �������Ǵӱ����������͵����ʼ�
	      String host = "localhost";
	      
	   // ��ȡϵͳ������
	      Properties properties = System.getProperties();
	      
	   // �����ʼ������� smtp.qq.com
	     // properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("smtp.qq.com", host);
	      
	   // ��ȡĬ�ϵ� Session ����
	      Session session = Session.getDefaultInstance(properties);
	      
	      // ������Ӧ��������
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      try{
	          // ����һ��Ĭ�ϵ� MimeMessage ����
	          MimeMessage message = new MimeMessage(session);
	          // ���� From: header field of the header.
	          message.setFrom(new InternetAddress(from));
	          // ���� To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO,
	                                   new InternetAddress(to));
	          // ���� Subject: header field
	          message.setSubject("This is the Subject Line!");
	          // ��������ʵ����Ϣ
	          message.setText("This is actual message");
	          // ������Ϣ
	          Transport.send(message);
	          String title = "���͵����ʼ�";
	          String res = "�ɹ�������Ϣ...";
	          String docType =
	          "<!doctype html public \"-//w3c//dtd html 4.0 " +          "transitional//en\">\n";
	          out.println(docType +
	          "<html>\n" +
	          "<head><title>" + title + "</title></head>\n" +
	          "<body bgcolor=\"#f0f0f0\">\n" +
	          "<h1 align=\"center\">" + title + "</h1>\n" +
	          "<p align=\"center\">" + res + "</p>\n" +
	          "</body></html>");
	       }catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
	 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
