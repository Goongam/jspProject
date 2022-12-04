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


@WebServlet("/insert.do")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("memberid");
		String p = request.getParameter("password");
		RegisterDTO rt =new RegisterDTO();
		rt.setMemberid(m);
		rt.setPassword(p);
		RegisterDAO rd = new RegisterDAO();
		try {
			rd.insertMember(rt);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("insertSuccess.jsp");
		dispatcher.forward(request, response);
	}

}
