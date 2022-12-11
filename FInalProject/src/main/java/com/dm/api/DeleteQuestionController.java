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
import com.dm.common.QuestionDAO;
import com.dm.common.ReportDAO;

/**
 * Servlet implementation class DeleteQuestionController
 */
@WebServlet("/DeleteQuestion.do")
public class DeleteQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		QuestionDAO qdao = new QuestionDAO();
		ReportDAO rado = new ReportDAO();
		
		try {
			rado.deleteArticle(id, "q"); //신고내역 삭제
			rado.deleteAnswerInQ(id); //질문의 답변 글 삭제	
			qdao.DeleteQuestion(id); //글삭제
			

			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
