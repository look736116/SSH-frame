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
@WebServlet(name = "UploadFile", urlPatterns = { "/UploadFile" }, initParams = {
		@WebInitParam(name = "filepath", value = "D:/Tools/sandbox/data/") })
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private boolean isMultipart;
	private String filePath;
	private File file;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFile() {
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
			// �����ϴ����ļ���
		    Iterator i = fileItems.iterator();
		    while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if ( !fi.isFormField () )   
		         {
		            // ��ȡ�ϴ��ļ��Ĳ���
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            String contentType = fi.getContentType();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // д���ļ�
		            if( fileName.lastIndexOf("\\") >= 0 ){
		               file = new File( filePath + 
		               fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		               file = new File( filePath + 
		               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		         }
		      }
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(String.valueOf(isMultipart));
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
