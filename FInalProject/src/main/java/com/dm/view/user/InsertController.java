package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.RegisterDAO;
import com.dm.common.RegisterDTO;


@WebServlet("/insert1.do")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String i = request.getParameter("id");
		String n = request.getParameter("nickname");
		String p = request.getParameter("password");
		RegisterDTO rm = new RegisterDTO();
		rm.setId(i);
		rm.setNickname(n);
		rm.setPassword(p);
		RegisterDAO rd = new RegisterDAO();
		try {
			rd.insertMember(rm);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

}
