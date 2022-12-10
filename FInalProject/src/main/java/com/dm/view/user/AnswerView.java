package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.AnswerDAO;


@WebServlet("/Answer.do")
public class AnswerView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("answerid");
		
		AnswerDAO adao = new AnswerDAO();
		try {
			int qid = adao.selectQidByAnswer(aid);
			
			response.sendRedirect("Question.do?qustionid="+qid+"#aid_"+aid);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
