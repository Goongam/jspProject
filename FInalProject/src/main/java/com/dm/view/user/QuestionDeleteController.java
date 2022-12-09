
package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;

@WebServlet("/delete_q.do")
public class QuestionDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuestionDAO pdao = new QuestionDAO();
		String del_id = request.getParameter("delQ");
		System.out.println(del_id);
		try {
			pdao.DeleteQuestion(del_id);
			
			response.sendRedirect("list.do");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, QuestionDTO delQ)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
