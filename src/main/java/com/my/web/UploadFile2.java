package com.my.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet(name = "UploadFile2", urlPatterns = { "/UploadFile2" }, initParams = {
		@WebInitParam(name = "filepath", value = "D:/Tools/sandbox/data/") })
public class UploadFile2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private boolean isMultipart;
	private String filePath;
	private File file;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFile2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		filePath = config.getInitParameter("filepath").toString();
		System.out.println(filePath);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// �����ж�����ͨ�������Ǵ��ļ��ϴ��ı�
		isMultipart = ServletFileUpload.isMultipartContent(request);

		response.setContentType("text/html; charset=UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("D:\\Tools\\sandbox\\temp"));

	    // ����һ���µ��ļ��ϴ��������
		ServletFileUpload upload = new ServletFileUpload(factory);
		 upload.setHeaderEncoding("UTF-8");
		//�����ϴ����ļ���С�����ֵ
		upload.setSizeMax(maxFileSize);
		
		try {
			 // �������󣬻�ȡ�ļ���
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				 if(item.isFormField()){
	                    String name = item.getFieldName();
	                    //�����ͨ����������ݵ�������������
	                    String value = item.getString("UTF-8");
	                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
	                    System.out.println(name + "=" + value);
	                }else{//���fileitem�з�װ�����ϴ��ļ�
	                    //�õ��ϴ����ļ����ƣ�
	                	String filename = item.getName();
	                    System.out.println(filename);
	                    if(filename==null || filename.trim().equals("")){
	                        continue;
	                    }
	                    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
	                    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
	                    filename = filename.substring(filename.lastIndexOf("\\")+1);
	                    //�õ��ϴ��ļ�����չ��
	                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
	                    filename = filename.substring(0,filename.lastIndexOf("."));
	                    //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
	                    System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);
	                    //�����ļ�
	                    file = new File( filePath + filename +"." + fileExtName);
	                    item.write(file);
	                }
			}
		   
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/uploadFile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
