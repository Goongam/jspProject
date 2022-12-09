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

@WebServlet("/getinfo.do")
public class MypageNickNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RegisterDAO rdao = new RegisterDAO();
		String nick = (String)session.getAttribute("idValue");
		System.out.println(session.getAttribute("idValue"));
		try {
			System.out.println("getinfo로 옴");
			session.setAttribute("nickName",rdao.getNickname(nick));
			session.setAttribute("profileImg",rdao.getProfileImg(nick));
			session.setAttribute("introduce",rdao.getIntroduce(nick));
			RequestDispatcher dispatcher = request.getRequestDispatcher("myPage.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
