package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.QuestionDTO;
import com.dm.common.RegisterDAO;

@WebServlet("/delete_m2.do")
public class MemberDeleteController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegisterDAO pdao = new RegisterDAO();
		HttpSession session = request.getSession();
		String del_id = request.getParameter("delM");

		try {
			session.removeAttribute("loginCkeck");
			session.removeAttribute("idValue");
			session.removeAttribute("pwValue");
			session.removeAttribute("Checked");

			pdao.DeleteMember(del_id);

			response.sendRedirect("index.do");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, QuestionDTO delQ)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
