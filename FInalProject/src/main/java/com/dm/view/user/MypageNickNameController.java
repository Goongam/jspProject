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

import com.dm.common.AnswerDAO;
import com.dm.common.AnswerDTO;
import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;
import com.dm.common.RegisterDAO;

@WebServlet("/getinfo.do")
public class MypageNickNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RegisterDAO rdao = new RegisterDAO();
		QuestionDAO qdao = new QuestionDAO();
		AnswerDAO adao = new AnswerDAO();
		ArrayList<String> fav = new ArrayList<String>();
		ArrayList<QuestionDTO> qInfo = new ArrayList<QuestionDTO>();
		ArrayList<AnswerDTO> aInfo = new ArrayList<AnswerDTO>();
		
		
		String nick = (String)session.getAttribute("idValue");
		try {
			qInfo = qdao.selectQuestionInfo(nick);
			aInfo = adao.selectAnswerInfo(nick);
			fav = qdao.favoriteCategory(nick);
			
			
			session.setAttribute("nickName",rdao.getNickname(nick));
			session.setAttribute("profileImg",rdao.getProfileImg(nick));
			session.setAttribute("introduce",rdao.getIntroduce(nick));
			session.setAttribute("vote", adao.getVoteCount(nick));
			session.setAttribute("questionCount",qdao.getQuestionCount(nick));
			session.setAttribute("answerCount",adao.getAnswerCount(nick));
			session.setAttribute("fav", fav);
			session.setAttribute("qInfo", qInfo);
			session.setAttribute("aInfo", aInfo);

			
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
