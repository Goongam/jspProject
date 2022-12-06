package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.CategoryDAO;
import com.dm.common.CategoryDTO;

@WebServlet("/NewQuestion.do")
public class NewQuestionView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			String loginCkeck = (String)session.getAttribute("loginCkeck");
			if(loginCkeck == null) {
				response.sendRedirect("login.jsp");
				return;
			}
			
			
			CategoryDAO cateDAO = new CategoryDAO();
			ArrayList<CategoryDTO> catelist = cateDAO.selectQuestion();
			session.setAttribute("catelist", catelist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("newQuestion.jsp");
			dispatcher.forward(request, response);
			
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
