package com.dm.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.AnswerDAO;
import com.dm.common.ReportDAO;

/**
 * Servlet implementation class DeleteAnswerController
 */
@WebServlet("/DeleteAnswer.do")
public class DeleteAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		AnswerDAO adao = new AnswerDAO();
		ReportDAO rado = new ReportDAO();
		
		
		try {
			int result = adao.deleteAnswer(id);
			rado.deleteArticle(id, "a");
			
			if(result == 1) {
				response.setHeader("Access-Control-Allow-Origin", "*");
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
