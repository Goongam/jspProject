package com.dm.view.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.RegisterDAO;

@WebServlet("/CheckId.do")
public class CheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		RegisterDAO rdao = new RegisterDAO();
		
		try {
			int checkId = rdao.CheckId(id);
			
			//response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(checkId);
			out.flush();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
