package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.RegisterDAO;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    public LoginController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterDAO rdao = new RegisterDAO();
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String ch = request.getParameter("infosave");
		
		System.out.println("pw:"+id);
		
		try {
			int loginrs = rdao.login(id, pw);
			HttpSession session = request.getSession();
			session.setAttribute("loginrs", loginrs);
			if(loginrs == 1) {
				session.setAttribute("loginCkeck","ok");
				session.setAttribute("idValue",id);
				session.setAttribute("pwValue",pw);
				if(ch != null) 
					session.setAttribute("Checked","Checked");
				else 
					session.removeAttribute("Checked");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.do");
				dispatcher.forward(request, response);
				}
			else if(loginrs == 0){
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			else if(loginrs == -1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp?loginfail=1");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
