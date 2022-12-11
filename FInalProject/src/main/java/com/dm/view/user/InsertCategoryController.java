package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.CategoryDAO;
import com.dm.common.CategoryDTO;

@WebServlet("/insertc.do")
public class InsertCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertCategoryController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoryName = request.getParameter("category_n");

		CategoryDTO rc = new CategoryDTO();
		rc.setCategory_name(categoryName);

		CategoryDAO rd = new CategoryDAO();

		try {
			rd.insertCategory(rc);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.do");
		dispatcher.forward(request, response);
	}

}
