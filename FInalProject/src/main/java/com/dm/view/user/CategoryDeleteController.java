package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.CategoryDAO;
import com.dm.common.QuestionDTO;

@WebServlet("/delete_c.do")
public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO cdao = new CategoryDAO();
		String del_id = request.getParameter("delC");
		try {
			cdao.DeleteCategory(del_id);
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
