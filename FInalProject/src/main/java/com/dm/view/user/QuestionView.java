package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.AnswerDAO;
import com.dm.common.AnswerDTO;
import com.dm.common.CategoryDAO;
import com.dm.common.CategoryDTO;
import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;
import com.dm.common.VoteDAO;

@WebServlet("/Question.do")
public class QuestionView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		try {
			
			
			String question_id = request.getParameter("qustionid"); 
			QuestionDAO questionDAO = new QuestionDAO();
			AnswerDAO answerDAO = new AnswerDAO();
			VoteDAO vdao = new VoteDAO(); 
			
			questionDAO.UpdateQuestionViews(question_id); //조회수+1
			
			QuestionDTO selectedDTO = questionDAO.selectQuestion(question_id); //질문 내용 select

			
			
			
			
			
			if(question_id != null && selectedDTO != null) { 
				ArrayList<AnswerDTO> answerList = answerDAO.selectAnswers(selectedDTO.getQuestion_id()); //답변들 select
				
				HashMap<Integer, Integer> answerVoteTable = new HashMap<Integer, Integer>();
				for(AnswerDTO answer : answerList) {
					answerVoteTable.put(answer.getId(), vdao.selectVote(answer.getId()+"")); //추천 수 select
				}
				
				session.setAttribute("questionData", selectedDTO);
				session.setAttribute("answerData", answerList);
				session.setAttribute("answerVoteTable", answerVoteTable);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Question.jsp");
				dispatcher.forward(request, response);
			}else { //글이 없거나 잘못된 파라미터인 경우
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
			
		
		} catch (SQLException e) {
			//에러
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
