package com.dm.imgcontroller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.json.simple.*;

/**
 * Servlet implementation class UploadSummernoteImageFile
 */

@WebServlet("/UploadSummernoteImageFile")
public class UploadSummernoteImageFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadSummernoteImageFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileRoot = this.getClass().getResource("").getPath();
		fileRoot = fileRoot.substring(1, fileRoot.indexOf("WEB-INF"))+"uploadImgs/";


		System.out.println(fileRoot);
		MultipartRequest multi = new MultipartRequest(request, fileRoot, 5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());


		String originalFileName = multi.getFilesystemName("file");
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		String savedFileName = UUID.randomUUID() + extension;
		
		File oldFile = new File(fileRoot + originalFileName);
		File newFile = new File(fileRoot + savedFileName);
		
		oldFile.renameTo(newFile);
		
		
		JSONObject json = new JSONObject();
		json.put("url", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/uploadImgs/"+savedFileName);
		json.put("responseCode", "succcess");

//
//
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		//System.out.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/uploadImgs/"+savedFileName);
	}

}
