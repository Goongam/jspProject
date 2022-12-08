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

@WebServlet("/InsertQuestion.do")
public class InsertQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setQuestion_title(request.getParameter("titledata"));
		questionDTO.setQuestion_contnet(request.getParameter("editordata"));
		questionDTO.setMemeber_id(request.getParameter("memberId"));
		questionDTO.setAnonymous(false);
		questionDTO.setcategory(request.getParameter("select_category"));
		if(request.getParameter("anonymous") != null) questionDTO.setAnonymous(true);
		else questionDTO.setAnonymous(false);
		
		int qustion_id = 0;
		QuestionDAO questionDAO = new QuestionDAO();
		try {
			qustion_id = questionDAO.insertQuestion(questionDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(qustion_id == -1) response.sendRedirect(request.getContextPath() + "/error.jsp"); //insert 실패
		else response.sendRedirect("Question.do?qustionid="+qustion_id); //질문id값으로 질문보기화면으로 이동
			
	}

}
