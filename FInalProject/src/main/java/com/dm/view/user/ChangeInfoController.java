package com.dm.view.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.RegisterDAO;

@WebServlet("/change.do")
public class ChangeInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RegisterDAO rdao = new RegisterDAO();
		String id = (String)session.getAttribute("idValue");
		String cnick = request.getParameter("changeNickname");
		String cpw = request.getParameter("changePw");
		String cimg = request.getParameter("changeProfileImg");
		String cintro = request.getParameter("changeIntroduce");
		

		rdao.infoChange(cnick,cpw,cimg,cintro,id);

		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("getinfo.do");
		dispatcher.forward(request, response);
	}

}
