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

import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;


@WebServlet("/todayq.do")
public class SelectTodayQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SelectTodayQuestionController() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		QuestionDTO rq = new QuestionDTO();
		QuestionDAO rd = new QuestionDAO();
		
		try {
			rq = rd.todayQuestion();
			session.setAttribute("todayQ_id", rq.getQuestion_id());
			session.setAttribute("todayQ_title", rq.getQuestion_title());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.do");
		dispatcher.forward(request, response);
	}

}
