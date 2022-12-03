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

@WebServlet("/Question.do")
public class QuestionView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			String question_id = request.getParameter("qustionid"); 
			QuestionDAO questionDAO = new QuestionDAO();
			QuestionDTO selectedDTO = questionDAO.selectQuestion(question_id);
			
			AnswerDAO answerDAO = new AnswerDAO();
			ArrayList<AnswerDTO> answerList = answerDAO.selectAnswers(selectedDTO.getQuestion_id());
			
			if(question_id != null && selectedDTO != null) { 
				HttpSession session = request.getSession();
				session.setAttribute("questionData", selectedDTO);
				session.setAttribute("answerData", answerList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("Question.jsp");
				dispatcher.forward(request, response);
			}else { //글이 없거나 잘못된 파라미터인 경우
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
