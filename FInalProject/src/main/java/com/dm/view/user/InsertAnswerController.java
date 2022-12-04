package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.common.AnswerDAO;
import com.dm.common.AnswerDTO;
import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;

/**
 * Servlet implementation class InsertAnswerController
 */
@WebServlet("/InsertAnswer.do")
public class InsertAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//id, title, content, member_id, anonymous, vote, question_id, edit_time
		AnswerDTO answerDTO = new AnswerDTO();
		answerDTO.setTitle(request.getParameter("titledata"));
		answerDTO.setContent(request.getParameter("editordata"));
		answerDTO.setMember_id(request.getParameter("memberId"));
		answerDTO.setAnonymous(false);
		answerDTO.setQuestion_id(Integer.parseInt(request.getParameter("QuestionId")));
		
		
		try {
			AnswerDAO answerDAO = new AnswerDAO();
			answerDAO.insertAnswer(answerDTO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//
//		int qustion_id = 0;
//		QuestionDAO questionDAO = new QuestionDAO();
//		try {
//			qustion_id = questionDAO.insertQuestion(questionDTO);
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//		//질문id값으로 질문보기화면으로 이동
		response.sendRedirect("Question.do?qustionid="+request.getParameter("QuestionId"));
	}

}
